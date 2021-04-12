package de.vapez2k.plugin.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.vapez2k.plugin.main.Main;

public class WarpListCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.listwarp")) {
				if (args.length == 0) {
					Tools plugin = new Tools();
					String[] warplist = plugin.getWarpList(p);
					String warps = "";
					for (int i = 0;i<warplist.length;i++) {
						if (i>0) {
							if(i>=1) {
								if ((i+8) < warplist.length) {
									i = i + 7;
									warps = warps + " " +filterString(warplist[i].toString(), ',');
								}
							}
						}else {
							warps = warps + " , " + warplist[i];
						}
					}
					warps = filterString(warps, ',');
					warps = warps.replace(' ', ',');
					warps = warps.replaceFirst(",,","");
					p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Verfügbare Warps: "+ChatColor.AQUA+warps);
				}else {
					p.sendMessage(Tools.Prefix() + "§cBitte verwende den richtigen Syntax: §6/listwarps§c!");
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

}
