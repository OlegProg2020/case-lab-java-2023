package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceImpl implements DataSource {

    private final String path;
    private final String user;
    private final String password;

    public DataSourceImpl(String path, String user, String password) {
        this.path = path;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(path, user, password);
    }
}
