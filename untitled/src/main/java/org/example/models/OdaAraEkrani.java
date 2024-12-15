package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OdaAraEkrani extends JFrame {
    private JFrame previousFrame;

    public OdaAraEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Oda Ara");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Bileşenler
        JLabel odaNoLabel = new JLabel("Oda Numarası:");
        JTextField odaNoField = new JTextField();
        JButton araButton = new JButton("Ara");
        JTextArea resultArea = new JTextArea();

        odaNoLabel.setBounds(50, 50, 120, 30);
        odaNoField.setBounds(180, 50, 150, 30);
        araButton.setBounds(120, 100, 150, 40);
        resultArea.setBounds(50, 150, 300, 100);
        resultArea.setEditable(false);

        add(odaNoLabel);
        add(odaNoField);
        add(araButton);
        add(resultArea);

        araButton.addActionListener(e -> {
            try {
                int odaNo = Integer.parseInt(odaNoField.getText());
                OdaDTO oda = OdaDAO.findOda(odaNo);

                if (oda != null) {
                    resultArea.setText("Oda Numarası: " + oda.getOdaNumarasi() +
                            "\nKapasite: " + oda.getKapasite() +
                            "\nFiyat: " + oda.getFiyat() +
                            "\nDurum: " + oda.getDurum() +
                            "\nManzara: " + oda.getManzara());
                } else {
                    resultArea.setText("Oda bulunamadı.");
                }
            } catch (Exception ex) {
                resultArea.setText("Hata: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
