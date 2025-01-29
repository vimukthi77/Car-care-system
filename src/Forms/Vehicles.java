/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.ResultSet;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Menus.TableCustom;
import Reports.Supplier_Report;
import Reports.Vehical_Report;
import Utill.InformTimer;
import Utill.RegX;
import static Utill.RegX.isVehicleExists;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import notification.Notification;

/**
 *
 * @author Oshan
 */
public class Vehicles extends javax.swing.JPanel {

    Vehical_Report vehical_Report = new Vehical_Report();
    /**
     * Creates new form Vehicles
     */
    public Vehicles() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        vtb();
    }
    public void vtb() {
        try {
            DefaultTableModel vts = (DefaultTableModel) V_table.getModel();
            vts.setRowCount(0);
            try (Statement tables = CarCare_DB.MyCon().createStatement();
                    ResultSet result = tables.executeQuery("SELECT * FROM vehicles")) {
                while (result.next()) {
                    Vector<Object> v = new Vector<>();
                    v.add(result.getString("Vehicle_ID"));
                    v.add(result.getString("Vehicle_Manufacturer"));
                    v.add(result.getString("Model"));
                    v.add(result.getString("Year"));
 
                    vts.addRow(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Menus.RoundPanel();
        roundPanel2 = new Menus.RoundPanel();
        V_Manufactuer_name = new textfield.TextField();
        V_Model = new textfield.TextField();
        Vehicle_Add_btn = new Menus.Button();
        Vehicle_Update_btn = new Menus.Button();
        Vehicle_Delete_btn = new Menus.Button();
        V_year = new textfield.TextField();
        VID_text = new javax.swing.JLabel();
        VID_show = new javax.swing.JLabel();
        Vehicle_Search_Box = new Menus.custom_textfield();
        VM_name = new javax.swing.JLabel();
        VModel_name = new javax.swing.JLabel();
        Year = new javax.swing.JLabel();
        roundPanel3 = new Menus.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        V_table = new javax.swing.JTable();
        vehical_print = new Menus.GradientButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.setPreferredSize(new java.awt.Dimension(446, 717));

        V_Manufactuer_name.setBackground(new java.awt.Color(51, 51, 51));
        V_Manufactuer_name.setForeground(new java.awt.Color(255, 255, 255));
        V_Manufactuer_name.setCaretColor(new java.awt.Color(255, 255, 255));
        V_Manufactuer_name.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        V_Manufactuer_name.setLabelText("Vehical Manufacter Name");
        V_Manufactuer_name.setLineColor(new java.awt.Color(255, 153, 0));
        V_Manufactuer_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                V_Manufactuer_nameFocusLost(evt);
            }
        });

        V_Model.setBackground(new java.awt.Color(51, 51, 51));
        V_Model.setForeground(new java.awt.Color(255, 255, 255));
        V_Model.setCaretColor(new java.awt.Color(255, 255, 255));
        V_Model.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        V_Model.setLabelText("Vehical Model Name");
        V_Model.setLineColor(new java.awt.Color(255, 153, 0));
        V_Model.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                V_ModelFocusLost(evt);
            }
        });

        Vehicle_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        Vehicle_Add_btn.setText("ADD");
        Vehicle_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Vehicle_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vehicle_Add_btnActionPerformed(evt);
            }
        });

        Vehicle_Update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh2.png"))); // NOI18N
        Vehicle_Update_btn.setText("UPDATE");
        Vehicle_Update_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Vehicle_Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vehicle_Update_btnActionPerformed(evt);
            }
        });

        Vehicle_Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin.png"))); // NOI18N
        Vehicle_Delete_btn.setText("DELETE");
        Vehicle_Delete_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Vehicle_Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vehicle_Delete_btnActionPerformed(evt);
            }
        });

        V_year.setBackground(new java.awt.Color(51, 51, 51));
        V_year.setForeground(new java.awt.Color(255, 255, 255));
        V_year.setCaretColor(new java.awt.Color(255, 255, 255));
        V_year.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        V_year.setLabelText("Year");
        V_year.setLineColor(new java.awt.Color(255, 153, 0));
        V_year.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                V_yearFocusLost(evt);
            }
        });

        VID_text.setBackground(new java.awt.Color(51, 51, 51));
        VID_text.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        VID_text.setForeground(new java.awt.Color(255, 255, 255));
        VID_text.setText("Vehicle ID:");

        VID_show.setBackground(new java.awt.Color(51, 51, 51));
        VID_show.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        VID_show.setForeground(new java.awt.Color(153, 255, 102));

        Vehicle_Search_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Vehicle_Search_BoxKeyReleased(evt);
            }
        });

        VM_name.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        VModel_name.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Year.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Vehicle_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(VID_text)
                        .addGap(18, 18, 18)
                        .addComponent(VID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(V_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(Vehicle_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Vehicle_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Vehicle_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(V_year, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(VM_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(V_Manufactuer_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                    .addComponent(VModel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Vehicle_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VID_text, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(V_Manufactuer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(VM_name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(V_Model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(VModel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(V_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Vehicle_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vehicle_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vehicle_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));

        V_table.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        V_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vehicle_ID", "Vehicle Manufacter", "Model Name", "Year"
            }
        ));
        V_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        V_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                V_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(V_table);

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
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vehical_print.setForeground(new java.awt.Color(255, 255, 255));
        vehical_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.gif"))); // NOI18N
        vehical_print.setText("Print Report");
        vehical_print.setEndGradientColor(new java.awt.Color(102, 204, 255));
        vehical_print.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        vehical_print.setShadowColor(new java.awt.Color(51, 51, 51));
        vehical_print.setStartGradientColor(new java.awt.Color(0, 102, 255));
        vehical_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehical_printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(vehical_print, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(vehical_print, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Vehicle_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vehicle_Add_btnActionPerformed
        // TODO add your handling code here:
        String Vehicle_Manufactuer_name = V_Manufactuer_name.getText();
        String Vehicle_Model = V_Model.getText();
        String Vehical_Year = V_year.getText();

        try {

            if (Vehicle_Manufactuer_name.isEmpty() && Vehicle_Model.isEmpty() && Vehical_Year.isEmpty()) {

                VM_name.setForeground(Color.red);
                VM_name.setText("Enter a Vehicle Manufaturer");

                VModel_name.setForeground(Color.red);
                VModel_name.setText("Enter a Vehicle Model");

                Year.setForeground(Color.red);
                Year.setText("Enter Vehicle Model Year");

                InformTimer.startTimer(VM_name, 2000);
                InformTimer.startTimer(VModel_name, 2000);
                InformTimer.startTimer(Year, 2000);
                
                    
            } else if (!Vehicle_Manufactuer_name.isEmpty() && (Vehicle_Model.isEmpty() && Vehical_Year.isEmpty())) {
                VModel_name.setForeground(Color.red);
                VModel_name.setText("Enter a Vehicle Model");
                InformTimer.startTimer(VModel_name, 2000);

                Year.setForeground(Color.red);
                Year.setText("Enter Vehicle Model Year");
                InformTimer.startTimer(Year, 2000);
                
            } else if (!Vehicle_Model.isEmpty() && (Vehical_Year.isEmpty() && Vehicle_Manufactuer_name.isEmpty())) {
                Year.setForeground(Color.red);
                Year.setText("Enter Vehicle Model Year");
                InformTimer.startTimer(Year, 2000);
                
                VM_name.setForeground(Color.red);
                VM_name.setText("Enter a Vehicle Manufaturer");
                InformTimer.startTimer(VM_name, 2000);
                
            }else if(!Vehical_Year.isEmpty() && (Vehicle_Model.isEmpty() && Vehicle_Manufactuer_name.isEmpty())){
                VModel_name.setForeground(Color.red);
                VModel_name.setText("Enter a Vehicle Model");
                InformTimer.startTimer(VModel_name, 2000);
                
                VM_name.setForeground(Color.red);
                VM_name.setText("Enter a Vehicle Manufaturer");
                InformTimer.startTimer(VM_name, 2000);
                
            } else if (Vehicle_Manufactuer_name.isEmpty()) {
                VM_name.setForeground(Color.red);
                VM_name.setText("Enter a Vehicle Manufaturer");
                InformTimer.startTimer(VM_name, 2000);

            } else if (Vehicle_Model.isEmpty()) {
                VModel_name.setForeground(Color.red);
                VModel_name.setText("Enter a Vehicle Model");
                InformTimer.startTimer(VModel_name, 2000);

            } else if (Vehical_Year.isEmpty()) {
                Year.setForeground(Color.red);
                Year.setText("Enter Vehicle Model Year");
                InformTimer.startTimer(Year, 2000);

            } else {

                Statement Save = CarCare_DB.MyCon().createStatement();
                Save.executeUpdate(" INSERT INTO vehicles (Vehicle_Manufacturer, Model,Year) VALUES ('" + Vehicle_Manufactuer_name + "','" + Vehicle_Model + "','" + Vehical_Year + "') ");

                V_Manufactuer_name.setText("");
                V_Model.setText("");
                V_year.setText("");

                Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Vehical Added Successfully !");
                notify.showNotification();

                vtb();
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
    }//GEN-LAST:event_Vehicle_Add_btnActionPerformed

    private void Vehicle_Search_BoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Vehicle_Search_BoxKeyReleased
        // TODO add your handling code here:
        String vid = Vehicle_Search_Box.getText();
        try {
            DefaultTableModel dt = (DefaultTableModel) V_table.getModel();
            dt.setRowCount(0);
            Statement tables = CarCare_DB.MyCon().createStatement();
            ResultSet result;
            if (vid.isEmpty()) {
                result = tables.executeQuery("SELECT * FROM vehicles");
            } else {
                result = tables.executeQuery("SELECT * FROM vehicles WHERE Vehicle_ID LIKE '%" + vid + "%'");
            }
            while (result.next()) {
                Vector v = new Vector();
                v.add(result.getString((1)));
                v.add(result.getString((2)));
                v.add(result.getString((3)));
                v.add(result.getString((4)));

                dt.addRow(v);
                
                VID_show.setText(result.getString("Vehicle_ID"));
                V_Manufactuer_name.setText(result.getString("Vehicle_Manufacturer"));
                V_Model.setText(result.getString("Model"));
                V_year.setText(result.getString("Year"));
            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(V_table.getModel());
            V_table.setRowSorter(sorter);
            if (!vid.isEmpty()) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + vid)); // Case-insensitive filter
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }//GEN-LAST:event_Vehicle_Search_BoxKeyReleased

    private void V_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_V_tableMouseClicked
        // TODO add your handling code here:
        int show = V_table.getSelectedRow();
        
        String V_id = V_table.getValueAt(show, 0).toString();
        String V_name = V_table.getValueAt(show, 1).toString();
        String V_m = V_table.getValueAt(show, 2).toString();
        String V_Year = V_table.getValueAt(show, 3).toString();
        
        
        VID_show.setText(V_id);
        V_Manufactuer_name.setText(V_name);
        V_Model.setText(V_m);
        V_year.setText(V_Year);
        
        
    }//GEN-LAST:event_V_tableMouseClicked

    private void Vehicle_Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vehicle_Update_btnActionPerformed
        // TODO add your handling code here:
        String VID = VID_show.getText();
        String Vehicle_M_name = V_Manufactuer_name.getText();
        String Vehicle_Model = V_Model.getText();
        String Vehical_Year = V_year.getText();
        
        try {

            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE vehicles SET Vehicle_Manufacturer ='"+Vehicle_M_name+"',Model='"+Vehicle_Model+"',Year='"+Vehical_Year+"'  WHERE Vehicle_ID  = '"+VID+"' ");
            
            VID_show.setText("");
            V_Manufactuer_name.setText("");
            V_Model.setText("");
            V_year.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.UPDATE, Notification.Location.TOP_CENTER, "Vehical Updated Successfully !");
            notify.showNotification();
            
            vtb();
           
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Vehicle_Update_btnActionPerformed

    private void Vehicle_Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vehicle_Delete_btnActionPerformed
        // TODO add your handling code here:
        String VID = VID_show.getText();
        
        try {
            
            Statement delete = CarCare_DB.MyCon().createStatement();
            delete.executeUpdate("DELETE FROM vehicles WHERE Vehicle_ID ='"+VID+"' ");
            
            VID_show.setText("");
            V_Manufactuer_name.setText("");
            V_Model.setText("");
            V_year.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DELETE, Notification.Location.TOP_CENTER, "Vehical Deleted Successfully !");
            notify.showNotification();
            
            vtb();
            
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }//GEN-LAST:event_Vehicle_Delete_btnActionPerformed

    private void vehical_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehical_printActionPerformed
        // TODO add your handling code here:
        vehical_Report.GetDataJTable();
        vehical_Report.exportExcel(V_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Vehical Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_vehical_printActionPerformed

    private void V_Manufactuer_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_V_Manufactuer_nameFocusLost
        // TODO add your handling code here:
        String Manufactuer = V_Manufactuer_name.getText();
        
        if(Manufactuer.isEmpty()){
            V_Manufactuer_name.setLineColor(Color.RED);
            VM_name.setForeground(Color.RED);
            VM_name.setText("Please Enter the Vehicle Model");
            
        }else if (!RegX.validateManufacturer(Manufactuer)) {
            V_Manufactuer_name.setLineColor(Color.RED);
            VM_name.setForeground(Color.RED);
            VM_name.setText("Invalid Model");
            
        }else{        
            V_Manufactuer_name.setLineColor(Color.GREEN);
        }
        InformTimer.startTimer(VM_name, 3000);
    }//GEN-LAST:event_V_Manufactuer_nameFocusLost

    private void V_ModelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_V_ModelFocusLost
        // TODO add your handling code here:
        String Model = V_Model.getText(); 
       
        if(Model.isEmpty()){
            V_Model.setLineColor(Color.RED);
            VModel_name.setForeground(Color.RED);
            VModel_name.setText("Please Enter the Vehicle Model");
            
        } else if(RegX.validateModel(Model)) {
            V_Model.setLineColor(Color.GREEN);
            VModel_name.setForeground(Color.GREEN);
            VModel_name.setText("Valid Model");
            
        }else{        
            V_Model.setLineColor(Color.RED);
            VModel_name.setForeground(Color.RED);
            VModel_name.setText("Invalid Model");
        }
        InformTimer.startTimer(VModel_name, 3000);
    }//GEN-LAST:event_V_ModelFocusLost

    private void V_yearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_V_yearFocusLost
        // TODO add your handling code here:
        String model = V_Model.getText();
        String year = V_year.getText();

        if (!year.isEmpty() && RegX.validateYear(year)) { 
            if (!isVehicleExists(model, year)) {
                V_year.setLineColor(Color.GREEN);
                Year.setForeground(Color.GREEN);
                Year.setText("Model and Year not Exists");
                
            } else {
                V_year.setLineColor(Color.RED);
                Year.setForeground(Color.RED);
                Year.setText("Model and Year Already Exists");
            }
        }else if(!year.isEmpty() && !RegX.validateYear(year)){       
            V_year.setLineColor(Color.RED);
            Year.setForeground(Color.RED);
            Year.setText("Invalid Model Year");
            
        }else{        
            V_year.setLineColor(Color.RED);
            Year.setForeground(Color.RED);
            Year.setText("Please Enter the Model year");
        }
        InformTimer.startTimer(Year, 3000);
    }//GEN-LAST:event_V_yearFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VID_show;
    private javax.swing.JLabel VID_text;
    private javax.swing.JLabel VM_name;
    private javax.swing.JLabel VModel_name;
    private textfield.TextField V_Manufactuer_name;
    private textfield.TextField V_Model;
    private javax.swing.JTable V_table;
    private textfield.TextField V_year;
    private Menus.Button Vehicle_Add_btn;
    private Menus.Button Vehicle_Delete_btn;
    private Menus.custom_textfield Vehicle_Search_Box;
    private Menus.Button Vehicle_Update_btn;
    private javax.swing.JLabel Year;
    private javax.swing.JScrollPane jScrollPane1;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    private Menus.GradientButton vehical_print;
    // End of variables declaration//GEN-END:variables
}
