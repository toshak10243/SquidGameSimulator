package dao;

import db.DBConnection;
import entity.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public static boolean insertPlayer(Player player) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO players (name, age) VALUES (?, ?)");
            ps.setString(1, player.getName());
            ps.setInt(2, player.getAge());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Player> getAllPlayers() {
        List<Player> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM players");

            while (rs.next()) {
                Player p = new Player(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("status"),
                    rs.getInt("current_game_level")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	// 1. Get all active players of a specific level
public static List<Player> getActivePlayers(int level) {
    List<Player> list = new ArrayList<>();
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM players WHERE status = 'Active' AND current_game_level = ?"
        );
        ps.setInt(1, level);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Player p = new Player(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("status"),
                rs.getInt("current_game_level")
            );
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
// 2. Update a player's status and game level
public static boolean updatePlayerStatus(int playerId, String status, int nextLevel) {
    Connection conn = DBConnection.getConnection();
    if (conn == null) {
        System.err.println("Cannot update player status: No DB connection.");
        return false;
    }

    try (PreparedStatement ps = conn.prepareStatement(
            "UPDATE players SET status = ?, current_game_level = ? WHERE id = ?")) {

        ps.setString(1, status);
        ps.setInt(2, nextLevel);
        ps.setInt(3, playerId);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        System.err.println("Error updating player status for ID: " + playerId);
        e.printStackTrace();
        return false;
    }
}


public static int getActivePlayersCount() {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM players WHERE status='Active'")) {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public static int getEliminatedPlayersCount() {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM players WHERE status='Eliminated'")) {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

}
