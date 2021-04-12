package de.vapez2k.plugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShowInvCommand implements CommandExecutor{

	@Override
	public boolean onCommand( CommandSender sender,  Command cmd, String label,String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("alge.showinv")) {
				if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					player.openInventory(target.getInventory());
				}else {
					player.sendMessage(Tools.Prefix()+"�cBitte verwende den richtigen Syntax: �/showinv <Spieler> �c!");
				}
			}else {
				player.sendMessage(Tools.Prefix()+"�cDu besitzt nicht gen�gend Rechte!");
			}
		}
		return false;
	}

}
