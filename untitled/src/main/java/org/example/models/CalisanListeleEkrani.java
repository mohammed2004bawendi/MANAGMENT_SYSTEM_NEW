package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CalisanListeleEkrani extends JFrame {
    private JFrame previousFrame;

    public CalisanListeleEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Çalışanları Gör");
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
            new CalisanListeleEkrani(previousFrame); // Bu ekranı yeniden başlat
            dispose(); // Mevcut ekranı kapat
        });

        // Menü çubuğuna butonları ekle
        menuBar.add(backButton);
        menuBar.add(refreshButton);

        // Menü çubuğunu pencereye ekle
        setJMenuBar(menuBar);

        // Çalışan bilgilerini göstermek için bir TextArea
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 20, 350, 300);
        textArea.setEditable(false);

        // Çalışanları veritabanından getir ve TextArea'ya yaz
        try {
            PersonelDAO personelDAO = new PersonelDAO();
            List<Personel> personelList = personelDAO.getAllPersonel();

            if (personelList.isEmpty()) {
                textArea.setText("Hiç çalışan kaydı bulunamadı!");
            } else {
                for (Personel personel : personelList) {
                    textArea.append(personel.toString() + "\n");
                }
            }
        } catch (Exception ex) {
            textArea.setText("Hata: " + ex.getMessage());
        }

        // TextArea'yı pencereye ekle
        add(textArea);

        setVisible(true);
    }
}
