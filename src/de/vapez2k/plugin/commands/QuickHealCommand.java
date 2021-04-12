package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")

public class QuickHealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.heal")) {
				if (args.length == 0) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage(Tools.Prefix() + "§6Du wurdest erfolgreich geheilt!");
				} else if (args.length == 1) {
					if (Bukkit.getPlayer(args[0]) instanceof Player) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (target.isOnline() == true) {
								target.setHealth(20);
								target.setFoodLevel(20);
								target.sendMessage(Tools.Prefix() + "§6Du wurdest soeben durch §1 " + player.getName()
										+ " §6geheilt!");
								player.sendMessage(
										Tools.Prefix() + "§1" + target.getName() + " §6wurde erfolgreich geheilt!");
							} else {
								player.sendMessage(Tools.Prefix() + "§4Der gewählte Spieler ist nicht online!");
							}
						} else {
							player.sendMessage(Tools.Prefix() + "§4Dieser Spieler existiert nicht!");
						}
					} else {
						player.sendMessage(Tools.Prefix()
								+ "§4Dieser Spieler scheint nicht zu existieren. Überprüfe deine Eingabe!");
					}
				} else {
					player.sendMessage(
							Tools.Prefix() + "§4Verwende bitte den korrekten Syntax:  §6/heal <Spieler> §4!");
				}
			} else {
				player.sendMessage(Tools.Prefix()
						+ "§4Du besitzt leider nicht genügend Rechte um diesen Befehl nutzen zu können!");
			}
		} else {
			player.sendMessage(Tools.Prefix() + "Dieser Befehl kann nicht von der Konsole aus aufgerufen werden!");
		}

		return false;
	}

}
