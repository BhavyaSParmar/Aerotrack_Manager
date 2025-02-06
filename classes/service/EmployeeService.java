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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author aayush
 */
public class EmployeeService {
    
    CRUDOperations crud = new CRUDOperations();
     // fetches database
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    public MongoCollection getCollection(){
    
    
        return crud.getCollection("employees", this.database);
    }
    
    public DefaultTableModel fillTable(MongoCollection col){
    
    
        String[] columnNames = {"ID", "Name", "designation", "Contact", "Email", "Salary", "Joining Date", "Manager"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       //all cells false
                       return false;
                    };
            };
     
        MongoCursor<Document> cursor = col.find().iterator();
        try {
            while(cursor.hasNext()) {      
                Document doc = cursor.next();
                String ifManager = doc.get("Designation").toString().equals("Manager") ? "Self" : doc.get("manager").toString() ;
                
                model.addRow(new Object[] { doc.get("_id"),
                    doc.get("name"), doc.get("Designation"),
                    doc.get("number"), doc.get("email"),
                    "$"+doc.get("salary"), doc.get("dateOfJoining"),
                     ifManager
                });
            }
        } finally {
            cursor.close();
        }
        
        return model;
    
    } 
    
    public Document fetchRecord(String email, MongoCollection empDoc){
    
        return crud.getFirstRecordByKey("email", email, empDoc);
    }
    
    
    public DefaultTableModel addToTable(Document searchedEmp){
    
    
        String[] columnNames = {"ID", "Name", "designation", "Contact", "Email", "Salary", "Joining Date", "Manager"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       //all cells false
                       return false;
                    };
            };

        String ifManager = searchedEmp.get("Designation").toString().equals("Manager") ? "Self" : searchedEmp.get("manager").toString() ;

        model.addRow(new Object[] { searchedEmp.get("_id"),
            searchedEmp.get("name"), searchedEmp.get("Designation"),
            searchedEmp.get("number"), searchedEmp.get("email"),
            "$"+searchedEmp.get("salary"), searchedEmp.get("dateOfJoining"),
            ifManager
        });
        
        
        return model;
    }
    
    public void deleteEmployee(Object value){
    
    
        crud.deleteDocumentById("_id", value, getCollection());
    
    }
    
    public ArrayList<String> listOfManagers(){
        
        ArrayList<String> arr = new ArrayList<String>();
        
        FindIterable<Document> managersDoc = crud.getRecordsByKey(
                            "Designation", 
                            "Manager", 
                            crud.getCollection("employees", this.database)
                    );
        
        MongoCursor<Document> cursor = managersDoc.iterator(); 
        try {
            while(cursor.hasNext()) {
                arr.add((String) cursor.next().get("name"));
            }
        } finally {
            cursor.close();
        }
        
        return arr;
    
    }
    
    
    public void updateManagerOfEmployee(String row, ArrayList<String> arr){
        
        Random rn = new Random();
                    
        FindIterable<Document> empWithManagers = crud.getRecordsByKey(
                "manager", 
                row, 
                getCollection()
        );

        MongoCursor<Document> empCursor = empWithManagers.iterator();

        try {
            while(empCursor.hasNext()) {
                int index = (int)Math.floor(Math.random()*((arr.size()-1) - 0 + 1)+0);


                Document query = new Document().append("_id",  empCursor.next().get("_id"));

                Bson updates = Updates.combine(
                Updates.set("manager", arr.get(index)));

                UpdateOptions options = new UpdateOptions().upsert(true);

            crud.updateDocumentById(query, updates, options, getCollection());
            }
        } finally {
            empCursor.close();
        }
    
    
    }
    
}
