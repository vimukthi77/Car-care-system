package calculator;

import DataBase.CarCare_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfitCalculator {

    public static double calculateProfit(double amount, double cost) {
        return amount - cost;
    }

    public static double calculateProfitFromDatabase(int orderId) {
        double profit = 0;

        // Fetch Amount and Cost from the income table for the specified Order_ID
        String selectSQL = "SELECT Amount, Cost FROM income WHERE Order_ID = ?";

        try (PreparedStatement selectStatement = CarCare_DB.MyCon().prepareStatement(selectSQL)) {
            selectStatement.setInt(1, orderId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    double amount = resultSet.getDouble("Amount");
                    double cost = resultSet.getDouble("Cost");

                    profit = calculateProfit(amount, cost);
                } else {
                    System.out.println("No data found for Order_ID: " + orderId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profit;
    }
}

