package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RezervasyonYapEkrani extends JFrame {
    private JFrame previousFrame;

    public RezervasyonYapEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Rezervasyon Yap");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();

        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener((ActionEvent e) -> {
            previousFrame.setVisible(true);
            dispose();
        });

        menuBar.add(backButton);
        setJMenuBar(menuBar);

        JLabel isimLabel = new JLabel("İsim Soyisim:");
        JTextField isimField = new JTextField();
        JLabel tcLabel = new JLabel("TC Kimlik:");
        JTextField tcField = new JTextField();
        JLabel tarihLabel = new JLabel("Tarih:");
        JTextField tarihField = new JTextField();
        JLabel odaLabel = new JLabel("Oda No:");
        JTextField odaField = new JTextField();
        JButton rezervasyonButton = new JButton("Rezervasyon Yap");

        isimLabel.setBounds(20, 20, 100, 30);
        isimField.setBounds(120, 20, 200, 30);
        tcLabel.setBounds(20, 60, 100, 30);
        tcField.setBounds(120, 60, 200, 30);
        tarihLabel.setBounds(20, 100, 100, 30);
        tarihField.setBounds(120, 100, 200, 30);
        odaLabel.setBounds(20, 140, 100, 30);
        odaField.setBounds(120, 140, 200, 30);
        rezervasyonButton.setBounds(120, 200, 200, 40);

        add(isimLabel);
        add(isimField);
        add(tcLabel);
        add(tcField);
        add(tarihLabel);
        add(tarihField);
        add(odaLabel);
        add(odaField);
        add(rezervasyonButton);

        rezervasyonButton.addActionListener(e -> {
            try {
                String isim = isimField.getText();
                String tc = tcField.getText();
                String tarih = tarihField.getText();
                int odaNo = Integer.parseInt(odaField.getText());

                if (isim.isEmpty() || tc.isEmpty() || tarih.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!OdaDAO.isOdaExists(odaNo)) {
                    JOptionPane.showMessageDialog(this, "Hata: Oda mevcut değil!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (OdaDAO.isOdaBooked(odaNo)) {
                    JOptionPane.showMessageDialog(this, "Hata: Oda zaten rezerve edilmiş!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Rezervasyon rezervasyon = new Rezervasyon(isim, tc, tarih, odaNo);
                RezervasyonDAO.insertRezervasyon(rezervasyon);

                // تحديث حالة الغرفة إلى "Dolu"
                OdaDAO.updateOdaStatus(odaNo, "Dolu");

                JOptionPane.showMessageDialog(this, "Rezervasyon başarıyla yapıldı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                isimField.setText("");
                tcField.setText("");
                tarihField.setText("");
                odaField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir oda numarası girin!", "Hata", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
