/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import database.CRUDOperations;
import database.DataBaseConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import model.EngineOrder;
import model.PaintJob;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class PaintService {
    
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    CRUDOperations crud = new CRUDOperations();

    public int updatePaintStatus(String status, ObjectId id) {
        
        MongoCollection<Document> collection = crud.getCollection("paint", database);
        
        Document query = new Document().append("_id",  id);
        
        Bson updates = Updates.combine(
                    Updates.set("status", status));
        
        UpdateOptions options = new UpdateOptions().upsert(true);
        
        
        return crud.updateDocumentById(query, updates, options, collection);
     
    }
    
    public int placeNewOrder(String planeNumber,String dueDate, String color, ArrayList<Document> records) throws ParseException{
        
       
        MongoCollection<Document> collection = crud.getCollection("airplanes", database);
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date d = sdf.parse(dueDate);
        
        Date currentTime = new Date(System.currentTimeMillis());
        
        if( currentTime.equals(d)){
        
            return 2;
        
        }

        ObjectId planeId = null;
        
        try {
            while(cursor.hasNext()) {                              
                Document doc = cursor.next();
                if(doc.get("flight").toString().equals(planeNumber)){
                    planeId = (ObjectId) doc.get("_id");
                }
                
            }
        } finally {
            cursor.close();
        }
        
        LocalDateTime myDateObj = LocalDateTime.now();
        
        PaintJob eo = new PaintJob(myDateObj, planeId, color, "In Queue", d );
        
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        
        
        for(int i = 0; i< records.size(); i++){
        
            Document record = records.get(i);
            
            if(record.get("date").equals(formattedDate) && record.get("planeId").equals(planeId)){
            
                return 1;
            
            }
        
        }
        
       
        Document doc = new Document()
                .append("date", eo.getDate())
                .append("planeId", eo.getPlaneId())
                .append("color", eo.getColor())
                .append("status", eo.getStatus())
                .append("dueDate", eo.getDueDate());
        
        crud.insertDocument(doc, crud.getCollection("paint", database));
        return 0;
    }
    
}
