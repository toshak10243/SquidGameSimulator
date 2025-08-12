package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import service.MusicPlayer;


public class AdminLoginUI extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // ✅ Fixed credentials (can be connected to DB if needed)
    private final String ADMIN_USER = "toshak";
    private final String ADMIN_PASS = "1234";

    public AdminLoginUI() {
        setTitle("Squid Game - Admin Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 🔹 Background image
        JLabel background = new JLabel(new ImageIcon("resources/images/maskman.jpg"));
        background.setBounds(0, 0, 600, 400);
        background.setLayout(null);
        add(background);

        // 🔹 Transparent black panel for login form
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 180));
        panel.setBounds(150, 80, 300, 220);
        panel.setLayout(null);
        background.add(panel);

        // 🔹 Title
        JLabel lblTitle = new JLabel("ADMIN LOGIN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.RED);
        lblTitle.setBounds(50, 10, 200, 30);
        panel.add(lblTitle);

        // 🔹 Username Label
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(30, 60, 100, 25);
        panel.add(lblUser);

        // 🔹 Username Field
        txtUsername = new JTextField();
        txtUsername.setBounds(120, 60, 150, 25);
        panel.add(txtUsername);

        // 🔹 Password Label
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(30, 100, 100, 25);
        panel.add(lblPass);

        // 🔹 Password Field
        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 100, 150, 25);
        panel.add(txtPassword);

        // 🔹 Login Button
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(100, 150, 100, 35);
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Verdana", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        // Hover effect
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(200, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(Color.RED);
            }
        });

        panel.add(btnLogin);

        // 🔹 Button Action
       btnLogin.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            
            // 🎵 Play music
            MusicPlayer music = new MusicPlayer();
            music.playMusic("squidgame.wav");

            // 🎯 Open Main Menu
            new MainMenu();

            // ❌ Close Login Window
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Invalid Credentials!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
});


        setVisible(true);
    }
}
