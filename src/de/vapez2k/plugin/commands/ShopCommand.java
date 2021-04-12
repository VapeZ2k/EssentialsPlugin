package de.vapez2k.plugin.commands;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.shop")) {
				if (args.length == 0) {
					Location shopLoc = new Location(player.getWorld(), 267, 75, -699);
					if (shopLoc != null) {
						player.teleport(shopLoc);
						player.playEffect(shopLoc, Effect.ENDER_SIGNAL, 0);
						player.sendMessage(Tools.Prefix() + "§6Du wurdest erfolgreich zum §1Shop §6teleportiert!");
					}
				} else {
					player.sendMessage(Tools.Prefix() + "§cBitte verwende den korrekten Syntax: §6/shop §c!");
				}
			} else {
				player.sendMessage(
						Tools.Prefix() + "§cDu besitzt nicht die nötigen Rechte um diesen Befehl verwenden zu können!");
			}
		} else {
			player.sendMessage(Tools.ConsolePrefix() + "Dieser Befehl ist nicht per Konsole ausführbar!");
		}

		return false;
	}

}
