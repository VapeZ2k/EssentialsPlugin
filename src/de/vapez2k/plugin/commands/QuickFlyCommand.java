package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")

public class QuickFlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.hasPermission("alge.fly")) {
				if (args.length == 0) {
					boolean flyEnabled = player.getAllowFlight();
					if (flyEnabled == false) {
						player.setAllowFlight(true);
						player.sendMessage(Tools.Prefix() + "§6Du kannst nun fliegen!");
					} else if (flyEnabled == true) {
						player.setAllowFlight(false);
						player.setFlying(false);
						player.sendMessage(Tools.Prefix() + "§6Du kannst nun nicht mehr fliegen!");
					}
				} else if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target.isOnline() == true) {
						boolean flyEnabled = target.getAllowFlight();
						if (flyEnabled == false) {
							target.setAllowFlight(true);
							target.sendMessage(Tools.Prefix() + "§6Dir wurde durch §1" + player.getName()
									+ " §6das Fliegen ermöglicht!");
							player.sendMessage(Tools.Prefix() + "§1" + target.getName() + " §6Kann nun fliegen!");
						} else if (flyEnabled == true) {
							target.setAllowFlight(false);
							target.setFlying(false);
							target.sendMessage(
									Tools.Prefix() + "§1" + player.getName() + " §6hat deine Flugpartie beendet!");
							player.sendMessage(
									Tools.Prefix() + "§1" + target.getName() + " §6Kann nun nicht mehr fliegen!");
						} else {
							player.sendMessage(Tools.Prefix()
									+ "§cIrgendetwas scheint schief gelaufen zu sein! Versuche es bitte erneut!");
						}
					} else {
						player.sendMessage(Tools.Prefix() + "§cDieser Spieler ist momentan nicht online!");
					}
				} else {
					player.sendMessage(
							Tools.Prefix() + "§cfBitte verwende den richtigen Syntax: §6/f oder /f <Spieler> §4!");
				}
			} else {
				player.sendMessage(
						Tools.Prefix() + "§cDu besitzt nicht genügend Rechte um diesen Befehl nutzen zu können!");
			}
		} else {
			player.sendMessage(Tools.Prefix() + "Dieser Befehl ist nicht per Konsole ausführbar!");
		}
		return false;
	}
}
