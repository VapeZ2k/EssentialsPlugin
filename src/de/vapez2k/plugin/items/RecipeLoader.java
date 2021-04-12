package de.vapez2k.plugin.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

public class RecipeLoader {

	@SuppressWarnings("deprecation")
	public void registerRecipes() {

		//SaddleCrafting
		ItemStack saddle = new ItemStack(Material.SADDLE);
		ShapedRecipe craftSaddle = new ShapedRecipe(saddle);
		craftSaddle.shape("LLL","LSL","HSH");
		craftSaddle.setIngredient('L', Material.LEATHER);
		craftSaddle.setIngredient('S', Material.LEAD);
		craftSaddle.setIngredient('H', Material.TRIPWIRE_HOOK);
		
		Bukkit.addRecipe(craftSaddle);

	}
	
}
