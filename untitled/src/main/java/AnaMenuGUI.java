import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AnaMenuGUI extends JFrame {
    private JButton yoneticiButton, resepsiyonButton, musteriButton;

    public AnaMenuGUI() {
        setTitle("Otel Yönetim Sistemi - Ana Menü");
        setSize(800, 600); // Initial size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("hotelFoto.jpeg"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Manual positioning

        // Buttons
        yoneticiButton = new JButton("Yönetici Girişi");
        resepsiyonButton = new JButton("Resepsiyon Girişi");
        musteriButton = new JButton("Müşteri Girişi");

        // Initial button positions
        yoneticiButton.setBounds(300, 200, 200, 40);
        resepsiyonButton.setBounds(300, 270, 200, 40);
        musteriButton.setBounds(300, 340, 200, 40);

        // Add buttons to the panel
        backgroundPanel.add(yoneticiButton);
        backgroundPanel.add(resepsiyonButton);
        backgroundPanel.add(musteriButton);

        // Add actions for buttons
        yoneticiButton.addActionListener(e -> {
            new YoneticiEkrani(this);
            dispose();
        });
         resepsiyonButton.addActionListener(e -> {
            new ResepsiyonEkrani(this);
            dispose();
        });


        musteriButton.addActionListener(e -> {
            new MusteriEkrani(this);
            dispose();
        });

        // Add the panel to the frame
        add(backgroundPanel);

        // Listen for window resize events
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustButtonPositions();
            }
        });

        setVisible(true);
    }

    // Dynamically adjust button positions based on the window size
    private void adjustButtonPositions() {
        int width = getWidth();
        int height = getHeight();

        int buttonWidth = 200;
        int buttonHeight = 40;

        // Calculate button positions dynamically
        yoneticiButton.setBounds((width - buttonWidth) / 2, height / 3, buttonWidth, buttonHeight);
        resepsiyonButton.setBounds((width - buttonWidth) / 2, height / 3 + 70, buttonWidth, buttonHeight);
        musteriButton.setBounds((width - buttonWidth) / 2, height / 3 + 140, buttonWidth, buttonHeight);
    }

    public static void main(String[] args) {
        new AnaMenuGUI();
    }
}
