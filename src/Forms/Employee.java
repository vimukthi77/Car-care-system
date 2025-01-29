/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import Utill.RegX;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Menus.TableCustom;
import Reports.Employee_Report;
import Utill.InformTimer;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import notification.Notification;


/**
 *
 * @author Oshan
 */
public class Employee extends javax.swing.JPanel {

    Employee_Report employee_Report = new Employee_Report();
    /**
     * Creates new form Employee
     */
    public Employee() {
        initComponents();
        employee_Report.GetDataJTable();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        etb();
 
    }
    public void etb() {
        try {
            DefaultTableModel dtx = (DefaultTableModel) E_table.getModel();
            dtx.setRowCount(0);

            try (Statement etables = CarCare_DB.MyCon().createStatement(); ResultSet result = etables.executeQuery("SELECT * FROM employee")) {

                while (result.next()) {
                    Vector<Object> v2 = new Vector<>();
                    v2.add(result.getString("Employee_ID"));
                    v2.add(result.getString("Name"));
                    v2.add(result.getString("NIC"));
                    v2.add(result.getString("Address"));
                    v2.add(result.getString("Email"));
                    v2.add(result.getString("Contact_No"));

                    dtx.addRow(v2);
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
        EName = new textfield.TextField();
        ENIC = new textfield.TextField();
        EAddress = new textfield.TextField();
        EEmail = new textfield.TextField();
        Emp_Delete_btn = new Menus.Button();
        Emp_Add_btn = new Menus.Button();
        Emp_Update_btn = new Menus.Button();
        jLabel1 = new javax.swing.JLabel();
        EID_show = new javax.swing.JLabel();
        Employee_Search_Box = new Menus.custom_textfield();
        EContact = new textfield.TextField();
        Name_check = new javax.swing.JLabel();
        NIC_check = new javax.swing.JLabel();
        Address_check = new javax.swing.JLabel();
        Contact_check = new javax.swing.JLabel();
        Email_check = new javax.swing.JLabel();
        roundPanel3 = new Menus.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        E_table = new javax.swing.JTable();
        Emp_Print = new Menus.GradientButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 724));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.setPreferredSize(new java.awt.Dimension(446, 717));

        EName.setBackground(new java.awt.Color(51, 51, 51));
        EName.setForeground(new java.awt.Color(255, 255, 255));
        EName.setCaretColor(new java.awt.Color(255, 255, 255));
        EName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        EName.setLabelText("Employee Name");
        EName.setLineColor(new java.awt.Color(255, 153, 0));

        ENIC.setBackground(new java.awt.Color(51, 51, 51));
        ENIC.setForeground(new java.awt.Color(255, 255, 255));
        ENIC.setCaretColor(new java.awt.Color(255, 255, 255));
        ENIC.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        ENIC.setLabelText("NIC");
        ENIC.setLineColor(new java.awt.Color(255, 153, 0));
        ENIC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ENICFocusLost(evt);
            }
        });

        EAddress.setBackground(new java.awt.Color(51, 51, 51));
        EAddress.setForeground(new java.awt.Color(255, 255, 255));
        EAddress.setCaretColor(new java.awt.Color(255, 255, 255));
        EAddress.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        EAddress.setLabelText("Address");
        EAddress.setLineColor(new java.awt.Color(255, 153, 0));

        EEmail.setBackground(new java.awt.Color(51, 51, 51));
        EEmail.setForeground(new java.awt.Color(255, 255, 255));
        EEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        EEmail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        EEmail.setLabelText("Email");
        EEmail.setLineColor(new java.awt.Color(255, 153, 0));
        EEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EEmailFocusLost(evt);
            }
        });

        Emp_Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin.png"))); // NOI18N
        Emp_Delete_btn.setText("DELETE");
        Emp_Delete_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Emp_Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Emp_Delete_btnActionPerformed(evt);
            }
        });

        Emp_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        Emp_Add_btn.setText("ADD");
        Emp_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Emp_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Emp_Add_btnActionPerformed(evt);
            }
        });

        Emp_Update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh2.png"))); // NOI18N
        Emp_Update_btn.setText("UPDATE");
        Emp_Update_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Emp_Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Emp_Update_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee ID:");

        EID_show.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        EID_show.setForeground(new java.awt.Color(153, 255, 102));

        Employee_Search_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Employee_Search_BoxKeyReleased(evt);
            }
        });

        EContact.setBackground(new java.awt.Color(51, 51, 51));
        EContact.setForeground(new java.awt.Color(255, 255, 255));
        EContact.setCaretColor(new java.awt.Color(255, 255, 255));
        EContact.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        EContact.setLabelText("Contact No");
        EContact.setLineColor(new java.awt.Color(255, 153, 0));
        EContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EContactFocusLost(evt);
            }
        });

        Name_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        NIC_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Address_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Contact_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Email_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EContact, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(EName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundPanel2Layout.createSequentialGroup()
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                                    .addComponent(Emp_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Emp_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(ENIC, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Emp_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(roundPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Employee_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Name_check, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NIC_check, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Address_check, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact_check, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email_check, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Employee_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(EName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Name_check, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ENIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NIC_check, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Address_check, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Contact_check, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Email_check, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Emp_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emp_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emp_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setPreferredSize(new java.awt.Dimension(531, 439));

        E_table.setBackground(new java.awt.Color(51, 51, 51));
        E_table.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        E_table.setForeground(new java.awt.Color(255, 255, 255));
        E_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee_ID", "Name", "NIC", "Addres", "Email", "Contact_No"
            }
        ));
        E_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        E_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                E_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(E_table);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        Emp_Print.setForeground(new java.awt.Color(255, 255, 255));
        Emp_Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.gif"))); // NOI18N
        Emp_Print.setText("Print Report");
        Emp_Print.setEndGradientColor(new java.awt.Color(102, 204, 255));
        Emp_Print.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Emp_Print.setShadowColor(new java.awt.Color(51, 51, 51));
        Emp_Print.setStartGradientColor(new java.awt.Color(0, 102, 255));
        Emp_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Emp_PrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Emp_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Emp_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Emp_Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Emp_Update_btnActionPerformed
        // TODO add your handling code here:
        String Employee_ID = EID_show.getText();
        String Employee_Name = EName.getText();
        String Employee_NIC = ENIC.getText();
        String Employee_Address = EAddress.getText();
        String Employee_Email = EEmail.getText();
        String Employee_Contact = EContact.getText();
        
        try {

            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE employee SET Name ='"+Employee_Name+"',NIC='"+Employee_NIC+"',Address='"+Employee_Address+"',Email='"+Employee_Email+"',Contact_No='"+Employee_Contact+"' WHERE Employee_ID = '"+Employee_ID+"' ");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.UPDATE, Notification.Location.TOP_CENTER, "Employee Updated Successfully !");
            notify.showNotification();
            
            
            etb();
           
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Emp_Update_btnActionPerformed

    private void Emp_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Emp_Add_btnActionPerformed
        // TODO add your handling code here:
        String Employee_ID = EID_show.getText();
        String Employee_Name = EName.getText();
        String Employee_NIC = ENIC.getText();
        String Employee_Address = EAddress.getText();
        String Employee_Email = EEmail.getText();
        String Employee_Contact = EContact.getText();

        try {
            if (Employee_Name.isEmpty() && Employee_NIC.isEmpty() && Employee_Address.isEmpty() && Employee_Email.isEmpty() && Employee_Contact.isEmpty()) {

                Name_check.setForeground(Color.RED);
                Name_check.setText("Please Enter the Name");

                NIC_check.setForeground(Color.RED);
                NIC_check.setText("Please Enter NIC");

                Address_check.setForeground(Color.RED);
                Address_check.setText("Please Enter the Address");

                Email_check.setForeground(Color.RED);
                Email_check.setText("Please Enter Email");

                Contact_check.setForeground(Color.RED);
                Contact_check.setText("Please Enter Contact Number");

                InformTimer.startTimer(Name_check, 2000);
                InformTimer.startTimer(NIC_check, 2000);
                InformTimer.startTimer(Address_check, 2000);
                InformTimer.startTimer(Email_check, 2000);
                InformTimer.startTimer(Contact_check, 2000);

            } else if (!Employee_Name.isEmpty() && Employee_NIC.isEmpty() && Employee_Address.isEmpty() && Employee_Email.isEmpty() && Employee_Contact.isEmpty()) {
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

            } else if (!Employee_NIC.isEmpty() && Employee_Address.isEmpty() && Employee_Email.isEmpty() && Employee_Contact.isEmpty() && Employee_Name.isEmpty()) {
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

            } else if (!Employee_Address.isEmpty() && Employee_NIC.isEmpty() && Employee_Email.isEmpty() && Employee_Contact.isEmpty() && Employee_Name.isEmpty()) {
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

            } else if (!Employee_Email.isEmpty() && Employee_Contact.isEmpty() && Employee_Name.isEmpty() && Employee_Address.isEmpty() && Employee_NIC.isEmpty()) {

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

            } else if (!Employee_Contact.isEmpty() && Employee_Name.isEmpty() && Employee_Address.isEmpty() && Employee_NIC.isEmpty() && Employee_Email.isEmpty()) {

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

            } else if (Employee_Name.isEmpty()) {
                EName.setLineColor(Color.RED);
                Name_check.setForeground(Color.RED);
                Name_check.setText("Please Enter the Name");

            } else if (Employee_NIC.isEmpty()) {
                ENIC.setLineColor(Color.RED);
                NIC_check.setForeground(Color.RED);
                NIC_check.setText("Please Enter NIC");

            } else if (Employee_Address.isEmpty()) {
                EAddress.setLineColor(Color.RED);
                Address_check.setForeground(Color.RED);
                Address_check.setText("Please Enter the Address");

            } else if (Employee_Email.isEmpty()) {
                EEmail.setLineColor(Color.RED);
                Email_check.setForeground(Color.RED);
                Email_check.setText("Please Enter Email");

            } else if (Employee_Contact.isEmpty()) {
                EContact.setLineColor(Color.RED);
                Contact_check.setForeground(Color.RED);
                Contact_check.setText("Please Enter Contact Number");

            } else {
                Statement Save = CarCare_DB.MyCon().createStatement();
                Save.executeUpdate("INSERT INTO employee (Name, Address, NIC, Contact_No, Email) VALUES ('" + Employee_Name + "','" + Employee_Address + "','" + Employee_NIC + "','" + Employee_Contact + "','" + Employee_Email + "')");

                EID_show.setText(Employee_ID);
                EName.setText("");
                ENIC.setText("");
                EAddress.setText("");
                EEmail.setText("");
                EContact.setText("");

                Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Employee Added Successfully !");
                notify.showNotification();

                etb();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_Emp_Add_btnActionPerformed

    private void Emp_Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Emp_Delete_btnActionPerformed
        // TODO add your handling code here:
        String Employee_ID = EID_show.getText();
        
        try {
            
            Statement delete = CarCare_DB.MyCon().createStatement();
            delete.executeUpdate("DELETE FROM employee WHERE Employee_ID='"+Employee_ID+"' ");
            
            EID_show.setText("");
            EName.setText("");
            EAddress.setText("");
            ENIC.setText("");
            EEmail.setText("");  
            EContact.setText("");
            Employee_Search_Box.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DELETE, Notification.Location.TOP_CENTER, "Employee Deleted Successfully !");
            notify.showNotification();
            
            etb();
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Emp_Delete_btnActionPerformed

    private void Employee_Search_BoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Employee_Search_BoxKeyReleased
        // TODO add your handling code here:
        String eid = Employee_Search_Box.getText();
        try {
            DefaultTableModel dt = (DefaultTableModel) E_table.getModel();
            dt.setRowCount(0);
            Statement tables = CarCare_DB.MyCon().createStatement();
            ResultSet result;
            if (eid.isEmpty()) {
                result = tables.executeQuery("SELECT * FROM employee");
            } else {
                result = tables.executeQuery("SELECT * FROM employee WHERE Employee_ID LIKE '%" + eid + "%'");
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
                
                EID_show.setText(result.getString("Employee_ID"));
                EName.setText(result.getString("Name"));
                EAddress.setText(result.getString("Address"));
                ENIC.setText(result.getString("NIC"));
                EContact.setText(result.getString("Contact_No"));
                EEmail.setText(result.getString("Email"));
            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(E_table.getModel());
            E_table.setRowSorter(sorter);
            if (!eid.isEmpty()) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + eid)); // Case-insensitive filter
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }//GEN-LAST:event_Employee_Search_BoxKeyReleased

    private void E_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E_tableMouseClicked
        // TODO add your handling code here:
        int show = E_table.getSelectedRow();
        
        String E_id = E_table.getValueAt(show, 0).toString();
        String E_name = E_table.getValueAt(show, 1).toString();
        String E_nic = E_table.getValueAt(show, 2).toString();
        String E_address = E_table.getValueAt(show, 3).toString();
        String E_email = E_table.getValueAt(show, 4).toString();
        String E_contact = E_table.getValueAt(show, 5).toString();
        
        
        
        EID_show.setText(E_id);
        EName.setText(E_name);
        EAddress.setText(E_address);
        ENIC.setText(E_nic);
        EContact.setText(E_contact);
        EEmail.setText(E_email);
    }//GEN-LAST:event_E_tableMouseClicked

    private void Emp_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Emp_PrintActionPerformed
        // TODO add your handling code here:
        employee_Report.GetDataJTable();
        employee_Report.exportExcel(E_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Employee Report Printed Successfully !");
        notify.showNotification();

    }//GEN-LAST:event_Emp_PrintActionPerformed

    private void ENICFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ENICFocusLost
        // TODO add your handling code here:
        String Customer_NIC = ENIC.getText().trim();
       
        if (RegX.isEmployeeExists(Customer_NIC, "NIC")) {
            ENIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("NIC already exsist");
            
        } else if(Customer_NIC.isEmpty()){
            ENIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("Please Enter NIC");  
            
        } else if(RegX.validateNIC(Customer_NIC)) {
            ENIC.setLineColor(Color.GREEN);
            NIC_check.setForeground(Color.GREEN);
            NIC_check.setText("Valid NIC");
            
        }else{        
            ENIC.setLineColor(Color.RED);
            NIC_check.setForeground(Color.RED);
            NIC_check.setText("Invalid NIC");
            
        }
        InformTimer.startTimer(NIC_check, 3000);
    }//GEN-LAST:event_ENICFocusLost

    private void EContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EContactFocusLost
        // TODO add your handling code here:
        String Emp_contact= EContact.getText(); 
        
        if (RegX.isEmployeeExists(Emp_contact, "Contact_No")) {
            EContact.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Contact Number is already exsist");
        } else if(Emp_contact.isEmpty()){
            EContact.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Please Enter Contact Number");            
        } else if(RegX.validateContact(Emp_contact)) {
            EContact.setLineColor(Color.GREEN);
            Contact_check.setForeground(Color.GREEN);
            Contact_check.setText("Valid Contact Number");           
        }else{        
            EContact.setLineColor(Color.RED);
            Contact_check.setForeground(Color.RED);
            Contact_check.setText("Invalid Contact Number");
        }
        InformTimer.startTimer(Contact_check, 3000);
    }//GEN-LAST:event_EContactFocusLost

    private void EEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EEmailFocusLost
        // TODO add your handling code here:
        String Emp_email= EEmail.getText(); 
        
        if (RegX.isEmployeeExists(Emp_email, "Email")) {
            EEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Email is already exsist");
        } else if(Emp_email.isEmpty()){
            EEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Please Enter the Email");            
        } else if(RegX.validateEmail(Emp_email)) {
            EEmail.setLineColor(Color.GREEN);
            Email_check.setForeground(Color.GREEN);
            Email_check.setText("Valid Email");           
        }else{        
            EEmail.setLineColor(Color.RED);
            Email_check.setForeground(Color.RED);
            Email_check.setText("Invalid Email");
        }
        InformTimer.startTimer(Email_check, 3000);
    }//GEN-LAST:event_EEmailFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address_check;
    private javax.swing.JLabel Contact_check;
    private textfield.TextField EAddress;
    private textfield.TextField EContact;
    private textfield.TextField EEmail;
    private javax.swing.JLabel EID_show;
    private textfield.TextField ENIC;
    private textfield.TextField EName;
    private javax.swing.JTable E_table;
    private javax.swing.JLabel Email_check;
    private Menus.Button Emp_Add_btn;
    private Menus.Button Emp_Delete_btn;
    private Menus.GradientButton Emp_Print;
    private Menus.Button Emp_Update_btn;
    private Menus.custom_textfield Employee_Search_Box;
    private javax.swing.JLabel NIC_check;
    private javax.swing.JLabel Name_check;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
