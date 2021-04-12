package de.vapez2k.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.delhome")) {
				if (args.length == 1) {
					Tools h = new Tools();
					String name = args[0].toLowerCase();
					if (h.homeExists(p,name)) {
						h.delHome(p, name);
						p.sendMessage(Tools.Prefix()+"§6Du hast soeben das Home §1"+name+" §6erfolgreich gelöscht!");
					} else {
						p.sendMessage(Tools.Prefix()+"§cDieses Home existiert nicht!");
					}
					
				} else {
					p.sendMessage(Tools.Prefix()+"§cBitte verwende den korrekten Syntax: §6/delhome <name> §c!");
				}
			} else {
				p.sendMessage(Tools.Prefix()+"§cDafür hast du keine Berechtigungen!");
			}
		}
		return false;
	}

}
