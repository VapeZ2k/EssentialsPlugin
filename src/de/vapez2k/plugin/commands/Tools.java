package de.vapez2k.plugin.commands;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import de.vapez2k.plugin.main.Main;



public class Tools {

	public static File homeFile = new File("plugins/VapeZ2kPlugin", "homes.yml");
	public static FileConfiguration homecfg = YamlConfiguration.loadConfiguration(homeFile);
	public static File warpFile = new File("plugins/VapeZ2kPlugin","warps.yml");
	public static FileConfiguration warpcfg = YamlConfiguration.loadConfiguration(warpFile);
	public static File nameFile = new File("plugins/VapeZ2kPlugin", "customnames.yml");
	public static FileConfiguration namecfg = YamlConfiguration.loadConfiguration(nameFile);
	public static File rankFile = new File("plugins/VapeZ2kPlugin", "ranksystem.yml");
	public static FileConfiguration rankcfg = YamlConfiguration.loadConfiguration(rankFile);
	public static File ranks = new File("plugins/VapeZ2kPlugin", "ranks.yml");
	public static FileConfiguration rankslist = YamlConfiguration.loadConfiguration(ranks);

	public static String Prefix() {
		String prefix = ChatColor.GOLD+"["+ChatColor.AQUA+"ALGE"+ChatColor.GOLD+"]";
		return prefix;
	}

	public static String ConsolePrefix() {
		String consolePrefix = "[ALGE]: ";
		return consolePrefix;
	}


	public Location getLocation() {
		FileConfiguration config = Main.getPlugin().getConfig();
		World currentWorld = Bukkit.getWorld(config.getString("Spawn.World"));
		double x = config.getDouble("Spawn.X");
		double y = config.getDouble("Spawn.Y");
		double z = config.getDouble("Spawn.Z");
		float yaw = (float) config.getDouble("Spawn.Yaw");
		float pitch = (float) config.getDouble("Spawn.Pitch");
		Location Loc = new Location(currentWorld, x, y, z, yaw, pitch);
		return Loc;
	}

	public static void setLocation(Location pLoc) {
		FileConfiguration config = Main.getPlugin().getConfig();
		config.set("Spawn.World", pLoc.getWorld().getName());
		config.set("Spawn.X", pLoc.getX());
		config.set("Spawn.Y", pLoc.getY());
		config.set("Spawn.Z", pLoc.getZ());
		config.set("Spawn.Yaw", pLoc.getYaw());
		config.set("Spawn.Pitch", pLoc.getPitch());
		Main.getPlugin().saveConfig();
	}

	public void setHome(Player p,String name) {
		if(!hasHomes(p)) {
			ConfigurationSection cs = homecfg.createSection(p.getUniqueId().toString());
			cs.set(name, null);
			cs.set(name+".Name", name);
			cs.set(name+".Creator", p.getUniqueId().toString());
			cs.set(name+".World", p.getLocation().getWorld().getName());
			cs.set(name+".X", p.getLocation().getX());
			cs.set(name+".Y", p.getLocation().getY());
			cs.set(name+".Z", p.getLocation().getZ());
			cs.set(name+".Yaw", p.getLocation().getYaw());
			cs.set(name+".Pitch", p.getLocation().getPitch());
			try {
				homecfg.save(homeFile);
			} catch(IOException e) {
				e.printStackTrace();
			}	
		}else {
			ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
			cs.set(name, null);
			cs.set(name+".Name", name);
			cs.set(name+".Creator", p.getUniqueId().toString());
			cs.set(name+".World", p.getLocation().getWorld().getName());
			cs.set(name+".X", p.getLocation().getX());
			cs.set(name+".Y", p.getLocation().getY());
			cs.set(name+".Z", p.getLocation().getZ());
			cs.set(name+".Yaw", p.getLocation().getYaw());
			cs.set(name+".Pitch", p.getLocation().getPitch());
			try {
				homecfg.save(homeFile);
			} catch(IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void setRank(Player p,String rangName) {
		if(rankExists(rangName)) {
			if (hasRank(p)) {
				ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
				ConfigurationSection ranks = rankslist.getConfigurationSection(rangName);
				cs.set("Name", ranks.getString("Name"));
				cs.set("Color", ranks.getString("ChatColor"));
				p.setPlayerListName(getRankColor(p)+"["+getRank(p)+"] "+getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				try {
					rankcfg.save(rankFile);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				ConfigurationSection cs = rankcfg.createSection(p.getUniqueId().toString());
				ConfigurationSection ranks = rankslist.getConfigurationSection(rangName);
				cs.set("Name", ranks.getString("Name"));
				cs.set("Color", ranks.getString("ChatColor"));
				p.setPlayerListName(getRankColor(p)+"["+getRank(p)+"] "+getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				try {
					rankcfg.save(rankFile);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			p.sendMessage(Tools.Prefix()+ChatColor.RED+"Dieser Rank existiert nicht!");
		}
	}
	
	public void createRank(String rangName,ChatColor color) {
		ConfigurationSection cs = rankslist.createSection(rangName);
		cs.set("Name", rangName);
		cs.set("ChatColor", color.toString());
		try {
			rankslist.save(ranks);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean rankExists(String rangName) {
		if (rankslist.isConfigurationSection(rangName)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void removeRank(Player p,String oldrank) {
		ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
		cs.set("Name", null);
		cs.set("Color", null);
		rankcfg.set(p.getUniqueId().toString(), null);
		p.setPlayerListName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
		try {
			rankcfg.save(rankFile);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delRank(Player p,String rangName) {
		ConfigurationSection cs = rankslist.getConfigurationSection(rangName);
		rankslist.set(rangName, null);
		cs.set("Name", null);
		cs.set("ChatColor", null);
		if (hasSpecificRank(p, rangName)) {
			p.setPlayerListName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
		}
		try {
			rankslist.save(ranks);
			namecfg.save(nameFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasRank(Player p) {
		if (rankcfg.contains(p.getUniqueId().toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getRank(Player p) {
		ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
		if (cs.getString("Name") != null) {
			return cs.getString("Name");
		}
		return "";
	}
	
	public String getRankWithColor(Player p) {
		ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
		return cs.getString("Color")+"["+cs.getString("Name")+"]";
	}
	
	public boolean hasSpecificRank(Player p,String rankName) {
		if (hasRank(p)) {
			ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
			if (cs.getString("Name").equals(rankName)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public Location getHomeLoc(String name,Player p) {
		ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
		World world = Bukkit.getWorld(cs.getString(name+".World"));
		double x = cs.getDouble(name+".X");
		double y = cs.getDouble(name+".Y");
		double z = cs.getDouble(name+".Z");
		float yaw = (float) homecfg.getDouble(name+".Yaw");
		float pitch = (float) homecfg.getDouble(name+".Pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	public String[] getHomeList(Player p) {
		Object[] src = homecfg.getConfigurationSection(p.getUniqueId().toString()).getKeys(true).toArray();
		String[] arr = Arrays.asList(src).toArray(new String[src.length]);
		return arr;
	}
	
	public String[] getWarpList(Player p) {
		Object[] src = warpcfg.getKeys(true).toArray();
		String[] arr = Arrays.asList(src).toArray(new String[src.length]);
		return arr;
	}
	
	public Location getWarpLoc(String name) {
		World world = Bukkit.getWorld(warpcfg.getString(name+".World"));
		double x = warpcfg.getDouble(name + ".X");
		double y = warpcfg.getDouble(name + ".Y");
		double z = warpcfg.getDouble(name + ".Z");
		float yaw = (float) warpcfg.getDouble(name + ".Yaw");
		float pitch = (float) warpcfg.getDouble(name + ".Pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}

	public boolean homeExists(Player p,String name) {
		ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
		if (cs.contains(name)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean hasHomes(Player p) {
		if (homecfg.isConfigurationSection(p.getUniqueId().toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean warpExists(String name) {
		return warpcfg.contains(name.toLowerCase());
	}
	
	public boolean isHomeCreator(Player p, String name) {
		ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
		if(cs.getString(name+".Creator") != null) {
			if (cs.getString(name+".Creator").equals(p.getUniqueId().toString())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean isWarpCreator(Player p, String name) {
		if (warpcfg.getString(name+".Creator").equals(p.getUniqueId().toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public void delHome(Player p,String name) {
		if (homecfg.isConfigurationSection(p.getUniqueId().toString())) {
			ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
			if (cs.contains(name)) {
				cs.set(name, null);
				cs.set(name+".Name", null);
				cs.set(name+".Creator", null);
				cs.set(name+".World", null);
				cs.set(name+".X", null);
				cs.set(name+".Y", null);
				cs.set(name+".Z", null);
				cs.set(name+".Yaw", null);
				cs.set(name+".Pitch",null);
				try {
					homecfg.save(homeFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieses Home existiert nicht!");
			}
		}else {
			p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Du besitzt keine Homes!");
		}
	}
	
	public void setWarp(Player p,String name) {
		warpcfg.set(name, null);
		warpcfg.set(name + ".Name", name);
		warpcfg.set(name + ".World", p.getLocation().getWorld().getName());
		warpcfg.set(name + ".X", p.getLocation().getX());
		warpcfg.set(name + ".Y", p.getLocation().getY());
		warpcfg.set(name + ".Z", p.getLocation().getZ());
		warpcfg.set(name + ".Yaw", p.getLocation().getYaw());
		warpcfg.set(name + ".Pitch", p.getLocation().getPitch());
		try {
			warpcfg.save(warpFile);
			p.sendMessage(
					Tools.Prefix() + "§6Du hast den Warp §1" + name + " §6erfolgreich gesetzt!");
		} catch (IOException e) {
			p.sendMessage(Tools.Prefix() + "§cEin Fehler ist aufgetreten: " + e.getMessage());
		}
	}
	
	public void delWarp(Player p,String name) {
		if (warpExists(name)) {
				warpcfg.set(name, null);
				warpcfg.set(name+".Name", null);
				warpcfg.set(name+".Creator", null);
				warpcfg.set(name+".World", null);
				warpcfg.set(name+".X", null);
				warpcfg.set(name+".Y", null);
				warpcfg.set(name+".Z", null);
				warpcfg.set(name+".Yaw", null);
				warpcfg.set(name+".Pitch",null);
				try {
					warpcfg.save(warpFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else {
			p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieser Warp existiert nicht!");
		}
	}
	
	
	
	public void reHome(Player p,String name, String newName) {
		if (homeExists(p,name)) {
			if (isHomeCreator(p, name)) {
				ConfigurationSection cs = homecfg.getConfigurationSection(p.getUniqueId().toString());
				Location homeLoc = getHomeLoc(name, p);
				cs.set(newName, null);
				cs.set(newName+".Name", newName);
				cs.set(newName+".Creator", p.getUniqueId().toString());
				cs.set(newName+".World", homeLoc.getWorld().getName());
				cs.set(newName+".X", homeLoc.getX());
				cs.set(newName+".Y", homeLoc.getY());
				cs.set(newName+".Z", homeLoc.getZ());
				cs.set(newName+".Yaw", homeLoc.getYaw());
				cs.set(newName+".Pitch", homeLoc.getPitch());
				cs.set(name, null);
				try {
					homecfg.save(homeFile);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieses Home gehört dir nicht!");
			}
		}else {
			p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieses Home existiert nicht!");
		}
	}
	

	
	public void reWarp(Player p,String name, String newName) {
		if (homeExists(p,name)) {
			if (isHomeCreator(p, name)) {
				warpcfg.set(newName, null);
				warpcfg.set(newName + ".Creator", warpcfg.getString(name+".Creator"));
				warpcfg.set(newName + ".World", warpcfg.getString(name + ".World"));
				warpcfg.set(newName + ".X", warpcfg.getDouble(name + ".X"));
				warpcfg.set(newName + ".Y", warpcfg.getDouble(name + ".Y"));
				warpcfg.set(newName + ".Z", warpcfg.getDouble(name + ".Z"));
				warpcfg.set(newName + ".Yaw", warpcfg.getDouble(name + ".Yaw"));
				warpcfg.set(newName + ".Pitch", warpcfg.getDouble(name + ".Pitch"));
				delHome(p, name);
				try {
					warpcfg.save(warpFile);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieser Warp gehört dir nicht!");
			}
		}else {
			p.sendMessage(Tools.Prefix()+ChatColor.GOLD+"Dieser Warp existiert nicht!");
		}
	}
	
	
	
	public ChatColor getChatColor(String color) {
		if (color != null) {
			switch (color.toLowerCase()) {
			case "black":
				return ChatColor.BLACK;
			case "dark_blue":
				return ChatColor.DARK_BLUE;
			case "dark_green":
				return ChatColor.DARK_GREEN;
			case "dark_aqua":
				return ChatColor.DARK_AQUA;
			case "dark_red":
				return ChatColor.DARK_RED;
			case "dark_purple":
				return ChatColor.DARK_PURPLE;
			case "gold":
				return ChatColor.GOLD;
			case "gray":
				return ChatColor.GRAY;
			case "dark_gray":
				return ChatColor.DARK_GRAY;
			case "blue":
				return ChatColor.BLUE;
			case "green":
				return ChatColor.GREEN;
			case "aqua":
				return ChatColor.AQUA;
			case "red":
				return ChatColor.RED;
			case "light_purple":
				return ChatColor.LIGHT_PURPLE;
			case "yellow":
				return ChatColor.YELLOW;
			case "white":
				return ChatColor.WHITE;
			case "magic":
				return ChatColor.MAGIC;
			}
		}else {
			return ChatColor.WHITE;
		}
		return ChatColor.WHITE;
	}
	
	public String getRankColor(Player p) {
		ConfigurationSection cs = rankcfg.getConfigurationSection(p.getUniqueId().toString());
		return cs.getString("Color");
	}
	
	public void delCustomName(Player p) {
		ConfigurationSection cs = namecfg.getConfigurationSection(p.getUniqueId().toString());
		cs.set("CustomName", null);
		cs.set("Color", null);
		try {
			namecfg.save(nameFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasCustomName(Player p) {
		if (namecfg.isConfigurationSection(p.getUniqueId().toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getCustomName(Player p) {
		ConfigurationSection cs = namecfg.getConfigurationSection(p.getUniqueId().toString());
		return cs.getString("CustomName");
	}
	
	public String getCustomColor(Player p) {
		ConfigurationSection cs = namecfg.getConfigurationSection(p.getUniqueId().toString());
		return cs.getString("Color");
	}
	
	public String getCustomRank(Player p) {
		ConfigurationSection cs = namecfg.getConfigurationSection(p.getUniqueId().toString());
		return cs.getString("Rank");
	}
	
	public void saveCustomName(Player p,String newname,String color) {
		if (!hasCustomName(p)) {
			ConfigurationSection cs = namecfg.createSection(p.getUniqueId().toString());
			cs.set("CustomName", newname);
			cs.set("Color", color);
			try {
				namecfg.save(nameFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			delCustomName(p);
			ConfigurationSection cs = namecfg.createSection(p.getUniqueId().toString());
			cs.set("CustomName", newname);
			cs.set("Color", color);
			try {
				namecfg.save(nameFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
public void setCustomName(Player p) {
	if (getChatColor(getCustomColor(p)) != null) {
		p.setDisplayName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
		p.setCustomName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
		p.setPlayerListName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
		p.setCustomNameVisible(true);
		p.setPlayerListHeader("Nickname: " + getChatColor(getCustomColor(p))+getCustomName(p));
	}
}

public void setCustomNameAndRank(Player p) {
	if (getChatColor(getCustomColor(p)) != null) {
		if (hasRank(p)) {
			if (rankExists(getRank(p))) {
				p.setDisplayName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setCustomName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setPlayerListName(getRankColor(p)+"["+getRank(p)+"] "+getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setCustomNameVisible(true);
				p.setPlayerListHeader("Nickname: " + getChatColor(getCustomColor(p))+getCustomName(p));
			}else {
				p.setDisplayName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setCustomName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setPlayerListName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
				p.setCustomNameVisible(true);
				p.setPlayerListHeader("Nickname: " + getChatColor(getCustomColor(p))+getCustomName(p));
			}
		}else {
			p.setDisplayName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
			p.setCustomName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
			p.setPlayerListName(getChatColor(getCustomColor(p))+getCustomName(p)+ChatColor.WHITE);
			p.setCustomNameVisible(true);
			p.setPlayerListHeader("Nickname: " + getChatColor(getCustomColor(p))+getCustomName(p));
		}
	
	}
}
	
	public void openMainMenu(Player p) {
		
		Inventory main_menu = Bukkit.createInventory(p, 9, ChatColor.BLUE + "ArmorStand GUI");

		//Options for main menu
		
		//Create Option
		ItemStack create = new ItemStack(Material.ARMOR_STAND);
		ItemMeta create_meta = create.getItemMeta();
		create_meta.setDisplayName(ChatColor.GREEN+"Create");
		ArrayList<String> create_lore = new ArrayList<>();
		create_lore.add(ChatColor.DARK_PURPLE +"Create a new armor stand.");
		create_meta.setLore(create_lore);
		create.setItemMeta(create_meta);
		
		
		//Close Option
		ItemStack close = new ItemStack(Material.BARRIER);
		ItemMeta close_meta =  close.getItemMeta();
		close_meta.setDisplayName(ChatColor.RED+"Close");
		ArrayList<String> close_lore = new ArrayList<>();
		close_lore.add(ChatColor.DARK_PURPLE+"Close the menu.");
		close_meta.setLore(close_lore);
		close.setItemMeta(close_meta);
		
		//Setting the Options
		main_menu.setItem(0, create);
		main_menu.setItem(8, close);
		p.openInventory(main_menu);
		
	}

	public void openCreateMenu(Player p) {
		Inventory create_menu = Bukkit.createInventory(p, 9, ChatColor.GREEN+"Create a Armor Stand");
		
		ItemStack arms = new ItemStack(Material.ARMOR_STAND);
		ItemStack glow = new ItemStack(Material.GLOWSTONE);
		ItemStack armor = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack base = new ItemStack(Material.STONE_SLAB);
		ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
		ItemStack cancel = new ItemStack(Material.RED_WOOL);
		
		ItemMeta arms_meta = arms.getItemMeta();
		arms_meta.setDisplayName(ChatColor.YELLOW+"Arms");
		ItemMeta glow_meta = glow.getItemMeta();
		glow_meta.setDisplayName(ChatColor.YELLOW+"Glow");
		ItemMeta armor_meta = armor.getItemMeta();
		armor_meta.setDisplayName(ChatColor.AQUA+"Armor");
		ItemMeta base_meta = base.getItemMeta();
		base_meta.setDisplayName(ChatColor.GOLD+"Base");
		ItemMeta confirm_meta = confirm.getItemMeta();
		confirm_meta.setDisplayName(ChatColor.GREEN+"Confirm Creation");
		ItemMeta cancel_meta = cancel.getItemMeta();
		cancel_meta.setDisplayName(ChatColor.RED+"Cancel Creation");
		
		
		create_menu.setItem(0, arms);
		create_menu.setItem(1, glow);
		create_menu.setItem(2, armor);
		create_menu.setItem(3, base);
		create_menu.setItem(7, confirm);
		create_menu.setItem(8, cancel);
		
		p.openInventory(create_menu);
	}

	public void openConfirmMenu(Player p, Material option) {
		Inventory confirm_menu = Bukkit.createInventory(p, 36, ChatColor.YELLOW+"Confirm selection");
		
		ItemStack option_item = new ItemStack(option);
		ItemMeta  option_meta = option_item.getItemMeta();
		
		if (option == Material.ARMOR_STAND) {
			option_meta.setDisplayName(ChatColor.YELLOW+"Give Arms?");
			option_item.setItemMeta(option_meta);
		} else if (option == Material.GLOWSTONE) {
			option_meta.setDisplayName(ChatColor.YELLOW+"Add Glow?");
			option_item.setItemMeta(option_meta);
		} else if (option == Material.STONE_SLAB) {
			option_meta.setDisplayName(ChatColor.YELLOW+"Add Base?");
			option_item.setItemMeta(option_meta);
		}
		
		ItemStack yes = new ItemStack(Material.GREEN_WOOL);
		ItemMeta yes_meta = yes.getItemMeta();
		yes_meta.setDisplayName(ChatColor.GREEN+"YES");
		yes.setItemMeta(yes_meta);
		ItemStack no = new ItemStack(Material.RED_WOOL);
		ItemMeta no_meta = no.getItemMeta();
		no_meta.setDisplayName(ChatColor.RED+"NO");
		no.setItemMeta(no_meta);
		
		confirm_menu.setItem(13,option_item);
		confirm_menu.setItem(21,yes);
		confirm_menu.setItem(23, no);
		
		p.openInventory(confirm_menu);
	}
}
