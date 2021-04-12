package de.vapez2k.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class ArmorStandGUI implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("alge.ArmorGUI")) {
				Tools gui = new Tools();
				gui.openMainMenu(p);	  
			}
		}	
		return false;
	}
}
