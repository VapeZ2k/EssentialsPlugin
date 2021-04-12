package de.vapez2k.plugin.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class RenameCommand implements CommandExecutor {
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		
		

		Player p = (Player) sender;
		if (sender instanceof Player) {
			if (p.hasPermission("alge.rename")) {
				}if (args.length == 2) {
					Tools plugin = new Tools();
					String newname = args[0];
					String color = args[1];
					if (plugin.getChatColor(color) != null) {
						plugin.saveCustomName(p, newname, color);
						if (plugin.hasRank(p)) {
							plugin.setCustomNameAndRank(p);
						}else {
							plugin.setCustomName(p);
						}
						p.sendMessage(Tools.Prefix() + "§6Dein neuer Name lautet: §1" + plugin.getChatColor(color)+newname);
					}else {
						p.sendMessage(Tools.Prefix() + ChatColor.GOLD+color+" §cIst keine verfügbare Farbe!");
					}	
				} else {
					p.sendMessage(Tools.Prefix() + "§cVerwende bitte den korrekten Syntax §6/rename <Name> §c!");
				}
			} else {
				p.sendMessage(Tools.Prefix() + "§cDu bist nicht berechtigt diesen Befehl zu verwenden!");
			}

		return false;
	}

}
