/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import DataBase.CarCare_DB;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oshan
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderCalculator {

    public static double calculateTotal(List<Object> selectedParts) {
        double total = 0;
        double profitMarginPercentage = 0.45;

        // Iterate through the selected parts and fetch prices from the Inventory Table
        for (Object selectedPart : selectedParts) {
            String partName = selectedPart.toString();
            double partPrice = getPartPrice(partName);
            
            double partProfit = partPrice * profitMarginPercentage;

            // Add the part price to the total
            total += partPrice+partProfit;
        }
        

        return total;
    }

    private static double getPartPrice(String partName) {
        // Fetch the price of the part from the Inventory Table using the part name
        // Replace this with your actual database query
        String query = "SELECT Price FROM inventory WHERE Part_Name = ?";
        try (PreparedStatement preparedStatement = CarCare_DB.MyCon().prepareStatement(query)) {
            preparedStatement.setString(1, partName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double price = resultSet.getDouble("Price");
                    return price;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Default to 0 if the price is not found
        return 0;
    }
}

