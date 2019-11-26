package com.fun.learning.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fun.learning.conf.Configuration;
import com.fun.learning.conf.ConfigurationHolder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Encapsulates creation of {@link DataSource} connection.
 */
public final class Database {

    private Database() {
        throw new AssertionError();
    }

    private static final HikariDataSource dataSource;

    static {
        final Configuration configuration = ConfigurationHolder.INSTANCE.configuration();
        final Configuration.DataSource props = configuration.getDatasource();

        final HikariConfig config = new HikariConfig();
        config.setDriverClassName(props.getDriverClassName());
        config.setJdbcUrl(props.getUrl());
        config.setUsername(props.getUsername());
        config.setPassword(props.getPassword());

        dataSource = new HikariDataSource(config);
    }

    public static Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
