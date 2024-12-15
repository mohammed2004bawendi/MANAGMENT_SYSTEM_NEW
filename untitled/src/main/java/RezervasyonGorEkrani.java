import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class RezervasyonGorEkrani extends JFrame {
    private JFrame previousFrame;
    public RezervasyonGorEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Rezervasyonları Gör");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Menü çubuğu oluştur
        JMenuBar menuBar = new JMenuBar();

        // Geri Butonu
        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener((ActionEvent e) -> {
            previousFrame.setVisible(true); // Önceki ekranı göster
            dispose(); // Mevcut ekranı kapat
        });

        // Yenile Butonu
        JButton refreshButton = new JButton("🔄 Yenile");
        refreshButton.addActionListener((ActionEvent e) -> {
            new RezervasyonGorEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğuna butonları ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        // Menü çubuğunu pencereye ekle
        setJMenuBar(menuBar);

        // Bileşenler
        JLabel tcKimlikLabel = new JLabel("TC Kimlik Numarası:");
        JTextField tcKimlikField = new JTextField();
        JButton gorButton = new JButton("Rezervasyonu Gör");

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 80, 350, 250);
        textArea.setEditable(false);

        tcKimlikLabel.setBounds(20, 20, 150, 30);
        tcKimlikField.setBounds(170, 20, 150, 30);
        gorButton.setBounds(100, 50, 200, 30);

        add(tcKimlikLabel);
        add(tcKimlikField);
        add(gorButton);
        add(textArea);

        // Rezervasyonu Gör butonuna tıklandığında
        gorButton.addActionListener(e -> {
            String tcKimlik = tcKimlikField.getText();  // Kullanıcıdan TC Kimlik Numarasını alıyoruz

            // Veritabanına bağlanarak bu TC Kimlik Numarası'na ait rezervasyonları çekiyoruz
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/otel_db", "root", "password")) {
                String query = "SELECT * FROM rezervasyon WHERE tcKimlik = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tcKimlik);  // TC Kimlik Numarası ile sorgulama

                ResultSet rs = stmt.executeQuery();

                // Eğer veritabanından rezervasyon varsa, ekrana yazdırıyoruz
                if (rs.next()) {
                    textArea.setText(""); // Önceki verileri temizle
                    do {
                        textArea.append("Rezervasyon No: " + rs.getDouble("rezervasyonNo") + "\n");
                        textArea.append("Oda No: " + rs.getInt("odaBilgisi") + ", Tarih: " + rs.getString("kalinacakTarihler") + "\n\n");
                    } while (rs.next());
                } else {
                    textArea.setText("Bu TC Kimlik Numarası ile yapılmış bir rezervasyon bulunamadı.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + ex.getMessage());
            }
        });

        setVisible(true); // Ekranı görünür hale getir
    }
}

