package de.vapez2k.plugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerRodListener implements Listener{
	
	@EventHandler
	public void PlayerRodHandler(PlayerFishEvent e) {
		if (e.getCaught() instanceof Player) {
			Player target = (Player) e.getCaught();
			target.damage(0);
		}
	}
}
