/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import Utill.InformTimer;
import calculator.CostCalculator;
import calculator.OrderCalculator;
import calculator.ProfitCalculator;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import notification.Notification;



/**
 *
 * @author Oshan
 */
public class Order extends javax.swing.JPanel {

   
    String cty;
    private Timer updateTimer;

    private int repaintCost;
    private int MaintanceCost;
    

    public Order()  {
        setLookAndFeel();
        initComponents(); 
        
        FillCombos();
        V_Manufactuers();
        R_parts.setEnabled(false); 
        SelectParts(R_parts); 
        R_parts.clearSelectedItems();
        updateTotalPrice();
        String customerName = CI_Name.getText();
        Orders ordersInstance = new Orders();
        ordersInstance.GetcustomerName(customerName);

         // 'this' should be a valid Order instance
      
        Order_chk_Repair.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Check the state of the checkbox
                if (Order_chk_Repair != null) {
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

        Order_V_Manufacturer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cty = Order_V_Manufacturer.getSelectedItem().toString();
                    V_Models();
                }
            }
        });

        Order_V_Model.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cty = Order_V_Model.getSelectedItem().toString();
                    yearfills();
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
    
    
    private void setLookAndFeel(){
    
        try {
            
            FlatLaf.registerCustomDefaultsSource("R_parts");            
            UIManager.setLookAndFeel(new FlatDarkLaf()); 
            
            
        } catch (Exception ex) {
            // Handle the exception
        }
    }
    
    private void updateTotalPrice() {
        SwingUtilities.invokeLater(() -> {
            List<Object> selectedParts = R_parts.getSelectedItems();
            
            try {
                double totalPrice = OrderCalculator.calculateTotal(selectedParts);
                double cost = CostCalculator.calculateCost(selectedParts);
                double totalPrice2 = totalPrice + repaintCost+MaintanceCost;
                double profit = totalPrice2-cost;
                
                Order_Tprice1.setText(String.valueOf(totalPrice2));
                cost_text.setText(String.valueOf(cost));
                profit_text.setText(String.valueOf(profit));
            } catch (Exception e) {
                // Handle the exception
            }
        });
    }
    
    private void SelectParts(JComboBox combo) {
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
    
    public void V_Manufactuers() {
        try {
            String sql = " SELECT DISTINCT Vehicle_Manufacturer FROM vehicles";
            Statement vmanu = CarCare_DB.MyCon().createStatement();
            ResultSet result3 = vmanu.executeQuery(sql);
            while (result3.next()) {
                String v_manufactuer = result3.getString("Vehicle_Manufacturer");
                Order_V_Manufacturer.addItem(v_manufactuer);
            }
            cty = Order_V_Manufacturer.getSelectedItem().toString();
        } catch (SQLException e) {

        }
    }

    public void V_Models() {
        try {
            String sql = "SELECT Model FROM vehicles WHERE Vehicle_Manufacturer = ? GROUP BY  Model";
            try (PreparedStatement vmod = CarCare_DB.MyCon().prepareStatement(sql)) {
                vmod.setString(1, cty);
                ResultSet result2 = vmod.executeQuery();
                Order_V_Model.removeAllItems();
                while (result2.next()) {
                    String v_model = result2.getString("Model");
                    Order_V_Model.addItem(v_model);
                }
            }
        } catch (SQLException e) {

        }
    }

    public void yearfills() {
        try {
            String sql = "SELECT Year FROM vehicles WHERE Model = ?";
            try (PreparedStatement y = CarCare_DB.MyCon().prepareStatement(sql)) {
                y.setString(1, cty);
                ResultSet result4 = y.executeQuery();
                Order_V_Year.removeAllItems();
                while (result4.next()) {
                    String v_year = result4.getString("Year");
                    Order_V_Year.addItem(v_year);
                }
            }
        } catch (SQLException e) {

        }

    }

    public void FillCombos() {
        try {
            //SELECT DISTINCT e.Name FROM employee e LEFT JOIN orders o ON e.Employee_ID = o.Employee_ID WHERE o.Job_Status IS NULL OR o.Job_Status = 'COMPLETED'
            String sql = " SELECT DISTINCT e.Name FROM employee e WHERE (e.Employee_ID NOT IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'NOT_COMPLETED') AND e.Employee_ID IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'COMPLETED' AND Employee_ID NOT IN (SELECT Employee_ID FROM orders WHERE Job_Status = 'NOT_COMPLETED'))) OR e.Employee_ID NOT IN (SELECT DISTINCT Employee_ID FROM orders) ";
            Statement emp = CarCare_DB.MyCon().createStatement();
            ResultSet result1 = emp.executeQuery(sql);
            while (result1.next()) {
                String name = result1.getString("Name");
                Order_Emp.addItem(name);

            }
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    private Date parseDates(String dateStr) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Menus.RoundPanel();
        cost_text = new javax.swing.JLabel();
        profit_text = new javax.swing.JLabel();
        roundPanel2 = new Menus.RoundPanel();
        CI_Name = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        CI_NIC = new textfield.TextField();
        CI_Address = new textfield.TextField();
        CI_Email = new textfield.TextField();
        CI_Contact = new textfield.TextField();
        Cname_check = new javax.swing.JLabel();
        Cnic_check = new javax.swing.JLabel();
        Caddress_check = new javax.swing.JLabel();
        Cemail_check = new javax.swing.JLabel();
        Ccontact_check = new javax.swing.JLabel();
        roundPanel3 = new Menus.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        Order_V_Manufacturer = new Menus.ComboBoxSuggestion();
        Order_License = new textfield.TextField();
        Order_V_Model = new Menus.ComboBoxSuggestion();
        Order_V_Year = new Menus.ComboBoxSuggestion();
        Order_chk_Repaint = new Menus.JCheckBoxCustom();
        Order_chk_Repair = new Menus.JCheckBoxCustom();
        Order_chk_Maintenance = new Menus.JCheckBoxCustom();
        Order_Emp = new Menus.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        Order_Tprice = new javax.swing.JLabel();
        Order_Add_btn = new Menus.Button();
        jLabel5 = new javax.swing.JLabel();
        Order_Date3 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        OID_shows = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Order_Tprice1 = new javax.swing.JLabel();
        R_parts = new Menus.ComboBoxMultiSelection();
        jLabel8 = new javax.swing.JLabel();
        VM_check = new javax.swing.JLabel();
        VModel_check = new javax.swing.JLabel();
        year_check = new javax.swing.JLabel();
        licenese_check = new javax.swing.JLabel();
        service_check = new javax.swing.JLabel();
        employee_check = new javax.swing.JLabel();
        date_check = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        CI_Name.setBackground(new java.awt.Color(51, 51, 51));
        CI_Name.setForeground(new java.awt.Color(255, 255, 255));
        CI_Name.setCaretColor(new java.awt.Color(255, 255, 255));
        CI_Name.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CI_Name.setLabelText("Customer Name");
        CI_Name.setLineColor(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer Information");

        CI_NIC.setBackground(new java.awt.Color(51, 51, 51));
        CI_NIC.setForeground(new java.awt.Color(255, 255, 255));
        CI_NIC.setCaretColor(new java.awt.Color(255, 255, 255));
        CI_NIC.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CI_NIC.setLabelText("NIC");
        CI_NIC.setLineColor(new java.awt.Color(255, 153, 0));

        CI_Address.setBackground(new java.awt.Color(51, 51, 51));
        CI_Address.setForeground(new java.awt.Color(255, 255, 255));
        CI_Address.setCaretColor(new java.awt.Color(255, 255, 255));
        CI_Address.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CI_Address.setLabelText("Address");
        CI_Address.setLineColor(new java.awt.Color(255, 153, 0));

        CI_Email.setBackground(new java.awt.Color(51, 51, 51));
        CI_Email.setForeground(new java.awt.Color(255, 255, 255));
        CI_Email.setCaretColor(new java.awt.Color(255, 255, 255));
        CI_Email.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CI_Email.setLabelText("Email");
        CI_Email.setLineColor(new java.awt.Color(255, 153, 0));

        CI_Contact.setBackground(new java.awt.Color(51, 51, 51));
        CI_Contact.setForeground(new java.awt.Color(255, 255, 255));
        CI_Contact.setCaretColor(new java.awt.Color(255, 255, 255));
        CI_Contact.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CI_Contact.setLabelText("Contact Number");
        CI_Contact.setLineColor(new java.awt.Color(255, 153, 0));

        Cname_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Cnic_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Caddress_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Cemail_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        Ccontact_check.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CI_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CI_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Cname_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CI_Name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Cnic_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CI_NIC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(Caddress_check, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Ccontact_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cemail_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CI_Contact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(CI_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Cname_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CI_NIC, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Cnic_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CI_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Caddress_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CI_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Cemail_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CI_Contact, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Ccontact_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Order Information");

        Order_V_Manufacturer.setBackground(new java.awt.Color(51, 51, 51));
        Order_V_Manufacturer.setBorder(null);
        Order_V_Manufacturer.setForeground(new java.awt.Color(255, 255, 255));
        Order_V_Manufacturer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Vehicle Manufacturer" }));
        Order_V_Manufacturer.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_V_Manufacturer.setName(""); // NOI18N

        Order_License.setBackground(new java.awt.Color(51, 51, 51));
        Order_License.setForeground(new java.awt.Color(255, 255, 255));
        Order_License.setCaretColor(new java.awt.Color(255, 255, 255));
        Order_License.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_License.setLabelText("License Plate No");
        Order_License.setLineColor(new java.awt.Color(255, 153, 0));

        Order_V_Model.setBackground(new java.awt.Color(51, 51, 51));
        Order_V_Model.setBorder(null);
        Order_V_Model.setForeground(new java.awt.Color(51, 51, 51));
        Order_V_Model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Vehicle Model" }));
        Order_V_Model.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_V_Model.setName(""); // NOI18N

        Order_V_Year.setBackground(new java.awt.Color(51, 51, 51));
        Order_V_Year.setBorder(null);
        Order_V_Year.setEditable(false);
        Order_V_Year.setForeground(new java.awt.Color(255, 255, 255));
        Order_V_Year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Year" }));
        Order_V_Year.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_V_Year.setName(""); // NOI18N

        Order_chk_Repaint.setBackground(new java.awt.Color(51, 51, 51));
        Order_chk_Repaint.setBorder(null);
        Order_chk_Repaint.setForeground(new java.awt.Color(255, 255, 255));
        Order_chk_Repaint.setText(" Repaint");
        Order_chk_Repaint.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_chk_Repaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_chk_RepaintActionPerformed(evt);
            }
        });

        Order_chk_Repair.setBackground(new java.awt.Color(51, 51, 51));
        Order_chk_Repair.setBorder(null);
        Order_chk_Repair.setForeground(new java.awt.Color(255, 255, 255));
        Order_chk_Repair.setText(" Repair");
        Order_chk_Repair.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_chk_Repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_chk_RepairActionPerformed(evt);
            }
        });

        Order_chk_Maintenance.setBackground(new java.awt.Color(51, 51, 51));
        Order_chk_Maintenance.setBorder(null);
        Order_chk_Maintenance.setForeground(new java.awt.Color(255, 255, 255));
        Order_chk_Maintenance.setText(" Maintenance");
        Order_chk_Maintenance.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_chk_Maintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_chk_MaintenanceActionPerformed(evt);
            }
        });

        Order_Emp.setBackground(new java.awt.Color(51, 51, 51));
        Order_Emp.setBorder(null);
        Order_Emp.setForeground(new java.awt.Color(255, 255, 255));
        Order_Emp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Available Employee" }));
        Order_Emp.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Order_Emp.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL");

        Order_Tprice.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        Order_Tprice.setForeground(new java.awt.Color(255, 255, 255));
        Order_Tprice.setText("Rs.");

        Order_Add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-to-cart.png"))); // NOI18N
        Order_Add_btn.setText("Add Order");
        Order_Add_btn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        Order_Add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_Add_btnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Type of Services Required:");

        Order_Date3.setBackground(new java.awt.Color(255, 255, 255));
        Order_Date3.setForeground(new java.awt.Color(255, 255, 255));
        Order_Date3.setDateFormatString("MMM - d - y");
        Order_Date3.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Order ID:");

        OID_shows.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        OID_shows.setForeground(new java.awt.Color(153, 255, 102));

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Set Date:");

        Order_Tprice1.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        Order_Tprice1.setForeground(new java.awt.Color(255, 255, 255));

        R_parts.setForeground(new java.awt.Color(51, 51, 51));
        R_parts.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        R_parts.setName(""); // NOI18N

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Repairing parts:");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(Order_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Order_Tprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Order_Tprice1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VM_check, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OID_shows, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addComponent(Order_chk_Repaint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Order_chk_Repair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Order_chk_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(VModel_check, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(year_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Order_V_Year, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(licenese_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Order_V_Model, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(Order_V_Manufacturer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(Order_License, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(service_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(R_parts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(employee_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Order_Emp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(date_check, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Order_Date3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OID_shows, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Order_V_Manufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(VM_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Order_V_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(VModel_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Order_V_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(year_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Order_License, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(licenese_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Order_chk_Repaint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_chk_Repair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_chk_Maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(service_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(R_parts, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Order_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(employee_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Order_Date3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_check, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Tprice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Tprice1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(401, 401, 401)
                    .addComponent(profit_text, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(516, Short.MAX_VALUE)))
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(446, 446, 446)
                    .addComponent(cost_text, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(502, Short.MAX_VALUE)))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(435, 435, 435)
                    .addComponent(profit_text, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(418, Short.MAX_VALUE)))
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(429, 429, 429)
                    .addComponent(cost_text, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(429, Short.MAX_VALUE)))
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
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Order_Add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_Add_btnActionPerformed
        // TODO add your handling code here:
        addOrderAndCustomer(); 
        
    }//GEN-LAST:event_Order_Add_btnActionPerformed

    private void Order_chk_RepaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_chk_RepaintActionPerformed
        // TODO add your handling code here:
        
        if(Order_chk_Repaint.isSelected()){
        
            repaintCost =3000;
        }else{
        
            repaintCost =0;
        }
        updateTotalPrice();
    }//GEN-LAST:event_Order_chk_RepaintActionPerformed

    private void Order_chk_RepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_chk_RepairActionPerformed
        // TODO add your handling code here:
        if(!Order_chk_Repair.isSelected()){
        
            Order_Tprice1.setText("0.0");
        }
    }//GEN-LAST:event_Order_chk_RepairActionPerformed

    private void Order_chk_MaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_chk_MaintenanceActionPerformed
        // TODO add your handling code here:
        if(Order_chk_Maintenance.isSelected()){
        
            MaintanceCost = 4500;
        }else{
        
            MaintanceCost = 0;
        }
        updateTotalPrice();
    }//GEN-LAST:event_Order_chk_MaintenanceActionPerformed

    private void addOrderAndCustomer(){
        addOrder();
        addCustomer();
        ResetUI();
        
       
    }
    
    private void addCustomer() {
        String Customer_Name = CI_Name.getText();
        String Customer_NIC = CI_NIC.getText();
        String Customer_Address = CI_Address.getText();
        String Customer_Email = CI_Email.getText();
        String Customer_Contact = CI_Contact.getText();
        
        try {
            
            if(Customer_Name.isEmpty() && Customer_NIC.isEmpty() && Customer_Address.isEmpty() && Customer_Email.isEmpty() && Customer_Contact.isEmpty()){
                
                
                Cname_check.setForeground(Color.RED);
                Cname_check.setText("Please Enter the Name");
                
                
                Cnic_check.setForeground(Color.RED);
                Cnic_check.setText("Please Enter NIC");
                
                
                Caddress_check.setForeground(Color.RED);
                Caddress_check.setText("Please Enter the Address");
                
                
                Cemail_check.setForeground(Color.RED);
                Cemail_check.setText("Please Enter Email");
                
                
                Ccontact_check.setForeground(Color.RED);
                Ccontact_check.setText("Please Enter Contact Number");
                
                InformTimer.startTimer(Cname_check, 2000);
                InformTimer.startTimer(Cnic_check, 2000);
                InformTimer.startTimer(Caddress_check, 2000);
                InformTimer.startTimer(Cemail_check, 2000);
                InformTimer.startTimer(Ccontact_check, 2000);
                
            }else{
                
                Statement Save = CarCare_DB.MyCon().createStatement();
                Save.executeUpdate("INSERT INTO customer (Name, Address,NIC,Contact_No,Email) VALUES ('" + Customer_Name + "','" + Customer_Address + "','" + Customer_NIC + "','" + Customer_Contact + "','" + Customer_Email + "')");

                          
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }

    private void addOrder() {
        String Order_V_Manufacture = Order_V_Manufacturer.getSelectedItem().toString();
        String Order_V_Models = Order_V_Model.getSelectedItem().toString();
        String Order_Year = Order_V_Year.getSelectedItem().toString();
        String License_No = Order_License.getText();
        String emp = Order_Emp.getSelectedItem().toString();
        String Customer_Name = CI_Name.getText();
        boolean repaint = !Order_chk_Repaint.isSelected();
        boolean reapair = !Order_chk_Repair.isSelected();
        boolean Maintanence = !Order_chk_Maintenance.isSelected();
        List<Object> selectedParts = R_parts.getSelectedItems();
        String Total = Order_Tprice1.getText();

        if (repaint && Order_V_Manufacture.equals("Select Vehicle Manufacturer") && Order_V_Models.equals(" Select Vehicle Model") && Order_Year.equals(" Select Year") && License_No.isEmpty() && emp.equals(" Select Available Employee")) {

            VM_check.setForeground(Color.red);
            VM_check.setText("Select the Vehicle Manufacturer");

            VModel_check.setForeground(Color.red);
            VModel_check.setText("Select the Model");

            year_check.setForeground(Color.red);
            year_check.setText("Select Year");

            licenese_check.setForeground(Color.red);
            licenese_check.setText("Enter Vehicle License Number");

            employee_check.setForeground(Color.red);
            employee_check.setText("Please Select a Employee");

            date_check.setForeground(Color.red);
            date_check.setText("Please Select the Date");

            service_check.setForeground(Color.red);
            service_check.setText("Select at Service");

            InformTimer.startTimer(VM_check, 2000);
            InformTimer.startTimer(VModel_check, 2000);
            InformTimer.startTimer(year_check, 2000);
            InformTimer.startTimer(licenese_check, 2000);
            InformTimer.startTimer(employee_check, 2000);
            InformTimer.startTimer(date_check, 2000);
            InformTimer.startTimer(service_check, 2000);

        } else {

            String service = "";
            if (Order_chk_Repaint.isSelected()) {
                service += "Repaint /";
                                
            }
            if (Order_chk_Repair.isSelected()) {
                service += " Repair /";
            }
            if (Order_chk_Maintenance.isSelected()) {
                service += " Maintenance";
            }

            if (Order_Date3.getDate() != null) {
                java.util.Date selectedDate = Order_Date3.getDate();
                java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

                try {
                    String insertSQL = "INSERT INTO orders (Customer,Vehicle_Manufacturer, Model, Year, License_Plate_No, Service, Assigned_Employee, Repair_parts, Total_amount, Start_Date, Job_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement save = CarCare_DB.MyCon().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                        save.setString(1, Customer_Name); //Order_V_Manufacture
                        save.setString(2, Order_V_Manufacture); //Order_V_Models
                        save.setString(3, Order_V_Models); //Order_Year
                        save.setString(4, Order_Year); //License_No
                        save.setString(5, License_No); //service
                        save.setString(6, service); //emp
                        save.setString(7, emp); //NOT_COMPLETED
                        StringBuilder repairPartsBuilder = new StringBuilder();
                        for (Object selectedPart : selectedParts) {
                            if (repairPartsBuilder.length() > 0) {
                                repairPartsBuilder.append("/ ");
                            }
                            repairPartsBuilder.append(selectedPart.toString());
                        }
                        save.setString(8, repairPartsBuilder.toString());
                        save.setString(9, Total);//Total
                        save.setDate(10, sqlDate);//sqlDate                     
                        save.setString(11, "NOT_COMPLETED"); //Customer_Name

                        int affectedRows = save.executeUpdate();

                        if (affectedRows > 0) {
                            try (ResultSet generatedKeys = save.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    String generatedOrderID = generatedKeys.getString(1);
                                    OID_shows.setText(generatedOrderID);

                                } else {
                                    System.out.println("Error: No Order ID obtained.");
                                }
                            }
                        }
                        String updateEmployeeIDSQL = "UPDATE orders o SET o.Employee_ID = (SELECT e.Employee_ID FROM employee e WHERE e.Name = o.Assigned_Employee) WHERE o.Employee_ID IS NULL";
                        try (PreparedStatement updateEmployeeID = CarCare_DB.MyCon().prepareStatement(updateEmployeeIDSQL)) {
                            updateEmployeeID.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                
                Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Order Added Successfully !");
                notify.showNotification();
            }
        }
        addIncome();
        addIncomeData();
    }
    
    private void ResetUI() {
        Order_License.setText("");
        Order_chk_Repaint.setSelected(false);
        Order_chk_Repair.setSelected(false);
        Order_chk_Maintenance.setSelected(false);
        Order_Date3.setDate(null);
        R_parts.clearSelectedItems();
        Order_Tprice1.setText("");

        if (Order_V_Manufacturer.getItemCount() > 0) {
            Order_V_Manufacturer.setSelectedIndex(0);
        }
        if (Order_V_Model.getItemCount() > 0) {
            Order_V_Model.setSelectedIndex(0);
        }
        if (Order_V_Year.getItemCount() > 0) {
            Order_V_Year.removeAllItems();
            Order_V_Year.addItem("Select Year");
        }

        if (Order_Emp.getItemCount() > 0) {
            Order_Emp.setSelectedIndex(0);
        }
        
        CI_Name.setText("");
        CI_NIC.setText("");
        CI_Address.setText("");
        CI_Email.setText("");
        CI_Contact.setText("");
    }
    
    private void addIncome() {
        try {
            Statement Save = CarCare_DB.MyCon().createStatement();
            Save.executeUpdate("INSERT IGNORE  INTO income (Order_ID, Amount) SELECT Order_ID, Total_amount  FROM orders");

        } catch (SQLException e) {

            System.out.println(e);
        }
    }
    
    private void addIncomeData(){
        String Orderss_ID = OID_shows.getText();
        String cost = cost_text.getText();
        String profit = profit_text.getText();
        
        try {
            Statement Update = CarCare_DB.MyCon().createStatement();
            Update.executeUpdate("UPDATE income SET Cost ='"+cost+"', Profit='"+profit+"' WHERE Order_ID = '"+Orderss_ID+"'  ");
            
        } catch (Exception e) {
        }
    }
    

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private textfield.TextField CI_Address;
    private textfield.TextField CI_Contact;
    private textfield.TextField CI_Email;
    private textfield.TextField CI_NIC;
    private textfield.TextField CI_Name;
    private javax.swing.JLabel Caddress_check;
    private javax.swing.JLabel Ccontact_check;
    private javax.swing.JLabel Cemail_check;
    private javax.swing.JLabel Cname_check;
    private javax.swing.JLabel Cnic_check;
    private javax.swing.JLabel OID_shows;
    private Menus.Button Order_Add_btn;
    private com.toedter.calendar.JDateChooser Order_Date3;
    private Menus.ComboBoxSuggestion Order_Emp;
    private textfield.TextField Order_License;
    private javax.swing.JLabel Order_Tprice;
    private javax.swing.JLabel Order_Tprice1;
    private Menus.ComboBoxSuggestion Order_V_Manufacturer;
    private Menus.ComboBoxSuggestion Order_V_Model;
    private Menus.ComboBoxSuggestion Order_V_Year;
    private Menus.JCheckBoxCustom Order_chk_Maintenance;
    private Menus.JCheckBoxCustom Order_chk_Repaint;
    private Menus.JCheckBoxCustom Order_chk_Repair;
    private Menus.ComboBoxMultiSelection R_parts;
    private javax.swing.JLabel VM_check;
    private javax.swing.JLabel VModel_check;
    private javax.swing.JLabel cost_text;
    private javax.swing.JLabel date_check;
    private javax.swing.JLabel employee_check;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel licenese_check;
    private javax.swing.JLabel profit_text;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    private javax.swing.JLabel service_check;
    private javax.swing.JLabel year_check;
    // End of variables declaration//GEN-END:variables
}
