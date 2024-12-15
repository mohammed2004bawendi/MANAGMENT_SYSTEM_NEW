package org.example.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonDAO {

    // إنشاء جدول الحجوزات
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS rezervasyon (" +
                "rezervasyonNo REAL PRIMARY KEY, " +
                "isimSoyisim TEXT, " +
                "tcKimlik TEXT, " +
                "kalinacakTarihler TEXT, " +
                "odaBilgisi INTEGER" +
                ");";

        try (Connection conn = ConnectDB.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Rezervasyon table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating Rezervasyon table: " + e.getMessage());
        }
    }

    // إضافة حجز
    public static void insertRezervasyon(Rezervasyon rezervasyon) {
        String sql = "INSERT INTO rezervasyon (rezervasyonNo, isimSoyisim, tcKimlik, kalinacakTarihler, odaBilgisi) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, rezervasyon.getRezervasyonNo());
            stmt.setString(2, rezervasyon.getIsimSoyisim());
            stmt.setString(3, rezervasyon.getTcKimlik());
            stmt.setString(4, rezervasyon.getKalinacakTarihler());
            stmt.setInt(5, rezervasyon.getOdaBilgisi());

            stmt.executeUpdate();
            System.out.println("Rezervasyon added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding Rezervasyon: " + e.getMessage());
        }
    }

    // حذف حجز
    public static void deleteRezervasyon(double rezervasyonNo) {
        String sql = "DELETE FROM rezervasyon WHERE rezervasyonNo = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, rezervasyonNo);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Rezervasyon deleted successfully.");
            } else {
                System.out.println("Rezervasyon not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting Rezervasyon: " + e.getMessage());
        }
    }

    // جلب جميع الحجوزات
    public static List<Rezervasyon> getAllRezervasyon() {
        List<Rezervasyon> rezervasyonList = new ArrayList<>();
        String sql = "SELECT * FROM rezervasyon";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rezervasyon rezervasyon = new Rezervasyon(
                        rs.getDouble("rezervasyonNo"),
                        rs.getString("isimSoyisim"),
                        rs.getString("tcKimlik"),
                        rs.getString("kalinacakTarihler"),
                        rs.getInt("odaBilgisi")
                );
                rezervasyonList.add(rezervasyon);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Rezervasyon: " + e.getMessage());
        }
        return rezervasyonList;
    }

    // جلب الحجز بناءً على رقم الحجز
    public static Rezervasyon getRezervasyonByNo(double rezervasyonNo) {
        String sql = "SELECT * FROM rezervasyon WHERE rezervasyonNo = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, rezervasyonNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Rezervasyon(
                            rs.getDouble("rezervasyonNo"),
                            rs.getString("isimSoyisim"),
                            rs.getString("tcKimlik"),
                            rs.getString("kalinacakTarihler"),
                            rs.getInt("odaBilgisi")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Rezervasyon by No: " + e.getMessage());
        }
        return null;
    }

    // جلب الحجوزات بناءً على رقم الكملك
    public static List<Rezervasyon> getRezervasyonByTcKimlik(String tcKimlik) {
        List<Rezervasyon> rezervasyonList = new ArrayList<>();
        String sql = "SELECT * FROM rezervasyon WHERE tcKimlik = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tcKimlik);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rezervasyon rezervasyon = new Rezervasyon(
                            rs.getDouble("rezervasyonNo"),
                            rs.getString("isimSoyisim"),
                            rs.getString("tcKimlik"),
                            rs.getString("kalinacakTarihler"),
                            rs.getInt("odaBilgisi")
                    );
                    rezervasyonList.add(rezervasyon);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Rezervasyon by TcKimlik: " + e.getMessage());
        }
        return rezervasyonList;
    }
}
