package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankSystemCommand implements CommandExecutor
{
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.rank")) {
				if (args.length < 4) {
					switch (args[0]) {
					case "create":
						if (args.length == 3) {
							Tools plugin = new Tools();
							plugin.createRank(args[1], plugin.getChatColor(args[2]));
							p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Der Rang "+ChatColor.AQUA+args[1]+ChatColor.GOLD+" wurde "+ChatColor.GOLD+" soeben erfolgreich erstellt!");
							
						}else {
							p.sendMessage(Tools.Prefix()+ChatColor.RED+"Bitte verwende den richtigen Syntax: "+ChatColor.GOLD+"/rank create <name> <farbe> "+ChatColor.RED+"!");
						}
						
					break;
					case "set":
						if (args.length == 3) {
							Tools plugin = new Tools();
							Player t = Bukkit.getPlayerExact(args[1]);
							String rangName = args[2];
							if(plugin.rankExists(rangName)) {
								plugin.setRank(t, rangName);
								p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Der Rang "+ChatColor.AQUA+rangName+ChatColor.GOLD+" wurde "+ChatColor.AQUA+t.getName()+ChatColor.GOLD+" soeben erfolgreich zugewiesen!");
							}else {
								p.sendMessage(Tools.Prefix()+ChatColor.RED+"Dieser Rank existiert nicht!");
							}
						}else {
							p.sendMessage(Tools.Prefix()+ChatColor.RED+"Bitte verwende den richtigen Syntax: "+ChatColor.GOLD+"/rank set <spieler> <rang> "+ChatColor.RED+"!");
						}
					break;
					case "delete":
						if (args.length == 2) {
							Tools plugin = new Tools();
							String rangName = args[1];
							if (plugin.rankExists(rangName)) {
								plugin.delRank(p,rangName);
								p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Der Rang "+ChatColor.AQUA+rangName+ChatColor.GOLD+" wurde soeben erfolgreich gelöscht!");
							}else {
								p.sendMessage(Tools.Prefix()+ChatColor.RED+"Dieser Rank existiert nicht!");
							}
						}else {
							p.sendMessage(Tools.Prefix()+ChatColor.RED+"Bitte verwende den richtigen Syntax: "+ChatColor.GOLD+"/rank delete <rang> "+ChatColor.RED+"!");
						}
					break;
					case "remove":
						if (args.length == 3) {
							Tools plugin = new Tools();
							Player t = Bukkit.getPlayer(args[1]);
							if (plugin.rankExists(args[2])) {
								if (plugin.hasSpecificRank(t, args[2])) {
									plugin.removeRank(t, args[2]);
									p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Der Rang "+ChatColor.AQUA+args[2]+ChatColor.GOLD+" wurde soeben erfolgreich von "+ChatColor.AQUA+t.getName()+ChatColor.GOLD+" entfernt!");
								}else {
									p.sendMessage(Tools.Prefix()+ChatColor.RED+"Der angegeben Spieler besitzt diesen Rang nicht!");
								}
							}else {
								p.sendMessage(Tools.Prefix()+ChatColor.RED+"Dieser Rank existiert nicht! "+args[2]);
							}
						}else {
							p.sendMessage(Tools.Prefix()+ChatColor.RED+"Bitte verwende den richtigen Syntax: "+ChatColor.GOLD+"/rank remove <spieler> <rang> "+ChatColor.RED+"!");
						}
					break;
					}
				}
			}
		}
		
		return false;
	}

}
