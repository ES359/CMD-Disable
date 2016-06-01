package me.ES359.com;

import Utilities.CMDUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 5/31/16.
 */
public class CoreCMD extends CMDUtils implements CommandExecutor
{

    CDisable main;

    public CoreCMD(CDisable instance)
    {
        main = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("cmddisable"))
        {
            if(!sender.hasPermission("CMD-disable.admin"))
            {
                sender.sendMessage(color("%prefix% &7No permission for &4Admin &7features."));
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color("&a&l>> &7Command usage: /cmd <[reload] &b&l|| &a[notifications]&f>"));
                }else if(args.length >0)
                {
                    switch (args[0])
                    {
                        case "reload":
                        case "rl":
                            if(!sender.hasPermission("CMD.admin.reload"))
                            {
                                sender.sendMessage(getPermission());
                            }else
                            {
                                this.main.getCmdConfig().reloadCMDConfig();
                                sender.sendMessage(color(main.getCmdConfig().getCMDConfig().getString("plugin-reloaded")));
                            }
                            break;

                        case "notifications":
                        case "notify":

                            if(!(sender instanceof Player))
                            {
                                sender.sendMessage(consoleError());
                            }else
                            {
                                Player p = (Player)sender;

                                UUID uuid = p.getUniqueId();

                                if(!main.getNotifications().contains(uuid))
                                {
                                    main.getNotifications().add(uuid);
                                    p.sendMessage(color("%prefix% &7You are now receiving command &anotifications"));
                                }else
                                {
                                    main.getNotifications().remove(uuid);
                                    p.sendMessage(color("%prefix% &7You are no longer receiving command &anotifications"));
                                }
                            }
                            break;

                        default:
                            sender.sendMessage(color("%prefix% &7Unknown argument... "));
                    }
                }
            }
        }
        return true;
    }

}
