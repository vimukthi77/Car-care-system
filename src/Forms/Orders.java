/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;


import DataBase.CarCare_DB;
import Menus.ComboBoxMultiSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Menus.TableCustom;
import Reports.Inventory_Report;
import Reports.Order_Report;
import calculator.CostCalculator;
import calculator.OrderCalculator;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import notification.Notification;



/**
 *
 * @author Oshan
 */
public class Orders extends javax.swing.JPanel {
    
    String cty;
    Order_Report order_Report = new Order_Report();
    private Timer updateTimer;
    private String customerName;
    

    
    
    public Orders() {
        
        setLookAndFeel();
        initComponents();
        FillCombo();
        V_Manufactuer();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT); 
        R_parts.setEnabled(false); 
        testData(R_parts);
        updateTotalPrice();
        otb();
        

        Orders_chk_Repair.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Check the state of the checkbox
                if (Orders_chk_Repair != null) {
                    // Check if comboBoxMultiSelection2 is not null
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        // If selected, enable the combo box
                        R_parts.setEnabled(true);
                    } else {
                        // If deselected, disable the combo box
                        R_parts.setEnabled(false);
                    }
                }
            }
        });
        
        

        Orders_V_Manufacturer.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cty = Orders_V_Manufacturer.getSelectedItem().toString();
                V_Model();
            }
        }
    });
        
        Orders_V_Model.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cty = Orders_V_Model.getSelectedItem().toString();
                yearfill();
            }
        }
    });
        
        R_parts.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {
                    if (R_parts.getSelectedItems().size() == 1) {
                        // If only one checkbox is selected or deselected, update immediately
                        updateTotalPrice();
                    } else {
                        // If multiple checkboxes are involved, use a timer to delay the update
                        if (updateTimer != null && updateTimer.isRunning()) {
                            updateTimer.stop();
                        }
                        updateTimer = new Timer(100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                updateTotalPrice();
                            }
                        });
                        updateTimer.setRepeats(false);
                        updateTimer.start();
                    }
                }
            }
        });
  }
   
    public void otb() {
    try {
        DefaultTableModel ots = (DefaultTableModel) O_table.getModel();
        ots.setRowCount(0);

        try (Statement tables = CarCare_DB.MyCon().createStatement();
             ResultSet result = tables.executeQuery("SELECT * FROM orders")) {

            while (result.next()) {
                Vector<Object> v = new Vector<>();
                v.add(result.getString("Order_ID"));
                v.add(result.getString("Customer"));
                v.add(result.getString("Vehicle_Manufacturer"));
                v.add(result.getString("Model"));
                v.add(result.getString("Year"));
                v.add(result.getString("License_Plate_No"));
                v.add(result.getString("Service"));
                v.add(result.getString("Employee_ID"));
                v.add(result.getString("Assigned_Employee"));
                v.add(result.getString("Repair_parts"));
                v.add(result.getString("Total_amount"));
                v.add(result.getString("Start_Date"));
                v.add(result.getString("Finished_Date"));
                v.add(result.getString("Job_Status"));

                ots.addRow(v);
            }
        }
    } catch (SQLException e) {
        // Print or log the exception for debugging
        e.printStackTrace();
    }
}
    
       
       
    public void V_Manufactuer(){
        try {
             String sql = " SELECT DISTINCT Vehicle_Manufacturer FROM vehicles " ;
             Statement vmanu  = CarCare_DB.MyCon().createStatement();
             ResultSet result3 = vmanu .executeQuery(sql);
             while(result3.next()){
                 String v_manufactuer = result3.getString("Vehicle_Manufacturer");
                 Orders_V_Manufacturer.addItem(v_manufactuer);   
             }
             cty = Orders_V_Manufacturer.getSelectedItem().toString();     
        } catch (SQLException e) {

        }
    }

    
    public void V_Model() {
        try {
            
            String sql = "SELECT Model FROM vehicles WHERE Vehicle_Manufacturer = ? GROUP BY  Model";
            try (PreparedStatement vmod = CarCare_DB.MyCon().prepareStatement(sql)) {
            vmod.setString(1, cty);
            ResultSet result2 = vmod.executeQuery();
            Orders_V_Model.removeAllItems();
            while (result2.next()) {
                String v_model = result2.getString("Model");
                Orders_V_Model.addItem(v_model);
            }
        }
    }catch (SQLException e) {   
    
    }
    
  }
    public void yearfill(){
        try {
        String sql = "SELECT Year FROM vehicles WHERE Model = ?";
        try (PreparedStatement y = CarCare_DB.MyCon().prepareStatement(sql)) {
            y.setString(1, cty);
            ResultSet result4 = y.executeQuery();
            Orders_V_Year.removeAllItems();
            while (result4.next()) {
                String v_year = result4.getString("Year");
                Orders_V_Year.addItem(v_year);
            }
        }
    } catch (SQLException e) {   
    
    }
        
}
    

    public void FillCombo(){
        try {
            //SELECT DISTINCT e.Name FROM employee e LEFT JOIN orders o ON e.Employee_ID = o.Employee_ID WHERE o.Job_Status IS NULL OR o.Job_Status = 'COMPLETED'
             String sql = " SELECT DISTINCT e.Name FROM employee e WHERE (e.Employee_ID NOT IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'NOT_COMPLETED') AND e.Employee_ID IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'COMPLETED' AND Employee_ID NOT IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'NOT_COMPLETED'))) OR e.Employee_ID NOT IN (SELECT DISTINCT Employee_ID FROM orders) " ;
             Statement emp  = CarCare_DB.MyCon().createStatement();
             ResultSet result1 = emp.executeQuery(sql);
             while(result1.next()){
                 String name = result1.getString("Name");
                 Orders_emp_select.addItem(name);
                 
             }
        } catch (SQLException e) {
            
            System.out.println(e);
        }
        
    }
    
    
    
    
    private double[] updateTotalPrice() {
        List<Object> selectedParts = R_parts.getSelectedItems();
        double[] result = new double[3];

        try {
            double totalPrice = OrderCalculator.calculateTotal(selectedParts);
            double cost = CostCalculator.calculateCost(selectedParts);
            double profit = totalPrice - cost;

            result[0] = totalPrice;
            result[1] = cost;
            result[2] = profit;
            Order_Tprice2.setText(String.valueOf(totalPrice));
            //cost_text2.setText(String.valueOf(cost));
            //profit_text2.setText(String.valueOf(profit));
        } catch (Exception e) {
            // Handle the exception
            result[0] = 0;
            result[1] = 0;
            result[2] = 0;
        }

        return result;
    }
    
    private void setLookAndFeel(){
    
        try {
            FlatLaf.registerCustomDefaultsSource("R_parts");
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    
    private void testData(JComboBox combo) {
        setLookAndFeel();
        R_parts.removeAllItems();       
        try {
            String sql = "SELECT * FROM inventory";
            try (PreparedStatement sn = CarCare_DB.MyCon().prepareStatement(sql)) {
                ResultSet result1 = sn.executeQuery();
                //Supplier_Name_Combo.removeAllItems();
                while (result1.next()) {
                    String parts = result1.getString("Part_Name");
                    R_parts.addItem(parts);
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
        Orders_V_Manufacturer = new Menus.ComboBoxSuggestion();
        Orders_V_Model = new Menus.ComboBoxSuggestion();
        Orders_V_Year = new Menus.ComboBoxSuggestion();
        Orders_LNo = new textfield.TextField();
        Orders_chk_Repaint = new Menus.JCheckBoxCustom();
        Orders_chk_Repair = new Menus.JCheckBoxCustom();
        Orders_chk_Maintenance = new Menus.JCheckBoxCustom();
        Orders_emp_select = new Menus.ComboBoxSuggestion();
        Orders_Add_btn = new Menus.Button();
        Orders_Update_btn = new Menus.Button();
        Orders_Delete_btn = new Menus.Button();
        jLabel1 = new javax.swing.JLabel();
        Job_fin = new Menus.Button();
        jLabel2 = new javax.swing.JLabel();
        OID_show = new javax.swing.JLabel();
        Order_Date = new com.toedter.calendar.JDateChooser();
        Order_Date2 = new com.toedter.calendar.JDateChooser();
        Orders_Search_box = new Menus.custom_textfield();
        R_parts = new Menus.ComboBoxMultiSelection();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Order_Tprice = new javax.swing.JLabel();
        Order_Tprice2 = new javax.swing.JLabel();
        roundPanel3 = new Menus.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        O_table = new javax.swing.JTable();
        profit_text2 = new javax.swing.JLabel();
        cost_text2 = new javax.swing.JLabel();
        orders_print = new Menus.GradientButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 724));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.setPreferredSize(new java.awt.Dimension(446, 717));

        Orders_V_Manufacturer.setBackground(new java.awt.Color(51, 51, 51));
        Orders_V_Manufacturer.setBorder(null);
        Orders_V_Manufacturer.setForeground(new java.awt.Color(255, 255, 255));
        Orders_V_Manufacturer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Vehicle Manufacturer" }));
        Orders_V_Manufacturer.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_V_Manufacturer.setName(""); // NOI18N

        Orders_V_Model.setBackground(new java.awt.Color(51, 51, 51));
        Orders_V_Model.setBorder(null);
        Orders_V_Model.setForeground(new java.awt.Color(255, 255, 255));
        Orders_V_Model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Vehical Model" }));
        Orders_V_Model.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_V_Model.setName(""); // NOI18N
        Orders_V_Model.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                Orders_V_ModelPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        Orders_V_Year.setBackground(new java.awt.Color(51, 51, 51));
        Orders_V_Year.setBorder(null);
        Orders_V_Year.setEditable(false);
        Orders_V_Year.setForeground(new java.awt.Color(255, 255, 255));
        Orders_V_Year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Year", " " }));
        Orders_V_Year.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_V_Year.setName(""); // NOI18N

        Orders_LNo.setBackground(new java.awt.Color(51, 51, 51));
        Orders_LNo.setForeground(new java.awt.Color(255, 255, 255));
        Orders_LNo.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_LNo.setLabelText("License Plate No");
        Orders_LNo.setLineColor(new java.awt.Color(255, 153, 0));

        Orders_chk_Repaint.setBackground(new java.awt.Color(51, 51, 51));
        Orders_chk_Repaint.setBorder(null);
        Orders_chk_Repaint.setForeground(new java.awt.Color(255, 255, 255));
        Orders_chk_Repaint.setText(" Repaint");
        Orders_chk_Repaint.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_chk_Repaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_chk_RepaintActionPerformed(evt);
            }
        });

        Orders_chk_Repair.setBackground(new java.awt.Color(51, 51, 51));
        Orders_chk_Repair.setBorder(null);
        Orders_chk_Repair.setForeground(new java.awt.Color(255, 255, 255));
        Orders_chk_Repair.setText(" Repair");
        Orders_chk_Repair.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_chk_Repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_chk_RepairActionPerformed(evt);
            }
        });

        Orders_chk_Maintenance.setBackground(new java.awt.Color(51, 51, 51));
        Orders_chk_Maintenance.setBorder(null);
        Orders_chk_Maintenance.setForeground(new java.awt.Color(255, 255, 255));
        Orders_chk_Maintenance.setText(" Maintenance");
        Orders_chk_Maintenance.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_chk_Maintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_chk_MaintenanceActionPerformed(evt);
            }
        });

        Orders_emp_select.setBackground(new java.awt.Color(51, 51, 51));
        Orders_emp_select.setBorder(null);
        Orders_emp_select.setForeground(new java.awt.Color(255, 255, 255));
        Orders_emp_select.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select Available Employees-" }));
        Orders_emp_select.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Orders_emp_select.setName(""); // NOI18N
        Orders_emp_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_emp_selectActionPerformed(evt);
            }
        });

        Orders_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        Orders_Add_btn.setText("ADD");
        Orders_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Orders_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_Add_btnActionPerformed(evt);
            }
        });

        Orders_Update_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh2.png"))); // NOI18N
        Orders_Update_btn.setText("UPDATE");
        Orders_Update_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Orders_Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_Update_btnActionPerformed(evt);
            }
        });

        Orders_Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin.png"))); // NOI18N
        Orders_Delete_btn.setText("DELETE");
        Orders_Delete_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Orders_Delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Orders_Delete_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Type of Services Required:");

        Job_fin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/completed-task.png"))); // NOI18N
        Job_fin.setText("JOB COMPLETE");
        Job_fin.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Job_fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Job_finActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Order ID:");

        OID_show.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        OID_show.setForeground(new java.awt.Color(153, 255, 102));

        Order_Date.setBackground(new java.awt.Color(51, 51, 51));
        Order_Date.setDateFormatString("MMM - d - y");
        Order_Date.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Order_Date2.setBackground(new java.awt.Color(51, 51, 51));
        Order_Date2.setDateFormatString("MMM - d - y");
        Order_Date2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Orders_Search_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Orders_Search_boxKeyReleased(evt);
            }
        });

        R_parts.setForeground(new java.awt.Color(51, 51, 51));
        R_parts.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        R_parts.setName(""); // NOI18N

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Repairing parts:");

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL");

        Order_Tprice.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        Order_Tprice.setForeground(new java.awt.Color(255, 255, 255));
        Order_Tprice.setText("Rs.");

        Order_Tprice2.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        Order_Tprice2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Orders_emp_select, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Job_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Order_Tprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Order_Tprice2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(Orders_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Orders_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Orders_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Orders_Search_box, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Orders_V_Manufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(Orders_chk_Repaint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Orders_chk_Repair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Orders_chk_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Orders_LNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Orders_V_Model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Orders_V_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Order_Date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(Order_Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(R_parts, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Orders_Search_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OID_show, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Orders_V_Manufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Orders_V_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Orders_V_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Orders_LNo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Order_Date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Date2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Orders_chk_Repaint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Orders_chk_Repair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Orders_chk_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(R_parts, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Orders_emp_select, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Job_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Tprice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Tprice2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Orders_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Orders_Update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Orders_Delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setToolTipText("");
        roundPanel3.setPreferredSize(new java.awt.Dimension(531, 439));

        O_table.setBackground(new java.awt.Color(51, 51, 51));
        O_table.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        O_table.setForeground(new java.awt.Color(255, 255, 255));
        O_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Order_ID", "Customer Name", "Vehicle_Manufacturer", "Model", "Year", "License_Plate_No", "Service", "Employee_ID", "Assigned_Employee", "Repair Parts", "Total Amount", "Start_date", "Finished_Date", "Job_Status"
            }
        ));
        O_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        O_table.setPreferredSize(new java.awt.Dimension(450, 220));
        O_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                O_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(O_table);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        orders_print.setForeground(new java.awt.Color(255, 255, 255));
        orders_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.gif"))); // NOI18N
        orders_print.setText("Print Report");
        orders_print.setEndGradientColor(new java.awt.Color(102, 204, 255));
        orders_print.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        orders_print.setShadowColor(new java.awt.Color(51, 51, 51));
        orders_print.setStartGradientColor(new java.awt.Color(0, 102, 255));
        orders_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orders_printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(orders_print, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(profit_text2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cost_text2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(orders_print, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(cost_text2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profit_text2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Orders_emp_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_emp_selectActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Orders_emp_selectActionPerformed

    private void Orders_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_Add_btnActionPerformed
        // TODO add your handling code here:
        String Order_V_Manufacture = Orders_V_Manufacturer.getSelectedItem().toString();
        String Order_V_Models = Orders_V_Model.getSelectedItem().toString();
        String Order_Year = Orders_V_Year.getSelectedItem().toString();
        String License_No = Orders_LNo.getText();
        String emp = Orders_emp_select.getSelectedItem().toString();
        List<Object> selectedParts = R_parts.getSelectedItems();
        String Total = Order_Tprice2.getText();

        String service = "";
        if (Orders_chk_Repaint.isSelected()) {
            service += "Repaint /";
        }
        if (Orders_chk_Repair.isSelected()) {
            service += " Repair /";
        }
        if (Orders_chk_Maintenance.isSelected()) {
            service += " Maintenance";
        }

        if (Order_Date.getDate() != null && Order_Date2.getDate() != null) {
            java.util.Date selectedDate = Order_Date.getDate();
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
            java.util.Date selectedDate2 = Order_Date2.getDate();
            java.sql.Date sqlDate2 = new java.sql.Date(selectedDate2.getTime());
            try {
                String insertSQL = "INSERT INTO orders (Vehicle_Manufacturer, Model, Year, License_Plate_No, Service, Assigned_Employee, Repair_parts, Total_amount, Start_Date, Finished_Date, Job_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement save = CarCare_DB.MyCon().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                    save.setString(1, Order_V_Manufacture);
                    save.setString(2, Order_V_Models);
                    save.setString(3, Order_Year);
                    save.setString(4, License_No);
                    save.setString(5, service);
                    save.setString(6, emp);
                    StringBuilder repairPartsBuilder = new StringBuilder();
                    for (Object selectedPart : selectedParts) {
                        if (repairPartsBuilder.length() > 0) {
                            repairPartsBuilder.append("/ ");
                        }
                        repairPartsBuilder.append(selectedPart.toString());
                    }                    
                    save.setString(7, repairPartsBuilder.toString());
                    save.setString(8, Total);
                    save.setDate(9, sqlDate);
                    save.setDate(10, sqlDate2);
                    save.setString(11, "NOT_COMPLETED");
                    
                    int affectedRows = save.executeUpdate();
                    
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = save.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                String generatedOrderID = generatedKeys.getString(1);
                                OID_show.setText(generatedOrderID);
                            } else {
                                System.out.println("Error: No Order ID obtained.");
                            }
                        }
                    }
                    String updateEmployeeIDSQL = "UPDATE orders o SET o.Employee_ID = (SELECT e.Employee_ID FROM employee e WHERE e.Name = o.Assigned_Employee) WHERE o.Employee_ID IS NULL";
                    try (PreparedStatement updateEmployeeID = CarCare_DB.MyCon().prepareStatement(updateEmployeeIDSQL)) {
                        updateEmployeeID.executeUpdate();
                    }
                    Orders_LNo.setText("");
                    Orders_chk_Repaint.setSelected(false);
                    Orders_chk_Repair.setSelected(false);
                    Orders_chk_Maintenance.setSelected(false);
                    Order_Date.setDate(null);
                    Order_Date2.setDate(null);
                    R_parts.clearSelectedItems();
                    R_parts.setEnabled(false); 

                    if (Orders_V_Manufacturer.getItemCount() > 0) {
                        Orders_V_Manufacturer.setSelectedIndex(0);
                    }
                    if (Orders_V_Model.getItemCount() > 0) {
                        Orders_V_Model.setSelectedIndex(0);
                    }
                    if (Orders_V_Year.getItemCount() > 0) {
                        Orders_V_Year.removeAllItems();
                        Orders_V_Year.addItem("Select Year");
                    }

                    if (Orders_emp_select.getItemCount() > 0) {
                        Orders_emp_select.setSelectedIndex(0);
                    }
                    otb();
                    
                    Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Order Added Successfully !");
                    notify.showNotification();
                    
                    
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Please select a date.");
        }

       
    }//GEN-LAST:event_Orders_Add_btnActionPerformed

    private void Orders_V_ModelPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_Orders_V_ModelPopupMenuWillBecomeInvisible
        // TODO add your handling code here:

    }//GEN-LAST:event_Orders_V_ModelPopupMenuWillBecomeInvisible

    private void Orders_chk_RepaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_chk_RepaintActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Orders_chk_RepaintActionPerformed

    private void Orders_chk_RepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_chk_RepairActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Orders_chk_RepairActionPerformed

    private void Orders_chk_MaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_chk_MaintenanceActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Orders_chk_MaintenanceActionPerformed

    private void Orders_Update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_Update_btnActionPerformed
        // TODO add your handling code here:
        
        UpdateOrdersandIncome();
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.UPDATE, Notification.Location.TOP_CENTER, "Order Updated Successfully !");
        notify.showNotification();
        ResetUI();
        
        
    }//GEN-LAST:event_Orders_Update_btnActionPerformed

    private void UpdateOrdersandIncome(){ 
        addIncomeData();
        UpdateOrders();
        addIncome();
        
    }
    
    void GetcustomerName(String customerName){   
        this.customerName = customerName;
    }

    private void UpdateOrders() {
        String OIDs = OID_show.getText();
        String Customer_Names = customerName;
        String Order_V_Manufacture = Orders_V_Manufacturer.getSelectedItem().toString();
        String Order_V_Models = Orders_V_Model.getSelectedItem().toString();
        String Order_Year = Orders_V_Year.getSelectedItem().toString();
        String License_No = Orders_LNo.getText();
        String emp = Orders_emp_select.getSelectedItem().toString();
        List<Object> selectedParts = R_parts.getSelectedItems();
        
        String Total = Order_Tprice2.getText();

        String service = "";
        if (Orders_chk_Repaint.isSelected()) {
            service += "Repaint /";
        }
        if (Orders_chk_Repair.isSelected()) {
            service += " Repair /";
        }
        if (Orders_chk_Maintenance.isSelected()) {
            service += " Maintenance";
        }

        if (Order_Date.getDate() != null && Order_Date2.getDate() != null) {
            java.util.Date selectedDate = Order_Date.getDate();
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
            java.util.Date selectedDate2 = Order_Date2.getDate();
            java.sql.Date sqlDate2 = new java.sql.Date(selectedDate2.getTime());

            try {
                Statement Update = CarCare_DB.MyCon().createStatement();
                String UpdateSQL = "UPDATE orders SET Vehicle_Manufacturer = ?, Model = ?, Year = ?, License_Plate_No = ?, Service = ?, Assigned_Employee = ?, Repair_parts = ?, Total_amount = ?, Start_Date = ?, Finished_Date = ?, Job_Status = ? WHERE Order_ID = ?";

                try (PreparedStatement updateStatement  = CarCare_DB.MyCon().prepareStatement(UpdateSQL)) {
                    //updateStatement .setString(1, Customer_Names);
                    updateStatement .setString(1, Order_V_Manufacture);
                    updateStatement .setString(2, Order_V_Models);
                    updateStatement .setString(3, Order_Year);
                    updateStatement .setString(4, License_No);
                    updateStatement .setString(5, service);
                    updateStatement .setString(6, emp);
                    StringBuilder repairPartsBuilder = new StringBuilder();
                    for (Object selectedPart : selectedParts) {
                        if (repairPartsBuilder.length() > 0) {
                            repairPartsBuilder.append("/ ");
                        }
                        repairPartsBuilder.append(selectedPart.toString());
                    }
                    updateStatement .setString(7, repairPartsBuilder.toString());
                    updateStatement .setString(8, Total);
                    updateStatement .setDate(9, sqlDate);
                    updateStatement .setDate(10, sqlDate2);
                    updateStatement.setString(11, "NOT COMPLETED");
                    updateStatement.setString(12, OIDs);
                  
                    updateStatement .executeUpdate();
                }
                otb();
                
                

                String updateEmployeeIDSQL = "UPDATE orders o SET o.Employee_ID = (SELECT e.Employee_ID FROM employee e WHERE e.Name = o.Assigned_Employee) WHERE o.Employee_ID IS NULL";
                try (PreparedStatement updateEmployeeID = CarCare_DB.MyCon().prepareStatement(updateEmployeeIDSQL)) {
                    updateEmployeeID.executeUpdate();
                }
                

                 // Assuming otb() and ResetUI() methods are defined elsewhere
                
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }


    private void ResetUI(){
        Orders_LNo.setText("");
        Orders_chk_Repaint.setSelected(false);
        Orders_chk_Repair.setSelected(false);
        Orders_chk_Maintenance.setSelected(false);
        Order_Date.setDate(null);
        Order_Date2.setDate(null);
        R_parts.clearSelectedItems();
        R_parts.setEnabled(false);

        if (Orders_V_Manufacturer.getItemCount() > 0) {
            Orders_V_Manufacturer.setSelectedIndex(0);
        }
        if (Orders_V_Model.getItemCount() > 0) {
            Orders_V_Model.setSelectedIndex(0);
        }
        if (Orders_V_Year.getItemCount() > 0) {
            Orders_V_Year.removeAllItems();
            Orders_V_Year.addItem("Select Year");
        }

        if (Orders_emp_select.getItemCount() > 0) {
            Orders_emp_select.setSelectedIndex(0);
        }
    }
    
    
    private void addIncomeData() {
        
        double[] values = updateTotalPrice();
        double totalPrice1 = values[0];
        double cost1 = values[1];
        double profit1 = values[2];
        String Orderss_ID = OID_show.getText();
       

        try {
            // Use PreparedStatement for better security and readability
            String updateSQL = "UPDATE income SET Amount = ?, Cost = ?, Profit = ? WHERE Order_ID = ?";
            try (PreparedStatement updateStatement = CarCare_DB.MyCon().prepareStatement(updateSQL)) {
                updateStatement.setString(1, String.valueOf(totalPrice1));
                updateStatement.setString(2, String.valueOf(cost1));
                updateStatement.setString(3, String.valueOf(profit1));
                updateStatement.setString(4, Orderss_ID);

                int affectedRows = updateStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Income data updated successfully.");
                } else {
                    System.out.println("Error: No rows updated in the income table.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void addIncome() {
        try {
            Statement Save = CarCare_DB.MyCon().createStatement();
            Save.executeUpdate("INSERT INTO income (Order_ID, Finished_Date) SELECT Order_ID, Finished_Date FROM orders ON DUPLICATE KEY UPDATE Finished_Date = VALUES (Finished_Date)");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    /*private void addIncomeData() {
        String Orderss_ID = OID_show.getText();
        String cost = cost_text2.getText();
        String profit = profit_text2.getText();
        String Total_price = Order_Tprice2.getText();

        try {
            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate(" UPDATE income SET  Amount = '"+Total_price+"', Cost ='" + cost + "', Profit='" + profit + "' WHERE Order_ID = '" + Orderss_ID + "'  ");

        } catch (Exception e) {
        }
    }*/

    private void Orders_Search_boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Orders_Search_boxKeyReleased
        // TODO add your handling code here:
        String osb = Orders_Search_box.getText();
   
        try {
            DefaultTableModel dt = (DefaultTableModel) O_table.getModel();
            dt.setRowCount(0);
            Statement tables = CarCare_DB.MyCon().createStatement();
            ResultSet result;
            if (osb.isEmpty()) {
                result = tables.executeQuery("SELECT * FROM orders");
            } else {
                result = tables.executeQuery("SELECT * FROM orders WHERE Order_ID  LIKE '%" + osb + "%'  OR License_Plate_No LIKE  '%" + osb + "%'");               
            }
            R_parts.clearSelectedItems();
            while (result.next()) {
                Vector v = new Vector();
                v.add(result.getString((1)));
                v.add(result.getString((2)));
                v.add(result.getString((3)));
                v.add(result.getString((4)));
                v.add(result.getString((5)));
                v.add(result.getString((6)));
                v.add(result.getString((7)));
                v.add(result.getString((8)));
                v.add(result.getString((9)));
                v.add(result.getString((10)));
                v.add(result.getString((11)));
                v.add(result.getString((12)));
                v.add(result.getString((13)));
                v.add(result.getString((14)));
                
 
                dt.addRow(v);
                
                String updateEmployeeIDSQL = "UPDATE orders o SET o.Employee_ID = (SELECT e.Employee_ID FROM employee e WHERE e.Name = o.Assigned_Employee) WHERE o.Employee_ID IS NULL";
                try (PreparedStatement updateEmployeeID = CarCare_DB.MyCon().prepareStatement(updateEmployeeIDSQL)) {
                    updateEmployeeID.executeUpdate();
                }
                
                OID_show.setText(result.getString("Order_ID"));
                Orders_V_Manufacturer.setSelectedItem(result.getString("Vehicle_Manufacturer"));
                Orders_V_Model.setSelectedItem(result.getString("Model"));
                Orders_V_Year.setSelectedItem(result.getString("Year"));
                Orders_LNo.setText(result.getString("License_Plate_No")); 
                String repairPartsValue = result.getString("Repair_parts");
                if (repairPartsValue != null && !repairPartsValue.isEmpty()) {
                    String[] repairParts = repairPartsValue.split("/ ");
                    List<Object> repairPartsList = Arrays.asList(repairParts);
                    R_parts.clearSelectedItems(); // Clear existing selected items
                    R_parts.setSelectedItems(repairPartsList);
                } else {
                    R_parts.clearSelectedItems(); // Clear selected items if none
                } 
                Order_Tprice2.setText(result.getString("Total_amount")); 
                String service = result.getString("Service");
                String[] services = service.split("/");
                Orders_chk_Repaint.setSelected(false);
                Orders_chk_Repair.setSelected(false);
                Orders_chk_Maintenance.setSelected(false);
                
                for (String s : services) {
                    s = s.trim(); // Remove extra spaces
                    if (s.equals("Repaint")) {
                        Orders_chk_Repaint.setSelected(true);
                    } else if (s.equals("Repair")) {
                        Orders_chk_Repair.setSelected(true);
                    } else if (s.equals("Maintenance")) {
                        Orders_chk_Maintenance.setSelected(true);
                    }
                }
                
                Date startDate = result.getDate("Start_Date");
                Date finishedDate = result.getDate("Finished_Date");
                if (startDate != null) {
                    Order_Date.setDate(startDate);
                }
                if (finishedDate != null) {
                    Order_Date2.setDate(finishedDate);
                }
                
                String assignedEmployee = result.getString("Assigned_Employee");
                if (assignedEmployee != null && !assignedEmployee.isEmpty()) {
                    Orders_emp_select.setSelectedItem(assignedEmployee);
                }    
            } 
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(O_table.getModel());
            O_table.setRowSorter(sorter);
            if (!osb.isEmpty()) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + osb)); // Case-insensitive filter
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

    }//GEN-LAST:event_Orders_Search_boxKeyReleased

    
    private void Orders_Delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Orders_Delete_btnActionPerformed
        // TODO add your handling code here:
        String Orders_ID = OID_show.getText();

        try {

            Statement delete = CarCare_DB.MyCon().createStatement();
            delete.executeUpdate("DELETE FROM orders WHERE Order_ID ='" + Orders_ID + "' ");

            Orders_LNo.setText("");
            Orders_chk_Repaint.setSelected(false);
            Orders_chk_Repair.setSelected(false);
            Orders_chk_Maintenance.setSelected(false);
            Order_Date.setDate(null);
            Order_Date2.setDate(null);

            if (Orders_V_Manufacturer.getItemCount() > 0) {
                Orders_V_Manufacturer.setSelectedIndex(0);
            }
            if (Orders_V_Model.getItemCount() > 0) {
                Orders_V_Model.setSelectedIndex(0);
            }
            if (Orders_V_Year.getItemCount() > 0) {
                Orders_V_Year.removeAllItems();
                Orders_V_Year.addItem("Select Year");
            }

            if (Orders_emp_select.getItemCount() > 0) {
                Orders_emp_select.setSelectedIndex(0);
            }
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DELETE, Notification.Location.TOP_CENTER, "Order Deleted Successfully !");
            notify.showNotification();

            otb();

        } catch (Exception e) {

            System.out.println(e);
        }

    }//GEN-LAST:event_Orders_Delete_btnActionPerformed

    private void O_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_O_tableMouseClicked
        // TODO add your handling code here:
        int Orders_show = O_table.getSelectedRow();

        if (Orders_show != -1) { // Check if a row is selected
            String Order_ID = getStringValue(Orders_show, 0);
            String Customer_name = getStringValue(Orders_show, 1);
            String Vehicle_Manufacturer = getStringValue(Orders_show, 2);
            String Model = getStringValue(Orders_show, 3);
            String Year = getStringValue(Orders_show, 4);
            String License_No = getStringValue(Orders_show, 5);
            String Service = getStringValue(Orders_show, 6);
            String Assigned_Employee = getStringValue(Orders_show, 8);
            String Total = getStringValue(Orders_show, 10);
            String Start_Date = getStringValue(Orders_show, 11);
            String Finished_Date = getStringValue(Orders_show, 12);

            OID_show.setText(Order_ID);
            Orders_V_Manufacturer.setSelectedItem(Vehicle_Manufacturer);
            Orders_V_Model.setSelectedItem(Model);
            Orders_V_Year.setSelectedItem(Year);
            Orders_LNo.setText(License_No);
            Orders_emp_select.setSelectedItem(Assigned_Employee);
            Order_Tprice2.setText(Total);

            // Set checkboxes based on Service
            Orders_chk_Repaint.setSelected(Service != null && Service.contains("Repaint"));
            Orders_chk_Repair.setSelected(Service != null && Service.contains("Repair"));
            Orders_chk_Maintenance.setSelected(Service != null && Service.contains("Maintenance"));

            // Assuming Order_Date and Order_Date2 are instances of JDateChooser or similar
            //Order_Date.setDate(parseDate(Start_Date));
            //Order_Date2.setDate(parseDate(Finished_Date));
            
            if (Start_Date != null && !Start_Date.isEmpty()) {
                Order_Date.setDate(parseDate(Start_Date));
            } else {
                Order_Date.setDate(null);
            }

            if (Finished_Date != null && !Finished_Date.isEmpty()) {
                Order_Date2.setDate(parseDate(Finished_Date));
            } else {
                Order_Date2.setDate(null);
            }
            

            try {
                Statement searchStatement = CarCare_DB.MyCon().createStatement(); // Assuming MyCon() returns a Connection object

                String query = "SELECT Repair_parts FROM orders WHERE Order_ID = " + Order_ID;
                ResultSet result = searchStatement.executeQuery(query);

                // Retrieve and set selected repair parts
                if (result.next()) {
                    String repairPartsValue = result.getString("Repair_parts");
                    if (repairPartsValue != null && !repairPartsValue.isEmpty()) {
                        String[] repairParts = repairPartsValue.split("/ ");
                        List<Object> repairPartsList = Arrays.asList(repairParts);
                        R_parts.clearSelectedItems(); // Clear existing selected items
                        R_parts.setSelectedItems(repairPartsList);
                    } else {
                        R_parts.clearSelectedItems(); // Clear selected items if none
                    }
                }

                // Close the ResultSet and Statement when done
                result.close();
                searchStatement.close();

            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } else {
            // Handle the case when no row is selected
            // For example, clear all the fields or display a message.
        } 
    }//GEN-LAST:event_O_tableMouseClicked

    private String getStringValue(int row, int column) {
        Object value = O_table.getValueAt(row, column);
        return (value != null) ? value.toString() : "";
    }
    
    private Date parseDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return null; // Return null if the date string is empty or null
    }
 
    private void Job_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Job_finActionPerformed
        // TODO add your handling code here:
        String Orders_ID = OID_show.getText();
        String Stat = "COMPLETED";
  
         try {
            
            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE orders SET Job_Status = '"+Stat+"' WHERE Order_ID = '"+Orders_ID+"'");
     
            otb();
            
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.DONE, Notification.Location.TOP_CENTER, "Job Completed Successfully !");
            notify.showNotification();
            
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
         
         
    }//GEN-LAST:event_Job_finActionPerformed

    private void orders_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orders_printActionPerformed
        // TODO add your handling code here:
        order_Report.GetDataJTable();
        order_Report.exportExcel(O_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Order Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_orders_printActionPerformed

    private List<Object> fetchSelectedValuesFromDatabase(){
        List<Object> selectedValues = new ArrayList<>();
        ResultSet result = null;
        try {
            
            // Fetch the selected values from the database and add them to the list
            // Example: selectedValues.add(result.getString("Selected_Column_Name"));
            selectedValues.add(result.getString("Repair_parts"));
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectedValues;
    }
    
    /*private void addCosts(){
        try {
            Statement Save = CarCare_DB.MyCon().createStatement();
            Save.executeUpdate("INSERT INTO income (Order_ID,Finished_Date,Amount,Cost) SELECT Order_ID,Finished_Date,Total_amount FROM orders");
        } catch (Exception e) {
        }
        
    }*/
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Menus.Button Job_fin;
    private javax.swing.JLabel OID_show;
    private javax.swing.JTable O_table;
    private com.toedter.calendar.JDateChooser Order_Date;
    private com.toedter.calendar.JDateChooser Order_Date2;
    private javax.swing.JLabel Order_Tprice;
    private javax.swing.JLabel Order_Tprice2;
    private Menus.Button Orders_Add_btn;
    private Menus.Button Orders_Delete_btn;
    private textfield.TextField Orders_LNo;
    private Menus.custom_textfield Orders_Search_box;
    private Menus.Button Orders_Update_btn;
    private Menus.ComboBoxSuggestion Orders_V_Manufacturer;
    private Menus.ComboBoxSuggestion Orders_V_Model;
    private Menus.ComboBoxSuggestion Orders_V_Year;
    private Menus.JCheckBoxCustom Orders_chk_Maintenance;
    private Menus.JCheckBoxCustom Orders_chk_Repaint;
    private Menus.JCheckBoxCustom Orders_chk_Repair;
    private Menus.ComboBoxSuggestion Orders_emp_select;
    private Menus.ComboBoxMultiSelection R_parts;
    private javax.swing.JLabel cost_text2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private Menus.GradientButton orders_print;
    private javax.swing.JLabel profit_text2;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
