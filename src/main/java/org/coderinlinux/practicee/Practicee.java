/**
 * Plugin by @CoderInLinux
 */
package org.coderinlinux.practicee;
import org.bukkit.event.Listener;
import org.coderinlinux.practicee.event.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.coderinlinux.practicee.commands.*;

public final class LobbyCore extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getCommand("enrich").setExecutor(new EnrichCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("kit").setExecutor(new KitCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand(this));
        this.getCommand("report").setExecutor(new ReportCommand());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        new EventManager(this);
        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled!");
    }
}
