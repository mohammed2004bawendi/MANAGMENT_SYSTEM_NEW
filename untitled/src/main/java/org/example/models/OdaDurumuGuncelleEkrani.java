package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OdaDurumuGuncelleEkrani extends JFrame {
    private JTextField txtOdaNumarasi, txtYeniDurum;
    private JButton btnGuncelle;
    private JFrame previousFrame;

    public OdaDurumuGuncelleEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Oda Durumu Güncelle");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // مكونات الشاشة
        JLabel lblOdaNumarasi = new JLabel("Oda Numarası:");
        lblOdaNumarasi.setBounds(20, 20, 100, 30);
        txtOdaNumarasi = new JTextField();
        txtOdaNumarasi.setBounds(130, 20, 200, 30);

        JLabel lblYeniDurum = new JLabel("Yeni Durum:");
        lblYeniDurum.setBounds(20, 60, 100, 30);
        txtYeniDurum = new JTextField();
        txtYeniDurum.setBounds(130, 60, 200, 30);

        btnGuncelle = new JButton("Güncelle");
        btnGuncelle.setBounds(130, 100, 100, 30);

        // زر العودة
        JButton backButton = new JButton("⬅ Geri");
        backButton.setBounds(10, 140, 100, 30);
        add(backButton);

        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        // إضافة المكونات إلى الشاشة
        add(lblOdaNumarasi);
        add(txtOdaNumarasi);
        add(lblYeniDurum);
        add(txtYeniDurum);
        add(btnGuncelle);

        // زر التحديث
        btnGuncelle.addActionListener(e -> {
            try {
                int odaNumarasi = Integer.parseInt(txtOdaNumarasi.getText());
                String yeniDurum = txtYeniDurum.getText();

                if (yeniDurum.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Lütfen yeni durumu giriniz!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // استدعاء الدالة لتحديث حالة الغرفة
                OdaDAO.updateOdaStatus(odaNumarasi, yeniDurum);

                JOptionPane.showMessageDialog(
                        this,
                        "Oda durumu başarıyla güncellendi!",
                        "Başarılı",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // تنظيف الحقول
                txtOdaNumarasi.setText("");
                txtYeniDurum.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Lütfen geçerli bir oda numarası giriniz!",
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
