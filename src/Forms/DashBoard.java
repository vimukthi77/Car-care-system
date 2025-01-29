/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Forms;

import DataBase.CarCare_DB;
import Menus.GradientButton;
import Menus.ModelChart;
import Menus.ModelData;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import notification.Notification;
import Reports.Customer_Report;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import Reports.Employee_Report;
import Reports.Income_Report;
import Reports.Inventory_Report;
import Reports.Order_Report;
import Reports.Supplier_Report;
import Reports.Vehical_Report;

/**
 *
 * @author Oshan
 */
public class DashBoard extends javax.swing.JPanel {

    Customer_Report customer_Report = new Customer_Report();
    Employee_Report employee_Report = new Employee_Report();
    Inventory_Report inventory_Report = new Inventory_Report();
    Vehical_Report vehical_Report = new Vehical_Report();
    Supplier_Report supplier_Report = new Supplier_Report();
    Order_Report order_Report = new Order_Report();
    Income_Report income_Report = new Income_Report();
    CardLayout cardLayout;


    public DashBoard() {
        initComponents();
        CountCustomers();
        CountActiveOrders();
        CountActiveEmployees();
        CountSales();
        chart.setTitle("Carcare Chart");
        chart.addLegend("Income", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend("Cost", Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend("Profit", Color.decode("#00FF00"), Color.decode("#F11712"));
        cardLayout = (CardLayout) (panelCard.getLayout());
        GradientButton myButton = new GradientButton();
        setData();
        AE.setForeground(Color.BLACK);
        TC.setForeground(Color.BLACK);
        AO.setForeground(Color.BLACK);
        TS.setForeground(Color.BLACK);
        AE_Count.setForeground(Color.BLACK);
        TC_Count.setForeground(Color.BLACK);
        AO_Count.setForeground(Color.BLACK);
        TS_Count.setForeground(Color.BLACK);
        ctb();
        etb();
        itb();
        otb();
        stb();
        vtb();
        ictb();

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
            System.out.println(e);
        }
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

    public void itb() {
        try {
            DefaultTableModel dts = (DefaultTableModel) I_table.getModel();
            dts.setRowCount(0);
            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM inventory")) {
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

    public void otb() {
        try {
            DefaultTableModel ots = (DefaultTableModel) O_table.getModel();
            ots.setRowCount(0);

            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM orders")) {

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

    public void stb() {
        try {
            DefaultTableModel sts = (DefaultTableModel) S_table.getModel();
            sts.setRowCount(0);

            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM suppliers")) {

                while (result.next()) {
                    Vector<Object> v = new Vector<>();
                    v.add(result.getString("Supplier_ID"));
                    v.add(result.getString("Name"));
                    v.add(result.getString("NIC"));
                    v.add(result.getString("Address"));
                    v.add(result.getString("Contact_No"));
                    v.add(result.getString("Email"));

                    sts.addRow(v);
                }
            }
        } catch (SQLException e) {
            // Print or log the exception for debugging
            e.printStackTrace();
        }
    }

    public void vtb() {
        try {
            DefaultTableModel vts = (DefaultTableModel) V_table.getModel();
            vts.setRowCount(0);
            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM vehicles")) {
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

    public void ictb() {
        try {
            DefaultTableModel icts = (DefaultTableModel) IC_table.getModel();
            icts.setRowCount(0);
            try (Statement tables = CarCare_DB.MyCon().createStatement(); ResultSet result = tables.executeQuery("SELECT * FROM income")) {
                while (result.next()) {
                    Vector<Object> v = new Vector<>();
                    v.add(result.getString("Order_ID"));
                    v.add(result.getString("Finished_Date"));
                    v.add(result.getString("Amount"));
                    v.add(result.getString("Cost"));
                    v.add(result.getString("Profit"));

                    icts.addRow(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMail(String toEmail, String subject, String text) {
        final String senderEmail = "Carcarepvtltd24@gmail.com"; //  sender email
        final String senderPassword = "faezoukssnhrwaeq"; //  sender password

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

       
        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            // Set Subject: header field
            message.setSubject(subject);

            // Set the actual message
            message.setContent(text, "text/html");

            System.out.println("Sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

            // Return true indicating success
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            // Return false indicating failure
            return false;
        }
    }

    public void setData() {
        try {
            List<ModelData> lists = new ArrayList<>();
            String sql = "SELECT DATE_FORMAT(Finished_Date, '%Y-%M') AS 'Month' , SUM(Amount) AS Total_Amount, SUM(Cost) AS Total_Cost, SUM(Profit) AS Total_Profit FROM income GROUP BY DATE_FORMAT(Finished_Date, '%Y%M') ORDER BY Finished_Date DESC limit 7";
            try (PreparedStatement sn = CarCare_DB.MyCon().prepareStatement(sql)) {
                ResultSet result1 = sn.executeQuery();
                while (result1.next()) {
                    String month = result1.getString("Month");
                    double amount = result1.getDouble("Total_Amount");
                    double cost = result1.getDouble("Total_Cost");
                    double profit = result1.getDouble("Total_Profit");
                    lists.add(new ModelData(month, amount, cost, profit));
                }
                result1.close();
                sn.close();
                for (int i = lists.size() - 1; i >= 0; i--) {

                    ModelData d = lists.get(i);
                    chart.addData(new ModelChart(d.getMonth(), new double[]{d.getAmount(), d.getCost(), d.getProfit()}));
                }
                chart.start();
            }

        } catch (SQLException e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Menus.RoundPanel();
        panelShadow1 = new Menus.PanelShadow();
        jLabel1 = new javax.swing.JLabel();
        AE_Count = new javax.swing.JLabel();
        AE = new javax.swing.JLabel();
        panelShadow6 = new Menus.PanelShadow();
        chart = new Menus.CurveLineChart();
        panelShadow5 = new Menus.PanelShadow();
        panelCard = new Menus.RoundPanel();
        roundPanel4 = new Menus.RoundPanel();
        Income_R = new Menus.Button();
        Order_R = new Menus.Button();
        Supplier_R = new Menus.Button();
        Inventory_R = new Menus.Button();
        Vehicle_R = new Menus.Button();
        Employee_R = new Menus.Button();
        Customer_R = new Menus.Button();
        roundPanel3 = new Menus.RoundPanel();
        Selected_Notify = new Menus.ComboBoxSuggestion();
        CNotify_mail = new textfield.TextField();
        Notify_Party = new Menus.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        CMessage = new javax.swing.JTextArea();
        send = new Menus.Button();
        roundPanel2 = new Menus.RoundPanel();
        Reports = new Menus.GradientButton();
        Notify = new Menus.GradientButton();
        panelShadow2 = new Menus.PanelShadow();
        jLabel4 = new javax.swing.JLabel();
        TC_Count = new javax.swing.JLabel();
        TC = new javax.swing.JLabel();
        panelShadow3 = new Menus.PanelShadow();
        jLabel7 = new javax.swing.JLabel();
        AO_Count = new javax.swing.JLabel();
        AO = new javax.swing.JLabel();
        panelShadow4 = new Menus.PanelShadow();
        jLabel10 = new javax.swing.JLabel();
        TS_Count = new javax.swing.JLabel();
        TS = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        C_tabel = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        E_table = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        I_table = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        O_table = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        S_table = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        V_table = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        IC_table = new javax.swing.JTable();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(1200, 756));

        roundPanel1.setBackground(new java.awt.Color(102, 102, 102));
        roundPanel1.setPreferredSize(new java.awt.Dimension(1200, 756));
        roundPanel1.setRequestFocusEnabled(false);

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setPreferredSize(new java.awt.Dimension(310, 0));
        panelShadow1.setShadowType(Menus.ShadowType.BOT);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/employee.gif"))); // NOI18N

        AE_Count.setFont(new java.awt.Font("Montserrat", 0, 100)); // NOI18N
        AE_Count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AE_Count.setText("69");

        AE.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        AE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AE.setText("Active Employees");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AE_Count, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(AE_Count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        panelShadow6.setBackground(new java.awt.Color(51, 51, 51));
        panelShadow6.setShadowType(Menus.ShadowType.BOT);

        chart.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart.setForeground(new java.awt.Color(255, 255, 255));
        chart.setFillColor(true);
        chart.setFocusable(false);
        chart.setTitleFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N

        javax.swing.GroupLayout panelShadow6Layout = new javax.swing.GroupLayout(panelShadow6);
        panelShadow6.setLayout(panelShadow6Layout);
        panelShadow6Layout.setHorizontalGroup(
            panelShadow6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow6Layout.setVerticalGroup(
            panelShadow6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow6Layout.createSequentialGroup()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelShadow5.setBackground(new java.awt.Color(51, 51, 51));
        panelShadow5.setShadowType(Menus.ShadowType.BOT);

        panelCard.setLayout(new java.awt.CardLayout());

        roundPanel4.setBackground(new java.awt.Color(102, 102, 102));

        Income_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/financial-statement.png"))); // NOI18N
        Income_R.setText("Income Report");
        Income_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Income_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Income_R.setPreferredSize(new java.awt.Dimension(119, 57));
        Income_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Income_RActionPerformed(evt);
            }
        });

        Order_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checklist.png"))); // NOI18N
        Order_R.setText("Order Report");
        Order_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Order_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Order_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Order_RActionPerformed(evt);
            }
        });

        Supplier_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargo.png"))); // NOI18N
        Supplier_R.setText("Supplier Report");
        Supplier_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Supplier_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Supplier_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supplier_RActionPerformed(evt);
            }
        });

        Inventory_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory.png"))); // NOI18N
        Inventory_R.setText("Inventory Report");
        Inventory_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Inventory_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Inventory_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_RActionPerformed(evt);
            }
        });

        Vehicle_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/car.png"))); // NOI18N
        Vehicle_R.setText("Vehical Report");
        Vehicle_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Vehicle_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Vehicle_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vehicle_RActionPerformed(evt);
            }
        });

        Employee_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/goal.png"))); // NOI18N
        Employee_R.setText("Employee Report");
        Employee_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Employee_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Employee_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Employee_RActionPerformed(evt);
            }
        });

        Customer_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/customer-feedback.png"))); // NOI18N
        Customer_R.setText("Customer Report");
        Customer_R.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Customer_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Customer_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Customer_RActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Vehicle_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Supplier_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Income_R, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Order_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Inventory_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Employee_R, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Customer_R, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Income_R, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_R, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Inventory_R, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(Supplier_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Vehicle_R, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Employee_R, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Customer_R, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelCard.add(roundPanel4, "card1");

        roundPanel3.setBackground(new java.awt.Color(102, 102, 102));

        Selected_Notify.setBackground(new java.awt.Color(51, 51, 51));
        Selected_Notify.setBorder(null);
        Selected_Notify.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Selected_Notify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Selected_NotifyActionPerformed(evt);
            }
        });

        CNotify_mail.setBackground(new java.awt.Color(102, 102, 102));
        CNotify_mail.setForeground(new java.awt.Color(255, 255, 255));
        CNotify_mail.setCaretColor(new java.awt.Color(255, 255, 255));
        CNotify_mail.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        CNotify_mail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        CNotify_mail.setLabelText("Email");
        CNotify_mail.setLineColor(new java.awt.Color(255, 153, 0));
        CNotify_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNotify_mailActionPerformed(evt);
            }
        });

        Notify_Party.setBackground(new java.awt.Color(51, 51, 51));
        Notify_Party.setBorder(null);
        Notify_Party.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Select Notify Party", "Employee", "Customer" }));
        Notify_Party.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        Notify_Party.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Notify_PartyActionPerformed(evt);
            }
        });

        CMessage.setBackground(new java.awt.Color(51, 51, 51));
        CMessage.setColumns(20);
        CMessage.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        CMessage.setForeground(new java.awt.Color(255, 255, 255));
        CMessage.setRows(5);
        CMessage.setBorder(null);
        CMessage.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(CMessage);

        send.setBackground(new java.awt.Color(153, 204, 0));
        send.setForeground(new java.awt.Color(0, 0, 0));
        send.setText("Send");
        send.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CNotify_mail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Notify_Party, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Selected_Notify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Notify_Party, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Selected_Notify, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CNotify_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCard.add(roundPanel3, "card2");

        roundPanel2.setBackground(new java.awt.Color(102, 102, 102));

        Reports.setForeground(new java.awt.Color(255, 255, 255));
        Reports.setText("Reports");
        Reports.setEndGradientColor(new java.awt.Color(255, 102, 102));
        Reports.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Reports.setShadowColor(new java.awt.Color(0, 0, 0));
        Reports.setStartGradientColor(new java.awt.Color(255, 51, 102));
        Reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportsActionPerformed(evt);
            }
        });

        Notify.setForeground(new java.awt.Color(255, 255, 255));
        Notify.setText("Notify");
        Notify.setEndGradientColor(new java.awt.Color(153, 255, 0));
        Notify.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        Notify.setShadowColor(new java.awt.Color(0, 0, 0));
        Notify.setStartGradientColor(new java.awt.Color(102, 204, 0));
        Notify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Notify, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(Notify, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout panelShadow5Layout = new javax.swing.GroupLayout(panelShadow5);
        panelShadow5.setLayout(panelShadow5Layout);
        panelShadow5Layout.setHorizontalGroup(
            panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelShadow5Layout.setVerticalGroup(
            panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelShadow2.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow2.setShadowType(Menus.ShadowType.BOT);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/customers.gif"))); // NOI18N

        TC_Count.setFont(new java.awt.Font("Montserrat", 0, 100)); // NOI18N
        TC_Count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TC_Count.setText("69");

        TC.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        TC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TC.setText("Total Customers");

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TC_Count, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(TC_Count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        panelShadow3.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow3.setShadowType(Menus.ShadowType.BOT);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/order.gif"))); // NOI18N

        AO_Count.setFont(new java.awt.Font("Montserrat", 0, 100)); // NOI18N
        AO_Count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AO_Count.setText("69");

        AO.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        AO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AO.setText("Active Orders");

        javax.swing.GroupLayout panelShadow3Layout = new javax.swing.GroupLayout(panelShadow3);
        panelShadow3.setLayout(panelShadow3Layout);
        panelShadow3Layout.setHorizontalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AO_Count, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShadow3Layout.setVerticalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(AO_Count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        panelShadow4.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow4.setShadowType(Menus.ShadowType.BOT);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sales.gif"))); // NOI18N

        TS_Count.setFont(new java.awt.Font("Montserrat", 0, 100)); // NOI18N
        TS_Count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TS_Count.setText("69");

        TS.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        TS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TS.setText("Total Sales");

        javax.swing.GroupLayout panelShadow4Layout = new javax.swing.GroupLayout(panelShadow4);
        panelShadow4.setLayout(panelShadow4Layout);
        panelShadow4Layout.setHorizontalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TS_Count, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShadow4Layout.setVerticalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(TS_Count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

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
        jScrollPane2.setViewportView(C_tabel);

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
        jScrollPane3.setViewportView(E_table);

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
        jScrollPane4.setViewportView(I_table);

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
        jScrollPane5.setViewportView(O_table);

        S_table.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        S_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Supplier_ID", "Name", "NIC", "Address", "Contact_No", "Email"
            }
        ));
        S_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane6.setViewportView(S_table);

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
        jScrollPane7.setViewportView(V_table);

        IC_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order_ID", "Finished_Date", "Amount", "Cost", "Profit"
            }
        ));
        jScrollPane8.setViewportView(IC_table);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(panelShadow6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1384, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CountCustomers() {

        try {
            Statement searchStatement = CarCare_DB.MyCon().createStatement(); // Assuming MyCon() returns a Connection object
            String query = "SELECT COUNT(*) AS customerCount FROM customer";
            ResultSet result = searchStatement.executeQuery(query);
            while (result.next()) {
                int count = result.getInt("customerCount");
                TC_Count.setText(String.valueOf(count));

            }

        } catch (SQLException e) {
        }
    }

    private void CountActiveEmployees() {

        try {
            Statement searchStatement = CarCare_DB.MyCon().createStatement(); // Assuming MyCon() returns a Connection object
            String query = "SELECT (SELECT COUNT(Employee_ID) FROM orders WHERE Job_Status = 'NOT COMPLETED') AS NotCompletedOrdersEmployeeCount , (SELECT COUNT(*) FROM employee) AS employeeCount";
            ResultSet result2 = searchStatement.executeQuery(query);
            while (result2.next()) {

                int count1 = result2.getInt("employeeCount");
                int count2 = result2.getInt("NotCompletedOrdersEmployeeCount");
                AE_Count.setText(String.valueOf(count1 - count2));

            }

        } catch (SQLException e) {
        }
    }

    private void CountActiveOrders() {

        try {
            Statement searchStatement = CarCare_DB.MyCon().createStatement(); // Assuming MyCon() returns a Connection object
            String query = "SELECT COUNT(Order_ID) AS ActiveOrdersCount FROM orders WHERE Job_Status = 'NOT COMPLETED'";
            ResultSet result2 = searchStatement.executeQuery(query);
            while (result2.next()) {

                int count = result2.getInt("ActiveOrdersCount");
                AO_Count.setText(String.valueOf(count));

            }

        } catch (Exception e) {
        }
    }

    private void CountSales() {

        try {
            Statement searchStatement = CarCare_DB.MyCon().createStatement(); // Assuming MyCon() returns a Connection object
            String query = "SELECT COUNT(Order_ID) AS CountSales FROM orders WHERE Job_Status = 'COMPLETED'";
            ResultSet result2 = searchStatement.executeQuery(query);
            while (result2.next()) {
                int count = result2.getInt("CountSales");
                TS_Count.setText(String.valueOf(count));

            }

        } catch (SQLException e) {
        }
    }
    private void ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportsActionPerformed
        // TODO add your handling code here:
        cardLayout.show(panelCard, "card1");
    }//GEN-LAST:event_ReportsActionPerformed

    private void NotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifyActionPerformed
        // TODO add your handling code here:
        cardLayout.show(panelCard, "card2");

    }//GEN-LAST:event_NotifyActionPerformed

    private void Income_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Income_RActionPerformed
        // TODO add your handling code here:
        income_Report.GetDataJTable();
        income_Report.exportExcel(IC_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Income Report Printed Successfully !");
        notify.showNotification();


    }//GEN-LAST:event_Income_RActionPerformed


    private void Customer_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_RActionPerformed
        // TODO add your handling code here:
        customer_Report.GetDataJTable();
        customer_Report.exportExcel(C_tabel);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Customer Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_Customer_RActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        String TO_EMAIL = CNotify_mail.getText();
        String SUBJECT;

        if ("Customer".equals(Notify_Party.getSelectedItem())) {
            SUBJECT = "Carcare Service";
        } else {
            SUBJECT = "Carcare Services: New Order Assigned !";
        }

        String MESSAGE = CMessage.getText();

        
        boolean result = sendMail(TO_EMAIL, SUBJECT, MESSAGE);

        if (result) {
            //JOptionPane.showMessageDialog(null, "Message Sent Successfully");
            Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.MAIL, Notification.Location.TOP_CENTER, "The Mail sended Successfully !");
            notify.showNotification();
            String selectedItem = (String) Selected_Notify.getSelectedItem();

            if (selectedItem != null) {
                // Remove the selected item from the Selected_Notify ComboBoxSuggestion
                Selected_Notify.removeItem(selectedItem);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error: Unable to send the message");
        }


    }//GEN-LAST:event_sendActionPerformed

    private void CNotify_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNotify_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CNotify_mailActionPerformed

    private void Notify_PartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Notify_PartyActionPerformed
        // TODO add your handling code here:
        try {
            if ("Customer".equals(Notify_Party.getSelectedItem())) {
                String sql = "SELECT Customer FROM orders WHERE Job_Status = 'COMPLETED'";
                try (PreparedStatement notify = CarCare_DB.MyCon().prepareStatement(sql)) {
                    ResultSet result2 = notify.executeQuery();
                    Selected_Notify.removeAllItems();
                    while (result2.next()) {
                        String notifyParty = result2.getString("Customer");
                        Selected_Notify.addItem(notifyParty);

                    }
                }

            } else if ("Employee".equals(Notify_Party.getSelectedItem())) {
                String sql = "SELECT Assigned_Employee FROM orders WHERE Job_Status = 'NOT COMPLETED'";
                try (Statement notify = CarCare_DB.MyCon().createStatement()) {
                    ResultSet result3 = notify.executeQuery(sql);
                    Selected_Notify.removeAllItems();
                    while (result3.next()) {
                        String notifyParty = result3.getString("Assigned_Employee");
                        Selected_Notify.addItem(notifyParty);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }//GEN-LAST:event_Notify_PartyActionPerformed

    private void Selected_NotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Selected_NotifyActionPerformed
        // TODO add your handling code here:

        try {
            if ("Customer".equals(Notify_Party.getSelectedItem())) {
                String selectedCustomer = Selected_Notify.getSelectedItem().toString();
                String sql = "SELECT customer.Email, orders.Order_ID,License_Plate_No,Model FROM orders JOIN customer ON orders.Customer = customer.Name WHERE orders.Customer = ?";
                try (PreparedStatement notify = CarCare_DB.MyCon().prepareStatement(sql)) {
                    notify.setString(1, selectedCustomer);
                    ResultSet result2 = notify.executeQuery();
                    CNotify_mail.setText("");
                    if (result2.next()) {
                        String notifyParty_mail = result2.getString("Email");
                        String OID = result2.getString("Order_ID");
                        String vehicleLicensePlate = result2.getString("License_Plate_No");
                        String Model = result2.getString("Model");
                        CNotify_mail.setText(notifyParty_mail);
                        CMessage.setText("<html><span style='font-family: Montserrat, sans-serif;'><b><font size='5'>Hello <span style='color: orange;'>" + selectedCustomer + "</span>!</font></b><br><br><b>Order ID:</b> <b>" + OID + "</b><font size='3'></font><br><br>Your Vehicle <b>" + Model + "-" + vehicleLicensePlate + "</b> is ready to collect.<br>Thank you for choosing Carcare(Pvt)Ltd !<br><br>Best Regards,<br>Carcare Team</span></html>");
                    }
                }

            } else if ("Employee".equals(Notify_Party.getSelectedItem())) {
                String selectedEmployee = Selected_Notify.getSelectedItem().toString();
                String sql = "SELECT employee.Email, employee.Employee_ID, orders.Order_ID,License_Plate_No,Model FROM orders JOIN employee ON orders.Assigned_Employee = employee.Name WHERE orders.Assigned_Employee = ?";
                try (PreparedStatement notify = CarCare_DB.MyCon().prepareStatement(sql)) {
                    notify.setString(1, selectedEmployee);
                    ResultSet result2 = notify.executeQuery();
                    CNotify_mail.setText("");
                    if (result2.next()) {
                        String notifyParty_mail = result2.getString("Email");
                        String EID = result2.getString("Employee_ID");
                        String OID = result2.getString("Order_ID");
                        String vehicleLicensePlate = result2.getString("License_Plate_No");
                        String Model = result2.getString("Model");
                        CNotify_mail.setText(notifyParty_mail);
                        CMessage.setText("<html><span style='font-family: Montserrat, sans-serif;'><b><font size='5'><span style='color: black;'>" + selectedEmployee + "</span></font></b><br><br><b><font size='4'>EID:</font></b> <b><font size='4'>" + EID + "</font></b><br><b><font size='4'>Order ID:</font></b> <b><font size='4'>" + OID + "</font></b><font size='3'></font><br><br>An order has been assigned for the vehicle <b>" + Model + "-" + vehicleLicensePlate + "</b>.<br>Please proceed with the necessary tasks.<br><br><b>Best Regards,<br>Carcare Team</b></span></html>");
                    }
                }

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_Selected_NotifyActionPerformed

    private void Order_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Order_RActionPerformed
        // TODO add your handling code here:
        order_Report.GetDataJTable();
        order_Report.exportExcel(O_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Order Report Printed Successfully !");
        notify.showNotification();

    }//GEN-LAST:event_Order_RActionPerformed

    private void Supplier_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supplier_RActionPerformed
        // TODO add your handling code here:
        supplier_Report.GetDataJTable();
        supplier_Report.exportExcel(S_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Supplier Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_Supplier_RActionPerformed

    private void Inventory_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_RActionPerformed
        // TODO add your handling code here:
        inventory_Report.GetDataJTable();
        inventory_Report.exportExcel(I_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Inventory Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_Inventory_RActionPerformed

    private void Vehicle_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vehicle_RActionPerformed
        // TODO add your handling code here:
        vehical_Report.GetDataJTable();
        vehical_Report.exportExcel(V_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Vehicle Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_Vehicle_RActionPerformed

    private void Employee_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Employee_RActionPerformed
        // TODO add your handling code here:
        employee_Report.GetDataJTable();
        employee_Report.exportExcel(E_table);
        Notification notify = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.PRINT, Notification.Location.TOP_CENTER, "Employee Report Printed Successfully !");
        notify.showNotification();
    }//GEN-LAST:event_Employee_RActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AE;
    private javax.swing.JLabel AE_Count;
    private javax.swing.JLabel AO;
    private javax.swing.JLabel AO_Count;
    private javax.swing.JTextArea CMessage;
    private textfield.TextField CNotify_mail;
    private javax.swing.JTable C_tabel;
    private Menus.Button Customer_R;
    private javax.swing.JTable E_table;
    private Menus.Button Employee_R;
    private javax.swing.JTable IC_table;
    private javax.swing.JTable I_table;
    private Menus.Button Income_R;
    private Menus.Button Inventory_R;
    private Menus.GradientButton Notify;
    private Menus.ComboBoxSuggestion Notify_Party;
    private javax.swing.JTable O_table;
    private Menus.Button Order_R;
    private Menus.GradientButton Reports;
    private javax.swing.JTable S_table;
    private Menus.ComboBoxSuggestion Selected_Notify;
    private Menus.Button Supplier_R;
    private javax.swing.JLabel TC;
    private javax.swing.JLabel TC_Count;
    private javax.swing.JLabel TS;
    private javax.swing.JLabel TS_Count;
    private javax.swing.JTable V_table;
    private Menus.Button Vehicle_R;
    private Menus.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private Menus.RoundPanel panelCard;
    private Menus.PanelShadow panelShadow1;
    private Menus.PanelShadow panelShadow2;
    private Menus.PanelShadow panelShadow3;
    private Menus.PanelShadow panelShadow4;
    private Menus.PanelShadow panelShadow5;
    private Menus.PanelShadow panelShadow6;
    private Menus.RoundPanel roundPanel1;
    private Menus.RoundPanel roundPanel2;
    private Menus.RoundPanel roundPanel3;
    private Menus.RoundPanel roundPanel4;
    private Menus.Button send;
    // End of variables declaration//GEN-END:variables
}
