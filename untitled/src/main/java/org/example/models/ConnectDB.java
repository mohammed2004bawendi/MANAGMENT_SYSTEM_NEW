package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:sqlite:mydb.db"; // مسار قاعدة البيانات
                connection = DriverManager.getConnection(url);
                System.out.println("Connection Successful");
            }
        } catch (SQLException e) {
            System.err.println("Error Connecting to Database: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection Closed");
            }
        } catch (SQLException e) {
            System.err.println("Error Closing Connection: " + e.getMessage());
        }
    }
}
