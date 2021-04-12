package de.vapez2k.plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.vapez2k.plugin.commands.Tools;

public class PlayerChatListener implements Listener{
	
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		try {
			Tools plugin = new Tools();
			if(plugin.hasRank(e.getPlayer())) {
				if (plugin.rankExists(plugin.getRank(e.getPlayer()))) {
					if (plugin.getRank(e.getPlayer()) != null) {
						e.setFormat(plugin.getRankColor(e.getPlayer())+"["+plugin.getRank(e.getPlayer())+ "] "+plugin.getChatColor(plugin.getCustomColor(e.getPlayer()))+ plugin.getCustomName(e.getPlayer())+ChatColor.WHITE+": "+e.getMessage());
					}
				}
			}else {
				if (plugin.hasCustomName(e.getPlayer())) {
					e.setFormat(plugin.getChatColor(plugin.getCustomColor(e.getPlayer()))+ plugin.getCustomName(e.getPlayer())+ChatColor.WHITE+": "+e.getMessage());
				}
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
