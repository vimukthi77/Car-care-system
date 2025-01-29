/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import DataBase.CarCare_DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Oshan
 */
public class CostCalculator {
    public static double calculateCost(List<Object> selectedParts) {
        double cost = 0;
        

        // Iterate through the selected parts and fetch prices from the Inventory Table
        for (Object selectedPart : selectedParts) {
            String partName = selectedPart.toString();
            double partPrice = getPartPrice(partName);
            
            

            // Add the part price to the total
            cost += partPrice;
        }

        
        return cost;
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
