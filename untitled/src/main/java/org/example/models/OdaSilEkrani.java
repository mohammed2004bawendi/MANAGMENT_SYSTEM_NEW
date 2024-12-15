package org.example.models;

import javax.swing.*;

public class OdaSilEkrani extends JFrame {
    private JTextField txtOdaNumarasi;
    private JButton btnSil;
    private JFrame previousFrame; // الشاشة السابقة

    // باني يقبل شاشة سابقة كوسيط
    public OdaSilEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Oda Sil");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // مكونات الشاشة
        JLabel lblOdaNumarasi = new JLabel("Oda Numarası:");
        lblOdaNumarasi.setBounds(20, 20, 100, 30);
        txtOdaNumarasi = new JTextField();
        txtOdaNumarasi.setBounds(130, 20, 200, 30);

        btnSil = new JButton("Sil");
        btnSil.setBounds(130, 70, 100, 30);

        // إضافة المكونات إلى الشاشة
        add(lblOdaNumarasi);
        add(txtOdaNumarasi);
        add(btnSil);

        // زر العودة إلى الشاشة السابقة
        JButton backButton = new JButton("⬅ Geri");
        backButton.setBounds(10, 120, 100, 30);
        add(backButton);

        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        // زر الحذف (ربط مع قاعدة البيانات)
        btnSil.addActionListener(e -> {
            try {
                int odaNumarasi = Integer.parseInt(txtOdaNumarasi.getText());

                // تحقق من وجود الغرفة
                if (!OdaDAO.isOdaExists(odaNumarasi)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Hata: Oda bulunamadı (Oda Numarası: " + odaNumarasi + ")",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // حذف الغرفة
                OdaDAO.deleteOda(odaNumarasi);
                JOptionPane.showMessageDialog(
                        null,
                        "Oda başarıyla silindi (Oda Numarası: " + odaNumarasi + ")",
                        "Başarılı",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Lütfen geçerli bir oda numarası girin!",
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Hata: " + ex.getMessage(),
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
}
