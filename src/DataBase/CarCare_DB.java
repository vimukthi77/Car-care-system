/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Oshan
 */
public class CarCare_DB {
    
    public static Connection MyCon() throws SQLException {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carcare", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
           
        }

        return con;
    }
    
    public static String getDatabaseName() {
        return "carcare"; // Replace with your actual database name if different
    }

    
}
