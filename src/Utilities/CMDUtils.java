package Utilities;

import me.ES359.com.CDisable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 5/31/16.
 */
public class CMDUtils
{

    /**
     * Plugin prefix.
     */
    private String prefix = ChatColor.translateAlternateColorCodes('&',"&c&lCommand&1&l>&r ");
    /**
     * Constant permission error.
     */
    private String permission = "Unknown command. Type \"/help\" for help.";
    public String getPermission()
    {
        return this.permission;
    }

    private String donationURL = color("https://www.paypal.me/ES359");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    /**
     * Author.
     */
    public boolean checkAuth(UUID user)
    {
        return user.toString().equals(author);
    }

    /**
     *  Returns this plugins version.
     */
    public String getPluginVersion(CDisable main, CommandSender sender)
    {
        return color("&fHello, &a&n"+sender.getName() +".&r\nYou are currently running version &b&n"+main.pdfFile.getVersion() + "&r of &e&n"+main.pdfFile.getName() +"&r\n \n&6Your server is running version &c&n"+ main.getServer().getBukkitVersion());
    }

    /**
     * Gets  the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }

    /**
     * @return Donation URL
     */
    public String getDonationURL()
    {
        return donationURL;
    }

    /**
     * Gets pre-defined permission error.
     * @return
     */

    public void logToConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(color(message));
    }

    /**
     *  Logging message to the console.
     *
     * @param msg
     */
    public void log(String msg)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(color("&c&l[LOG]&f " + msg));
    }

    /**
     *  Displays plugin description Information.
     *
     * @param sender
     * @param
     *
     */
    public void desc(CommandSender sender, CDisable main)
    {
        sender.sendMessage(color("&2========== " + getPrefix().replace(":","") + "&2=========="));
        sender.sendMessage(color("&7[&9" + main.pdfFile.getName() + "&7] &6Created by, &b&l" +main.pdfFile.getAuthors()+"&6."));
        sender.sendMessage(color("&2" + main.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + main.pdfFile.getWebsite()));
        sender.sendMessage(color(""));
        sender.sendMessage(color(""));
        sender.sendMessage(color("     &6&l>>>&2&l===============&6&l<<<\t"));
    }

    public String color(String message) {

        String msg =  message;
        msg = msg.replace("&", "§");
        msg = msg.replace("%prefix%",getPrefix());
        msg = msg.replace("%permission%",getPermission());
        return msg;
    }

    /**
     *  A method of controlling exception messages in the console.
     *
     * @param e
     */
    public void exceptionDebug(Exception e)
    {
        if(CDisable.DEBUG)
        {
            logToConsole("&cERROR: &3" +e.getMessage());
            e.printStackTrace();
        }else
        {
            logToConsole("&cERROR: &3" +e.getMessage());
        }
    }

    String prefix_Console = color("&d&oConsole &b&l> ");
    public String consoleError()
    {
        return color(prefix_Console+"&4Error &l&7> &6This command isn't for use by the &c[&2Console&c].");
    }

    public void sendText(ArrayList<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%sender%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    public void sendText(ArrayList<String> text, Player sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%sender%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }
}
