package de.vapez2k.plugin.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;



public class HomeListCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.listhomes")) {
				if(args.length == 0) {
					Tools plugin = new Tools();
					String[] homelist = plugin.getHomeList(p);
					String homes = "";
					for (int i = 0;i<homelist.length;i++) {
						if (i>0) {
							if(i>=1) {
								if ((i+9) < homelist.length) {
									i = i + 8;
									homes = homes + " " +filterString(homelist[i].toString(), ',');
								}
							}
						}else {
							homes = homes + " , " + homelist[i];
						}
					}
					homes = filterString(homes, ',');
					homes = homes.replace(' ', ',');
					homes = homes.replaceFirst(",,","");
					p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Deine Homes: "+ChatColor.AQUA+homes);
				}else {
					p.sendMessage(Tools.Prefix()+"§cBitte verwende den richtigen Syntax: §6/listhomes§c!");
				}
			}else {
				p.sendMessage(Tools.Prefix()+"§cHierfür hast du keine Berechtigung!");
			}
		}
		
		return false;
	}
	
	
	public static String filterString(String s, char c) {
        for (int i = 0;i<=s.length()-1;i++) {
            if (s.charAt(i) == c) {
                s = s.replace(String.valueOf(c), "");
            }
        }
        return s;
    }

	
	   public static String filterString2(String s, char c,char c2) {
	        for (int i = 0;i<=s.length()-1;i++) {
	            if (s.charAt(i) == c) {
	                s = s.replace(String.valueOf(c), "");
	                s.replace(String.valueOf(c2), "");
	            }
	        }
	        return s;
	    }
	
}
