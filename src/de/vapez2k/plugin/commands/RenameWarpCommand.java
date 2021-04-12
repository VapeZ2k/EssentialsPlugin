package de.vapez2k.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameWarpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("alge.rehome")) {
				if (args.length == 2) {
					String name = args[0];
					String newName = args[1];
					Tools h = new Tools();
					h.reWarp(p, name, newName);
					p.sendMessage(Tools.Prefix()+ "§6Du hast soeben erfolgreich §1" + name + " §6in §1"+ newName + "§6umbenannt!");
				}else {
					p.sendMessage(Tools.Prefix()+"§cBitte verwende den korrekten Syntax: §6/rewarp <name> <newname>§c!");
				}
			}else {
				p.sendMessage(Tools.Prefix()+"§cDafür hast du keine Berechtigungen!");
			}
		}
		
		return false;
	}
	
}
