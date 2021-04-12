package de.vapez2k.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.delwarp")) {
				if (args.length == 1) {
					Tools w = new Tools();
					String name = args[0].toLowerCase();
					if (w.warpExists(name)) {
						w.delWarp(p, name);
						p.sendMessage(Tools.Prefix()+"§6Du hast soeben den Warp §1"+name+" §6erfolgreich gelöscht!");
					}else {
						p.sendMessage(Tools.Prefix()+"§cDieses Home existiert nicht!");
					}
				}else {
					p.sendMessage(Tools.Prefix()+"§cBitte verwende den korrekten Syntax: §6/delwarp <name> §c!");
				}
			}else {
				p.sendMessage(Tools.Prefix()+"§cDafür hast du keine Berechtigungen!");
			}
		}
		
		return false;
	}

}
