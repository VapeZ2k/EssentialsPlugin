package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import de.vapez2k.plugin.main.Main;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.spawn")) {
				FileConfiguration config = Main.getPlugin().getConfig();
				World currentWorld = Bukkit.getWorld(config.getString("Spawn.World"));
				double x = config.getDouble("Spawn.X");
				double y = config.getDouble("Spawn.Y");
				double z = config.getDouble("Spawn.Z");
				float yaw = (float) config.getDouble("Spawn.Yaw");
				float pitch = (float) config.getDouble("Spawn.Pitch");
				Location Loc = new Location(currentWorld, x, y, z, yaw, pitch);
				if (args.length == 0) {
					player.teleport(Loc);
					player.sendMessage(Tools.Prefix() + "§6Du wurdest erfolgreich zum §1Spawn §6teleportiert!");
				} else {
					player.sendMessage(Tools.Prefix() + "$cBitte verwende den korrekten Syntax §6/spawn §c!");
				}
			} else {
				player.sendMessage(Tools.Prefix()
						+ "$cDu hast nicht ausreichend Berechtigungen um diesn Befehl nutzen zu können!");
			}
		} else {
			player.sendMessage(Tools.Prefix() + "Dieser Befehl ist nicht per Konsole ausführbar!");
		}

		return false;
	}
}
