package org.example.models;

import javax.swing.*;
import java.util.ArrayList;

public class OdaListeleEkrani extends JFrame {
    private JTextArea txtAreaOdalar;
    private JButton btnListele;
    private JFrame previousFrame; // الشاشة السابقة

    // باني جديد يقبل الشاشة السابقة كوسيط
    public OdaListeleEkrani(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setTitle("Odaları Listele");
        setSize(500, 500); // تعديل الحجم لتحسين العرض
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // منطقة النص لعرض الغرف
        txtAreaOdalar = new JTextArea();
        txtAreaOdalar.setBounds(20, 20, 440, 350);
        txtAreaOdalar.setEditable(false);

        // تمرير نص
        JScrollPane scrollPane = new JScrollPane(txtAreaOdalar);
        scrollPane.setBounds(20, 20, 440, 350);
        add(scrollPane);

        // زر قائمة الغرف
        btnListele = new JButton("Listele");
        btnListele.setBounds(200, 380, 100, 30);

        // إضافة المكونات إلى النافذة
        add(btnListele);

        // زر العودة إلى الشاشة السابقة
        JButton backButton = new JButton("⬅ Geri");
        backButton.setBounds(10, 420, 100, 30);
        add(backButton);

        backButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        // ربط زر القائمة بوظيفة عرض الغرف
        btnListele.addActionListener(e -> {
            txtAreaOdalar.setText(""); // تنظيف منطقة النص
            ArrayList<OdaDTO> odalar = OdaDAO.getAllOdaAsList(); // استرداد جميع الغرف ككائنات OdaDTO
            if (odalar.isEmpty()) {
                txtAreaOdalar.setText("Kayıtlı oda bulunamadı."); // إذا لم تكن هناك غرف
            } else {
                for (OdaDTO oda : odalar) {
                    txtAreaOdalar.append(
                            "Oda No: " + oda.getOdaNumarasi() +
                                    ", Kapasite: " + oda.getKapasite() +
                                    ", Fiyat: " + oda.getFiyat() +
                                    ", Durum: " + oda.getDurum() +
                                    ", Manzara: " + oda.getManzara() + "\n"
                    );
                }
            }
        });

        setVisible(true);
    }
}
