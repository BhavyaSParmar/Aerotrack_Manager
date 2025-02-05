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
public class RepairHomeService {

    private CRUDOperations crud = new CRUDOperations();

    // fetches database
    private MongoDatabase database = DataBaseConnection.connectToDatabase().database;

    private ArrayList<Document> repairDocuments = new ArrayList<>();

    public ArrayList<Document> getArrayData() {
        return this.repairDocuments;

    }

    // retrieves name from database
    private String getName(ObjectId id, String collectionName, String key) {

        MongoCollection<Document> col = crud.getCollection(collectionName, this.database);
       
        return (String) crud.getFirstRecordByKey("_id", id, col).get(key);

    }

    // gets collection from database
    private MongoCollection<Document> getRecords() {

        return crud.getCollection("repair", this.database);
    }

    // Adds engines records to ArrayList
    private void addToArrayList(MongoCollection<Document> repairCollection) {

        FindIterable<Document> fi = repairCollection.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while (cursor.hasNext()) {
                this.repairDocuments.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

    }

    // formats date into format MM/dd/yyyy
    private String formatDate(Date date) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

        return outputFormat.format(date);
    }

    // replaces ids with values by fetching from database
    private void fillIdsWithValues() {

        for (int i = 0; i < this.repairDocuments.size(); i++) {

            Document record = this.repairDocuments.get(i);

            record
                    .append("name",
                            getName((ObjectId) record.get("userId"),
                                    "users",
                                    "name"));

            record
                    .append("planeNumber",
                            getName((ObjectId) record.get("planeId"),
                                    "airplanes",
                                    "flight"));

            record.append("date", formatDate((Date) record.get("date")));

            record.append("dueDate", formatDate((Date) record.get("dueDate")));

        }

    }

    // preapres engine records to display on table
    private void formatEngineRecords() {

        MongoCollection<Document> engineCollection = getRecords();

        addToArrayList(engineCollection);

        fillIdsWithValues();

    }

    public DefaultTableModel getEngineRecords() throws ParseException {

        formatEngineRecords();

        this.repairDocuments = sortByDate(this.repairDocuments);

        return returnTableModel(this.repairDocuments);

    }

    public DefaultTableModel returnTableModel(ArrayList<Document> recordDocuments) {

        recordDocuments = recordDocuments != null ? recordDocuments : this.repairDocuments;

        String[] columnNames = {"Date", "Plane Number", "Repair", "Description", "Requested By", "Due Date", "Status"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        ;
        };
        
        for (int i = 0; i < recordDocuments.size(); i++) {

            Document order = recordDocuments.get(i);

            model.addRow(new Object[]{order.get("date"),
                order.get("planeNumber"),
                order.get("repairPart"),
                order.get("description"),
                order.get("name"),
                order.get("dueDate"),
                order.get("status"),});

        }

        return model;
    }

    public ArrayList<Document> fetchByFilter(String key, String value) {

        return filterRecords(key, value);

    }

    private ArrayList<Document> filterRecords(String key, String value) {

        ArrayList<Document> filteredrecords = new ArrayList<Document>();

        for (int i = 0; i < this.repairDocuments.size(); i++) {

            Document doc = this.repairDocuments.get(i);

            if (doc.get(key).equals(value)) {
                filteredrecords.add(doc);
            }

        }

        return filteredrecords;
    }

    private ArrayList<Document> sortByDate(ArrayList<Document> recordDocuments) throws ParseException {

        for (int i = 0; i < recordDocuments.size() - 1; i++) {

            int largest = i;

            for (int j = i + 1; j < recordDocuments.size(); j++) {
                SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy");
                Date d1 = sdformat.parse(recordDocuments.get(largest).get("date").toString());
                Date d2 = sdformat.parse(recordDocuments.get(j).get("date").toString());

                if (d2.compareTo(d1) > 0) {
                    largest = j;
                }
            }

            Document temp = recordDocuments.get(i);
            recordDocuments.set(i, recordDocuments.get(largest));
            recordDocuments.set(largest, temp);

        }

        return recordDocuments;

    }

}
