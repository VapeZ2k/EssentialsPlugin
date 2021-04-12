package de.vapez2k.plugin.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.vapez2k.plugin.commands.Tools;
import de.vapez2k.plugin.main.Main;

public class PlayerJoinListener implements Listener{
	
	@EventHandler
	public void PlayerJoinEventHandler(PlayerJoinEvent e) {
		if(e != null) {
			if (e.getPlayer() != null) {
				Tools plugin = new Tools();
				Player p = e.getPlayer();
				Date now = new Date();
				SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy:mm:ss");
				String time = formate.format(now);
				plugin.setCustomNameAndRank(p);
				e.setJoinMessage(plugin.getRankWithColor(p)+plugin.getCustomColor(p)+plugin.getCustomName(p)+ChatColor.GOLD+" ist zurück!");
				if (p.hasPlayedBefore()) {
					p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Willkommen zurück "+ChatColor.AQUA+p.getName()+"!");
					p.sendMessage(ChatColor.GOLD+"Serverzeit: "+ChatColor.AQUA+time);
					p.sendMessage(ChatColor.GOLD+"Falls du Hilfe benötigst nutze: "+ChatColor.AQUA+"/help alge");
					p.sendMessage(ChatColor.GOLD+"Viel Spaß!");
					e.setJoinMessage(plugin.getRankWithColor(p)+plugin.getChatColor(plugin.getCustomColor(p))+" "+plugin.getCustomName(p)+ChatColor.GOLD+" ist zurück!");
				}else {
					p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Willkommen "+ChatColor.AQUA+p.getName()+"!");
					p.sendMessage(ChatColor.GOLD+"Serverzeit: "+ChatColor.AQUA+time);
					p.sendMessage(ChatColor.GOLD+"Falls du Hilfe benötigst nutze: "+ChatColor.AQUA+"/help alge");
					p.sendMessage(ChatColor.GOLD+"Viel Spaß!");
				}	
			}
		}
	}

}
