package de.vapez2k.plugin.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
	
	public static File warpFile = new File("plugins/VapeZ2kPlugin","warps.yml");
	public static FileConfiguration warpcfg = YamlConfiguration.loadConfiguration(warpFile);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.warp")) {
				if (args.length == 1) {
					String name = args[0].toLowerCase();
					Tools plugin = new Tools();
					if (plugin.warpExists(name)) {
						p.teleport(plugin.getWarpLoc(name));
						p.sendMessage(Tools.Prefix() + "§6Du wurdest erfolgreich zum Warp §1" + name  + " §6teleportiert!");
					} else {
						p.sendMessage(Tools.Prefix()+ "$cDieser Warp existiert nicht! Nutze §6/setwarp <warpname> §c, um einen Warp zu erstellen!");
					}
				} else {
					p.sendMessage(
							Tools.Prefix() + "§cBitte verwende den korrekten Syntax: §6/warp <warpname> §c!");
				}
			} else {
				p.sendMessage(Tools.Prefix() + "§cDu hast nicht die nötigen Rechte für diesen Befehl!");
			}
		}

		return false;
	}

}
