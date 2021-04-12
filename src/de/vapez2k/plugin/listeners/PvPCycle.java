package de.vapez2k.plugin.listeners;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;

import de.vapez2k.plugin.commands.Tools;
import de.vapez2k.plugin.main.Main;

public class PvPCycle implements Listener{
	
	 public HashMap<String, Long> cooldown = new HashMap<String, Long>();
	 Main plugin;
	 
	@EventHandler
	public void runPvpCycle(EntityDamageByEntityEvent e) {
		

		    if (e.getDamager() instanceof Player){
	            if (e.getEntity() instanceof Player) {
	            	Player p = (Player) e.getEntity();
	            	Player attkPlayer = (Player) e.getDamager();
	            	 long time = p.getWorld().getTime();
					 if (time > 0 && time < 12300){
						 e.setCancelled(true);
					 }else {
						 Tools plugin = new Tools();
						 if (plugin.getRank(p).equals(plugin.getRank(attkPlayer))) {
							 e.setCancelled(true);
						 }
					 }
	            }
		    }else if(e.getCause().equals(DamageCause.PROJECTILE)) {
		    	if(((Projectile)e.getDamager()).getShooter() != null) {
		    		if(((Projectile)e.getDamager()).getShooter() instanceof Player) {
		    			Player p = (Player) e.getEntity();
		    			Player atkPlayer = (Player) ((Projectile)e.getDamager()).getShooter();
		            	 long time = p.getWorld().getTime();
						 if (time > 0 && time < 12300){
							 e.setCancelled(true);
						 }else {
							 Tools plugin = new Tools();
							 if (plugin.getRank(p).equals(plugin.getRank(atkPlayer))) {
								 e.setCancelled(true);
							 }
						 }

		    		}
		    	};
		    }
	}
	
	public HashMap<String,Long> getCooldowns() {
		return cooldown;
	}
}
