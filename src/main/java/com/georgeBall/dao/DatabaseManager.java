package com.georgeBall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:desktopFinanceManager.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_URL);
        System.out.println("Database connection established: " + conn); // Debug statement
        return conn;
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String createUserTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT NOT NULL UNIQUE, "
                    + "password TEXT NOT NULL)";
            stmt.execute(createUserTable);

            String createTransactionTable = "CREATE TABLE IF NOT EXISTS transactions ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "description TEXT NOT NULL, "
                    + "amount REAL NOT NULL, "
                    + "category TEXT NOT NULL, "
                    + "date TEXT NOT NULL)";
            stmt.execute(createTransactionTable);

            System.out.println("User table created or already exists."); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
