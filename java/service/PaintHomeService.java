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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class PaintHomeService {
    
    private CRUDOperations crud = new CRUDOperations();
    
     // fetches database
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;
    
    private ArrayList<Document> paintDocuments = new ArrayList<>();
    
    public ArrayList<Document> getArrayData(){
        
        return this.paintDocuments;
    
    }
    
    // gets collection from database
    private MongoCollection<Document> getRecords(){
    
        return crud.getCollection("paint", this.database);
    }
    
     // retrieves name from database
    private String getName(ObjectId id, String collectionName, String key){
        
   
        
        MongoCollection<Document> col = crud.getCollection(collectionName, this.database);
        return (String) crud.getFirstRecordByKey("_id", id, col).get(key);
           
    }
    
    // preapres engine records to display on table
    private void formatPaintRecords(){
    
        
        MongoCollection<Document> paintCollection = getRecords();
        
        addToArrayList(paintCollection);
        
        fillIdsWithValues();
        
       
    }
    
        // replaces ids with values by fetching from database
    private void fillIdsWithValues(){
        
        
        for(int i = 0; i < this.paintDocuments.size(); i++){
            
            
        
            Document record = this.paintDocuments.get(i);
            System.out.print(record);
            
           
            record
                    .append("planeNumber",
                            getName((ObjectId) record.get("planeId"),
                                    "airplanes",
                                    "flight"));
            
            record.append("date", formatDate((Date) record.get("date")));
            
            record.append("dueDate", formatDate((Date) record.get("dueDate")));
        
        }
        
        
    }
    
    // formats date into format MM/dd/yyyy
    private String formatDate(Date date){
    
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("MM/dd/yyyy");


    
        return outputFormat.format(date);
    }
    
       // Adds engines records to ArrayList
    private void addToArrayList(MongoCollection<Document> paintCollection){
    
        
        FindIterable<Document> fi = paintCollection.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {                              
                this.paintDocuments.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        
    }
    
    public DefaultTableModel getPaintRecords() throws ParseException{
    
        formatPaintRecords();
        
        this.paintDocuments = sortByDate(this.paintDocuments);
        
        return returnTableModel(this.paintDocuments);
        
  
    }
    
    public DefaultTableModel returnTableModel(ArrayList<Document> recordDocuments){
        
        recordDocuments = recordDocuments != null ? recordDocuments : this.paintDocuments;
        
        String[] columnNames = { "Date", "Plane Number", "Color", "Status", "Due Date"};
    
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                   //all cells false
                   return false;
                };
        };
        
        for(int i = 0; i < recordDocuments.size(); i++){
            
            Document order = recordDocuments.get(i);
        
            model.addRow(new Object[] { order.get("date"), order.get("planeNumber"), order.get("color"), order.get("status"), order.get("dueDate")});
        
        } 
         
        return model;
    }
    
    public ArrayList<Document> fetchByFilter(String key, String value) {
        
       return filterRecords(key, value);
       
        
    }
    
       
    private ArrayList<Document> filterRecords(String key,String value){
        
        ArrayList<Document> filteredrecords = new ArrayList<>();
    
        for(int i = 0; i < this.paintDocuments.size(); i++){
        
            Document doc = this.paintDocuments.get(i);
            
            if(doc.get(key).equals(value)){
                filteredrecords.add(doc);
            }
        
        }
        
        return filteredrecords;
    }
    
    private ArrayList<Document> sortByDate(ArrayList<Document> recordDocuments) throws ParseException{
        
        
        for (int i = 0; i < recordDocuments.size()-1 ; i++){
            
            int largest = i;
            
            for (int j = i+1; j < recordDocuments.size() ; j++){
                SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy");
                Date d1 = sdformat.parse(recordDocuments.get(largest).get("date").toString());
                Date d2 = sdformat.parse(recordDocuments.get(j).get("date").toString());
        
                if(d2.compareTo(d1) > 0){
                    largest = j;
                }
            }
            
            Document temp = recordDocuments.get(i);
            recordDocuments.set(i, recordDocuments.get(largest));
            recordDocuments.set(largest, temp);
            
        }
        
        return recordDocuments;
        
    
    }
    
     public void deleteOrder(Object value){
    
    
        crud.deleteDocumentById("_id", value, crud.getCollection("paint", this.database));
    
    }
    
}
