/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import java.time.LocalDateTime;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class CRUDOperations {
    
    
    
    // fetches collection
    public MongoCollection<Document> getCollection(String collectionName, MongoDatabase database ){
        
        return database.getCollection(collectionName);
    }
    
    // retrievs document by key whose value is String type
    public Document getFirstRecordByKey(String key, String value, MongoCollection<Document> doc){
      
        return doc.find(new Document(key, value)).first();
        
    }
    
    // retrievs document by id
    public Document getFirstRecordByKey(String key, ObjectId value, MongoCollection<Document> doc){
      
        return doc.find(new Document(key, value)).first();
        
    }
    
    // retrievs all documents
    public FindIterable<Document> getRecordsByKey(String key, String value, MongoCollection<Document> doc){
      
        return doc.find(new Document(key, value));
        
    }
    
    public int updateObjectOfKey(ObjectId id, String key ,Document obj, MongoCollection<Document> doc ){
    
        Document query = new Document().append("_id",  id);
        Bson updates = Updates.combine(
                Updates.set(key, obj));

        UpdateOptions options = new UpdateOptions().upsert(true);
        UpdateResult result = null;
        try {
             result= doc.updateOne(query, updates, options);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
        
        if(result != null ){
            return (int) result.getModifiedCount();
        }
        
        return 0;
        
    
    }
    
    
    public Document getRecordByTwoKeys(String key1, String value1, String key2, String value2, MongoCollection<Document> doc){
    
    
        return doc.find(Filters.and(Filters.eq(key1, value1),Filters.eq(key2, value2) )).first();
        
    }
    
    public Document getRecordByTwoKeys(String key1, ObjectId value1, String key2, LocalDateTime value2, MongoCollection<Document> doc){
    
    
        return doc.find(Filters.and(Filters.eq(key1, value1),Filters.eq(key2, value2) )).first();
        
    }
    
    public int updateArrayById(ObjectId id, String key, String obj, MongoCollection collection ){
    
        BasicDBObject match = new BasicDBObject();
        match.put( "_id", id );
        
        BasicDBObject updateArray = new BasicDBObject();
        updateArray.put( "$push", new BasicDBObject( key, obj ) );
        
        UpdateResult result= collection.updateOne(match, updateArray );
    
        return (int) result.getModifiedCount();
    }
    
     public int updateArrayById(ObjectId id, String key, ObjectId obj, MongoCollection collection ){
    
        BasicDBObject match = new BasicDBObject();
        match.put( "_id", id );
        
        BasicDBObject updateArray = new BasicDBObject();
        updateArray.put( "$push", new BasicDBObject( key, obj ) );
        
        UpdateResult result= collection.updateOne(match, updateArray );
    
        return (int) result.getModifiedCount();
    }
    
    public void insertDocument(Document doc, MongoCollection<Document> collection){
        
       collection.insertOne(doc);
                
    }
    
    public ObjectId getIdByKey(String key,String value, MongoCollection<Document> collection){
    
        return (ObjectId) getFirstRecordByKey(key,value,collection).get("_id");
    }
    
    
    public void deleteDocumentById(String key,Object value, MongoCollection<Document> collection){
    
        collection.deleteOne(Filters.eq(key, value));
    }
    
    public int updateDocumentById(Document query, Bson updates, UpdateOptions options, MongoCollection<Document> collection){
    
        try {
                UpdateResult result = collection.updateOne(query, updates, options);
                return (int) result.getModifiedCount();
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        return 0;
    
    }
    

}
