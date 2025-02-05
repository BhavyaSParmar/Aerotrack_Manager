/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import database.CRUDOperations;
import database.DataBaseConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.EngineOrder;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class EngineOrderService {
    
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    CRUDOperations crud = new CRUDOperations();
    
    public int updateOrderStatus(String status, ObjectId id){
    
        
        MongoCollection<Document> collection = crud.getCollection("engine", database);
        
        Document query = new Document().append("_id",  id);
        
        Bson updates = Updates.combine(
                    Updates.set("status", status));
        
        UpdateOptions options = new UpdateOptions().upsert(true);
        
        
        return crud.updateDocumentById(query, updates, options, collection);
     
    }
    
    public Boolean placeNewOrder(String planeNumber, ObjectId userId, ArrayList<Document> records){
        
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
        
        EngineOrder eo = new EngineOrder(userId, myDateObj, "Order Placed", planeId);
        
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        
        
        for(int i = 0; i< records.size(); i++){
        
            Document record = records.get(i);
            
            if(record.get("date").equals(formattedDate) && record.get("planeId").equals(planeId)){
            
                return false;
            
            }
        
        }
        
       
        Document doc = new Document()
                .append("userId", eo.getUserId())
                .append("date", eo.getDate())
                .append("status", eo.getStatus())
                .append("planeId", eo.getPlaneId());
        
        crud.insertDocument(doc, crud.getCollection("engine", database));
        return true;
    }
    
    
    
    
    
    public ArrayList<String> getPlaneNumbers(){
        
        ArrayList<String> planeNumbers = new ArrayList<String>();
        
        MongoCollection<Document> collection = crud.getCollection("airplanes", database);
        
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {                              
                Document doc = cursor.next();
                planeNumbers.add((String) doc.get("flight"));
                
            }
        } finally {
            cursor.close();
        }
        
        
        
        return planeNumbers;
    }
    
    public boolean createEnginerOrderRequest(EngineOrder engineOrder) {
        try {
            Document document = new Document()
                    .append("userId", engineOrder.getUserId())
                    .append("date", engineOrder.getDate())
                    .append("status", engineOrder.getStatus())
                    .append("planeId", engineOrder.getPlaneId());
            
            this.crud.insertDocument(document, this.crud.getCollection("engine", database));
            return true;

        } catch (Exception e) {

            return false;
        }
    }
    
    
}
