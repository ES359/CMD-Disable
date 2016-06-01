package me.ES359.com;

import Utilities.CMDUtils;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by ES359 on 5/31/16.
 */
public class CMDListener extends CMDUtils implements Listener
{

    CDisable main;

    public CMDListener(CDisable instance)
    {
        this.main = instance;
    }


    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event)
    {
        Debug.log(getPrefix() + " &aCommand pre-process event.");
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();

        String denied = this.main.getCmdConfig().getCMDConfig().getString("restriction-msg");

        denied = denied.replace("%player%", p.getName());

        boolean usePunish = main.getConfig().getBoolean("punish-Enabled");

        String message[] = event.getMessage().split(" ");
        String command = message[0];
        List<String> denyList = this.main.getCmdConfig().getCMDConfig().getStringList("blocked-cmds");
        List<String> allowList = this.main.getCmdConfig().getCMDConfig().getStringList("allowed-cmds");

        if(p.isOp())
        {
            if(usePunish)
            {
                if(allowList.contains(command))
                {
                    event.setCancelled(false);
                    if(main.getCmdConfig().getCMDConfig().getBoolean("Log.allowed-cmd"))
                    {
                        logToConsole("%prefix% &7" + p.getName() + " &7used the command &2" + command);
                    }
                }else if(denyList.contains(command))
                {

                    event.setCancelled(true);
                    if(main.getCmdConfig().getCMDConfig().getBoolean("Log.blocked-cmd"))
                    {
                        logToConsole("%prefix% &7" + p.getName() + " &7used the &4blocked command &c" + command);
                    }
                    String fun = this.main.getCmdConfig().getCMDConfig().getString("punish-command");

                    fun = fun.replace("%player%", event.getPlayer().getName());

                    for(Player staff : Bukkit.getServer().getOnlinePlayers())
                    {
                        if(main.getNotifications().contains(staff.getUniqueId()))
                        {
                            staff.sendMessage(color("&c&l> &8[&4Warning&8] &c" + p.getName() + " &7used the &4blocked cmd: &c" + command));
                        }
                    }

                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), color(fun));
                }
            }else
            {
                if(allowList.contains(command))
                {
                    event.setCancelled(false);
                    if(main.getCmdConfig().getCMDConfig().getBoolean("Log.allowed-cmd"))
                    {
                        logToConsole("%prefix% &7" + p.getName() + " &7used the command &2" + command);
                    }
                }else if(denyList.contains(command))
                {
                    event.setCancelled(true);
                    if(main.getCmdConfig().getCMDConfig().getBoolean("Log.blocked-cmd"))
                    {
                        logToConsole("%prefix% &7" + p.getName() + " &7used the &4blocked command &c" + command);
                    }

                    for(Player staff : Bukkit.getServer().getOnlinePlayers())
                    {
                        if(main.getNotifications().contains(staff.getUniqueId()))
                        {
                            staff.sendMessage(color("&c&l> &8[&4Warning&8] &c" + p.getName() + " &7used the &4blocked cmd: &c" + command));
                        }
                    }
                    p.sendMessage(color(denied));
                }
            }
        }
    }
}
