package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScareCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.scare")) {
				if (args.length == 0) {
					player.sendMessage(
							Tools.Prefix() + "§cBitte verwende den richtigen Syntax: §6/scare <Spieler> §c!");
				} else if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					target.isOnline();
					if (target.isOnline()) {
						target.damage(1);
						target.playEffect(target.getLocation(), Effect.WITHER_SHOOT, 0);
						target.sendTitle("§4BUH!", "You got scared by " + "§1" + player.getName());
					} else {
						player.sendMessage(Tools.Prefix() + "§cDer gewählte Spieler ist nicht online!");
					}
				} else {
					player.sendMessage(
							Tools.Prefix() + "§cBitte verwende den richtigen Syntax: §6/scare <Spieler> §c!");
				}
			} else {
				player.sendMessage(Tools.Prefix() + "§cDu besitzt nicht die nötigen Rechte für diesen Befehl!");
			}
		}

		return false;
	}

}
