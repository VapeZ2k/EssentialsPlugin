package de.vapez2k.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.sethome")) {
				if (args.length == 1) {
					String name = args[0].toLowerCase();
						Tools h = new Tools();
						h.setHome(p, name);
						p.sendMessage(Tools.Prefix()+"§6Du hast soeben erfolgreich ein neues Home namens §1"+ name + " §6gesetzt!");
					}else {
						p.sendMessage(Tools.Prefix()+ "§cBitte verwende den richtigen Syntax: §6/home <name> /sethome <name> §c!");
					}
				} else {
					p.sendMessage(Tools.Prefix()+"§cHierfür hast du keine Berechtigung!");
				}
		}
		
		return false;
	}

	

}
