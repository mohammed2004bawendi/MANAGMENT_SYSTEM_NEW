package org.example.models;

import java.sql.*;
import java.util.ArrayList;

public class OdaDAO {

    // إنشاء جدول الغرف
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS oda (" +
                "odaNumarasi INTEGER PRIMARY KEY, " +
                "kapasite INTEGER, " +
                "fiyat REAL, " +
                "durum TEXT, " +
                "manzara TEXT)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Oda table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating Oda table: " + e.getMessage());
        }
    }

    // إضافة غرفة
    public static void insertOda(OdaDTO oda) {
        String sql = "INSERT INTO oda (odaNumarasi, kapasite, fiyat, durum, manzara) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oda.getOdaNumarasi());
            stmt.setInt(2, oda.getKapasite());
            stmt.setDouble(3, oda.getFiyat());
            stmt.setString(4, oda.getDurum());
            stmt.setString(5, oda.getManzara());
            stmt.executeUpdate();
            System.out.println("Oda added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding Oda: " + e.getMessage());
        }
    }

    // تحديث حالة الغرفة
    public static void updateOdaStatus(int odaNumarasi, String yeniDurum) {
        String sql = "UPDATE oda SET durum = ? WHERE odaNumarasi = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, yeniDurum);
            stmt.setInt(2, odaNumarasi);
            int updatedRows = stmt.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Oda status updated successfully.");
            } else {
                System.out.println("Oda not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating Oda status: " + e.getMessage());
        }
    }

    // التحقق مما إذا كانت الغرفة محجوزة
    public static boolean isOdaBooked(int odaNumarasi) {
        String sql = "SELECT durum FROM oda WHERE odaNumarasi = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, odaNumarasi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String durum = rs.getString("durum");
                    return durum != null && durum.equalsIgnoreCase("dolu");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if Oda is booked: " + e.getMessage());
        }
        return false;
    }

    // البحث عن غرفة
    public static OdaDTO findOda(int odaNumarasi) {
        String sql = "SELECT * FROM oda WHERE odaNumarasi = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, odaNumarasi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OdaDTO(
                            rs.getInt("odaNumarasi"),
                            rs.getInt("kapasite"),
                            rs.getDouble("fiyat"),
                            rs.getString("durum"),
                            rs.getString("manzara")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding Oda: " + e.getMessage());
        }
        return null;
    }

    // حذف غرفة
    public static void deleteOda(int odaNumarasi) {
        String sql = "DELETE FROM oda WHERE odaNumarasi = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, odaNumarasi);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Oda deleted successfully.");
            } else {
                System.out.println("Oda not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting Oda: " + e.getMessage());
        }
    }

    // التحقق مما إذا كانت الغرفة موجودة
    public static boolean isOdaExists(int odaNumarasi) {
        String sql = "SELECT COUNT(*) FROM oda WHERE odaNumarasi = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, odaNumarasi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if Oda exists: " + e.getMessage());
        }
        return false;
    }

    // استرداد جميع الغرف
    public static ArrayList<OdaDTO> getAllOdaAsList() {
        ArrayList<OdaDTO> odaList = new ArrayList<>();
        String sql = "SELECT * FROM oda";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                OdaDTO oda = new OdaDTO(
                        rs.getInt("odaNumarasi"),
                        rs.getInt("kapasite"),
                        rs.getDouble("fiyat"),
                        rs.getString("durum"),
                        rs.getString("manzara")
                );
                odaList.add(oda);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Oda list: " + e.getMessage());
        }
        return odaList;
    }
}
