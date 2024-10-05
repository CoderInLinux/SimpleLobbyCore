/**
 * Plugin by @CoderInLinux
 */
package org.coderinlinux.practicee;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.coderinlinux.practicee.event.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.coderinlinux.practicee.commands.*;

public final class Practicee extends JavaPlugin implements Listener {
    public static String prefix = "§d[§5Compass§d]";

    @Override
    public void onEnable() {
        getConfig().getString("compass-name", "&d⇨ &5 Warp &d⇦");
        getConfig().addDefault("compass-inventory-name", "&d⇨ &5 Vestibulos &d⇦");
        getConfig().addDefault("get-compass-on-join", true);
        getConfig().addDefault("get-compass-on-join-slot", 4);
        getConfig().addDefault("get-compass-worlds", new String[] { "world", "world_nether", "world_the_end" });
        getConfig().addDefault("only-allow-command-lc_get-in-worlds", true);
        getConfig().addDefault("can-drop-compass", false);
        getConfig().addDefault("inventory-lines-amount", 4);
        getConfig().addDefault("sound-effect", true);

        getConfig().addDefault("options", new String[] { "spawn", "pvp" });

        getConfig().addDefault("data.spawn.name", "&6Spawn");
        getConfig().addDefault("data.spawn.lore", new String[] { "&7Click this Item to execute command", "&8/warp spawn"});
        getConfig().addDefault("data.spawn.item", 2);
        getConfig().addDefault("data.spawn.cmd", "warp spawn");
        getConfig().addDefault("data.spawn.executedByPlayer", true);
        getConfig().addDefault("data.spawn.position-in-inventory", 10);

        getConfig().addDefault("data.pvp.name", "&6PvP Zone");
        getConfig().addDefault("data.pvp.lore", new String[] { "&7Click this Item to execute command", "&8/pvp <player> join" });
        getConfig().addDefault("data.pvp.item", 267);
        getConfig().addDefault("data.pvp.cmd", "pvp %n% join");
        getConfig().addDefault("data.pvp.executedByPlayer", false);
        getConfig().addDefault("data.pvp.position-in-inventory", 19);

        getConfig().options().copyHeader(true);
        getConfig().options().copyDefaults(true);
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("enrich").setExecutor(new EnrichCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("kit").setExecutor(new KitCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand(this));
        this.getCommand("report").setExecutor(new ReportCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("compass").setExecutor(new CompassCommand(this));
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new CompassCommand(this), this);
        new EventManager(this);
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled!");
    }
}
