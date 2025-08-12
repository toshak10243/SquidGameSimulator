package gui;

import dao.GameDAO;
import dao.PlayerDAO;
import entity.Game;
import logic.GameManager;
import service.PrizePool; // ✅ Import added

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSimulationUI extends JFrame {

    private JLabel lblLevel;
    private JLabel lblGameName;
    private JLabel lblBackground;
    private JTextArea logArea;
    private JButton btnSimulate;
    private JButton btnReset;
    private JLabel statsLabel; 

    private int currentLevel = 1;

    public GameSimulationUI() {
        setTitle("Squid Game Simulation");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background Label
        lblBackground = new JLabel();
        lblBackground.setBounds(0, 0, 900, 600);
        lblBackground.setLayout(null);
        add(lblBackground);

        // Top Game Info (Colorful)
        lblLevel = new JLabel("Level: 1");
        lblLevel.setFont(new Font("Arial", Font.BOLD, 26));
        lblLevel.setForeground(new Color(255, 215, 0)); // Gold
        lblLevel.setBounds(30, 20, 200, 30);
        lblBackground.add(lblLevel);

        lblGameName = new JLabel("Game: ");
        lblGameName.setFont(new Font("Arial", Font.BOLD, 26));
        lblGameName.setForeground(new Color(173, 216, 230)); // Light Blue
        lblGameName.setBounds(300, 20, 500, 30);
        lblBackground.add(lblGameName);
		
        // ✅ Stats Label
        statsLabel = new JLabel("Stats: Players Left = 0 | Eliminated = 0 | Prize Pool = ₹0");
        statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsLabel.setForeground(Color.YELLOW);
        statsLabel.setBounds(30, 50, 820, 25);
        lblBackground.add(statsLabel);

        // Log Area with colorful text
        logArea = new JTextArea() {
            @Override
            public void append(String str) {
                super.append(str);
                setCaretPosition(getDocument().getLength()); // Auto scroll to bottom
            }
        };
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        logArea.setForeground(Color.WHITE);
        logArea.setBackground(new Color(0, 0, 0, 180)); // slightly darker transparent black
        logArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2), // White border
                BorderFactory.createEmptyBorder(10, 10, 10, 10)  // Padding
        ));

        // ScrollPane styling
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(30, 80, 820, 300);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Custom scroll bar colors
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(255, 255, 255, 100); // White semi-transparent
                this.trackColor = new Color(0, 0, 0, 50);        // Transparent black
            }
        });

        lblBackground.add(scrollPane);

        // Simulate Button (Green theme)
        btnSimulate = new JButton("Start Next Game");
        btnSimulate.setBounds(200, 400, 200, 40);
        btnSimulate.setBackground(new Color(34, 139, 34)); // Forest Green
        btnSimulate.setForeground(Color.WHITE);
        btnSimulate.setFont(new Font("Verdana", Font.BOLD, 16));
        btnSimulate.setFocusPainted(false);
        lblBackground.add(btnSimulate);

        // Reset Button (Red theme)
        btnReset = new JButton("Reset Players");
        btnReset.setBounds(500, 400, 200, 40);
        btnReset.setBackground(new Color(178, 34, 34)); // Firebrick
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Verdana", Font.BOLD, 16));
        btnReset.setFocusPainted(false);
        lblBackground.add(btnReset);

        // Action: Simulate
        btnSimulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = GameDAO.getGameByLevel(currentLevel);
                if (game == null) {
                    logArea.append("All levels completed!\n");
                    updateStats(); // ✅ Update stats even if finished
                    return;
                }

                String result = GameManager.simulateLevel(currentLevel);
                logArea.append(result + "\n\n");

                // Update level if players remain
                if (PlayerDAO.getActivePlayers(currentLevel + 1).size() > 0) {
                    currentLevel++;
                    updateGameInfo();
                }
                updateStats(); // ✅ Update after simulate
            }
        });

        // Action: Reset
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameManager.resetAllPlayers();
                currentLevel = 1;
                updateGameInfo();
                updateStats(); // ✅ Update after reset
                logArea.setText("🔁 Game reset to Level 1.\n");
            }
        });

        updateGameInfo(); // Initial load
        updateStats();    // ✅ Initial stats
        setVisible(true);
    }

    private void updateGameInfo() {
        Game game = GameDAO.getGameByLevel(currentLevel);
        if (game != null) {
            lblLevel.setText("Level: " + game.getLevel());
            lblGameName.setText("Game: " + game.getName());

            String path = game.getBackgroundImagePath();
            if (!path.startsWith("images/")) {
                path = "images/" + path;
            }

            try {
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(
                        lblBackground.getWidth(),
                        lblBackground.getHeight(),
                        Image.SCALE_SMOOTH
                );
                lblBackground.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                lblBackground.setIcon(null);
                System.out.println("⚠️ Image not found: " + path);
            }
        } else {
            lblLevel.setText("Level: -");
            lblGameName.setText("Game: Completed");
            lblBackground.setIcon(null);
        }
        logArea.append("\n Current Prize Pool: " + PrizePool.getFormattedPrize() + "\n");

        updateStats(); // ✅ Update stats every time info changes
    }
	
    private void updateStats() {
        int active = GameManager.getActivePlayerCount();
        int eliminated = GameManager.getEliminatedPlayerCount();
        String prize = PrizePool.getFormattedPrize();
        statsLabel.setText("Stats: Players Left = " + active + " | Eliminated = " + eliminated + " | Prize Pool = " + prize);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameSimulationUI());
    }
}
