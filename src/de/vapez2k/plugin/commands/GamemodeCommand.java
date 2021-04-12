package de.vapez2k.plugin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.gamemode")) {
				if (args.length == 0) {
					String gamemode = player.getGameMode().toString();
					if (gamemode.equalsIgnoreCase("SURVIVAL")) {
						player.setGameMode(GameMode.CREATIVE);
						gamemode = "CREATIVE";
						player.sendMessage(
								Tools.Prefix() + "§6Dein Gamemode wurde erfolgreich auf §1" + gamemode + " §6gesetzt!");
					} else if (gamemode.equalsIgnoreCase("CREATIVE")) {
						player.setGameMode(GameMode.SURVIVAL);
						gamemode = "SURVIVAL";
						player.sendMessage(
								Tools.Prefix() + "§6Dein Gamemode wurde erfolgreich auf §1" + gamemode + " §6gesetzt!");
					} else if (gamemode.equalsIgnoreCase("ADVENTURE")) {
						player.setGameMode(GameMode.CREATIVE);
						gamemode = "CREATIVE";
						player.sendMessage(
								Tools.Prefix() + "§6Dein Gamemode wurde erfolgreich auf §1" + gamemode + " §6gesetzt!");
					} else if (gamemode.equalsIgnoreCase("SPECTATOR")) {
						player.setGameMode(GameMode.CREATIVE);
						gamemode = "CREATIVE";
						player.sendMessage(
								Tools.Prefix() + "§6Dein Gamemode wurde erfolgreich auf §1" + gamemode + " §6gesetzt!");
					}

				} else {
					player.sendMessage(Tools.Prefix() + "§cBitte verwende den korrekten Syntax: §6/gm §4!");
				}
			} else {
				player.sendMessage(Tools.Prefix() + "§cDu besitzt nicht die nötigen Rechte für diesen Befehl!");
			}
		}
		return false;
	}

}
