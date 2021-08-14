package me.playajames.tdsdbconnector;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class TDSDatabaseConnector extends JavaPlugin {

    public static boolean DEBUG;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        setConfigSettings();
        if (!checkDatabaseConnection()) return;
        getLogger().info("Database connection established.");
        this.getLogger().info("TDSDatabaseConnector has been enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("TDSDatabaseConnector has been disabled.");
    }

    private void setConfigSettings() {
        FileConfiguration config = getConfig();
        this.DEBUG = config.getBoolean("debug");
    }

    private boolean checkDatabaseConnection() {
        if (HikariCPFactory.getConnection() == null) {
            return false;
        }
        return true;
    }

}
