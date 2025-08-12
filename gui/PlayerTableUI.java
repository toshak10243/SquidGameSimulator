package gui;

import dao.PlayerDAO;
import entity.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PlayerTableUI extends JFrame {
    public PlayerTableUI() {
        setTitle("All Players");
        setSize(600, 400);

        String[] columnNames = {"ID", "Name", "Age", "Status", "Game Level"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        List<Player> players = PlayerDAO.getAllPlayers();
        for (Player p : players) {
            Object[] row = {
                p.getId(), p.getName(), p.getAge(), p.getStatus(), p.getCurrentGameLevel()
            };
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
