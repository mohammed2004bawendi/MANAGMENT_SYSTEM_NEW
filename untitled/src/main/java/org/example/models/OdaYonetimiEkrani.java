package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OdaYonetimiEkrani extends JFrame {
    private JFrame previousFrame;

    public OdaYonetimiEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Oda Yönetimi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Menü çubuğu
        JMenuBar menuBar = new JMenuBar();

        // Geri Butonu
        JButton backButton = new JButton("⬅ Geri");
        backButton.addActionListener((ActionEvent e) -> {
            previousFrame.setVisible(true);
            dispose();
        });

        menuBar.add(backButton);
        setJMenuBar(menuBar);

        // Butonlar
        JButton odaAraButton = new JButton("Oda Ara");
       /* JButton odaDurumuGuncelleButton = new JButton("Durumu Güncelle");*/
        JButton odaListeleButton = new JButton("Odaları Listele");

        // Konumlandırma
        odaAraButton.setBounds(100, 50, 200, 40);
     /*   odaDurumuGuncelleButton.setBounds(100, 100, 200, 40);*/
        odaListeleButton.setBounds(100, 150, 200, 40);

        add(odaAraButton);
        /*add(odaDurumuGuncelleButton);*/
        add(odaListeleButton);

        // Oda Ara
        odaAraButton.addActionListener(e -> {
            new OdaAraEkrani(this);
        });

       /* // Durumu Güncelle
        odaDurumuGuncelleButton.addActionListener(e -> {
            new OdaDurumuGuncelleEkrani(this);
        }); */

        // Odaları Listele
        odaListeleButton.addActionListener(e -> {
            new OdaListeleEkrani(this);
        });

        setVisible(true);
    }
}
