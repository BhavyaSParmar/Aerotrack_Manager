/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.employee;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import database.CRUDOperations;
import javax.swing.JOptionPane;
import model.Employee;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import validation.Validation;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 *
 * @author Asus
 */
public class AddUpdateEmployee extends javax.swing.JFrame {

    /**
     * Creates new form AddUpdateEmployee
     */
    MongoDatabase database;
    CRUDOperations crud = new CRUDOperations();
    ObjectId id;
    String action;
    
    public AddUpdateEmployee(MongoDatabase database, ObjectId id, String action) {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        this.database = database;
        this.action = action;
        
        fillManagers();
        
        if(action.equals("view")){
            this.id = id;
            populateFields();
            crudBtn.setText("Close");
        }
        else if(action.equals("update")){
            this.id = id;
            populateFields();
            crudBtn.setText("Update");
        }
        else{
            crudBtn.setText("Add");
        }
      
    }
    
    public void populateFields(){
        
        MongoCollection coll = crud.getCollection("employees", this.database);
        Document doc = crud.getFirstRecordByKey("_id", this.id, coll);
        
        String name[] = doc.get("name").toString().split(" ");
        
        
        
        nameField.setText(name[0]);
        lastField.setText(name[1]);
        designationField.setSelectedItem(doc.get("Designation").toString());
        salaryField.setText(doc.get("salary").toString());
        managerField.setSelectedItem(doc.get("manager").toString());
        dateField.setText(doc.get("dateOfJoining").toString());
        numberField.setText(doc.get("number").toString());
        emailField.setText(doc.get("email").toString());
        
    
    }
    
    public void fillManagers(){
        
       
    
        FindIterable<Document> doc = crud.getRecordsByKey("Designation", "Manager", crud.getCollection("employees", this.database));
        
        MongoCursor<Document> cursor = doc.iterator(); // (2)
        try {
            while(cursor.hasNext()) {
                managerField.addItem((String) cursor.next().get("name"));
            }
        } finally {
            cursor.close();
        }
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        crudBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        designationField = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        salaryField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        managerField = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        dateField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        numberField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lastField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        crudBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        crudBtn.setText("Add ");
        crudBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crudBtnMouseClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/03.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(crudBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(crudBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("First Name");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Designation");

        designationField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Captain", "First Officer", "Air Hostess", "Manager"}));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Salary");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Manager");

        managerField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select"}));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date Of Joining");

        dateField.setText("00/00/0000");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contact Number");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Last Name");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/addEmployeesss.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(127, 127, 127)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(designationField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(managerField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lastField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(designationField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managerField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(231, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crudBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crudBtnMouseClicked
        // TODO add your handling code here:

        if(this.action.equals("add") || this.action.equals("update")){
            String validateMessage = ValidateAllFields();

            if(validateMessage.length() > 0){
                JOptionPane.showMessageDialog(this,validateMessage);
            }
            else{

                
                if(verifyUniqueEmail(emailField.getText())){
                
                    if(this.action.equals("add")){
                        Employee emp = new Employee();

                        addToModel(emp);

                        Document newDoc = createDocument(emp);

                        crud.insertDocument(newDoc, crud.getCollection("employees", this.database));

                        JOptionPane.showMessageDialog(this,"Employee was added successfully");
                    }
                    else if(this.action.equals("update")){


                        Document query = new Document().append("_id",  this.id);
                        String desig = managerField.getSelectedItem().toString().equals("Select") ? "" : managerField.getSelectedItem().toString();
                        Bson updates = Updates.combine(
                        Updates.set("name", nameField.getText()+" "+lastField.getText()),
                              Updates.set("Designation", designationField.getSelectedItem().toString()),
                              Updates.set("salary", salaryField.getText()),
                              Updates.set("manager", desig),
                              Updates.set("dateOfJoining", dateField.getText()),
                              Updates.set("number", numberField.getText()),
                              Updates.set("email", emailField.getText())
                        );

                        UpdateOptions options = new UpdateOptions().upsert(true);

                        crud.updateDocumentById(query, updates, options, crud.getCollection("employees", this.database));
                        JOptionPane.showMessageDialog(this,"Employee was updated successfully");
                    }
                    dispose();
                }else{
                
                    JOptionPane.showMessageDialog(this,"Email is already in use. Please use different email id");
                
                }
                
                

            }
        }
        else if(this.action.equals("view")){
            dispose();
        }
    }//GEN-LAST:event_crudBtnMouseClicked
    
    private Boolean verifyUniqueEmail(String email){
        
       
        FindIterable<Document> fi =  crud.getCollection("employees", this.database).find();
        MongoCursor<Document> cursor = fi.iterator();
        
        
        try {
            while(cursor.hasNext()) {                              
                Document doc = cursor.next();
                
                if(doc.get("email").toString().equals(email)){
                    return false;
                }
                
            }
        } finally {
            cursor.close();
        }
    
        
        return true;
    }
    
    private Document createDocument(Employee emp){
    
        return new Document()
                .append("name",emp.getName())
                .append("Designation", emp.getDesignation())
                .append("salary", emp.getSalary())
                .append("manager", emp.getManager())
                .append("dateOfJoining", emp.getDateOfJoining())
                .append("number", emp.getNumber())
                .append("email", emp.getEmail())
                .append("flights", emp.getFlights());
       
    }
    
    
    private void addToModel(Employee emp){
        
        emp.setName(nameField.getText()+" "+lastField.getText());
        emp.setDesignation(designationField.getSelectedItem().toString());
        emp.setSalary(salaryField.getText());
        
        if(emp.getDesignation().equals("Manager")){
            
            emp.setManager(" ");
        
        }else{
            emp.setManager(managerField.getSelectedItem().toString());
        
        }
        
        emp.setDateOfJoining(dateField.getText().toString());
        emp.setEmail(emailField.getText());
        emp.setNumber(Long.parseLong(numberField.getText()));
        
    }
    
    
    private String ValidateAllFields(){
    
        String message = "";
        Validation validate = new Validation();
        
        if(!validate.onlyCharacters(nameField.getText())){
            message+="Fisrt Name, ";
            nameField.setText("Can contain only characters");
        
        }
        if(!validate.onlyCharacters(nameField.getText())){
            message+="Last Name, ";
            lastField.setText("Can contain only characters");
        
        }
        
        if(!validate.salaryValidate(salaryField.getText())){
            message+="Salary, ";
            salaryField.setText("Can contain only numbers");
        }
        
        if(!validate.onlyNumbers(numberField.getText())){
            message+="Contact, ";
            numberField.setText("only 10 numbers");
        }
        
        if(numberField.getText().length() > 10 || numberField.getText().length() < 10){
            message+="Contact, ";
            numberField.setText("only 10 numbers");
        }
        
        
        if(designationField.getSelectedItem().equals("Select")){
            message+="Designation, ";
        }
        if(!designationField.getSelectedItem().equals("Manager")){
            if(managerField.getSelectedItem().equals("Select")){
                message+="Manager, ";
            }
        }
        if(!validate.emailValidation(emailField.getText())){
            message+="Email, ";
            emailField.setText("Invalid Email");
        }
        
        
        if(!validate.dateValidate(dateField.getText())){
            message+="Date, ";

            dateField.setText("00/00/0000");
        }
        
       
        if(message.length() < 1){
        
            return "";
        }else{
        
            return "Fields "+message+" are not valid!";
        }
    
    
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crudBtn;
    private javax.swing.JFormattedTextField dateField;
    private javax.swing.JComboBox<String> designationField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField lastField;
    private javax.swing.JComboBox<String> managerField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField numberField;
    private javax.swing.JTextField salaryField;
    // End of variables declaration//GEN-END:variables
}
