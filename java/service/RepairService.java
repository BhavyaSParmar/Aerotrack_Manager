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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import model.PaintJob;
import model.Repair;
import model.RepairJob;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class RepairService {
    
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    CRUDOperations crud = new CRUDOperations();

    public int updatePaintStatus(String status, ObjectId id) {
        
        MongoCollection<Document> collection = crud.getCollection("repair", database);
        
        Document query = new Document().append("_id",  id);
        
        Bson updates = Updates.combine(
                    Updates.set("status", status));
        
        UpdateOptions options = new UpdateOptions().upsert(true);
        
        
        return crud.updateDocumentById(query, updates, options, collection);
     
    }
    
    public int placeNewOrder(String planeNumber,String dueDate, String description, String repair ,ObjectId userId, ArrayList<Document> records) throws ParseException{
        
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date newDate = sdf.parse(dueDate);
        
        Date currentTime = new Date(System.currentTimeMillis());
        
        if( currentTime.equals(newDate)){
        
            return 2;
        
        }
        
        MongoCollection<Document> collection = crud.getCollection("airplanes", database);
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        
       
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
        
        RepairJob rj = new RepairJob(myDateObj, planeId,"In Queue", newDate, repair, userId, description);
        
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        
        
        for(int i = 0; i< records.size(); i++){
        
            Document record = records.get(i);
            
            if(record.get("date").equals(formattedDate) && record.get("planeId").equals(planeId)){
            
                return 1;
            
            }
        
        }
        
       
        Document doc = new Document()
                .append("date", rj.getDate())
                .append("planeId", rj.getPlaneId())
                .append("status", rj.getStatus())
                .append("dueDate", rj.getDueDate())
                .append("repairPart", rj.getRepairPart())
                .append("userId", rj.getUserId())
                .append("description", rj.getDescription());
        
        crud.insertDocument(doc, crud.getCollection("repair", database));
        return 0;
    }
    
     public boolean insertRepairRequest(Repair repair) {

        try {
            Document doc = new Document();
            doc.append("repairPart", repair.getRepairPart());
            doc.append("status", repair.getStatus());
            doc.append("dueDate", repair.getDueDate());
            doc.append("date", repair.getDate());
            doc.append("description", repair.getDescription());
            doc.append("planeId",repair.getPlaneId());
            doc.append("userId",repair.getUserId());
            this.crud.insertDocument(doc, this.crud.getCollection("repair", this.database));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
}