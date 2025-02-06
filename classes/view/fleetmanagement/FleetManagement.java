/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.fleetmanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.CRUDOperations;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.AirplaneTrip;
import org.bson.Document;
import org.bson.types.ObjectId;
import service.AirplaneTripService;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.AfterFlight;
import model.Airplane;
import model.EngineOrder;
import service.EngineOrderService;
import util.ActiveUser;

/**
 *
 * @author hetpatel
 */
public class FleetManagement extends javax.swing.JFrame {

    /**
     * Creates new form FleetManagement
     */
    ObjectId id;
    List<AirplaneTrip> tripsList;
    String lastLocation = "";
    MongoDatabase database;
    CRUDOperations crud = new CRUDOperations();
    private final AirplaneTripService airplaneTripService;
    private final EngineOrderService engineOrderService;

    public FleetManagement(MongoDatabase database, ObjectId id) {

        initComponents();
        this.database = database;
        this.id = id;
        this.airplaneTripService = new AirplaneTripService();
        this.engineOrderService = new EngineOrderService();
//        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        fillTable();
        if (tripsList != null && !tripsList.isEmpty()) {
            fillLastTripTable();
        } else {
            dataMessage.setText("No Data to show");
        }

    }

    public MongoCollection<Document> getAirplane() {

        return crud.getCollection("airplanes", database);
    }

    public MongoCollection<Document> getTrips() {

        return crud.getCollection("trips", database);
    }

    public void fillTable() {

        tripsList = this.airplaneTripService.fetchPlaneTripsByID(this.id);
        
        if (tripsList != null) {
            String[] columnNames = {"From", "To", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            ;
            };
            //filtering Upcoming trips
           
            List<AirplaneTrip> trip = tripsList.stream().sorted((e1, e2) -> e2.getDepartureDate().compareTo(e1.getDepartureDate()))
                    .filter((e1) -> {
                        Calendar c1 = Calendar.getInstance();
                        c1.setTime(e1.getDepartureDate());
                        Calendar c2 = Calendar.getInstance();
                        c2.setTime(new Date());

                        System.out.println("c1.before(c2): " + (c1.after(c2) || c1.equals(c2)));
                        return (c1.after(c2) || c1.equals(c2));
                    }).collect(Collectors.toList());
            System.out.println("view.fleetmanagement.FleetManagement.fillTable()"+ trip);
            
            SimpleDateFormat formator = new SimpleDateFormat("MM/dd/yyyy");
            trip.stream().forEach(e -> model.addRow(new Object[]{e.getFrom(), e.getTo(), formator.format(e.getDepartureDate())}));
            tripsListTable.setModel(model);

            //setting passtrips trips
            trip = tripsList.stream().sorted((e1, e2) -> e2.getDepartureDate().compareTo(e1.getDepartureDate()))
                    .filter((e1) -> {
                        Calendar c1 = Calendar.getInstance();
                        c1.setTime(e1.getDepartureDate());
                        Calendar c2 = Calendar.getInstance();
                        c2.setTime(new Date());
                        System.out.println("c1.before(c2): " + c1.before(c2));
                        return c1.before(c2);
                    }).collect(Collectors.toList());
            DefaultTableModel upcomingModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            ;
            };
          
            trip.stream().forEach(e -> upcomingModel.addRow(new Object[]{e.getFrom(), e.getTo(), formator.format(e.getDepartureDate())}));
            this.pastTripsListTable.setModel(upcomingModel);
        }
    }

    public void fillLastTripTable() {

        if (tripsList != null && !tripsList.isEmpty()) {
            String[] columnNames = {"", ""};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            ;
            };
            
            SimpleDateFormat formator = new SimpleDateFormat("MM/dd/yyyy");
            AirplaneTrip trip = tripsList.stream().sorted((e1, e2) -> e2.getDepartureDate().compareTo(e1.getDepartureDate())).findFirst().orElse(null);
            model.addRow(new Object[]{"From: ", trip.getFrom()});
            model.addRow(new Object[]{"To: ", trip.getTo()});
            model.addRow(new Object[]{"Travel Time: ", trip.getTraveTime()});
            model.addRow(new Object[]{"Departure Date: ", formator.format(trip.getDepartureDate())});
            model.addRow(new Object[]{"Arrival Date: ", formator.format(trip.getArrivalDate())});
            model.addRow(new Object[]{"Departure Time: ", trip.getDepartureTime()});
            model.addRow(new Object[]{"Arrival Time: ", trip.getArrivalTime()});
            if (trip.getLayover() != null && trip.getLayover().equals("None")) {
                model.addRow(new Object[]{"Layover: ", trip.getLayover()});
            }

            int totalPassengers = trip.getEconomy() + trip.getPremiumEconomy() + trip.getBusiness();

            model.addRow(new Object[]{"Total Passengers: ", totalPassengers});

            lastLocation = trip.getTo();
            lastTripTable.setModel(model);

            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            TableModel tableModel = lastTripTable.getModel();

            for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                lastTripTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
            }

        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tripsListTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        lastTripTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        pastTripsListTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        deleteFlight = new javax.swing.JButton();
        addTripBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        engineHealthBtn = new javax.swing.JButton();
        repairBtn = new javax.swing.JButton();
        dataMessage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        tripsListTable.setCellSelectionEnabled(true);
        tripsListTable.setRowHeight(50);
        jScrollPane1.setViewportView(tripsListTable);
        tripsListTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lastTripTable.setRowHeight(50);
        jScrollPane3.setViewportView(lastTripTable);

        pastTripsListTable.setCellSelectionEnabled(true);
        pastTripsListTable.setRowHeight(50);
        pastTripsListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pastTripsListTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(pastTripsListTable);
        pastTripsListTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upcoming Trips");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Last Trip");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Past Trip");

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jButton1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        deleteFlight.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        deleteFlight.setText("Delete");
        deleteFlight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteFlightMouseClicked(evt);
            }
        });

        addTripBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        addTripBtn.setText("Add Trip");
        addTripBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTripBtnMouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jButton2.setText("Show Location");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        engineHealthBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        engineHealthBtn.setText("Engine Health");
        engineHealthBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                engineHealthBtnMouseClicked(evt);
            }
        });

        repairBtn.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        repairBtn.setText("Repair");
        repairBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repairBtnMouseClicked(evt);
            }
        });

        dataMessage.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        dataMessage.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dataMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(repairBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(engineHealthBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addTripBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteFlight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dataMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTripBtn)
                .addGap(37, 37, 37)
                .addComponent(engineHealthBtn)
                .addGap(35, 35, 35)
                .addComponent(repairBtn)
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addGap(37, 37, 37)
                .addComponent(deleteFlight)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
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
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/airplaneLanding.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(179, 179, 179)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTripBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTripBtnMouseClicked
        // TODO add your handling code here:

        AddTrip addtrip = new AddTrip(getAirplane(), this.id, getTrips(), this);
        addtrip.setDefaultCloseOperation(1);
    }//GEN-LAST:event_addTripBtnMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:

        LocationDisplay location = new LocationDisplay(this.lastLocation);
        location.setDefaultCloseOperation(1);
    }//GEN-LAST:event_jButton2MouseClicked

    private void deleteFlightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFlightMouseClicked
        // TODO add your handling code here:

        int input = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Be honest...",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // 0=ok, 2=cancel
        System.out.println(input);
        if (input == 0) {
            this.airplaneTripService.deleteTripAndPlane(id);

        }

    }//GEN-LAST:event_deleteFlightMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        fillTable();
        fillLastTripTable();
    }//GEN-LAST:event_jButton1MouseClicked

    private void repairBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repairBtnMouseClicked
        // TODO add your handling code here:
        RepairRequestFrame frame = new RepairRequestFrame(this.id);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(1);
    }//GEN-LAST:event_repairBtnMouseClicked

    private void engineHealthBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_engineHealthBtnMouseClicked
        // TODO add your handling code here:
        AirplaneTrip trip = tripsList.stream().skip(tripsList.size() - 1).sorted((e1, e2) -> e2.getDepartureDate().compareTo(e1.getDepartureDate())).findFirst().orElse(null);
        AfterFlight afterFlight = trip.getAfterFlight();
        System.out.println("AFTER FLIGHT "+ afterFlight.getEngineCondition());
        if (afterFlight == null || afterFlight.getEngineCondition() == null) {
            JOptionPane.showMessageDialog(null, "No engine present for the last upcoming flight");
            return;
        }
        try {
            String condition = afterFlight.getEngineCondition();
             System.out.println("AFTER FLIGHT "+ afterFlight.getEngineCondition());
            
            if (condition.matches("Good")) {
                String options[] = new String[]{"Proceed", "Ignore"};
                int input = JOptionPane.showOptionDialog(null, "Engine performance is " + condition + "%. Do you want request a new engine", "Engine Health",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (input == 0) {
                    //create a request
                    EngineOrder order = new EngineOrder(ActiveUser.getActiveUser().getId(), LocalDateTime.now(), "Requested", this.id);
                    Boolean success = this.engineOrderService.createEnginerOrderRequest(order);
                    if (success) {
                        //success inserted the record
                        JOptionPane.showMessageDialog(null, "New Engine Order successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to raise an new engine");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to raise an new engine");
        }

        //check the last data trip health
    }//GEN-LAST:event_engineHealthBtnMouseClicked

    private void pastTripsListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pastTripsListTableMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint()) + 1;
        AirplaneTrip tt = tripsList.get(source.getSelectedRow());
        PlaneTripDetails1 pd = new PlaneTripDetails1(tt.getDbDocument(), getAirplane(), this.id);
        pd.setDefaultCloseOperation(1);
    }//GEN-LAST:event_pastTripsListTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTripBtn;
    private javax.swing.JLabel dataMessage;
    private javax.swing.JButton deleteFlight;
    private javax.swing.JButton engineHealthBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable lastTripTable;
    private javax.swing.JTable pastTripsListTable;
    private javax.swing.JButton repairBtn;
    private javax.swing.JTable tripsListTable;
    // End of variables declaration//GEN-END:variables

}
