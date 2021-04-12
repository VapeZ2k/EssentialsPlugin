package de.vapez2k.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

public class SetWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.setwarp")) {
				if (args.length == 1) {
					String name = args[0].toLowerCase();
					Tools plugin = new Tools();
					plugin.setWarp(p, name);
				} else {
					p.sendMessage(
							Tools.Prefix() + "§cBitte verwende den korrekte Syntax §6/setwarp <Warpname> §c!");
				}
			} else {
				p.sendMessage(Tools.Prefix() + "§cDazu hast du keine Rechte!");
			}
		}

		return false;
	}

}
