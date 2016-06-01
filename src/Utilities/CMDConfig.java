package Utilities;

import me.ES359.com.CDisable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by ES359 on 5/19/16.
 */
public class CMDConfig
{

    private FileConfiguration CMDConfig = null;
    private File CMDConfigFile = null;

    private CDisable main;
    private CMDUtils utils = new CMDUtils();

    public CMDConfig(CDisable instance)
    {
        main = instance;
    }

    public void reloadCMDConfig() {
        if (CMDConfigFile == null) {
            CMDConfigFile = new File(main.getDataFolder(), "Config.yml");
        }
        CMDConfig = YamlConfiguration.loadConfiguration(CMDConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource("Config.yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            CMDConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getCMDConfig() {
        if (CMDConfig == null) {
            reloadCMDConfig();
        }
        return CMDConfig;
    }

    public void saveCMDConfig() {
        if (CMDConfig == null || CMDConfigFile == null) {
            return;
        }
        try {
            getCMDConfig().save(CMDConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " +CMDConfigFile, ex);
        }
    }

    public void saveDefaultCMDConfig() {
        if (CMDConfigFile == null) {
            CMDConfigFile = new File(main.getDataFolder(), "Config.yml");
        }
        if (!CMDConfigFile.exists()) {
            main.saveResource("Config.yml", false);
        }
    }

}
