import org.example.models.OdaYonetimiEkrani;
import org.example.models.RezervasyonListeEkrani;
import org.example.models.RezervasyonSilEkrani;
import org.example.models.RezervasyonYapEkrani;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResepsiyonEkrani extends JFrame {
    private JFrame previousFrame;

    public ResepsiyonEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Resepsiyon Ekranı");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Butonların manuel konumlandırılması

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
            new ResepsiyonEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğuna butonları ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        // Menü çubuğunu pencereye ekle
        setJMenuBar(menuBar);

        // Butonlar
        JButton odaYonetimiButton = new JButton("Oda Yönetimi");
        JButton rezervasyonYapButton = new JButton("Rezervasyon Yap");
        JButton rezervasyonSilButton = new JButton("Rezervasyon Sil");
        JButton rezervasyonListeleButton = new JButton("Tüm Rezervasyonları Gör");

        // Butonların konumları
        odaYonetimiButton.setBounds(100, 30, 200, 40);
        rezervasyonYapButton.setBounds(100, 80, 200, 40);
        rezervasyonSilButton.setBounds(100, 130, 200, 40);
        rezervasyonListeleButton.setBounds(100, 180, 200, 40);

        // Butonları eklemek
        add(odaYonetimiButton);
        add(rezervasyonYapButton);
        add(rezervasyonSilButton);
        add(rezervasyonListeleButton);

        // Oda Yönetimi butonuna tıklandığında oda yönetimi ekranını aç
        odaYonetimiButton.addActionListener(e -> {
            new OdaYonetimiEkrani(this);
            dispose();
        });

        // Rezervasyon Yap butonuna tıklandığında rezervasyon yapma ekranını aç
        rezervasyonYapButton.addActionListener(e -> {
            new RezervasyonYapEkrani(this);
            dispose();
        });

        // Rezervasyon Sil butonuna tıklandığında rezervasyon silme ekranını aç
        rezervasyonSilButton.addActionListener(e -> {
            new RezervasyonSilEkrani(this);
            dispose();
        });

        // Tüm Rezervasyonları Gör butonuna tıklandığında listeleme ekranını aç
        rezervasyonListeleButton.addActionListener(e -> {
            new RezervasyonListeEkrani(this);
            dispose();
        });

        setVisible(true); // Ekranı görünür hale getir
    }
}
