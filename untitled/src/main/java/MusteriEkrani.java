import org.example.models.MusteriRezervasyonGorEkrani;
import org.example.models.OdaListeleEkrani;
import org.example.models.RezervasyonSilEkrani;
import org.example.models.RezervasyonYapEkrani;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MusteriEkrani extends JFrame {
    private JFrame previousFrame;

    public MusteriEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Müşteri Ekranı");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Menü çubuğu oluştur
        JMenuBar menuBar = new JMenuBar();

        // Geri Butonu
        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener((ActionEvent e) -> {
            previousFrame.setVisible(true);
            dispose();
        });

        menuBar.add(backButton);
        setJMenuBar(menuBar);

        // Butonlar
        JButton rezervasyonGorButton = new JButton("Rezervasyonlarımı Gör");
        JButton odaListeleButton = new JButton("Tüm Odaları Gör");
        JButton rezervasyonSilButton = new JButton("Rezervasyon Sil");
        JButton rezervasyonYapButton = new JButton("Rezervasyon Yap"); // زر الحجز الأساسي

        // Butonların konumları
        rezervasyonGorButton.setBounds(100, 30, 200, 40);
        odaListeleButton.setBounds(100, 100, 200, 40);
        rezervasyonSilButton.setBounds(100, 170, 200, 40);
        rezervasyonYapButton.setBounds(100, 240, 200, 40); // موقع زر الحجز

        // Butonları eklemek
        add(rezervasyonGorButton);
        add(odaListeleButton);
        add(rezervasyonSilButton);
        add(rezervasyonYapButton); // إضافة زر الحجز

        // Rezervasyonları Gör butonuna tıklandığında rezervasyonları görüntüleme ekranını aç
        rezervasyonGorButton.addActionListener(e -> {
            new MusteriRezervasyonGorEkrani(this);
            dispose();
        });

        // Tüm Odaları Gör butonuna tıklandığında oda listeleme ekranını aç
        odaListeleButton.addActionListener(e -> {
            new OdaListeleEkrani(this);
            dispose();
        });

        // Rezervasyon Sil butonuna tıklandığında rezervasyon silme ekranını aç
        rezervasyonSilButton.addActionListener(e -> {
            new RezervasyonSilEkrani(this);
            dispose();
        });

        // Rezervasyon Yap butonuna tıklandığında rezervasyon yapma ekranını aç
        rezervasyonYapButton.addActionListener(e -> {
            new RezervasyonYapEkrani(this);
            dispose();
        });

        setVisible(true);
    }
}