package org.coderinlinux.practicee;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.coderinlinux.practicee.event.JoinEvent;

import java.awt.desktop.QuitEvent;

public class EventManager {
    public Plugin plugin;
    public PluginManager pluginManager;

    public EventManager(Plugin plugin) {
        this.plugin = plugin;
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerEvents(){
        pluginManager.registerEvents((Listener) new JoinEvent(), plugin);
    }
}
