package org.example.models;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();
        if (conn != null) {
            System.out.println("Database connection is ready!");
        }
        db.closeConnection();
    }
}
