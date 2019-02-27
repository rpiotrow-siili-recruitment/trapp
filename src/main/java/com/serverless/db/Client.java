package com.serverless.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private static final Logger LOG = LogManager.getLogger(Client.class);
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(Exception noDriver) {
            throw new RuntimeException("No driver found", noDriver);
        }
    }
    public static Optional<Connection> getConnection() {
        try {
            return Optional.of(DriverManager.getConnection("jdbc:mariadb://192.168.3.149:3306/test?user=root"));
        } catch (SQLException cannotConnect) {
            LOG.error("Failed connecting to db", cannotConnect);
            return Optional.empty();
        }
    }
}