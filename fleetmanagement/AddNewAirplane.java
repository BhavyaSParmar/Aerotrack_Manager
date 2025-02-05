/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.fleetmanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.CRUDOperations;
import java.awt.Font;
import javax.swing.JOptionPane;
import model.Airplane;
import org.bson.Document;
import org.bson.types.ObjectId;
import validation.Validation;

/**
 *
 * @author hetpatel
 */
public class AddNewAirplane extends javax.swing.JFrame {

    /**
     * Creates new form AddNewAirplane
     */
    MongoDatabase database;
    CRUDOperations crud = new CRUDOperations();
    
    String[] airbusModels = {"A321", "A380", "A2300", "A220"};
    String[] boeingModels = {"737Max", "777x", "777", "767"};
    
    public AddNewAirplane(MongoDatabase database) {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        this.database = database;
        setLocationRelativeTo(null);
        addModels();
        
    }
    
    public void addModels(){
        
        String planeCompany = companyField.getSelectedItem().toString();
        
        if(planeCompany.equals("Boeing")){
            
            modelField.setModel(new javax.swing.DefaultComboBoxModel<>(boeingModels));
            
        }else{
            modelField.setModel(new javax.swing.DefaultComboBoxModel<>(airbusModels));
        }
    
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        companyField = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        numberField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        modelField = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Company");
        jLabel1.setFont(new Font("Thonburi", Font.PLAIN, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Add New Airplane To Fleet");
        jLabel2.setFont(new Font("Thonburi", Font.PLAIN, 20));

        companyField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boeing", "Airbus"}));
        companyField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                companyFieldItemStateChanged(evt);
            }
        });
        companyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Airline Number");
        jLabel3.setFont(new Font("Thonburi", Font.PLAIN, 20));

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Model");
        jLabel4.setFont(new Font("Thonburi", Font.PLAIN, 20));

        modelField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        addBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        addBtn.setText("Add");
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/addNewAirplane.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 188, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(modelField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void companyFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_companyFieldItemStateChanged
        // TODO add your handling code here:
        addModels();
    }//GEN-LAST:event_companyFieldItemStateChanged

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        // TODO add your handling code here:
        
        if(new Validation().specialCharacters(numberField.getText())){
            
            
            Airplane airplane = new Airplane();
            
            airplane.setCompany(companyField.getSelectedItem().toString());
            airplane.setFlight(numberField.getText().toUpperCase());
            airplane.setModel(modelField.getSelectedItem().toString());
            
            addData(airplane);
            
            Document airplaneDoc = createDocument(airplane);
            
        
            MongoCollection<Document> collection = crud.getCollection("airplanes", this.database);
                
            crud.insertDocument(airplaneDoc, collection);
            
            MongoCollection<Document> collectionTrips = crud.getCollection("trips", this.database);
            
            Document tripsDoc = new Document()
                    .append("_id", new ObjectId())
                    .append("planeId", airplaneDoc.get("_id"))
                    .append("trips", new Document());
            
            
            crud.insertDocument(tripsDoc, collectionTrips);
            
            JOptionPane.showMessageDialog(this,
                 "Airplane Added Successfully!",
            "Add Airplane",
            JOptionPane.INFORMATION_MESSAGE);
            numberField.setText("");
            dispose();
            
        
        }else{
        
            JOptionPane.showMessageDialog(this,
                 "Airline number can contain only numbers and characters",
            "Airline number",
            JOptionPane.ERROR_MESSAGE);
            numberField.setText("");
        }
        
    }//GEN-LAST:event_addBtnMouseClicked

    private void companyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companyFieldActionPerformed
    
    
    private void addData(Airplane airplane){
    
        switch(airplane.getModel()){
    
            case "737Max":
                airplane.setSeats(228);
                airplane.setEngine(2);
                airplane.setAisle("Single");
                
                break;
                
            case "777x":
                airplane.setSeats(555);
                airplane.setEngine(4);
                airplane.setAisle("Double");
                break;
            case "777":
                airplane.setSeats(555);
                airplane.setEngine(4);
                airplane.setAisle("Double");
                break;
            case "767":
                airplane.setSeats(300);
                airplane.setEngine(2);
                airplane.setAisle("Single");
                break;
                
            case "A230":
                airplane.setSeats(130);
                airplane.setEngine(2);
                airplane.setAisle("Single");
                break;
            case "A2300":
                airplane.setSeats(228);
                airplane.setEngine(2);
                airplane.setAisle("Single");
                break;
            case "A380":
                airplane.setSeats(270);
                airplane.setEngine(4);
                airplane.setAisle("Double");
                break;
            case "321":
                airplane.setSeats(246);
                airplane.setEngine(2);
                airplane.setAisle("Single");
                break;
            default:
                throw new AssertionError();
        }
        
    
    }

    private Document createDocument(Airplane airplane){
    
        return new Document()
                .append("_id", new ObjectId())
                .append("company", airplane.getCompany())
                .append("flight", airplane.getFlight())
                .append("model", airplane.getModel())
                .append("seats", airplane.getSeats())
                .append("engine", airplane.getEngine())
                .append("aisle", airplane.getAisle());
                
        
    
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> companyField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> modelField;
    private javax.swing.JTextField numberField;
    // End of variables declaration//GEN-END:variables
}
