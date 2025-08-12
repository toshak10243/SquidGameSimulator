package logic;

import db.DBConnection;
import entity.Player;
import java.sql.*;
import java.util.*;
import dao.GameDAO;
import dao.PlayerDAO;
import entity.Game;
import java.util.List;
import service.PrizePool; // ✅ Added import

public class GameManager {

    public static List<Player> loadActivePlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE status='ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStatus(rs.getString("status"));
                players.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public static void simulateRound() {
        List<Player> activePlayers = loadActivePlayers();

        if (activePlayers.size() < 2) {
            System.out.println("Not enough players to simulate.");
            return;
        }

        Collections.shuffle(activePlayers); // randomize order

        int total = activePlayers.size();
        int eliminateCount = total / 2;

        List<Player> toEliminate = activePlayers.subList(0, eliminateCount);
        List<Player> toAdvance = activePlayers.subList(eliminateCount, total);

        try (Connection con = DBConnection.getConnection()) {

            // Eliminate players
            String eliminateSQL = "UPDATE players SET status = 'Eliminated' WHERE id = ?";
            PreparedStatement eliminateStmt = con.prepareStatement(eliminateSQL);
            for (Player p : toEliminate) {
                eliminateStmt.setInt(1, p.getId());
                eliminateStmt.executeUpdate();
            }

            // Advance players
            String advanceSQL = "UPDATE players SET current_game_level = current_game_level + 1 WHERE id = ?";
            PreparedStatement advanceStmt = con.prepareStatement(advanceSQL);
            for (Player p : toAdvance) {
                advanceStmt.setInt(1, p.getId());
                advanceStmt.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Round simulation complete.");
    }

    public static int[] simulateRoundWithResult() {
        List<Player> activePlayers = loadActivePlayers();

        if (activePlayers.size() < 2) {
            return new int[]{0, 0};
        }

        Collections.shuffle(activePlayers);

        int total = activePlayers.size();
        int eliminateCount = total / 2;

        List<Player> toEliminate = activePlayers.subList(0, eliminateCount);
        List<Player> toAdvance = activePlayers.subList(eliminateCount, total);

        try (Connection con = DBConnection.getConnection()) {
            // Eliminate
            String eliminateSQL = "UPDATE players SET status = 'Eliminated' WHERE id = ?";
            PreparedStatement eliminateStmt = con.prepareStatement(eliminateSQL);
            for (Player p : toEliminate) {
                eliminateStmt.setInt(1, p.getId());
                eliminateStmt.executeUpdate();
            }

            // Advance
            String advanceSQL = "UPDATE players SET current_game_level = current_game_level + 1 WHERE id = ?";
            PreparedStatement advanceStmt = con.prepareStatement(advanceSQL);
            for (Player p : toAdvance) {
                advanceStmt.setInt(1, p.getId());
                advanceStmt.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new int[]{toEliminate.size(), toAdvance.size()};
    }

    public static void resetAllPlayers() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE players SET status = 'Active', current_game_level = 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();

            // ✅ Reset Prize Pool
            PrizePool.reset();

            System.out.println("All players have been reset.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String simulateLevel(int level) {
        Game game = GameDAO.getGameByLevel(level);
        if (game == null) {
            return "❌ Game level not found!";
        }

        List<Player> activePlayers = PlayerDAO.getActivePlayers(level);
        if (activePlayers.isEmpty()) {
            return "⚠ No active players for Level " + level;
        }

        Collections.shuffle(activePlayers);

        // ✅ Random survival rate between 40% and 70%
        Random rand = new Random();
        double survivalRate = 0.4 + (0.3 * rand.nextDouble());
        int survivors = Math.max(1, (int) Math.round(activePlayers.size() * survivalRate));

        int eliminatedCount = 0;

        for (int i = 0; i < activePlayers.size(); i++) {
            Player p = activePlayers.get(i);
            if (i < survivors) {
                PlayerDAO.updatePlayerStatus(p.getId(), "Active", level + 1);
            } else {
                PlayerDAO.updatePlayerStatus(p.getId(), "Eliminated", level);
                eliminatedCount++;
            }
        }

        // ✅ Add eliminations to prize pool
        PrizePool.addEliminations(eliminatedCount);

        return "Game: " + game.getName() + " simulated!\n"
             + "Players Participated: " + activePlayers.size() + "\n"
             + "Survived: " + survivors + "\n"
             + "Eliminated: " + eliminatedCount + "\n"
             + "💰 Prize Pool: " + PrizePool.getFormattedPrize();
    }
	public static int getActivePlayerCount() {
    return PlayerDAO.getActivePlayersCount();
}

public static int getEliminatedPlayerCount() {
    return PlayerDAO.getEliminatedPlayersCount();
}
}
