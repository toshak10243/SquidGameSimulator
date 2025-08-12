package gui;

import dao.PlayerDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.GameSimulationUI;
import logic.GameManager;



public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Squid Game Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Background Panel ---
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        setContentPane(backgroundPanel);

        // --- Logo ---
        ImageIcon logoIcon = new ImageIcon("resources/images/logo.png"); // Recommended: Use transparent logo PNG
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(logoLabel);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // --- Buttons ---
        JButton registerButton = createButton("Register Player");
        JButton viewButton = createButton("View Players");
		JButton startGameButton = createButton("Start Game Simulation");
        JButton resetButton = createButton("Reset All Players");



        backgroundPanel.add(registerButton);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(viewButton);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		backgroundPanel.add(startGameButton);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        backgroundPanel.add(resetButton);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20))); // spacing below


        // GameSimulationUI.java


        // --- Info Section ---
        String[] infoLines = {
            "• 456 contestants risk their lives to win a life-changing cash prize.",
            "• All players must participate in a series of 12 childhood-inspired survival games.",
            "• Failure to complete a game or breaking rules leads to immediate elimination.",
            "• Eliminated players are permanently removed, increasing the prize pool.",
            "• Only one player survives till the end and claims the entire reward."
        };

        Font infoFont = new Font("Arial", Font.PLAIN, 14);
        Color infoColor = Color.WHITE;

        for (String line : infoLines) {
            JLabel label = new JLabel(line);
            label.setFont(infoFont);
            label.setForeground(infoColor);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            backgroundPanel.add(label);
        }

        // --- Button Actions ---
        registerButton.addActionListener(e -> new PlayerFormUI());
        viewButton.addActionListener(e -> new PlayerTableUI());
		startGameButton.addActionListener(e -> {
    GameSimulationUI gameUI = new GameSimulationUI();
    gameUI.setVisible(true);
});
resetButton.addActionListener(e -> {
    GameManager.resetAllPlayers();
    JOptionPane.showMessageDialog(null, "All players have been reset to Active and Level 1.");
});





        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        Font font = new Font("Arial Black", Font.PLAIN, 16);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(font);
        button.setBackground(new Color(230, 0, 40)); // Squid Game Red
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(300, 50));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 80, 100));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(230, 0, 40));
            }
        });

        return button;
    }
}
