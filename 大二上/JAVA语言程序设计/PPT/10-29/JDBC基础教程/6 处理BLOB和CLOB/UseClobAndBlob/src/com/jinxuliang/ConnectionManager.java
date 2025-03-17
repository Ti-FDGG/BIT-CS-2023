package com.jinxuliang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String MsSqlServerConnectionString =
            "jdbc:sqlserver://localhost:1433;database=MyDB;" +
                    "IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static ConnectionManager instance = null;
    private Connection conn = null;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private boolean openConnection() {
        try {
            conn = DriverManager.getConnection(MsSqlServerConnectionString);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            if (openConnection()) {
                return conn;
            } else {
                return null;
            }
        }
        return conn;
    }

    public void close() {
        System.out.println("Closing connection");
        try {
            conn.close();
            conn = null;
        } catch (Exception e) {
        }
    }

}
