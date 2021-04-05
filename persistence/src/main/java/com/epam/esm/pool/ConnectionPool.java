package com.epam.esm.pool;


import com.epam.esm.domain.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private BlockingQueue<ConnectionProxy> connectionQueue;

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }

        return connectionPool;
    }


    /**
     * Constructor responsible for initialising connection pool
     */
    private ConnectionPool() {
        ApplicationProperties appProp = ApplicationProperties.INSTANCE;
        connectionQueue = new LinkedBlockingQueue<>(appProp.getAvailableConnections());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < appProp.getAvailableConnections(); i++) {
            try {
                ConnectionProxy connection = new ConnectionProxy(DriverManager.getConnection(appProp.getUrl() + appProp.getDbName(), appProp.getUser(), appProp.getPassword()));
                connectionQueue.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public BlockingQueue<ConnectionProxy> getConnectionQueue() {
        return connectionQueue;
    }

    /**
     * method responsible for receiving connection form pool
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * method responsible for returning connection to pool after being executed
     */
    public Connection returnToPool(ConnectionProxy connection) {
        connectionQueue.add(connection);
        return connection;
    }
    /**
     * method responsible for closing all connection from pool
     */
    public void destroyPool() {

        for (ConnectionProxy connectionProxy : connectionQueue) {
            try {
                connectionProxy.realClose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return;
            }
        }
        connectionQueue.clear();
    }

}