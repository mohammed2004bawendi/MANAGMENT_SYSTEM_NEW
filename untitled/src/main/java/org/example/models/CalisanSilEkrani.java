package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalisanSilEkrani extends JFrame {
    private JFrame previousFrame;

    public CalisanSilEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Çalışan Sil");
        setSize(300, 200);
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
            new CalisanSilEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğunu pencereye ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);
        setJMenuBar(menuBar);

        // Bileşenler
        JLabel kimlikLabel = new JLabel("Personel Kimlik No:");
        JTextField kimlikField = new JTextField();
        JButton silButton = new JButton("Sil");

        kimlikLabel.setBounds(20, 20, 150, 30);
        kimlikField.setBounds(170, 20, 100, 30);
        silButton.setBounds(100, 80, 100, 30);

        add(kimlikLabel);
        add(kimlikField);
        add(silButton);

        silButton.addActionListener(e -> {
            String kimlik = kimlikField.getText();

            try {
                PersonelDAO personelDAO = new PersonelDAO();

                // تحقق إذا كان الشخص موجودًا
                if (!personelDAO.isPersonelExists(kimlik)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Hata: Çalışan bulunamadı!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // حذف الشخص
                personelDAO.deletePersonel(kimlik);
                JOptionPane.showMessageDialog(this, "Çalışan başarıyla silindi!");

                // تنظيف الحقل
                kimlikField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
