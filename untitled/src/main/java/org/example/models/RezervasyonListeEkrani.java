package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RezervasyonListeEkrani extends JFrame {
    private JFrame previousFrame;

    public RezervasyonListeEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Tüm Rezervasyonları Gör");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });
        menuBar.add(backButton);
        setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 20, 350, 300);
        textArea.setEditable(false);

        JButton refreshButton = new JButton("🔄 Listeyi Yenile");
        refreshButton.setBounds(120, 330, 150, 30);

        add(textArea);
        add(refreshButton);

        refreshButton.addActionListener((ActionEvent e) -> {
            List<Rezervasyon> rezervasyonlar = RezervasyonDAO.getAllRezervasyon();
            if (rezervasyonlar.isEmpty()) {
                textArea.setText("Hiçbir rezervasyon bulunamadı!");
            } else {
                textArea.setText("Tüm Rezervasyonlar:\n");
                for (Rezervasyon rezervasyon : rezervasyonlar) {
                    textArea.append(
                            "Rezervasyon No: " + rezervasyon.getRezervasyonNo() +
                                    ", İsim: " + rezervasyon.getIsimSoyisim() +
                                    ", Tarih: " + rezervasyon.getKalinacakTarihler() +
                                    ", Oda No: " + rezervasyon.getOdaBilgisi() + "\n"
                    );
                }
            }
        });

        setVisible(true);
    }
}
