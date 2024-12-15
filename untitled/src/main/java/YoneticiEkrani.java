import org.example.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class YoneticiEkrani extends JFrame {
    private JFrame previousFrame;

    public YoneticiEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Yönetici Ekranı");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            new YoneticiEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğuna butonları ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        // Menü çubuğunu pencereye ekle
        setJMenuBar(menuBar);

        // Butonlar
        JButton calisanEkleButton = new JButton("Çalışan Ekle");
        JButton calisanSilButton = new JButton("Çalışan Sil");
        JButton calisanListeleButton = new JButton("Çalışanları Listele");
        JButton odaEkleButton = new JButton("Oda Ekle");
        JButton odaSilButton = new JButton("Oda Sil");
        JButton odaListeleButton = new JButton("Oda Listele");
        JButton rezervasyonYapButton = new JButton("Rezervasyon yap");
        JButton rezervasyonGorButton = new JButton("Rezervasyonları Gör");
        JButton rezervasyonSilButton = new JButton("Rezervasyon Sil");

        // Butonların konumları
        calisanEkleButton.setBounds(300, 100, 200, 40);
        calisanSilButton.setBounds(300, 170, 200, 40);
        calisanListeleButton.setBounds(300, 240, 200, 40);
        odaEkleButton.setBounds(300, 310, 200, 40);
        odaSilButton.setBounds(300, 380, 200, 40);
        odaListeleButton.setBounds(300, 450, 200, 40);
        rezervasyonYapButton.setBounds(300, 520, 200, 40);
        rezervasyonGorButton.setBounds(300, 590, 200, 40);
        rezervasyonSilButton.setBounds(300, 660, 200, 40);

        // Butonları pencereye ekle
        add(calisanEkleButton);
        add(calisanSilButton);
        add(calisanListeleButton);
        add(odaEkleButton);
        add(odaSilButton);
        add(odaListeleButton);
        add(rezervasyonYapButton);
        add(rezervasyonGorButton);
        add(rezervasyonSilButton);
        add(rezervasyonSilButton);

        // Çalışan Ekleme butonuna tıklandığında çalışan ekleme ekranını aç
        calisanEkleButton.addActionListener(e -> {
            new CalisanEkleEkrani(this);
            dispose();});

        // Çalışan Silme butonuna tıklandığında çalışan silme ekranını aç
        calisanSilButton.addActionListener(e -> {
            new CalisanSilEkrani(this);
            dispose();
        });

        calisanListeleButton.addActionListener(e -> {
            new CalisanListeleEkrani(this);
            dispose();
        });

        // Oda Ekleme butonuna tıklandığında oda ekleme ekranını aç
        odaEkleButton.addActionListener(e -> {
            new OdaEkleEkrani(this);
            dispose();
        });

        // Oda Silme butonuna tıklandığında oda silme ekranını aç
        odaSilButton.addActionListener(e -> {
            new OdaSilEkrani(this);
            dispose();
        });

        odaListeleButton.addActionListener(e -> {
            new OdaListeleEkrani(this);
        });

        rezervasyonYapButton.addActionListener(e -> {
            new RezervasyonYapEkrani(this);
            dispose();
        });

        // Rezervasyonları Görme butonuna tıklandığında rezervasyon listeleme ekranını aç
        rezervasyonGorButton.addActionListener(e -> {
            new RezervasyonListeEkrani(this);
            dispose();
        });

        rezervasyonSilButton.addActionListener(e -> {
            new RezervasyonSilEkrani(this); // افتح شاشة حذف الحجز
            dispose(); // أغلق الشاشة الحالية
        });


        setVisible(true);
    }
}
