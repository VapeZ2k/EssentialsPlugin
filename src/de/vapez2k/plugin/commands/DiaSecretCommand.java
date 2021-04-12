package de.vapez2k.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiaSecretCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("alge.dia")) {
				Inventory inv = Bukkit.createInventory(player, 9 * 3, "§1eZ :)");

				ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
				ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
				ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
				ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta metaHelmet = helmet.getItemMeta();
				ItemMeta metaChest = chest.getItemMeta();
				ItemMeta metaPants = pants.getItemMeta();
				ItemMeta metaBoots = boots.getItemMeta();
				ItemMeta metaSword = sword.getItemMeta();
				metaHelmet.addEnchant(Enchantment.WATER_WORKER, 1, false);
				metaHelmet.addEnchant(Enchantment.THORNS, 10, true);
				metaChest.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
				metaChest.addEnchant(Enchantment.THORNS, 10, true);
				metaPants.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
				metaPants.addEnchant(Enchantment.THORNS, 10, true);
				metaBoots.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
				metaBoots.addEnchant(Enchantment.THORNS, 10, true);
				metaBoots.addEnchant(Enchantment.PROTECTION_FALL, 10, true);
				metaBoots.addEnchant(Enchantment.FROST_WALKER, 10, true);
				metaSword.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
				metaSword.addEnchant(Enchantment.FIRE_ASPECT, 10, true);
				helmet.setItemMeta(metaHelmet);
				chest.setItemMeta(metaChest);
				pants.setItemMeta(metaPants);
				boots.setItemMeta(metaBoots);
				sword.setItemMeta(metaSword);
				inv.addItem(sword);
				inv.addItem(helmet);
				inv.addItem(chest);
				inv.addItem(pants);
				inv.addItem(boots);
				player.openInventory(inv);

			} else {
				player.sendMessage("§cNice Try.");
			}
		}

		return false;
	}

}
