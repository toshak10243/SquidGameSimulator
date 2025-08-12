package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/squidgame";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            // Reconnect if null or closed
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            }
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            conn = null; // so it can retry later
        }
        return conn;
    }
}
