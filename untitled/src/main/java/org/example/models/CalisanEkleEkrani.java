package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalisanEkleEkrani extends JFrame {
    private JFrame previousFrame;

    public CalisanEkleEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Çalışan Ekle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Menü çubuğu oluştur
        JMenuBar menuBar = new JMenuBar();

        // Geri Butonu
        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener((ActionEvent e) -> {
            previousFrame.setVisible(true); // Önceki ekranı göster
            dispose(); // Mevcut ekranı kapات
        });

        // Yenile Butonu
        JButton refreshButton = new JButton("🔄 Yenile");
        refreshButton.addActionListener((ActionEvent e) -> {
            new CalisanEkleEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğuna butonları ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        // Menü çubuğunu pencereye ekle
        setJMenuBar(menuBar);

        // Bileşenler
        JLabel isimLabel = new JLabel("İsim Soyisim:");
        JTextField isimField = new JTextField();
        JLabel kimlikLabel = new JLabel("Kimlik:");
        JTextField kimlikField = new JTextField();
        JLabel gorevLabel = new JLabel("Görev:");
        JTextField gorevField = new JTextField();
        JLabel maasLabel = new JLabel("Maaş:");
        JTextField maasField = new JTextField();

        JButton ekleButton = new JButton("Ekle");

        isimLabel.setBounds(20, 20, 100, 30);
        isimField.setBounds(120, 20, 150, 30);
        kimlikLabel.setBounds(20, 60, 100, 30);
        kimlikField.setBounds(120, 60, 150, 30);
        gorevLabel.setBounds(20, 100, 100, 30);
        gorevField.setBounds(120, 100, 150, 30);
        maasLabel.setBounds(20, 140, 100, 30);
        maasField.setBounds(120, 140, 150, 30);
        ekleButton.setBounds(90, 200, 100, 30);

        add(isimLabel);
        add(isimField);
        add(kimlikLabel);
        add(kimlikField);
        add(gorevLabel);
        add(gorevField);
        add(maasLabel);
        add(maasField);
        add(ekleButton);

        ekleButton.addActionListener(e -> {
            String isim = isimField.getText().trim();
            String kimlik = kimlikField.getText().trim();
            String gorev = gorevField.getText().trim();
            String maasText = maasField.getText().trim();

            // التحقق من الحقول الفارغة
            if (isim.isEmpty() || kimlik.isEmpty() || gorev.isEmpty() || maasText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // التحقق من الإدخالات
            if (!isim.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                JOptionPane.showMessageDialog(this, "İsim sadece harflerden oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kimlik.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Kimlik sadece rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!gorev.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                JOptionPane.showMessageDialog(this, "Görev sadece harflerden oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double maas;
            try {
                maas = Double.parseDouble(maasText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Maaş geçerli bir sayı olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // إذا كانت جميع الإدخالات صحيحة، يتم إضافة الموظف
            PersonelDAO personelDAO = new PersonelDAO();
            Personel yeniPersonel = new Personel(isim, kimlik, gorev, maas);
            personelDAO.addPersonel(yeniPersonel);

            JOptionPane.showMessageDialog(this, "Çalışan başarıyla eklendi!");
        });

        setVisible(true);
    }
}
