/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.fleetmanagement;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import database.CRUDOperations;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;
import model.AfterFlight;
import model.AirplaneTrip;
import model.Checks;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import validation.Validation;


/**
 *
 * @author hetpatel
 */

public class AddTrip extends javax.swing.JFrame {

    /**
     * Creates new form AddTrip
     */
    
    Places p;
    MongoCollection<Document> airplanes;
    MongoCollection<Document> trips;
    ObjectId id;
    DateFormatter dateFormatter;
    FleetManagement fm;
    CRUDOperations crud = new CRUDOperations();
    MongoDatabase database;
    
    public AddTrip(MongoCollection<Document> airplane, ObjectId id, MongoCollection<Document> trips, FleetManagement fm) {
        
         // add date field
        DateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
        dateFormatter  = new DateFormatter(dateFormat); 
        this.fm = fm;
        this.database = this.fm.database;
        initComponents();
        
        this.p = new Places();
        this.airplanes = airplane;
        this.id = id;
        this.trips = trips;
        setLocationRelativeTo(null);
        // fills dropdown places
        System.out.println("view.fleetmanagement.AddTrip.<init>()");
        Document plane = crud.getFirstRecordByKey("_id", this.id, this.airplanes);
        planeName.setText(plane.get("company").toString().toUpperCase() +"  ->  "+plane.get("flight"));
        fillPlaces();
        fillTime();
        fillPilotsAndHostess();
//        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//        pack();
        setResizable(false);
        setVisible(true);
    }
    
    public void fillPlaces(){
    
        for(int i = 0; i < p.getPlaces().length; i++){
           
           fromField.addItem(p.getPlaces()[i]);
           toField.addItem(p.getPlaces()[i]);
           layoverField.addItem(p.getPlaces()[i]);
       
        }
    
    }
    
    public void fillTime(){
    
        for(int i = 1; i < 13; i++){
            if(i < 10){
                hrsField.addItem(0+""+i+"");
                hrsField1.addItem(0+""+i+"");  
            }
            else{
                hrsField.addItem(i+"");
                hrsField1.addItem(i+"");  
            }
                  
        }
        
        for(int i = 0; i < 61; i++){
            if( i < 10){
                minutesFiled.addItem(0+""+i+"");
                minutesFiled1.addItem(0+""+i+"");
            }else{
                minutesFiled.addItem(i+"");
                minutesFiled1.addItem(i+"");
            }
            
        
        }
    
    }
    
    public void fillPilotsAndHostess(){
    
        MongoCursor<Document> cursor = crud.getCollection("employees", this.database).find().iterator();
        
        try {
            while(cursor.hasNext()) {      
                Document doc = cursor.next();
                if(doc.get("Designation").toString().equals("Captain") ||
                        doc.get("Designation").toString().equals("First Officer")
                        ){
                    pilot1.addItem(doc.get("email").toString());
                    pilot2.addItem(doc.get("email").toString());
                
                }else{
                
                    hostess1.addItem(doc.get("email").toString());
                    hostess2.addItem(doc.get("email").toString());
                }
                
            }
        } finally {
            cursor.close();
        }
        
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dateField1 = new javax.swing.JFormattedTextField(dateFormatter);
        jLabel6 = new javax.swing.JLabel();
        dateField = new javax.swing.JFormattedTextField(dateFormatter);
        jLabel5 = new javax.swing.JLabel();
        hrsField1 = new javax.swing.JComboBox<>();
        minutesFiled1 = new javax.swing.JComboBox<>();
        dayTimeField1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        hostess2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        hrsField = new javax.swing.JComboBox<>();
        minutesFiled = new javax.swing.JComboBox<>();
        dayTimeField = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        hostess1 = new javax.swing.JComboBox<>();
        pilot2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        pilot1 = new javax.swing.JComboBox<>();
        layoverField = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        toField = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        fromField = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        planeName = new javax.swing.JLabel();
        addNewTrip = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel7.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Arrival Date : ");

        dateField1.setValue(new Date());

        jLabel6.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Departure Date : ");

        dateField.setText("mm/dd/yyyy");
        dateField.setValue(new Date());

        jLabel5.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Arrival Time");

        hrsField1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Hour"}));

        minutesFiled1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Minute"}));

        dayTimeField1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM"}));

        jLabel11.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Hostess 2");

        hostess2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select"}));
        hostess2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hostess2ItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Departure Time: ");

        hrsField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Hour"}));

        minutesFiled.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Minute"}));

        dayTimeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM"}));

        jLabel10.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Hostess 1");

        hostess1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select"}));
        hostess1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hostess1ItemStateChanged(evt);
            }
        });

        pilot2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Select" }));
        pilot2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pilot2ItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pilot 1");

        pilot1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select"}));
        pilot1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pilot1ItemStateChanged(evt);
            }
        });

        layoverField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select layover"}));
        layoverField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                layoverFieldItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Layover : ");

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("To");

        toField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Destination"}));
        toField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                toFieldItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("From :");

        fromField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select From"}));
        fromField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fromFieldItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Pilot 2");

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        planeName.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        planeName.setForeground(new java.awt.Color(255, 255, 255));

        addNewTrip.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        addNewTrip.setText("Add New Trip");
        addNewTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewTripMouseClicked(evt);
            }
        });
        addNewTrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTripActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(planeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(addNewTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(planeName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246)
                .addComponent(addNewTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/addtrip.jpg"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hrsField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(minutesFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dayTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hrsField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(minutesFiled1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dayTimeField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(layoverField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fromField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(130, 130, 130)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pilot1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(hostess2, 0, 187, Short.MAX_VALUE)
                                        .addComponent(hostess1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pilot2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(toField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pilot1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(fromField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(layoverField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pilot2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutesFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostess1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayTimeField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutesFiled1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrsField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostess2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(160, 160, 160))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNewTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewTripMouseClicked
        // TODO add your handling code here:
        String msg = validateFields();
        if(msg.length() > 0){

            JOptionPane.showMessageDialog(this,
                msg,
                "Field Validations",
                JOptionPane.ERROR_MESSAGE);
        }else{

            AirplaneTrip at = new AirplaneTrip();
            at.setFrom((String) fromField.getSelectedItem());
            at.setTo((String) toField.getSelectedItem());
            String flight = layoverField.getSelectedItem().toString().equals("Select layover") ? "Direct" : "Has Layover";
            at.setFlight(flight);
            String layoverName = layoverField.getSelectedItem().toString().equals("Select layover") ? "None" : layoverField.getSelectedItem().toString();
            at.setLayover(layoverName);

            SimpleDateFormat sdf
            = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm");

            int exactHours = Integer.parseInt((String) hrsField.getSelectedItem());
            if(dayTimeField.getSelectedItem().equals("PM")){
                exactHours +=12;
            }
            String startDate = dateField.getText() +" "+exactHours+":"+minutesFiled.getSelectedItem().toString();

            int exactHours1 = Integer.parseInt((String) hrsField1.getSelectedItem());
            if(dayTimeField1.getSelectedItem().equals("PM")){
                exactHours1 +=12;
            }

            String endDate =  dateField1.getText() +" "+exactHours1+":"+minutesFiled1.getSelectedItem().toString();

            try {
                Date d1 = sdf.parse(startDate);
                Date d2 = sdf.parse(endDate);

                long difference_In_Time = d2.getTime() - d1.getTime();

                long difference_In_Days
                = TimeUnit
                .MILLISECONDS
                .toDays(difference_In_Time);

                long difference_In_Hours
                = TimeUnit
                .MILLISECONDS
                .toHours(difference_In_Time)%24;

                long difference_In_Minutes
                = TimeUnit
                .MILLISECONDS
                .toMinutes(difference_In_Time) % 60;

                at.setTraveTime(difference_In_Days +" days, "+difference_In_Hours +" hrs, "+difference_In_Minutes+" mins");

            } catch (ParseException ex) {
                Logger.getLogger(AddTrip1.class.getName()).log(Level.SEVERE, null, ex);
            }
            at.setDepartureDate(new Date(dateField.getText()));
            at.setArrivalDate(new Date(dateField1.getText()));
            at.setDepartureTime(hrsField.getSelectedItem() +":"+ minutesFiled.getSelectedItem().toString()+" "+dayTimeField.getSelectedItem());
            at.setArrivalTime(hrsField1.getSelectedItem() +":"+ minutesFiled1.getSelectedItem().toString()+" "+dayTimeField1.getSelectedItem());
            at.setAverageSpeed(450);
            at.setAverageHeight(3600);
            at.setEconomy(112);
            at.setPremiumEconomy(40);
            at.setBusiness(20);
            at.setMileage(2650);
            at.setStops(0);
            at.setLayoverTime("0");
            at.setChecks(new Checks());
            at.setAfterFlight(new AfterFlight());
            String[] p = {pilot1.getSelectedItem().toString(), pilot2.getSelectedItem().toString()};
            at.setPilots(p );
            String[] h = { hostess1.getSelectedItem().toString(), hostess2.getSelectedItem().toString()};
            at.setHostess(h);

            System.out.println(this.id);
            Document doc = crud.getFirstRecordByKey("planeId", this.id, trips);

            ObjectId newId = (ObjectId) doc.get("_id");

            Document newTrip = (Document) doc.get("trips");

            // adding new trip to trips document
            int noOfTrips = newTrip.size();
            Gson gson = new Gson();
            BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(at));
            obj.append("arrivalDate", at.getArrivalDate());
            obj.append("departureDate",at.getDepartureDate());

            newTrip.append("trip_"+(noOfTrips+1), obj);

            int result = crud.updateObjectOfKey(newId, "trips",newTrip, trips);

            MongoCollection pilotDocUser = crud.getCollection("employees", this.database);

            Document pilot1Doc = crud.getFirstRecordByKey("email", pilot1.getSelectedItem().toString(), pilotDocUser);

            int resultProject = crud.updateArrayById((ObjectId) pilot1Doc.get("_id"), "flights", newId, pilotDocUser);

            Document pilot2Doc = crud.getFirstRecordByKey("email", pilot2.getSelectedItem().toString(), pilotDocUser);

            int resultProject2 = crud.updateArrayById((ObjectId) pilot2Doc.get("_id"), "flights", newId, pilotDocUser);

            Document hos1Doc = crud.getFirstRecordByKey("email", hostess1.getSelectedItem().toString(), pilotDocUser);

            int resultProject3 = crud.updateArrayById((ObjectId) hos1Doc.get("_id"), "flights", newId, pilotDocUser);

            Document hos2Doc = crud.getFirstRecordByKey("email", hostess2.getSelectedItem().toString(), pilotDocUser);

            int resultProject4 = crud.updateArrayById((ObjectId) hos2Doc.get("_id"), "flights", newId, pilotDocUser);

            if(result > 0 && resultProject > 0 && resultProject2 > 0 && resultProject3 > 0 && resultProject4 > 0){
                JOptionPane.showMessageDialog(this,
                    "Trip was added successfully",
                    "Data Added",
                    JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this,
                    "Error while adding new trip. Please try again later",
                    "Field Validations",
                    JOptionPane.ERROR_MESSAGE);
            }

            setVisible(false);
            dispose();

        }

    }//GEN-LAST:event_addNewTripMouseClicked

    private void addNewTripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTripActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewTripActionPerformed

    private void hostess2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hostess2ItemStateChanged
        // TODO add your handling code here:
        String h1 = hostess1.getSelectedItem().toString();
        String h2 = hostess2.getSelectedItem().toString();

        if(h2.equals(h1)){
            hostess1.setSelectedIndex(0);

            JOptionPane.showMessageDialog(this,
                "Both Air Hostess cannot be same",
                "Pilots",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_hostess2ItemStateChanged

    private void hostess1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hostess1ItemStateChanged
        // TODO add your handling code here:
        String h1 = hostess1.getSelectedItem().toString();
        String h2 = hostess2.getSelectedItem().toString();

        if(h2.equals(h1)){
            hostess2.setSelectedIndex(0);

            JOptionPane.showMessageDialog(this,
                "Both Air Hostess cannot be same",
                "Pilots",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_hostess1ItemStateChanged

    private void pilot2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pilot2ItemStateChanged
        // TODO add your handling code here:
        String p1 = pilot1.getSelectedItem().toString();
        String p2 = pilot2.getSelectedItem().toString();
        System.out.println(p1 +" "+p2);
        if(p2.equals(p1)){
            pilot1.setSelectedIndex(0);

            JOptionPane.showMessageDialog(this,
                "Both Pilots cannot be same",
                "Pilots",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_pilot2ItemStateChanged

    private void pilot1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pilot1ItemStateChanged
        // TODO add your handling code here:
        String p1 = pilot1.getSelectedItem().toString();
        String p2 = pilot2.getSelectedItem().toString();

        System.out.println(p1 +" "+p2);
        if(p2.equals(p1)){
            pilot2.setSelectedIndex(0);

            JOptionPane.showMessageDialog(this,
                "Both Pilots cannot be same",
                "Pilots",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_pilot1ItemStateChanged

    private void layoverFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_layoverFieldItemStateChanged
        // TODO add your handling code here:

        String fromSel = (String) fromField.getSelectedItem();
        String toSel = (String) toField.getSelectedItem();
        String laySel = (String) layoverField.getSelectedItem();

        if(fromSel.equals(laySel)){
            fromField.setSelectedIndex(0);
        }
        if(toSel.equals(laySel)){
            layoverField.setSelectedIndex(0);
        }
    }//GEN-LAST:event_layoverFieldItemStateChanged

    private void toFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_toFieldItemStateChanged
        // TODO add your handling code here:

        String fromSel = (String) fromField.getSelectedItem();
        String toSel = (String) toField.getSelectedItem();
        String laySel = (String) layoverField.getSelectedItem();

        if(fromSel.equals(toSel)){
            fromField.setSelectedIndex(0);
        }
        if(laySel.equals(toSel)){
            layoverField.setSelectedIndex(0);
        }

    }//GEN-LAST:event_toFieldItemStateChanged

    private void fromFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fromFieldItemStateChanged
        // TODO add your handling code here:

        String fromSel = (String) fromField.getSelectedItem();
        String toSel = (String) toField.getSelectedItem();
        String laySel = (String) layoverField.getSelectedItem();

        if(toSel.equals(fromSel)){
            toField.setSelectedIndex(0);
        }
        if(laySel.equals(fromSel)){
            layoverField.setSelectedIndex(0);
        }

    }//GEN-LAST:event_fromFieldItemStateChanged
    
    
    public String validateFields(){
        
        String message = "";
    
        if(fromField.getSelectedItem().equals("Select From")){
            message += "From field, ";
        }
        if(toField.getSelectedItem().equals("Select Destination")){
            message += "To field, ";
        }
        if(hrsField.getSelectedItem().equals("Select Hour")){
            message += "Departure Hours Field, ";
        }
        if(hrsField1.getSelectedItem().equals("Select Hour")){
            message += "Arrival Hours Field, ";
        }
        if(minutesFiled.getSelectedItem().equals("Select Minute")){
            message += "Departure Minutes Field, ";
        }
        if(minutesFiled1.getSelectedItem().equals("Select Minute")){
            message += ", Arrival Minutes Field, ";
        }
        
        if(!new Validation().dateValidate(dateField.getText().toString())){
            message += "Departure Date Field, ";
        }
        
        if(!new Validation().dateValidate(dateField1.getText().toString())){
            message += "Arrival Date Field, ";
        }
        
        if(message.length() > 0){
            message+=" are not valid";
        }
        if(pilot1.getSelectedItem().equals("Select")){
            message += ", Pilot 1 Field, ";
        }
        if(pilot2.getSelectedItem().equals("Select")){
            message += ", Pilot 2 Field, ";
        }
        if(hostess1.getSelectedItem().equals("Select")){
            message += ", Hostess 1 Field, ";
        }
        if(hostess2.getSelectedItem().equals("Select")){
            message += ", Hostess 2 Field, ";
        }
    
        return message;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewTrip;
    private javax.swing.JFormattedTextField dateField;
    private javax.swing.JFormattedTextField dateField1;
    private javax.swing.JComboBox<String> dayTimeField;
    private javax.swing.JComboBox<String> dayTimeField1;
    private javax.swing.JComboBox<String> fromField;
    private javax.swing.JComboBox<String> hostess1;
    private javax.swing.JComboBox<String> hostess2;
    private javax.swing.JComboBox<String> hrsField;
    private javax.swing.JComboBox<String> hrsField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> layoverField;
    private javax.swing.JComboBox<String> minutesFiled;
    private javax.swing.JComboBox<String> minutesFiled1;
    private javax.swing.JComboBox<String> pilot1;
    private javax.swing.JComboBox<String> pilot2;
    private javax.swing.JLabel planeName;
    private javax.swing.JComboBox<String> toField;
    // End of variables declaration//GEN-END:variables
}
