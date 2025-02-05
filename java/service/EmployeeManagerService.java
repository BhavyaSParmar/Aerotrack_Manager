/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import database.CRUDOperations;
import database.DataBaseConnection;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class EmployeeManagerService {
    
    
    private CRUDOperations crud = new CRUDOperations();
    
     // fetches database
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    private MongoCollection getCollection(){
    
        return crud.getCollection("employees", this.database);
    }
    
    
    public String getName(ObjectId id){
    
        Document emp = crud.getFirstRecordByKey("_id", id, getCollection());
        
        return (String) emp.get("name");
       
    }
    
    public DefaultTableModel getRecords(String name){
        
        System.out.println(name);
    
        FindIterable<Document> fi = crud.getRecordsByKey("name", name, getCollection());
        
        MongoCursor<Document> cursor = fi.iterator();
        
        String[] columnNames = {"Name", "designation", "Contact", "Email", "Salary", "Joining Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       //all cells false
                       return false;
                    };
            };
        
        
        try {
            while(cursor.hasNext()) {    
                
                
                Document doc = cursor.next();
                System.out.println(doc);
                
                model.addRow(new Object[] {
                    doc.get("name"), doc.get("Designation"),
                    doc.get("number"), doc.get("email"),
                    "$"+doc.get("salary"), doc.get("dateOfJoining")
                });
                 
            }
        } finally {
            cursor.close();
        }
        
        return model;
        
    }
    
}
