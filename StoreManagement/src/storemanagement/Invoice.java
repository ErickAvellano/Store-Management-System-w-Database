/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package storemanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import javax.swing.JScrollPane;



public class Invoice extends javax.swing.JFrame {

    Connection Con = null;
    PreparedStatement pst = null;
    ResultSet Rs = null;
    Statement St = null;
    
    
    
    public Invoice() {
        initComponents();
        DisplayItems();
        Clear();
        updateTime();
        AddtoReceiptBtn.setVisible(false);
        customerId();
        
    }
    public Invoice(int branchID){
        initComponents();
        DisplayBranch();
        IDB.setText(Integer.toString(branchID));
        DisplayItems();
        Clear();
        updateTime();
        AddtoReceiptBtn.setVisible(false);
        customerId();
        
    }
    
    private int CustomerId;
    private void getID(){
        try{
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database","root","");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM customer");
            while (Rs.next()) {
    // Retrieve the data from the ResultSet
            CustomerId = Rs.getInt("Cid");
            }
        }catch(Exception ex){
            
        }
    }
    private String cashier;
    
   private void DisplayBranch() {
    try {
        Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database", "root", "");
        St = Con.createStatement();
        int branchID = Integer.parseInt(IDB.getText());
        Rs = St.executeQuery("SELECT * FROM branch WHERE Bid = " + branchID);
        while (Rs.next()) {
            // Retrieve the data from the ResultSet
            cashier = Rs.getString("Cname");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    int salesId = getSalesId();
    private void insertsales(){
        try {
            getID();
            DisplayBranch();
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database","root","");
            PreparedStatement Save = Con.prepareStatement("INSERT INTO salesinvoice (Invoiceid, Idate, Bid, Cname, Cid, Pid, Sales) VALUES (?, ?, ?, ?, ?, ?, ?)");
            Save.setInt(1, salesId);
            Save.setString(2, Date_time.getText());
            Save.setInt(3, Integer.parseInt(IDB.getText()));
            Save.setString(4, cashier);
            Save.setInt(5, CustomerId);
            Save.setInt(6, Integer.parseInt(PidTb.getText()));
            Save.setDouble(7, grdtotal);

            int row = Save.executeUpdate();
        if (row > 0) {
            JOptionPane.showMessageDialog(this, "Sales Added");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add sales");
        }
        Con.close();
    } catch (SQLException ex) {
        // Handle the SQLException and display an error message or perform any other necessary actions
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        // Handle the NumberFormatException when parsing PidTb.getText()
        ex.printStackTrace();
    }
     }
   
    
    private void minusqty(){
         try{
             int newqty = AvailQty - Integer.valueOf(PQtyTb.getText());
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database","root","");
             String UpdateQuery = "UPDATE product SET Pqty=? where Pid=?";
             PreparedStatement Ps = Con.prepareStatement(UpdateQuery);
             int pidValue = Integer.parseInt(PidTb.getText());
             Ps.setInt(1, newqty); // Use index 1 instead of 0
             Ps.setInt(2, pidValue);

                if (Ps.executeUpdate() == 1) {
                    DisplayItems();
                }
         }catch(Exception e){
             
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Branchname = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        IDB = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        PidTb = new javax.swing.JTextField();
        PnameTb = new javax.swing.JTextField();
        PQtyTb = new javax.swing.JTextField();
        PpriceTb = new javax.swing.JTextField();
        PCatCb = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        AddtoReceiptBtn = new javax.swing.JButton();
        Print = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CFname = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CLname = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Cnumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CAddress = new javax.swing.JTextField();
        ProceedBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Date_time = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ReceiptArea = new javax.swing.JTextArea();
        Total = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("B&B");

        LogoutBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        LogoutBtn.setText("Logout");
        LogoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutBtnMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel17.setText("Branch ID:");

        Branchname.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N

        IDB.setEditable(false);
        IDB.setAutoscrolls(false);
        jScrollPane2.setViewportView(IDB);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Branchname))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutBtn)
                .addGap(95, 95, 95))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(LogoutBtn)
                        .addComponent(jLabel17)
                        .addComponent(Branchname)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel3.setText("First Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel6.setText("Product Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel7.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel8.setText("Category");

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel9.setText("Price");

        PQtyTb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        PpriceTb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        PpriceTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PpriceTbActionPerformed(evt);
            }
        });

        PCatCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feeds", "Medicine", "Insecticide", "Herbicide", "Pesticide", " ", " " }));
        PCatCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PCatCbItemStateChanged(evt);
            }
        });

        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ProductTable.setRowHeight(25);
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ProductTable);

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel10.setText("Branch Stock");

        AddtoReceiptBtn.setBackground(new java.awt.Color(51, 255, 51));
        AddtoReceiptBtn.setText("Add to Receipt");
        AddtoReceiptBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddtoReceiptBtnMouseClicked(evt);
            }
        });
        AddtoReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtoReceiptBtnActionPerformed(evt);
            }
        });

        Print.setBackground(new java.awt.Color(204, 204, 204));
        Print.setText("Print");
        Print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintMouseClicked(evt);
            }
        });
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        ClearBtn.setBackground(new java.awt.Color(255, 51, 51));
        ClearBtn.setText("Clear");
        ClearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearBtnMouseClicked(evt);
            }
        });
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel11.setText("Customer");

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel12.setText("Receipt");

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel4.setText("Product ID");

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel13.setText("Last Name");

        CLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLnameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel14.setText("Address:");

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel15.setText("Phone Number");

        ProceedBtn.setBackground(new java.awt.Color(0, 102, 204));
        ProceedBtn.setText("Proceed");
        ProceedBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProceedBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProceedBtnMouseEntered(evt);
            }
        });
        ProceedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProceedBtnActionPerformed(evt);
            }
        });

        Date_time.setEditable(false);
        Date_time.setColumns(20);
        Date_time.setRows(5);
        jScrollPane4.setViewportView(Date_time);

        ReceiptArea.setEditable(false);
        ReceiptArea.setColumns(20);
        ReceiptArea.setRows(5);
        jScrollPane3.setViewportView(ReceiptArea);

        jLabel16.setText("Total:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel19.setText("Billing");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(PidTb, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(PnameTb))
                            .addComponent(jLabel6))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(PQtyTb, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(PpriceTb)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(PCatCb, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(AddtoReceiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel10)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(CFname, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addGap(74, 74, 74)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(CLname, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(CAddress)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(86, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(193, 193, 193))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(ProceedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(171, 171, 171))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(164, 164, 164))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Cnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(145, 145, 145))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(40, 40, 40)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addContainerGap())))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(jLabel4)
                    .addContainerGap(759, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(jLabel19)
                    .addContainerGap(814, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PCatCb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(PidTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PQtyTb, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(ProceedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel16))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(757, 757, 757))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnameTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PpriceTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(AddtoReceiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(137, 137, 137)
                    .addComponent(jLabel4)
                    .addContainerGap(609, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel19)
                    .addContainerGap(712, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PpriceTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PpriceTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PpriceTbActionPerformed

    int AvailQty;
    int i = 0;
    Double price, total= 0.0, grdtotal = 0.0;
    private void ProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)ProductTable.getModel();
        int Index = ProductTable.getSelectedRow();
        PidTb.setText(model.getValueAt(Index, 0).toString());
        PnameTb.setText(model.getValueAt(Index, 1).toString());
        PCatCb.setSelectedItem(model.getValueAt(Index, 2).toString());
        AvailQty = Integer.valueOf(model.getValueAt(Index, 3).toString());
        PpriceTb.setText(model.getValueAt(Index, 4).toString());

    }//GEN-LAST:event_ProductTableMouseClicked
    int Custid;
    Statement St1 = null;
    ResultSet Rs1 = null;
    private void customerId(){
        try{
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database", "root", "");
        St1 = Con.createStatement();
        Rs1 = St1.executeQuery("SELECT MAX(Cid) AS MaxCid FROM customer");
        
        if (Rs1.next()) {
            int maxCid = Rs1.getInt("MaxCid");
            if (Rs1.wasNull()) {
                Custid = 1000; // If no records found, start from 1000
            } else {
                Custid = maxCid + 1; // Start from the maximum Cid value + 1
            }
        } else {
            Custid = 1000; // Default value if no records found
        }
        }catch(Exception E){
            
        }
    }
    private int getSalesId() {
    int salesId = 1000; // Default value if no records found
    
    try {
        Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database", "root", "");
        St = Con.createStatement();
        Rs = St.executeQuery("SELECT MAX(Invoiceid) AS MaxsIn FROM salesinvoice");
        
        if (Rs.next()) {
            int maxsCid = Rs.getInt("maxsIn");
            if (!Rs.wasNull()) {
                salesId = maxsCid + 1; // Start from the maximum Invoiceid value + 1
            }
        }
    } catch (Exception ex) {
        // Handle the exception
        ex.printStackTrace();
    } 
    
    return salesId;
}
    private String getProductFromDataSource(String productId) {
    try {
        PreparedStatement save1 = Con.prepareStatement("SELECT Pname FROM product WHERE Pid = ?");
        save1.setString(1, productId);
        ResultSet rs = save1.executeQuery();
        if (rs.next()) {
            return rs.getString("Pname");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ""; // Return empty string if product name is not found or an error occurs
}

    private void AddtoReceiptBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddtoReceiptBtnMouseClicked

        if(PnameTb.getText().isEmpty() || PQtyTb.getText().isEmpty()){
             JOptionPane.showMessageDialog(this,"Missing Information");
         }else if(AvailQty < Integer.valueOf(PQtyTb.getText())){
             JOptionPane.showMessageDialog(this,"Not Enough Product");
         }
         else{//Double price, total= 0.0, grdtotal = 0.0;
          i++;
          
            grdtotal = grdtotal + (Integer.valueOf(PpriceTb.getText()) * Integer.valueOf(PQtyTb.getText()));
             if(i==1){
                ReceiptArea.setText(ReceiptArea.getText()+"\t------------------------B & B Store---------------------\n");
                ReceiptArea.setText(ReceiptArea.getText()+"Cid\tFirst\tLast\tPhone #\n");
                ReceiptArea.setText(ReceiptArea.getText()+Custid +"\t"+ CFname.getText()+"\t"+CLname.getText()+"\t"+Cnumber.getText()+"\t"+"\n");
                ReceiptArea.setText(ReceiptArea.getText()+"Address: "+ CAddress.getText()+"\n");
                ReceiptArea.setText(ReceiptArea.getText()+"InvoiceID: "+ salesId+"\n");
                ReceiptArea.setText(ReceiptArea.getText()+"\t-------------------------Invoice----------------------\n");
                ReceiptArea.setText(ReceiptArea.getText()+"\n Qty\tPID\tProduct\tPrice\n\n");
                ReceiptArea.setText(ReceiptArea.getText()+""+PQtyTb.getText()+"\t"+PidTb.getText()+"\t"+PnameTb.getText()+"\t"+PpriceTb.getText()+"\t"+Integer.valueOf(PQtyTb.getText())* Integer.valueOf(PpriceTb.getText())+"\n");
            } else {
                ReceiptArea.setText(ReceiptArea.getText()+""+PQtyTb.getText()+"\t"+PidTb.getText()+"\t"+PnameTb.getText()+"\t"+PpriceTb.getText()+"\t"+Integer.valueOf(PQtyTb.getText())* Integer.valueOf(PpriceTb.getText())+"\n");
             }
             Total.setText("Php"+grdtotal);
             minusqty();
         }
    }//GEN-LAST:event_AddtoReceiptBtnMouseClicked

    private void DisplayItems(){
        try{
        Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database","root","");
        St = Con.createStatement();
        String selectedCategory = PCatCb.getSelectedItem().toString(); // Assuming your JComboBox is named 'comboBox'
        String query = "SELECT * FROM product WHERE Pcategory = '" + selectedCategory + "'";
        Rs = St.executeQuery(query);
        ProductTable.setModel(DbUtils.resultSetToTableModel(Rs));

    }catch(Exception ex){

    }
    }
    
    private void Clear(){
        PidTb.setText("");
        PnameTb.setText("");
        PQtyTb.setText("");
        PpriceTb.setText("");
        
    }
    private void Clear1(){
        CFname.setText("");
        CLname.setText("");
        Cnumber.setText("");
        CAddress.setText("");
        
    }
    private void Clear2(){
        ReceiptArea.setText("");
    }
    private void updateTime() {
    // Get the current time
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateTime = sdf.format(now);

    // Update the JTextPane named Date_time with the current date and time
    Date_time.setText(dateTime);
    
    JScrollPane scrollPane = (JScrollPane) Date_time.getParent().getParent();
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
}
    
    private void AddtoReceiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtoReceiptBtnActionPerformed

    }//GEN-LAST:event_AddtoReceiptBtnActionPerformed
    private void PrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMouseClicked
        try{
            insertsales();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "it works");
        }
    }//GEN-LAST:event_PrintMouseClicked

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintActionPerformed

    private void ClearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearBtnMouseClicked
        PidTb.setText("");
        PnameTb.setText("");
        PpriceTb.setText("");
        PQtyTb.setText("");
        ReceiptArea.setText(""); // Clear the receipt area
        grdtotal = 0.0;
        Total.setText("");
        salesId = salesId + 1;
    
    i = 0; 
        Clear2();
    }//GEN-LAST:event_ClearBtnMouseClicked

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void PCatCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PCatCbItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
           DisplayItems();
        }  
    }//GEN-LAST:event_PCatCbItemStateChanged

    private void CLnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CLnameActionPerformed

    private void ProceedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProceedBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProceedBtnActionPerformed

    
    int customerid;
    private void ProceedBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProceedBtnMouseClicked
      if (CFname.getText().isEmpty() || CLname.getText().isEmpty() ||Cnumber.getText().isEmpty() || CAddress.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Missing Information");
        }else{
            try{
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b&b_database","root","");
                PreparedStatement Save = Con.prepareStatement("INSERT INTO customer (Cid, Fname, Lname, Cnumber, Caddress) VALUES (?, ?, ?, ?, ?)");
                PreparedStatement save1 = Con.prepareStatement("SELECT Cid FROM customer");
                Save.setInt(1, Custid);
                Save.setString(2, CFname.getText());
                Save.setString(3, CLname.getText());
                Save.setString(4, Cnumber.getText());
                Save.setString(5, CAddress.getText());
                int row; Save.executeUpdate();
                JOptionPane.showMessageDialog(this, "Customer Added");
                Con.close();
                
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    customerId();    
    AddtoReceiptBtn.setVisible(true);
      
    }//GEN-LAST:event_ProceedBtnMouseClicked

    private void LogoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutBtnMouseClicked
            Login logins = new Login();
            logins.setVisible(true);
               this.dispose();
    }//GEN-LAST:event_LogoutBtnMouseClicked

    private void ProceedBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProceedBtnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProceedBtnMouseEntered

    private void Date_timeActionPerformed(java.awt.event.ActionEvent evt) {                                          
    updateTime(); // Update the clock display immediately when the button is clicked

    // Create a timer to update the clock every second
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTime();
        }
    });
    timer.start();
} 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddtoReceiptBtn;
    private javax.swing.JLabel Branchname;
    private javax.swing.JTextField CAddress;
    private javax.swing.JTextField CFname;
    private javax.swing.JTextField CLname;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JTextField Cnumber;
    private javax.swing.JTextArea Date_time;
    private javax.swing.JTextPane IDB;
    private javax.swing.JLabel LogoutBtn;
    private javax.swing.JComboBox<String> PCatCb;
    private javax.swing.JTextField PQtyTb;
    private javax.swing.JTextField PidTb;
    private javax.swing.JTextField PnameTb;
    private javax.swing.JTextField PpriceTb;
    private javax.swing.JButton Print;
    private javax.swing.JButton ProceedBtn;
    private javax.swing.JTable ProductTable;
    private javax.swing.JTextArea ReceiptArea;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
