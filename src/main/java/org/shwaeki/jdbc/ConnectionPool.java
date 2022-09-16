package org.shwaeki.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {

    private final static String URL = "jdbc:mysql://localhost:3306/java_project";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int POOL_SIZE = 10;
    private static ConnectionPool instance;
    private Set<Connection> connections;


    private ConnectionPool() {
        connections = new HashSet<>();

        try {
            Class.forName(DRIVER);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connections.add(con);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }


    public Connection getConnection() {

        Connection connection = connections.iterator().next();
        connections.remove(connection);
        return connection;
    }

    void restoreConnection(Connection connection) {
        connections.add(connection);
        notify();
    }

    void closeAllConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
