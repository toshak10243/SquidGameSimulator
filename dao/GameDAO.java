// File: dao/GameDAO.java
package dao;

import db.DBConnection;
import entity.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    public static List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM games ORDER BY level ASC");

            while (rs.next()) {
                games.add(new Game(
                    rs.getInt("level"),
                    rs.getString("name"),
                    rs.getString("background_image_path")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

    public static Game getGameByLevel(int level) {
        Game game = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM games WHERE level = ?");
            ps.setInt(1, level);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                game = new Game(
                    rs.getInt("level"),
                    rs.getString("name"),
                    rs.getString("background_image_path")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }
}
