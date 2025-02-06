/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.CRUDOperations;
import database.DataBaseConnection;
import org.bson.Document;

/**
 *
 * @author aayush
 */
public class Login {
    
    CRUDOperations crud = new CRUDOperations();
    
    public MongoDatabase getDatabase(){
    
        MongoDatabase database = DataBaseConnection.connectToDatabase().database;
        
        return database;
    }
    
    public Document getUser(String value, String col, String role){
                
        return crud.getRecordByTwoKeys("username", value, "role", role, getDatabase().getCollection(col));
        
    }
    
    public Boolean comparePassword(String pass1, String pass2){
        
        return pass1.equals(pass2);
    
    }
    
}
