package de.vapez2k.plugin.main;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.vapez2k.plugin.commands.ArmorStandGUI;
import de.vapez2k.plugin.commands.DelHomeCommand;
import de.vapez2k.plugin.commands.DelWarpCommand;
import de.vapez2k.plugin.commands.DiaSecretCommand;
import de.vapez2k.plugin.commands.GamemodeCommand;
import de.vapez2k.plugin.commands.HomeCommand;
import de.vapez2k.plugin.commands.HomeListCommand;
import de.vapez2k.plugin.commands.QuickFlyCommand;
import de.vapez2k.plugin.commands.QuickHealCommand;
import de.vapez2k.plugin.commands.RankSystemCommand;
import de.vapez2k.plugin.commands.RenameCommand;
import de.vapez2k.plugin.commands.RenameHomeCommand;
import de.vapez2k.plugin.commands.RenameWarpCommand;
import de.vapez2k.plugin.commands.ScareCommand;
import de.vapez2k.plugin.commands.SetHomeCommand;
import de.vapez2k.plugin.commands.SetSpawnCommand;
import de.vapez2k.plugin.commands.SetWarpCommand;
import de.vapez2k.plugin.commands.ShopCommand;
import de.vapez2k.plugin.commands.ShowInvCommand;
import de.vapez2k.plugin.commands.SpawnCommand;
import de.vapez2k.plugin.commands.Tools;
import de.vapez2k.plugin.commands.WarpCommand;
import de.vapez2k.plugin.commands.WarpListCommand;
import de.vapez2k.plugin.items.RecipeLoader;
import de.vapez2k.plugin.listeners.PlayerChatListener;
import de.vapez2k.plugin.listeners.PlayerJoinListener;
import de.vapez2k.plugin.listeners.PlayerLeaveListener;
import de.vapez2k.plugin.listeners.PlayerRodListener;
import de.vapez2k.plugin.listeners.PvPCycle;

public class Main extends JavaPlugin {

	private static Main plugin;

	public void onEnable() {
		plugin = this;
		System.out.println(Tools.ConsolePrefix() + "The Plugin has been loaded successfully!");
		new RecipeLoader().registerRecipes();
		getCommand("scare").setExecutor(new ScareCommand());
		getCommand("gm").setExecutor(new GamemodeCommand());
		getCommand("h").setExecutor(new QuickHealCommand());
		getCommand("f").setExecutor(new QuickFlyCommand());
		getCommand("rename").setExecutor(new RenameCommand());
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("setwarp").setExecutor(new SetWarpCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("dia").setExecutor(new DiaSecretCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("delhome").setExecutor(new DelHomeCommand());
		getCommand("rehome").setExecutor(new RenameHomeCommand());
		getCommand("rewarp").setExecutor(new RenameWarpCommand());
		getCommand("delwarp").setExecutor(new DelWarpCommand());
		getCommand("listhomes").setExecutor(new HomeListCommand());
		getCommand("listwarps").setExecutor(new WarpListCommand());
		getCommand("rank").setExecutor(new RankSystemCommand());
		getCommand("showinv").setExecutor(new ShowInvCommand());

		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
		getServer().getPluginManager().registerEvents(new PvPCycle(), this);
		getServer().getPluginManager().registerEvents(new PlayerRodListener(), this);	}

	public static Main getPlugin() {
		return plugin;
	}
	
	public boolean day() {
	    Server server = getServer();
	    long time = server.getWorld("world").getTime();

	    if(time > 0 && time < 12300) {
	        return true;
	    } else {
	        return false;
	    }
	}
}
