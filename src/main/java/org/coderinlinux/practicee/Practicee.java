package org.coderinlinux.practicee;

import org.coderinlinux.practicee.event.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.coderinlinux.practicee.commands.*;

public final class Practicee extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("enrich").setExecutor(new EnrichCommand());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        new EventManager(this);
        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled!");
    }
}
