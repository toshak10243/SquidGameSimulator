package gui;

import dao.PlayerDAO;
import entity.Player;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerFormUI extends JFrame {

    private JTextField nameField, ageField;

    public PlayerFormUI() {
        setTitle("Register New Player");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // --- Main Panel ---
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        add(panel);

        // --- Title ---
        JLabel titleLabel = new JLabel("Register New Player");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // --- Name Field ---
        JLabel nameLabel = createLabel("Name:");
        panel.add(nameLabel);
        nameField = createTextField();
        panel.add(nameField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- Age Field ---
        JLabel ageLabel = createLabel("Age:");
        panel.add(ageLabel);
        ageField = createTextField();
        panel.add(ageField);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // --- Submit Button ---
        JButton submitButton = new JButton("Register");
        submitButton.setFont(new Font("Arial Black", Font.BOLD, 16));
        submitButton.setBackground(new Color(230, 0, 40)); // Squid Game red
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setPreferredSize(new Dimension(200, 40));
        submitButton.setMaximumSize(new Dimension(300, 40));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));

        // Hover effect
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(255, 80, 100));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(230, 0, 40));
            }
        });

        panel.add(submitButton);

        // --- Button Action ---
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerPlayer();
            }
        });

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(new Color(40, 40, 40));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return textField;
    }

    private void registerPlayer() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();

        if (name.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Player player = new Player(name, age);
            boolean success = PlayerDAO.insertPlayer(player);

            if (success) {
                JOptionPane.showMessageDialog(this, "Player Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                ageField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register player.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
