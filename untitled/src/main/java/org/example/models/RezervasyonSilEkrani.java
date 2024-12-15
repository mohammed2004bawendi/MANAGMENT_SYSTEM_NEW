package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RezervasyonSilEkrani extends JFrame {
    private JTextField txtRezervasyonNo;
    private JFrame previousFrame;

    public RezervasyonSilEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Rezervasyon Sil");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblRezervasyonNo = new JLabel("Rezervasyon No:");
        lblRezervasyonNo.setBounds(20, 20, 120, 30);
        txtRezervasyonNo = new JTextField();
        txtRezervasyonNo.setBounds(150, 20, 200, 30);

        JButton btnSil = new JButton("Sil");
        btnSil.setBounds(150, 70, 100, 30);

        // Geri Butonu
        JButton backButton = new JButton("⬅ Geri");
        backButton.setBounds(10, 120, 100, 30);
        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        add(lblRezervasyonNo);
        add(txtRezervasyonNo);
        add(btnSil);
        add(backButton);

        btnSil.addActionListener(e -> {
            try {
                double rezervasyonNo = Double.parseDouble(txtRezervasyonNo.getText());

                // Check if reservation exists
                Rezervasyon rezervasyon = RezervasyonDAO.getRezervasyonByNo(rezervasyonNo);
                if (rezervasyon == null) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Rezervasyon bulunamadı!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Delete the reservation
                RezervasyonDAO.deleteRezervasyon(rezervasyonNo);

                // Update room status to "Boş"
                OdaDAO.updateOdaStatus(rezervasyon.getOdaBilgisi(), "Boş");

                JOptionPane.showMessageDialog(
                        this,
                        "Rezervasyon başarıyla silindi!",
                        "Başarılı",
                        JOptionPane.INFORMATION_MESSAGE
                );

                txtRezervasyonNo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Lütfen geçerli bir rezervasyon numarası giriniz!",
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
