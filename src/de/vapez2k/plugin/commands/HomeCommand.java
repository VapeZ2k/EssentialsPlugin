package de.vapez2k.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.vapez2k.plugin.listeners.PvPCycle;
import io.netty.handler.ssl.PemPrivateKey;
import net.minecraft.server.v1_15_R1.EntityFox.i;

public class HomeCommand implements CommandExecutor {
	
	private PvPCycle pvp = new PvPCycle();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			if (p.hasPermission("alge.home")) {
				if (args.length == 1) {
						String name = args[0].toLowerCase();
						Tools h = new Tools();
						if (h.hasHomes(p)) {
							if (h.homeExists(p,name)) {
								if (h.isHomeCreator(p, name)) {
									p.teleport(h.getHomeLoc(name,p));
									p.sendMessage(Tools.Prefix()+ "§6Du wurdest erfolgreich nach §1" + name + " §6teleportiert!");			
								} else {
									p.sendMessage(Tools.Prefix()+"§cDu besitzt kein solches Home! Verwende §6/sethome <name> §cum ein neues Home zu erstellen!");
								}
							} else {
								p.sendMessage(Tools.Prefix()+"§cDieses Home existiert nicht! Verwende §6/sethome <name> §cum ein neues Home zu erstellen!");
							}
						}else {
							p.sendMessage(Tools.Prefix()+"§cDu besitzt momentan keine Homes! Erstelle eins mit §6/sethome <name> !");
						}
					} else {
						p.sendMessage(Tools.Prefix()+"§cBitte verwende den richtigen Syntax: §6/home <name> /sethome <name> §c!");
					}
				} else {
					p.sendMessage(Tools.Prefix()+"§cHierfür hast du keine Berechtigung!");
			}
		}
		return false;
	}
}
