/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utill;

import DataBase.CarCare_DB;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Oshan
 */
public class RegX {

    private static final String NIC_PATTERN = "[0-9]{9}[vV]$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    private static final String MOBILE_LANDLINE_PATTERN = "^(077|076|071|072|078|075)\\d{7}$|^0[1-9]\\d{8}$";
    private static final String VEHICLE_MODEL_PATTERN = "^[A-Z][a-zaA-Z]*\\s[A-Z][a-zA-Z]*$";
    private static final String MODEL_YEAR = "^(200[0-9]|201[0-9]|202[0-4])$";
    private static final String MANUFACTUERER_PATTERN = "^[A-Z][a-zA-Z0-9]*$";

    public static boolean validateNIC(String nic) {
        Pattern pattern = Pattern.compile(NIC_PATTERN);
        Matcher matcher = pattern.matcher(nic);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateContact(String number) {

        Pattern pattern = Pattern.compile(MOBILE_LANDLINE_PATTERN);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static boolean validateModel(String model) {

        Pattern pattern = Pattern.compile(VEHICLE_MODEL_PATTERN);
        Matcher matcher = pattern.matcher(model);
        return matcher.matches();
    }

    public static boolean validateYear(String year) {

        Pattern pattern = Pattern.compile(MODEL_YEAR);
        Matcher matcher = pattern.matcher(year);
        return matcher.matches();
    }

    public static boolean validateManufacturer(String Manufacturer) {

        Pattern pattern = Pattern.compile(MANUFACTUERER_PATTERN);
        Matcher matcher = pattern.matcher(Manufacturer);
        return matcher.matches();
    }

    public static boolean isCustomerExists(String value, String column) {
        try {
            value = value.replaceAll("\\s", "");
            // Dynamically generate the SQL query based on the specified column
            String query = "SELECT 1 FROM customer WHERE " + column + " = ?";
            try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement(query)) {
                checkIfExists.setString(1, value);
                try (ResultSet resultSet = checkIfExists.executeQuery()) {
                    return resultSet.next(); // If the result set has at least one row, the customer exists
                }
            }
        } catch (SQLException e) {
            // Handle or log the SQL exception
            e.printStackTrace();
            return false; // Assuming it's not existing in case of an exception
        }
    }

    public static boolean isEmployeeExists(String value, String column) {
        try {
            value = value.replaceAll("\\s", "");
            // Dynamically generate the SQL query based on the specified column
            String query = "SELECT 1 FROM employee WHERE " + column + " = ?";
            try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement(query)) {
                checkIfExists.setString(1, value);
                try (ResultSet resultSet = checkIfExists.executeQuery()) {
                    return resultSet.next(); // If the result set has at least one row, the customer exists
                }
            }
        } catch (SQLException e) {
            // Handle or log the SQL exception
            e.printStackTrace();
            return false; // Assuming it's not existing in case of an exception
        }
    }

    public static boolean isVehicleExists(String model, String year) {
        try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement("SELECT 1 FROM vehicles WHERE Model = ? AND Year = ?")) {
            checkIfExists.setString(1, model);
            checkIfExists.setString(2, year);
            try (ResultSet resultSet = checkIfExists.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            throw new RuntimeException("Error checking vehicle existence", e);
        }
    }

    public static boolean isSupplierExists(String value, String column) {
        try {
            //value = value.replaceAll("\\s", "");
            // Dynamically generate the SQL query based on the specified column
            String query = "SELECT 1 FROM Suppliers WHERE " + column + " = ?";
            try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement(query)) {
                checkIfExists.setString(1, value);
                try (ResultSet resultSet = checkIfExists.executeQuery()) {
                    return resultSet.next(); // If the result set has at least one row, the customer exists
                }
            }
        } catch (SQLException e) {
            // Handle or log the SQL exception
            e.printStackTrace();
            return false; // Assuming it's not existing in case of an exception
        }
    }

    public static boolean isNICExistsInDatabase(String value) {
        try {
            value = value.replaceAll("\\s", "");

            // Query to retrieve all table and column names
            String query = "SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND COLUMN_NAME LIKE '%NIC%'"; // Modified query
            try (PreparedStatement getColumns = CarCare_DB.MyCon().prepareStatement(query)) {
                getColumns.setString(1, CarCare_DB.getDatabaseName());
                try (ResultSet columnsResult = getColumns.executeQuery()) {
                    while (columnsResult.next()) {
                        String tableName = columnsResult.getString("TABLE_NAME");
                        String columnName = columnsResult.getString("COLUMN_NAME");

                        // Check existence in each column
                        query = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ?";
                        try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement(query)) {
                            checkIfExists.setString(1, value);
                            try (ResultSet resultSet = checkIfExists.executeQuery()) {
                                if (resultSet.next()) {
                                    return true; // NIC exists in the current column
                                }
                            }
                        }
                    }
                }
            }

            // NIC not found in any NIC column
            return false;
        } catch (SQLException e) {
            // Handle or log the SQL exception
            e.printStackTrace();
            return false; // Assuming it's not existing in case of an exception
        }
    }

    public static String getDatabaseName() {
        return "carcare"; // Replace with your actual database name if different
    }

    /*public static boolean isCustomerExists(String nic) {
        try {
            nic = nic.replaceAll("\\s", "");
            // Query the database to check if a customer with the given NIC already exists
            try (PreparedStatement checkIfExists = CarCare_DB.MyCon().prepareStatement("SELECT 1 FROM customer WHERE NIC = ?")) {
                checkIfExists.setString(1, nic);
                try (ResultSet resultSet = checkIfExists.executeQuery()) {
                    return resultSet.next(); // If the result set has at least one row, the customer exists
                }
            }
        } catch (SQLException e) {
            // Handle or log the SQL exception
            e.printStackTrace();
            return false; // Assuming it's not existing in case of an exception
        }              
    }*/
}
