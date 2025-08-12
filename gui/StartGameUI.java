package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.GameManager;

public class StartGameUI extends JFrame {

    private JButton startGameButton;
    private JTextArea statusArea;

    public StartGameUI() {
        setTitle("Squid Game - Start Game Simulation");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        startGameButton = new JButton("Start Game Round");
        statusArea = new JTextArea(10, 40);
        statusArea.setEditable(false);

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = GameManager.startGameRound(); // return a status string
                statusArea.setText(result);
            }
        });

        setLayout(new BorderLayout());
        add(startGameButton, BorderLayout.NORTH);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);
    }
}
