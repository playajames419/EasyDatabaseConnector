package me.playajames.easydatabaseconnector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPFactory {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        FileConfiguration pluginConfig = EasyDatabaseConnector.getPlugin(EasyDatabaseConnector.class).getConfig();
        config.setJdbcUrl(parseJdbcUrl(pluginConfig));
        config.setUsername(pluginConfig.getString("username"));
        config.setPassword(pluginConfig.getString("password"));
        config.addDataSourceProperty("cachePrepStmts" , pluginConfig.getBoolean("dataSource.cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize" , pluginConfig.getInt("dataSource.prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , pluginConfig.getInt("dataSource.prepStmtCacheSqlLimit"));
        config.addDataSourceProperty("useServerPrepStmts" , pluginConfig.getBoolean("dataSource.useServerPrepStmts"));
        config.addDataSourceProperty("useLocalSessionState" , pluginConfig.getBoolean("dataSource.useLocalSessionState"));
        config.addDataSourceProperty("rewriteBatchedStatements" , pluginConfig.getBoolean("dataSource.rewriteBatchedStatements"));
        config.addDataSourceProperty("cacheResultSetMetadata" , pluginConfig.getBoolean("dataSource.cacheResultSetMetadata"));
        config.addDataSourceProperty("cacheServerConfiguration" , pluginConfig.getBoolean("dataSource.cacheServerConfiguration"));
        config.addDataSourceProperty("elideSetAutoCommits" , pluginConfig.getBoolean("dataSource.elideSetAutoCommits"));
        config.addDataSourceProperty("maintainTimeStats" , pluginConfig.getBoolean("dataSource.maintainTimeStats"));
        ds = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException throwables) {
            if (EasyDatabaseConnector.DEBUG) throwables.printStackTrace();
            EasyDatabaseConnector.getPlugin(EasyDatabaseConnector.class).getLogger().warning("Invalid database configuration.");
        }
        return connection;
    }

    private static String parseJdbcUrl(FileConfiguration pluginConfig) {
        String jdbcUrl = "jdbc:mysql://:host::port/:table";
        jdbcUrl = jdbcUrl.replace(":host", pluginConfig.getString("host"));
        jdbcUrl = jdbcUrl.replace(":port", pluginConfig.getString("port"));
        jdbcUrl = jdbcUrl.replace(":table", pluginConfig.getString("table"));
        return jdbcUrl;
    }
}
