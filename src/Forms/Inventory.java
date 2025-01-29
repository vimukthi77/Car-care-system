/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Menus.TableCustom;
import Reports.Customer_Report;
import Reports.Inventory_Report;
import Utill.InformTimer;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import notification.Notification;

/**
 *
 * @author Oshan
 */
public class Inventory extends javax.swing.JPanel {
    Inventory_Report inventory_Report = new Inventory_Report();
    
    String ity;

    /**
     * Creates new form Inventory
     */
    public Inventory() {
        initComponents();
        suppliersfill();
        inventory_Report.GetDataJTable();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        itb();
        
        ity = Supplier_Name_Combo.getSelectedItem().toString();
        
        Supplier_Name_Combo.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ity = Supplier_Name_Combo.getSelectedItem().toString();
            }
        }
    });
        
        In_Price.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotal();
            }
        });

        In_Qty.addChangeListener(e -> updateTotal());
    
  }
    private void updateTotal() {
        try {
            double price = Double.parseDouble(In_Price.getText());
            int units = (int) In_Qty.getValue();
            double total = price * units;
            In_Total.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            // Handle the exception if parsing fails (e.g., non-numeric input)
            In_Total.setText("");
        }
    }
    
    
    
    
    
    public void itb() {
        try {
            DefaultTableModel dts = (DefaultTableModel) I_table.getModel();
            dts.setRowCount(0);
            try (Statement tables = CarCare_DB.MyCon().createStatement();
                    ResultSet result = tables.executeQuery("SELECT * FROM inventory")) {
                while (result.next()) {
                    Vector<Object> v = new Vector<>();
                    v.add(result.getString("Part_ID"));
                    v.add(result.getString("Part_Name"));
                    v.add(result.getString("Price"));
                    v.add(result.getString("Units"));
                    v.add(result.getString("Total"));
                    v.add(result.getString("Supplier"));
                    
                    dts.addRow(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void suppliersfill() {
        try {
            String sql = "SELECT * FROM suppliers";
            try (PreparedStatement sn = CarCare_DB.MyCon().prepareStatement(sql)) {
                ResultSet result1 = sn.executeQuery();
                //Supplier_Name_Combo.removeAllItems();
                while (result1.next()) {
                    String s_name = result1.getString("Name");
                    Supplier_Name_Combo.addItem(s_name);
                }
            }
        } catch (SQLException e) {

        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Menus.RoundPanel();
        roundPanel2 = new Menus.RoundPanel();
        In_Name = new textfield.TextField();
        In_Price = new textfield.TextField();
        In_Qty = new Menus.Spinner();
        Inventory_Search_Box = new Menus.custom_textfield();
        IID_show_text = new javax.swing.JLabel();
        IID_show = new javax.swing.JLabel();
        In_Total = new javax.swing.JLabel();
        Supplier_Name_Combo = new Menus.ComboBoxSuggestion();
        Inventory_Add_btn = new Menus.Button();
        Inventory_Update_btn = new Menus.Button();
        Inventory_Delete_btn = new Menus.Button();
        In_Total_text = new javax.swing.JLabel();
        Suppliername = new javax.swing.JLabel();
        PartName = new javax.swing.JLabel();
        PartPrice = new javax.swing.JLabel();
        roundPanel3 = new Menus.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        I_table = new javax.swing.JTable();
        Inventory_print = new Menus.GradientButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 724));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.setPreferredSize(new java.awt.Dimension(446, 717));

        In_Name.setBackground(new java.awt.Color(51, 51, 51));
        In_Name.setForeground(new java.awt.Color(255, 255, 255));
        In_Name.setCaretColor(new java.awt.Color(255, 255, 255));
        In_Name.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        In_Name.setLabelText("Part Name");
        In_Name.setLineColor(new java.awt.Color(255, 153, 0));

        In_Price.setBackground(new java.awt.Color(51, 51, 51));
        In_Price.setForeground(new java.awt.Color(255, 255, 255));
        In_Price.setCaretColor(new java.awt.Color(255, 255, 255));
        In_Price.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        In_Price.setLabelText("Price (Per Unit)");
        In_Price.setLineColor(new java.awt.Color(255, 153, 0));

        In_Qty.setBorder(null);
        In_Qty.setLabelText("Part Qty");

        Inventory_Search_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Inventory_Search_BoxKeyReleased(evt);
            }
        });

        IID_show_text.setBackground(new java.awt.Color(51, 51, 51));
        IID_show_text.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        IID_show_text.setForeground(new java.awt.Color(255, 255, 255));
        IID_show_text.setText("Inventory ID:");

        IID_show.setBackground(new java.awt.Color(51, 51, 51));
        IID_show.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        IID_show.setForeground(new java.awt.Color(153, 255, 102));

        In_Total.setBackground(new java.awt.Color(51, 51, 51));
        In_Total.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        In_Total.setForeground(new java.awt.Color(153, 255, 102));

        Supplier_Name_Combo.setBackground(new java.awt.Color(51, 51, 51));
        Supplier_Name_Combo.setBorder(null);
        Supplier_Name_Combo.setForeground(new java.awt.Color(255, 255, 255));
        Supplier_Name_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  Supplier Name" }));
        Supplier_Name_Combo.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Supplier_Name_Combo.setName(""); // NOI18N

        Inventory_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        Inventory_Add_btn.setText("ADD");
        Inventory_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Inventory_Add_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Inventory_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_Add_btnActionPerformed(evt);
            }
        });

        Inventory_Update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh2.png"))); // NOI18N
        Inventory_Update_btn.setText("UPDATE");
        Inventory_Update_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Inventory_Update_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Inventory_Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_Update_btnActionPerformed(evt);
            }
        });

        Inventory_Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin.png"))); // NOI18N
        Inventory_Delete_btn.setText("DELETE");
        Inventory_Delete_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Inventory_Delete_btn.setShadowColor(new java.awt.Color(0, 0, 0));
        Inventory_Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_Delete_btnActionPerformed(evt);
            }
        });

        In_Total_text.setBackground(new java.awt.Color(51, 51, 51));
        In_Total_text.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        In_Total_text.setForeground(new java.awt.Color(153, 255, 102));
        In_Total_text.setText("Rs.");

        Suppliername.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        PartName.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        PartPrice.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(In_Price, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(PartPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(In_Qty, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(In_Total_text)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(In_Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(In_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(IID_show_text)
                                .addGap(18, 18, 18)
                                .addComponent(IID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Inventory_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Supplier_Name_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(Inventory_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Inventory_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Inventory_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PartName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Inventory_Search_Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IID_show_text, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(Supplier_Name_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(In_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PartName, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(In_Total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(In_Total_text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(In_Qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(In_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PartPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Inventory_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inventory_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inventory_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setPreferredSize(new java.awt.Dimension(531, 439));

        I_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Part_ID", "Name", "Price (Rs.)", "Units(Qty)", "Total_Price (Rs.)", "Supplier_Name"
            }
        ));
        I_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        I_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                I_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(I_table);

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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        Inventory_print.setForeground(new java.awt.Color(255, 255, 255));
        Inventory_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.gif"))); // NOI18N
        Inventory_print.setText("Print Report");
        Inventory_print.setEndGradientColor(new java.awt.Color(102, 204, 255));
        Inventory_print.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Inventory_print.setShadowColor(new java.awt.Color(51, 51, 51));
        Inventory_print.setStartGradientColor(new java.awt.Color(0, 102, 255));
        Inventory_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_printActionPerformed(evt);
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
                        .addComponent(Inventory_print, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Inventory_print, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Inventory_Search_BoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Inventory_Search_BoxKeyReleased
        // TODO add your handling code here:
        String Iid = Inventory_Search_Box.getText();
        try {
            DefaultTableModel dt = (DefaultTableModel) I_table.getModel();
            dt.setRowCount(0);
            Statement tables = CarCare_DB.MyCon().createStatement();
            ResultSet result;
            if (Iid.isEmpty()) {
                result = tables.executeQuery("SELECT * FROM inventory");
            } else {
                result = tables.executeQuery("SELECT * FROM inventory WHERE Part_ID LIKE '%" + Iid + "%'");
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

                IID_show.setText(result.getString("Part_ID"));
                Supplier_Name_Combo.setSelectedItem(result.getString("Supplier"));
                In_Name.setText(result.getString("Part_Name"));
                In_Price.setText(result.getString("Price"));
                In_Qty.setValue(Integer.parseInt(result.getString("Units")));
                In_Total.setText(result.getString("Total"));

            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(I_table.getModel());
            I_table.setRowSorter(sorter);
            if (!Iid.isEmpty()) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Iid)); // Case-insensitive filter
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }//GEN-LAST:event_Inventory_Search_BoxKeyReleased

    private void Inventory_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_Add_btnActionPerformed
        // TODO add your handling code here:
        String Inventory_ID = IID_show.getText();
        String Supplier = Supplier_Name_Combo.getSelectedItem().toString();
        String Part_name = In_Name.getText();
        String Part_price = In_Price.getText();
        String Part_unit = In_Qty.getValue().toString();

        try {
            if (Supplier.equals("Supplier Name") || Part_name.isEmpty() || Part_price.isEmpty() || Part_unit.equals("0")) {
                if (Supplier.equals("Supplier Name")) {
                    Suppliername.setForeground(Color.RED);
                    Suppliername.setText("Please select a Supplier");
                } 

                if (Part_name.isEmpty()) {
                    PartName.setForeground(Color.RED);
                    PartName.setText("Please Enter Part Name");
                } 

                if (Part_price.isEmpty()) {
                    PartPrice.setForeground(Color.RED);
                    PartPrice.setText("Please Enter the Price");
                } 

                InformTimer.startTimer(Suppliername, 2000);
                InformTimer.startTimer(PartName, 2000);
                InformTimer.startTimer(PartPrice, 2000);
                
            } else {
                
                double price = Double.parseDouble(Part_price);
                int units = Integer.parseInt(Part_unit);
                double total = price * units;

                Statement Save = CarCare_DB.MyCon().createStatement();
                Save.executeUpdate("INSERT INTO inventory (Part_Name, Price,Units,Total,Supplier) VALUES ('" + Part_name + "','" + Part_price + "','" + Part_unit + "','" + total + "','" + Supplier + "')");

                IID_show.setText(Inventory_ID);
                Supplier_Name_Combo.setSelectedItem("Supplier Name");
                In_Name.setText("");
                In_Price.setText("");
                In_Qty.setValue(0);
                In_Total.setText("");

                Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Parts Added Successfully !");
                notify.showNotification();

                itb();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_Inventory_Add_btnActionPerformed

    private void Inventory_Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_Update_btnActionPerformed
        // TODO add your handling code here:
        String Inventory_ID = IID_show.getText();
        String Supplier = Supplier_Name_Combo.getSelectedItem().toString();
        String Part_name = In_Name.getText();
        String Part_price = In_Price.getText();
        String Part_unit = In_Qty.getValue().toString();
      

        try {
            
            double price = Double.parseDouble(Part_price);
            int units = Integer.parseInt(Part_unit);
            double total = price * units;
            

            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE inventory SET Part_Name ='"+Part_name+"',Price ='"+Part_price+"',Units='"+Part_unit+"',Total='"+total+"',Supplier='"+Supplier+"' WHERE Part_ID = '"+Inventory_ID+"'  ");

            IID_show.setText("");
            Supplier_Name_Combo.setSelectedItem("Supplier Name");
            In_Name.setText("");
            In_Price.setText("");
            In_Qty.setValue(0);
            In_Total.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.UPDATE, Notification.Location.TOP_CENTER, "Parts Updated Successfully !");
            notify.showNotification();


            itb();

        } catch (SQLException e) {

            System.out.println(e);
        }
       
    }//GEN-LAST:event_Inventory_Update_btnActionPerformed

    private void Inventory_Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_Delete_btnActionPerformed
        // TODO add your handling code here:
        String Inventory_ID = IID_show.getText();

        try {

            Statement delete = CarCare_DB.MyCon().createStatement();
            delete.executeUpdate("DELETE FROM inventory WHERE Part_ID ='"+Inventory_ID+"' ");

            IID_show.setText("");
            Supplier_Name_Combo.setSelectedItem("Supplier Name");
            In_Name.setText("");
            In_Price.setText("");
            In_Qty.setValue(0);
            In_Total.setText("");
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DELETE, Notification.Location.TOP_CENTER, "Parts Deleted Successfully !");
            notify.showNotification();

            itb();

        } catch (Exception e) {

            System.out.println(e);
        }
        
    }//GEN-LAST:event_Inventory_Delete_btnActionPerformed

    private void I_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_I_tableMouseClicked
        // TODO add your handling code here:
        int Inventory_show = I_table.getSelectedRow();
        
        String P_id = I_table.getValueAt(Inventory_show, 0).toString();
        String P_name = I_table.getValueAt(Inventory_show, 1).toString();
        String price = I_table.getValueAt(Inventory_show, 2).toString();
        String unit = I_table.getValueAt(Inventory_show, 3).toString();
        String total = I_table.getValueAt(Inventory_show, 4).toString(); 
        String supplier = I_table.getValueAt(Inventory_show, 5).toString();
        
        
        IID_show.setText(P_id);
        Supplier_Name_Combo.setSelectedItem(supplier);
        In_Name.setText(P_name);
        In_Price.setText(price);
        In_Qty.setValue(Integer.parseInt(unit));
        In_Total.setText(total);
    }//GEN-LAST:event_I_tableMouseClicked

    private void Inventory_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_printActionPerformed
        // TODO add your handling code here:
        inventory_Report.GetDataJTable();
        inventory_Report.exportExcel(I_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Inventory Report Printed Successfully !");
        notify.showNotification();

    }//GEN-LAST:event_Inventory_printActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IID_show;
    private javax.swing.JLabel IID_show_text;
    private javax.swing.JTable I_table;
    private textfield.TextField In_Name;
    private textfield.TextField In_Price;
    private Menus.Spinner In_Qty;
    private javax.swing.JLabel In_Total;
    private javax.swing.JLabel In_Total_text;
    private Menus.Button Inventory_Add_btn;
    private Menus.Button Inventory_Delete_btn;
    private Menus.custom_textfield Inventory_Search_Box;
    private Menus.Button Inventory_Update_btn;
    private Menus.GradientButton Inventory_print;
    private javax.swing.JLabel PartName;
    private javax.swing.JLabel PartPrice;
    private Menus.ComboBoxSuggestion Supplier_Name_Combo;
    private javax.swing.JLabel Suppliername;
    private javax.swing.JScrollPane jScrollPane1;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
