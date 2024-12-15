package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MusteriRezervasyonGorEkrani extends JFrame {
    private JFrame previousFrame;

    public MusteriRezervasyonGorEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Rezervasyonlarımı Gör");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel tcLabel = new JLabel("TC Kimlik:");
        tcLabel.setBounds(20, 20, 100, 30);
        add(tcLabel);

        JTextField tcField = new JTextField();
        tcField.setBounds(120, 20, 200, 30);
        add(tcField);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 80, 340, 250);
        textArea.setEditable(false);
        add(textArea);

        JButton searchButton = new JButton("Listele");
        searchButton.setBounds(120, 340, 100, 30);
        add(searchButton);

        JButton backButton = new JButton("⬅ Geri");
        backButton.setBounds(10, 340, 100, 30);
        add(backButton);

        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        searchButton.addActionListener(e -> {
            String tcKimlik = tcField.getText();
            if (tcKimlik.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Lütfen TC Kimlik numarasını girin!",
                        "Hata",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            List<Rezervasyon> rezervasyonList = RezervasyonDAO.getRezervasyonByTcKimlik(tcKimlik);

            if (rezervasyonList.isEmpty()) {
                textArea.setText("Hiçbir rezervasyon bulunamadı.");
                return;
            }

            textArea.setText(""); // Clear previous results
            for (Rezervasyon rezervasyon : rezervasyonList) {
                textArea.append("Rezervasyon No: " + rezervasyon.getRezervasyonNo() +
                        ", İsim: " + rezervasyon.getIsimSoyisim() +
                        ", Tarih: " + rezervasyon.getKalinacakTarihler() +
                        ", Oda: " + rezervasyon.getOdaBilgisi() + "\n");
            }
        });

        setVisible(true);
    }
}
