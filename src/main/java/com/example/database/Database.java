package com.example.database;

import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres_main";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection conn = null;

    public Database() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Database Connection Initialized.");
    }

    public void closeConnection() {
        if (conn == null) return;
        try {
            conn.close();
            conn = null;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean execute(String sql) throws SQLException {
        if (conn == null)
            throw new SQLException("Connection null!");
        Statement statement = conn.createStatement();
        boolean res = statement.execute(sql);
        statement.close();
        return res;
    }

    public void executeUpdate(String sql) throws SQLException {
        if (conn == null)
            throw new SQLException("Connection null!");
        Statement statement = conn.createStatement();
        int res = statement.executeUpdate(sql);
        statement.close();
    }

}
