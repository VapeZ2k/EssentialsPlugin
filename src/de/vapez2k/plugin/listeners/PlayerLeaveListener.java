package de.vapez2k.plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.vapez2k.plugin.commands.Tools;

public class PlayerLeaveListener implements Listener{

	@EventHandler
	public void PlayerLeaveEventHandler(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Tools plugin = new Tools();
		if (p != null) {
			e.setQuitMessage(plugin.getRankWithColor(p)+plugin.getChatColor(plugin.getCustomColor(p))+" "+plugin.getCustomName(p)+ChatColor.GOLD+" ist geleavt!");
		}
	}
}
