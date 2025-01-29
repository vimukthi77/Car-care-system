/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utill.RegX;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Menus.TableCustom;
import notification.Notification;
import Reports.Customer_Report;
import Utill.InformTimer;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;





/**
 *
 * @author Oshan
 */
public class Customer extends javax.swing.JPanel {

    Customer_Report customer_Report = new Customer_Report();
    
    
    

    public Customer() {
        initComponents();
        customer_Report.GetDataJTable();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);        
        ctb();
        
        
    }
    
    
    
    public void ctb() {
        try {
           
            DefaultTableModel dts = (DefaultTableModel) C_tabel.getModel();
            dts.setRowCount(0);

            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM customer")) {

                while (result.next()) {
                    Vector<Object> v = new Vector<>();
                    v.add(result.getString("Customer_ID"));
                    v.add(result.getString("Name"));
                    v.add(result.getString("Address"));
                    v.add(result.getString("NIC"));
                    v.add(result.getString("Contact_No"));
                    v.add(result.getString("Email"));

                    dts.addRow(v);
                }
                
            }
            
        } catch (SQLException e) {
            // Print or log the exception for debugging
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Menus.RoundPanel();
        roundPanel2 = new Menus.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        C_tabel = new javax.swing.JTable();
        roundPanel3 = new Menus.RoundPanel();
        Customer_Add_btn = new Menus.Button();
        CName = new textfield.TextField();
        CNIC = new textfield.TextField();
        CContact_No = new textfield.TextField();
        CEmail = new textfield.TextField();
        Customer_Update_btn = new Menus.Button();
        Customer_Delete_btn = new Menus.Button();
        CID_show = new javax.swing.JLabel();
        CID_show_text = new javax.swing.JLabel();
        Customer_Search_Box = new Menus.custom_textfield();
        CAddress = new textfield.TextField();
        NIC_check = new javax.swing.JLabel();
        Contact_check = new javax.swing.JLabel();
        Email_check = new javax.swing.JLabel();
        Name_check = new javax.swing.JLabel();
        Address_check = new javax.swing.JLabel();
        Print = new Menus.GradientButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 750));
        setRequestFocusEnabled(false);

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        C_tabel.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        C_tabel.setForeground(new java.awt.Color(255, 255, 255));
        C_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Customer Name", "Address", "NIC", "Contact number", "Email"
            }
        ));
        C_tabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        C_tabel.setGridColor(new java.awt.Color(102, 102, 102));
        C_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                C_tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(C_tabel);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setPreferredSize(new java.awt.Dimension(446, 717));

        Customer_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        Customer_Add_btn.setText("ADD");
        Customer_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Customer_Add_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Customer_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Customer_Add_btnActionPerformed(evt);
            }
        });

        CName.setBackground(new java.awt.Color(51, 51, 51));
        CName.setForeground(new java.awt.Color(255, 255, 255));
        CName.setCaretColor(new java.awt.Color(255, 255, 255));
        CName.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        CName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CName.setLabelText("Customer Name");
        CName.setLineColor(new java.awt.Color(255, 153, 0));

        CNIC.setBackground(new java.awt.Color(51, 51, 51));
        CNIC.setForeground(new java.awt.Color(255, 255, 255));
        CNIC.setCaretColor(new java.awt.Color(255, 255, 255));
        CNIC.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        CNIC.setDragEnabled(true);
        CNIC.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CNIC.setLabelText("NIC");
        CNIC.setLineColor(new java.awt.Color(255, 153, 0));
        CNIC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CNICFocusLost(evt);
            }
        });

        CContact_No.setBackground(new java.awt.Color(51, 51, 51));
        CContact_No.setForeground(new java.awt.Color(255, 255, 255));
        CContact_No.setCaretColor(new java.awt.Color(255, 255, 255));
        CContact_No.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        CContact_No.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CContact_No.setLabelText("Contact Number");
        CContact_No.setLineColor(new java.awt.Color(255, 153, 0));
        CContact_No.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CContact_NoFocusLost(evt);
            }
        });

        CEmail.setBackground(new java.awt.Color(51, 51, 51));
        CEmail.setForeground(new java.awt.Color(255, 255, 255));
        CEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        CEmail.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        CEmail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CEmail.setLabelText("Email");
        CEmail.setLineColor(new java.awt.Color(255, 153, 0));
        CEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CEmailFocusLost(evt);
            }
        });

        Customer_Update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh2.png"))); // NOI18N
        Customer_Update_btn.setText("UPDATE");
        Customer_Update_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Customer_Update_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Customer_Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Customer_Update_btnActionPerformed(evt);
            }
        });

        Customer_Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin.png"))); // NOI18N
        Customer_Delete_btn.setText("DELETE");
        Customer_Delete_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Customer_Delete_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Customer_Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Customer_Delete_btnActionPerformed(evt);
            }
        });

        CID_show.setBackground(new java.awt.Color(51, 51, 51));
        CID_show.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        CID_show.setForeground(new java.awt.Color(153, 255, 102));

        CID_show_text.setBackground(new java.awt.Color(51, 51, 51));
        CID_show_text.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        CID_show_text.setForeground(new java.awt.Color(255, 255, 255));
        CID_show_text.setText("Customer ID:");

        Customer_Search_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Customer_Search_BoxKeyReleased(evt);
            }
        });

        CAddress.setBackground(new java.awt.Color(51, 51, 51));
        CAddress.setForeground(new java.awt.Color(255, 255, 255));
        CAddress.setCaretColor(new java.awt.Color(255, 255, 255));
        CAddress.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        CAddress.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CAddress.setLabelText("Address");
        CAddress.setLineColor(new java.awt.Color(255, 153, 0));

        NIC_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Contact_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Email_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Name_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Address_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(CID_show_text)
                        .addGap(18, 18, 18)
                        .addComponent(CID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Customer_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel3Layout.createSequentialGroup()
                                .addComponent(Customer_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Customer_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Customer_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NIC_check, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email_check, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact_check, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Name_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CNIC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Address_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CContact_No, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(Customer_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CID_show_text, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Name_check, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NIC_check, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Address_check, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CContact_No, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Contact_check, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Email_check, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Customer_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Customer_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Customer_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        Print.setForeground(new java.awt.Color(255, 255, 255));
        Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.gif"))); // NOI18N
        Print.setText("Print Report");
        Print.setEndGradientColor(new java.awt.Color(102, 204, 255));
        Print.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Print.setShadowColor(new java.awt.Color(51, 51, 51));
        Print.setStartGradientColor(new java.awt.Color(0, 102, 255));
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 
    private void Customer_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_Add_btnActionPerformed
        // TODO add your handling code here:
        String Customer_ID = CID_show.getText();
        String Customer_Name = CName.getText();
        String Customer_NIC = CNIC.getText();
        String Customer_Address = CAddress.getText();
        String Customer_Email = CEmail.getText();
        String Customer_Contact = CContact_No.getText();

        try {

            if (Customer_Name.isEmpty() && Customer_NIC.isEmpty() && Customer_Address.isEmpty() && Customer_Email.isEmpty() && Customer_Contact.isEmpty()) {

                CName.setLineColor(Color.RED);
                Name_check.setForeground(Color.RED);
                Name_check.setText("Please Enter the Name");

                CNIC.setLineColor(Color.RED);
                NIC_check.setForeground(Color.RED);
                NIC_check.setText("Please Enter NIC");

                CAddress.setLineColor(Color.RED);
                Address_check.setForeground(Color.RED);
                Address_check.setText("Please Enter the Address");

                CEmail.setLineColor(Color.RED);
                Email_check.setForeground(Color.RED);
                Email_check.setText("Please Enter Email");

                CContact_No.setLineColor(Color.RED);
                Contact_check.setForeground(Color.RED);
                Contact_check.setText("Please Enter Contact Number");

                InformTimer.startTimer(Name_check, 2000);
                InformTimer.startTimer(NIC_check, 2000);
                InformTimer.startTimer(Address_check, 2000);
                InformTimer.startTimer(Email_check, 2000);
                InformTimer.startTimer(Contact_check, 2000);
                
            } else if (!Customer_Name.isEmpty() && Customer_NIC.isEmpty() && Customer_Address.isEmpty() && Customer_Email.isEmpty() && Customer_Contact.isEmpty()) {
                
                NIC_check.setForeground(Color.red);
                NIC_check.setText("Please Enter NIC");
                Address_check.setForeground(Color.red);
                Address_check.setText("Please Enter Address");
                Contact_check.setForeground(Color.red);
                Contact_check.setText("Please Enter Contact Number ");
                Email_check.setForeground(Color.red);
                Email_check.setText("Please Enter Email");

                InformTimer.startTimer(NIC_check, 2000);
                InformTimer.startTimer(Address_check, 2000);
                InformTimer.startTimer(Contact_check, 2000);
                InformTimer.startTimer(Email_check, 2000);

            } else if (!Customer_NIC.isEmpty() && Customer_Address.isEmpty() && Customer_Email.isEmpty() && Customer_Contact.isEmpty() && Customer_Name.isEmpty()) {
                Name_check.setForeground(Color.red);
                Name_check.setText("Please Enter Name");
                Address_check.setForeground(Color.red);
                Address_check.setText("Please Enter Address");
                Contact_check.setForeground(Color.red);
                Contact_check.setText("Please Enter Contact Number ");
                Email_check.setForeground(Color.red);
                Email_check.setText("Please Enter Email");

                InformTimer.startTimer(Name_check, 2000);
                InformTimer.startTimer(Address_check, 2000);
                InformTimer.startTimer(Contact_check, 2000);
                InformTimer.startTimer(Email_check, 2000);

            } else if (!Customer_Address.isEmpty() && Customer_NIC.isEmpty() && Customer_Email.isEmpty() && Customer_Contact.isEmpty() && Customer_Name.isEmpty()) {
                Name_check.setForeground(Color.red);
                Name_check.setText("Please Enter Name");
                NIC_check.setForeground(Color.red);
                NIC_check.setText("Please Enter NIC");
                Contact_check.setForeground(Color.red);
                Contact_check.setText("Please Enter Contact Number ");
                Email_check.setForeground(Color.red);
                Email_check.setText("Please Enter Email");
                InformTimer.startTimer(Name_check, 3000);
                InformTimer.startTimer(NIC_check, 3000);
                InformTimer.startTimer(Contact_check, 3000);
                InformTimer.startTimer(Email_check, 3000);

            } else if (!Customer_Email.isEmpty() && Customer_Contact.isEmpty() && Customer_Name.isEmpty() && Customer_Address.isEmpty() && Customer_NIC.isEmpty()) {

                Name_check.setForeground(Color.red);
                Name_check.setText("Please Enter Name");
                NIC_check.setForeground(Color.red);
                NIC_check.setText("Please Enter NIC");
                Address_check.setForeground(Color.red);
                Address_check.setText("Please Enter Address");
                Contact_check.setForeground(Color.red);
                Contact_check.setText("Please Enter Contact Number");
                InformTimer.startTimer(Name_check, 3000);
                InformTimer.startTimer(NIC_check, 3000);
                InformTimer.startTimer(Address_check, 3000);
                InformTimer.startTimer(Contact_check, 3000);

            } else if (!Customer_Contact.isEmpty() && Customer_Name.isEmpty() && Customer_Address.isEmpty() && Customer_NIC.isEmpty() && Customer_Email.isEmpty()) {

                Name_check.setForeground(Color.red);
                Name_check.setText("Please Enter Name");
                NIC_check.setForeground(Color.red);
                NIC_check.setText("Please Enter NIC");
                Address_check.setForeground(Color.red);
                Address_check.setText("Please Enter Address");
                Email_check.setForeground(Color.red);
                Email_check.setText("Please Enter Email");
                InformTimer.startTimer(Name_check, 3000);
                InformTimer.startTimer(NIC_check, 3000);
                InformTimer.startTimer(Address_check, 3000);
                InformTimer.startTimer(Email_check, 3000);

            } else if (Customer_Name.isEmpty()) {
                CName.setLineColor(Color.RED);
                Name_check.setForeground(Color.RED);
                Name_check.setText("Please Enter the Name");

            } else if (Customer_NIC.isEmpty()) {
                CNIC.setLineColor(Color.RED);
                NIC_check.setForeground(Color.RED);
                NIC_check.setText("Please Enter NIC");

            } else if (Customer_Address.isEmpty()) {
                CAddress.setLineColor(Color.RED);
                Address_check.setForeground(Color.RED);
                Address_check.setText("Please Enter the Address");

            } else if (Customer_Email.isEmpty()) {
                CEmail.setLineColor(Color.RED);
                Email_check.setForeground(Color.RED);
                Email_check.setText("Please Enter Email");

            } else if (Customer_Contact.isEmpty()) {
                CContact_No.setLineColor(Color.RED);
                Contact_check.setForeground(Color.RED);
                Contact_check.setText("Please Enter Contact Number");

            } else {

                Statement Save = CarCare_DB.MyCon().createStatement();
                Save.executeUpdate("INSERT INTO customer (Name, Address, NIC, Contact_No, Email) VALUES ('" + Customer_Name + "','" + Customer_Address + "','" + Customer_NIC + "','" + Customer_Contact + "','" + Customer_Email + "')");

                CID_show.setText(Customer_ID);
                CName.setText("");
                CNIC.setText("");
                CAddress.setText("");
                CEmail.setText("");
                CContact_No.setText("");

                NIC_check.setText("");
                Contact_check.setText("");
                Email_check.setText("");

                Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Customer Added Successfully !");
                notify.showNotification();

                ctb();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
     
    }//GEN-LAST:event_Customer_Add_btnActionPerformed


    private void Customer_Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_Update_btnActionPerformed
        // TODO add your handling code here:
        String Customer_ID = CID_show.getText();
        String Customer_Name = CName.getText();
        String Customer_NIC = CNIC.getText();
        String Customer_Address = CAddress.getText();
        String Customer_Email = CEmail.getText();
        String Customer_Contact = CContact_No.getText();
        
        try {
            
            //Statement  s = Carcare.MyCon().createStatement();
            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE customer SET Name ='"+Customer_Name+"',Address='"+Customer_Address+"',NIC='"+Customer_NIC+"',Contact_No='"+Customer_Contact+"',Email='"+Customer_Email+"' WHERE Customer_ID = '"+Customer_ID+"'  ");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.UPDATE, Notification.Location.TOP_CENTER, "Customer Updated Successfully !");
            notify.showNotification();
            
            ctb();
                      
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Customer_Update_btnActionPerformed

    private void Customer_Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_Delete_btnActionPerformed
        // TODO add your handling code here:
        String Customer_ID = CID_show.getText();
       
        try {
            
            Statement delete = CarCare_DB.MyCon().createStatement();
            delete.executeUpdate("DELETE FROM customer WHERE Customer_ID='"+Customer_ID+"' ");
            
            CID_show.setText("");
            CName.setText("");
            CAddress.setText("");
            CNIC.setText("");
            CEmail.setText("");  
            CContact_No.setText("");
            Customer_Search_Box.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DELETE, Notification.Location.TOP_CENTER, "Customer Deleted Successfully !");
            notify.showNotification();
            
            ctb();
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Customer_Delete_btnActionPerformed

    private void Customer_Search_BoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Customer_Search_BoxKeyReleased
        // TODO add your handling code here:
        String cid = Customer_Search_Box.getText();
        try {
            DefaultTableModel dt = (DefaultTableModel) C_tabel.getModel();
            dt.setRowCount(0);
            Statement tables = CarCare_DB.MyCon().createStatement();
            ResultSet result;
            if (cid.isEmpty()) {
                result = tables.executeQuery("SELECT * FROM customer");
            } else {
                result = tables.executeQuery("SELECT * FROM customer WHERE Customer_ID LIKE '%" + cid + "%'");
            }
            while (result.next()) {
                Vector v = new Vector();
                v.add(result.getString((1)));
                v.add(result.getString((2))); 
                v.add(result.getString((3)));
                v.add(result.getString((4)));
                v.add(result.getString((5)));
                v.add(result.getString((6)));
                dt.addRow(v);
                
                CID_show.setText(result.getString("Customer_ID"));
                CName.setText(result.getString("Name"));
                CAddress.setText(result.getString("Address"));
                CNIC.setText(result.getString("NIC"));
                CContact_No.setText(result.getString("Contact_No"));
                CEmail.setText(result.getString("Email"));
                
                
            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(C_tabel.getModel());
            C_tabel.setRowSorter(sorter);
            if (!cid.isEmpty()) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + cid)); // Case-insensitive filter
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }//GEN-LAST:event_Customer_Search_BoxKeyReleased
   
    private void C_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C_tabelMouseClicked
        // TODO add your handling code here:
        int show = C_tabel.getSelectedRow();
        
        String C_id = C_tabel.getValueAt(show, 0).toString();
        String C_name = C_tabel.getValueAt(show, 1).toString();
        String C_address = C_tabel.getValueAt(show, 2).toString();
        String C_nic = C_tabel.getValueAt(show, 3).toString();
        String C_contact = C_tabel.getValueAt(show, 4).toString();
        String C_email = C_tabel.getValueAt(show, 5).toString();
        
        CID_show.setText(C_id);
        CName.setText(C_name);
        CAddress.setText(C_address);
        CNIC.setText(C_nic);
        CContact_No.setText(C_contact);
        CEmail.setText(C_email);
    }//GEN-LAST:event_C_tabelMouseClicked

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
        customer_Report.GetDataJTable();
        customer_Report.exportExcel(C_tabel);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Customer Report Printed Successfully !");
        notify.showNotification();
        
    }//GEN-LAST:event_PrintActionPerformed

    private void CNICFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CNICFocusLost
        // TODO add your handling code here:
        String Customer_NIC = CNIC.getText().trim();
       
        if (RegX.isCustomerExists(Customer_NIC, "NIC")) {
            CNIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("NIC already exsist");
            
        } else if(Customer_NIC.isEmpty()){
            CNIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("Please Enter NIC");  
            
        } else if(RegX.validateNIC(Customer_NIC)) {
            CNIC.setLineColor(Color.GREEN);
            NIC_check.setForeground(Color.GREEN);
            NIC_check.setText("Valid NIC");
            
        }else{        
            CNIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("Invalid NIC");
            
        }
        InformTimer.startTimer(NIC_check, 3000);
    }//GEN-LAST:event_CNICFocusLost

    private void CContact_NoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CContact_NoFocusLost
        // TODO add your handling code here:
        String Customer_contact= CContact_No.getText(); 
        
        if (RegX.isCustomerExists(Customer_contact, "Contact_No")) {
            CContact_No.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Contact Number is already exsist");
        } else if(Customer_contact.isEmpty()){
            CContact_No.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Please Enter Contact Number");            
        } else if(RegX.validateContact(Customer_contact)) {
            CContact_No.setLineColor(Color.GREEN);
            Contact_check.setForeground(Color.GREEN);
            Contact_check.setText("Valid Contact Number");           
        }else{        
            CContact_No.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Invalid Contact Number");
        }
        InformTimer.startTimer(Contact_check, 3000);
    }//GEN-LAST:event_CContact_NoFocusLost

    private void CEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CEmailFocusLost
        // TODO add your handling code here:
        String Customer_email= CEmail.getText(); 
        
        if (RegX.isCustomerExists(Customer_email, "Email")) {
            CEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Email is already exsist");
        } else if(Customer_email.isEmpty()){
            CEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Please Enter the Email");            
        } else if(RegX.validateEmail(Customer_email)) {
            CEmail.setLineColor(Color.GREEN);
            Email_check.setForeground(Color.GREEN);
            Email_check.setText("Valid Email");           
        }else{        
            CEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Invalid Email");
        }
        InformTimer.startTimer(Email_check, 3000);        
    }//GEN-LAST:event_CEmailFocusLost
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address_check;
    private textfield.TextField CAddress;
    private textfield.TextField CContact_No;
    private textfield.TextField CEmail;
    private javax.swing.JLabel CID_show;
    private javax.swing.JLabel CID_show_text;
    private textfield.TextField CNIC;
    private textfield.TextField CName;
    private javax.swing.JTable C_tabel;
    private javax.swing.JLabel Contact_check;
    private Menus.Button Customer_Add_btn;
    private Menus.Button Customer_Delete_btn;
    private Menus.custom_textfield Customer_Search_Box;
    private Menus.Button Customer_Update_btn;
    private javax.swing.JLabel Email_check;
    private javax.swing.JLabel NIC_check;
    private javax.swing.JLabel Name_check;
    private Menus.GradientButton Print;
    private javax.swing.JScrollPane jScrollPane1;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
