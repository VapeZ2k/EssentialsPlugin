package de.vapez2k.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.vapez2k.plugin.main.Main;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("alge.setspawn")) {
				if (args.length == 0) {
					Location pLoc = player.getLocation();
					FileConfiguration config = Main.getPlugin().getConfig();
					config.set("Spawn.World", pLoc.getWorld().getName());
					config.set("Spawn.X", pLoc.getX());
					config.set("Spawn.Y", pLoc.getY());
					config.set("Spawn.Z", pLoc.getZ());
					config.set("Spawn.Yaw", pLoc.getYaw());
					config.set("Spawn.Pitch", pLoc.getPitch());
					Main.getPlugin().saveConfig();
					player.sendMessage(Tools.Prefix() + "§6Du hast erfolgreich den neuen §1Spawn §6gesetzt!");
				} else {
					player.sendMessage(Tools.Prefix() + "$cBitte verwende den richtigen Syntax: §6/setspawn §c!");
				}
			} else {
				player.sendMessage(Tools.Prefix() + "$cDu hast keine Brechtigungen für diesen Befehl!");
			}
		}
		return false;
	}

}
