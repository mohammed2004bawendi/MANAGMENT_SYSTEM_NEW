package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OdaEkleEkrani extends JFrame {
    private JFrame previousFrame;

    public OdaEkleEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Oda Ekle");
        setSize(300, 300);
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
            new OdaEkleEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğunu pencereye ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        setJMenuBar(menuBar);

        // Bileşenler
        JLabel odaNumarasiLabel = new JLabel("Oda Numarası:");
        JTextField odaNumarasiField = new JTextField();
        JLabel kapasiteLabel = new JLabel("Kapasite:");
        JTextField kapasiteField = new JTextField();
        JLabel fiyatLabel = new JLabel("Fiyat:");
        JTextField fiyatField = new JTextField();
        JLabel durumLabel = new JLabel("Durum:");
        JTextField durumField = new JTextField();
        JLabel manzaraLabel = new JLabel("Manzara:");
        JTextField manzaraField = new JTextField();

        JButton ekleButton = new JButton("Ekle");

        odaNumarasiLabel.setBounds(20, 20, 100, 30);
        odaNumarasiField.setBounds(120, 20, 150, 30);
        kapasiteLabel.setBounds(20, 60, 100, 30);
        kapasiteField.setBounds(120, 60, 150, 30);
        fiyatLabel.setBounds(20, 100, 100, 30);
        fiyatField.setBounds(120, 100, 150, 30);
        durumLabel.setBounds(20, 140, 100, 30);
        durumField.setBounds(120, 140, 150, 30);
        manzaraLabel.setBounds(20, 180, 100, 30);
        manzaraField.setBounds(120, 180, 150, 30);
        ekleButton.setBounds(90, 230, 100, 30);

        add(odaNumarasiLabel);
        add(odaNumarasiField);
        add(kapasiteLabel);
        add(kapasiteField);
        add(fiyatLabel);
        add(fiyatField);
        add(durumLabel);
        add(durumField);
        add(manzaraLabel);
        add(manzaraField);
        add(ekleButton);

        ekleButton.addActionListener(e -> {
            try {
                // التحقق من رقم الغرفة وكفاءتها
                if (odaNumarasiField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Oda Numarası boş olamaz!");
                }
                int odaNumarasi = Integer.parseInt(odaNumarasiField.getText());

                // التحقق من السعة
                if (kapasiteField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Kapasite boş olamaz!");
                }
                int kapasite = Integer.parseInt(kapasiteField.getText());
                if (kapasite <= 0) {
                    throw new IllegalArgumentException("Kapasite 0'dan büyük olmalıdır!");
                }

                // التحقق من السعر
                if (fiyatField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Fiyat boş olamaz!");
                }
                double fiyat = Double.parseDouble(fiyatField.getText());
                if (fiyat <= 0) {
                    throw new IllegalArgumentException("Fiyat 0'dan büyük olmalıdır!");
                }

                // التحقق من الحالة
                String durum = durumField.getText();
                if (durum.isEmpty()) {
                    throw new IllegalArgumentException("Durum boş olamaz!");
                }

                // التحقق من المنظر
                String manzara = manzaraField.getText();
                if (manzara.isEmpty()) {
                    throw new IllegalArgumentException("Manzara boş olamaz!");
                }

                // التحقق من وجود الغرفة
                if (OdaDAO.isOdaExists(odaNumarasi)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Bu oda zaten mevcut (Oda Numarası: " + odaNumarasi + ")",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // إضافة الغرفة
                OdaDTO oda = new OdaDTO(odaNumarasi, kapasite, fiyat, durum, manzara);
                OdaDAO.insertOda(oda);

                JOptionPane.showMessageDialog(
                        this,
                        "Oda başarıyla eklendi!",
                        "Başarılı",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // تنظيف الحقول
                odaNumarasiField.setText("");
                kapasiteField.setText("");
                fiyatField.setText("");
                durumField.setText("");
                manzaraField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Lütfen geçerli sayısal değerler girin!",
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Hata: " + ex.getMessage(),
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Hata: " + ex.getMessage(),
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
}
