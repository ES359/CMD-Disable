package me.ES359.com;

import Utilities.CMDConfig;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 5/31/16.
 */
public class CDisable extends JavaPlugin
{
    private ArrayList<UUID> toggle = new ArrayList<>();
    public PluginDescriptionFile pdfFile = this.getDescription();
    private CMDConfig config;
    static public boolean DEBUG = true;

    public void onEnable()
    {
        config = new CMDConfig(this);
        configuration();
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new CMDListener(this), this);
        commands();
    }

    void configuration()
    {
        Debug.log(Debug.pluginLog() + "&5Configuration loading...");
        config.saveDefaultCMDConfig();
        config.saveCMDConfig();
    }

    void commands()
    {
        Debug.log(Debug.pluginLog() + "&6Commands registered.");
        registerCmd("cmddisable", new CoreCMD(this));
    }

    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }

    public CMDConfig getCmdConfig()
    {
        return this.config;
    }
    public ArrayList<UUID> getNotifications()
    {
        return toggle;
    }
}
