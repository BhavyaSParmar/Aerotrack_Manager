/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.payroll;

import com.itextpdf.text.DocumentException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import database.CRUDOperations;
import model.Employee;
import model.Payroll_Admin;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import model.Pdf;
import service.PdfService;
import util.EmailSMTP;

/**
 *
 * @author hetpatel
 */
public class updateSalary extends javax.swing.JFrame {

	/**
	 * Creates new form viewAllowance
	 */

	MongoDatabase database;
	MongoCollection<Document> empDoc;
	MongoCollection<Document> payrollAdmin;
	MongoCollection<Document> payrollAllowances;
	MongoCollection<Document> payrollDeduction;
	CRUDOperations crud = new CRUDOperations();
	int row = -1;

	public updateSalary(MongoDatabase database) {
		initComponents();
		this.database = database;
		payrollAdmin = crud.getCollection("payrollAdmin", this.database);
		payrollAllowances = crud.getCollection("payrollAllowances", this.database);
		payrollDeduction = crud.getCollection("payrollDeduction", this.database);
//		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//		pack();
                setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		fetchEmployees();
	}

	public void fetchEmployees(){

		empDoc = crud.getCollection("employees", this.database);

		String[] columnNames = {"ID", "Name", "designation", "Contact", "Email", "Salary", "Joining Date", "Manager"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			};
		};

		// model.addRow(new Object[] { trip.get("from"), trip.get("to"), trip.get("date") });
		MongoCursor<Document> cursor = empDoc.find().iterator();
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

		empTable.setModel(model);

	}
        
        private void updateOrDeleteDocument(ObjectId empid, String allowance, String deduction){
            Document selectedEmp = crud.getFirstRecordByKey("emp_id", empid, payrollAdmin);
            ArrayList<String> employeeAllowances = (ArrayList<String>) selectedEmp.get("allowances");
            ArrayList<String> employeeDeductions = (ArrayList<String>) selectedEmp.get("deductions");
            if(employeeAllowances.size() == 1 && employeeDeductions.size() == 1 && allowance !=null && payrollDeduction !=null){
                crud.deleteDocumentById("emp_id", empid, payrollAdmin);
            }
            else{
            ArrayList<String> allowances = new ArrayList<String>();
            ArrayList<String> deductions = new ArrayList<String>(); 
            for(String a: employeeAllowances) {
		if(!a.equals(allowance)){
                    allowances.add(a);
                }
            }
            for(String d: employeeDeductions) {
		if(!d.equals(deduction)){
                    deductions.add(d);
                }
            }
            Document query = new Document().append("emp_id", empid);
            Bson updates = Updates.combine(
			Updates.set("emp_id",empid),
            Updates.set("allowances", allowances),
            Updates.set("deductions", deductions)
      ); 
      UpdateOptions options = new UpdateOptions().upsert(true);
      crud.updateDocumentById(query, updates, options, crud.getCollection("payrollAdmin", this.database));		
            }
        }
	
	 private void createOrUpdateDocument(Payroll_Admin pa){
		Document selectedEmp = crud.getFirstRecordByKey("emp_id", pa.getEmp_id(), payrollAdmin);
		if(selectedEmp != null) {
			ArrayList<String> employeeAllowances = (ArrayList<String>) selectedEmp.get("allowances");
			ArrayList<String> employeeDeductions = (ArrayList<String>) selectedEmp.get("deductions");
			for(String allowance: employeeAllowances) {
				pa.setAllowances(allowance);
			}
			for(String deduction: employeeDeductions) {
				pa.setDeductions(deduction);
			}
			Document query = new Document().append("emp_id", pa.getEmp_id());
			Bson updates = Updates.combine(
			Updates.set("emp_id",pa.getEmp_id()),
            Updates.set("allowances", pa.getAllowances()),
            Updates.set("deductions", pa.getDeductions())
      ); 
      UpdateOptions options = new UpdateOptions().upsert(true);
      crud.updateDocumentById(query, updates, options, crud.getCollection("payrollAdmin", this.database));		
		}
		else {
			selectedEmp = new Document()
                .append("emp_id",pa.getEmp_id())
                .append("allowances", pa.getAllowances())
                .append("deductions", pa.getDeductions());
        crud.insertDocument(selectedEmp, crud.getCollection("payrollAdmin", this.database));
		}
    }
    
    private void addToModel(Payroll_Admin pa, ObjectId empID,String allowance,String deduction){
        pa.setEmp_id(empID);
        if(!allowance.equals(" "))
            pa.setAllowances(allowance);
        if(!deduction.equals(" "))
        pa.setDeductions(deduction);        
    }
    
    private void setDropdownData(ObjectId empid) {
    	Document selectedEmp = crud.getFirstRecordByKey("emp_id", empid, payrollAdmin);
        String[] allowanceitems = new String[(int) payrollAllowances.countDocuments()+1];
		String[] deductionitems = new String[(int) payrollDeduction.countDocuments()+1];
                String[] allowancedeleteitems = new String[(int) payrollAllowances.countDocuments()+1];
		String[] deductiondeleteitems = new String[(int) payrollDeduction.countDocuments()+1];
		MongoCursor<Document> cursor = payrollAllowances.find().iterator();
                ArrayList<String> employeeAllowances = null;
		ArrayList<String> employeeDeductions = null;
        if(selectedEmp!=null){
		employeeAllowances = (ArrayList<String>) selectedEmp.get("allowances");
		employeeDeductions = (ArrayList<String>) selectedEmp.get("deductions");
        }
		int i=1, j=1;
		allowanceitems[0] = " ";
		deductionitems[0] = " ";
                allowancedeleteitems[0] = " ";
                deductiondeleteitems[0] = " ";
		try {
			while(cursor.hasNext()) { 
				Document doc = cursor.next();
				if(employeeAllowances==null || !employeeAllowances.contains(doc.get("Reason").toString()))
					allowanceitems[i++]= doc.get("Reason").toString();
                                else
                                    allowancedeleteitems[j++] = doc.get("Reason").toString();
			}
		}finally {
			cursor.close();
			i=1;
                        j=1;
		}

		MongoCursor<Document> cursor1 = payrollDeduction.find().iterator();
		try {
			while(cursor1.hasNext()) { 
				Document doc = cursor1.next();
				if(employeeDeductions==null || !employeeDeductions.contains(doc.get("Reason").toString()))
                                    deductionitems[i++]= doc.get("Reason").toString();
                                else
                                    deductiondeleteitems[j++] = doc.get("Reason").toString();
			}
		}finally {
			cursor1.close();
		}
		
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(deductionitems));
		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(allowanceitems));
                deleteAllowance.setModel(new javax.swing.DefaultComboBoxModel<>(allowancedeleteitems));
                deleteDeduction.setModel(new javax.swing.DefaultComboBoxModel<>(deductiondeleteitems));
    }
    
    private void setPyrollTableData(ObjectId empid){
        Document selectedEmp = crud.getFirstRecordByKey("emp_id", empid, payrollAdmin);
       
        Document employee = crud.getFirstRecordByKey("_id", empid, empDoc);
        ArrayList<String> allowancesArray = (ArrayList<String>) selectedEmp.get("allowances");
        int a = 0, d = 0;
        ArrayList<String> deductionsArray = (ArrayList<String>) selectedEmp.get("deductions");
        for(String allowance: allowancesArray){
            Document allowanceamount = crud.getFirstRecordByKey("Reason", allowance, payrollAllowances);
            a = a + Integer.parseInt(allowanceamount.get("Amount").toString().replaceAll(",",""));
        }
        for(String deduction: deductionsArray){
            System.out.println(deduction+" RMPPPPPPP");
            Document deductionamount = crud.getFirstRecordByKey("Reason", deduction, payrollDeduction);
             
            d = d + Integer.parseInt(deductionamount.get("Amount").toString().replaceAll(",",""));
        }
        String[] columnNames = {"ID", "Name", "email", "salary", "allowances", "deductions", "Total"};
	int salary = Integer.parseInt(employee.get("salary").toString().replaceAll(",",""));		
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			};
		};
        model.addRow(new Object[] { employee.get("_id"),
						employee.get("name"), employee.get("email"),
						"$"+employee.get("salary"), "$"+a,
						"$"+d, "$"+(salary-d+a) });

				PayrollTable.setModel(model);
    }

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        empTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        PayrollTable = new javax.swing.JTable();
        RefreshBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        deleteDeduction = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        deleteAllowance = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        emailBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        searchField.setText("search by email");
        searchField.setFont(new Font("Thonburi", Font.PLAIN, 18));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        empTable.setBackground(new java.awt.Color(0, 51, 102));
        empTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        empTable.setForeground(new java.awt.Color(255, 255, 255));
        empTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Name", "designation", "Contact", "Email", "Salary"
            }
        ));
        empTable.setRowHeight(50);
        empTable.setFont(new Font("Serif", Font.PLAIN, 15));
        empTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(empTable);

        PayrollTable.setBackground(new java.awt.Color(0, 51, 102));
        PayrollTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PayrollTable.setForeground(new java.awt.Color(255, 255, 255));
        PayrollTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Email", "Salary", "Allowance", "Deduction", "Total"
            }
        ));
        PayrollTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PayrollTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(PayrollTable);

        RefreshBtn.setBackground(new java.awt.Color(0, 0, 0));
        RefreshBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        RefreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        RefreshBtn.setText("Refresh");
        RefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel6.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("UPDATE SALARY");

        jLabel7.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DELETE ALLOWANCE");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DELETE DEDUCTION");

        deleteDeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDeductionActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ADD DEDUCTION");

        jLabel10.setFont(new java.awt.Font("Dubai", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ADD ALLOWANCE");

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        printBtn.setBackground(new java.awt.Color(0, 0, 0));
        printBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        printBtn.setForeground(new java.awt.Color(255, 255, 255));
        printBtn.setText("Print");
        printBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printBtnMouseClicked(evt);
            }
        });
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setBackground(new java.awt.Color(0, 0, 0));
        DeleteBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("DELETE");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        emailBtn.setBackground(new java.awt.Color(0, 0, 0));
        emailBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        emailBtn.setForeground(new java.awt.Color(255, 255, 255));
        emailBtn.setText("Email");
        emailBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailBtnMouseClicked(evt);
            }
        });
        emailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(deleteDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox2, 0, 190, Short.MAX_VALUE)
                                            .addComponent(deleteAllowance, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DeleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(emailBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(emailBtn)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(printBtn))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(RefreshBtn))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
		// TODO add your handling code here:
		System.out.println(evt.getKeyCode());
		if(evt.getKeyCode() == 10){

			System.out.println("IN");

			String email = searchField.getText();

			Document searchedEmp = crud.getFirstRecordByKey("email", email, empDoc);

			if(searchedEmp == null){

				JOptionPane.showMessageDialog(this,"Employee data doesn't exist");
				fetchEmployees();
			}else{

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

				empTable.setModel(model);

			}

		}
	}//GEN-LAST:event_searchFieldKeyPressed

	private void empTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empTableMouseClicked
		// TODO add your handling code here:
		JTable source = (JTable)evt.getSource();
		row = source.rowAtPoint( evt.getPoint() );
		ObjectId empid =  (ObjectId) empTable.getValueAt(row, 0);
		setDropdownData(empid);
		
	}//GEN-LAST:event_empTableMouseClicked

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		ObjectId empid =  (ObjectId) empTable.getValueAt(row, 0);
		String deduction = (String) jComboBox1.getSelectedItem();
		String allowance = (String) jComboBox2.getSelectedItem();
		 Payroll_Admin pa = new Payroll_Admin();
         addToModel(pa, empid, allowance, deduction);
         createOrUpdateDocument(pa);
         setDropdownData(empid);
         setPyrollTableData(empid);
         JOptionPane.showMessageDialog(this,"Payroll Allowances/Deductions are added successfully");		
	}//GEN-LAST:event_jButton1ActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void RefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshBtnActionPerformed
        // TODO add your handling code here:
        fetchEmployees();
    }//GEN-LAST:event_RefreshBtnActionPerformed

    private void deleteDeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDeductionActionPerformed
        // TODO add your handling code here:
        if(row > -1)
        {
            ObjectId empid =  (ObjectId) empTable.getValueAt(row, 0);
		String deduction = (String) deleteDeduction.getSelectedItem();
		String allowance = (String) deleteAllowance.getSelectedItem();
		 Payroll_Admin pa = new Payroll_Admin();
            int input = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Be honest...",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(input == 0){
               updateOrDeleteDocument(empid, allowance, deduction);
               setDropdownData(empid);
               setPyrollTableData(empid);
               JOptionPane.showMessageDialog(this,"Record was deleted successfully");
            }
        }else{
        JOptionPane.showMessageDialog(this,"Please select row");
        }  	
    }//GEN-LAST:event_deleteDeductionActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        // TODO add your handling code here:
        if(row > -1)
        {
            ObjectId empid =  (ObjectId) empTable.getValueAt(row, 0);
		String deduction = (String) deleteDeduction.getSelectedItem();
		String allowance = (String) deleteAllowance.getSelectedItem();
            int input = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Be honest...",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(input == 0){
               updateOrDeleteDocument(empid, allowance, deduction);
               setDropdownData(empid);
               setPyrollTableData(empid);
               JOptionPane.showMessageDialog(this,"Record was deleted successfully");
            }
        }else{
        JOptionPane.showMessageDialog(this,"Please select row");
        }  
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if(row > -1)
        {
            ObjectId empid =  (ObjectId) empTable.getValueAt(row, 0);
        setPyrollTableData(empid);
        }
        else{
            JOptionPane.showMessageDialog(this,"Please select row");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printBtnActionPerformed

    private void printBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printBtnMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            generatePdf();
            JOptionPane.showMessageDialog(this,"PDF Generated");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(updateSalary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | URISyntaxException | IOException ex) {
            Logger.getLogger(updateSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_printBtnMouseClicked

    private void PayrollTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PayrollTableMouseClicked
        
    }//GEN-LAST:event_PayrollTableMouseClicked

    private void emailBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailBtnMouseClicked
        try {
            // TODO add your handling code here:
            Pdf pdf = generatePdf(); 
            
            EmailSMTP email = new EmailSMTP(pdf.getEmail(), pdf.getName());
            
            JOptionPane.showMessageDialog(this,"Email Sent");


        } catch (DocumentException | URISyntaxException | IOException | MessagingException ex) {
            Logger.getLogger(updateSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_emailBtnMouseClicked

    private void emailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailBtnActionPerformed

    private Pdf generatePdf() throws DocumentException, URISyntaxException, IOException{
    
    
        ObjectId id = (ObjectId) PayrollTable.getValueAt(0, 0);
                
        Pdf pdf = new Pdf();
        pdf.setId(id);
        pdf.setName((String) PayrollTable.getValueAt(0, 1));
        pdf.setEmail((String) PayrollTable.getValueAt(0, 2));
        pdf.setSalary((String) PayrollTable.getValueAt(0, 3));
        pdf.setAllowances((String) PayrollTable.getValueAt(0, 4));
        pdf.setDeductions((String) PayrollTable.getValueAt(0, 5));
        pdf.setTotal((String) PayrollTable.getValueAt(0, 6));

        PdfService pdfService = new PdfService(pdf);
        pdfService.generatePdf();
        
        return pdf;
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTable PayrollTable;
    private javax.swing.JButton RefreshBtn;
    private javax.swing.JComboBox<String> deleteAllowance;
    private javax.swing.JComboBox<String> deleteDeduction;
    private javax.swing.JButton emailBtn;
    private javax.swing.JTable empTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton printBtn;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
