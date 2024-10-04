package org.coderinlinux.practicee.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.coderinlinux.practicee.Practicee;

import java.util.HashMap;
import java.util.Map;

public class SpawnCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final Map<String, Location> spawns = new HashMap<>();

    public SpawnCommand(Practicee practicee) {
        this.plugin = practicee;
        loadSpawns();
    }

    private void loadSpawns() {
        FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveDefaultConfig();

        for (String spawnName : config.getConfigurationSection("spawns.").getKeys(false)) {
            Location location = null;
            if (config.contains("spawns." + spawnName)) {
                World world = Bukkit.getWorld(config.getString("spawns." + spawnName + ".world"));
                if (world != null) {
                    double x = config.getDouble("spawns." + spawnName + ".x");
                    double y = config.getDouble("spawns." + spawnName + ".y");
                    double z = config.getDouble("spawns." + spawnName + ".z");
                    float yaw = (float) config.getDouble("spawns." + spawnName + ".yaw");
                    float pitch = (float) config.getDouble("spawns." + spawnName + ".pitch");
                    location = new Location(world, x, y, z, yaw, pitch);
                }
            }
            spawns.put(spawnName, location);
        }
    }

    private void saveSpawns() {
        FileConfiguration config = plugin.getConfig();
        config.set("spawns", spawns);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser usado por jugadores");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            Location spawn = spawns.get("default");
            if (spawn != null) {
                player.teleport(spawn);
                player.sendMessage(ChatColor.GREEN + "Te has teletransportado al spawn principal.");
            } else {
                player.sendMessage(ChatColor.RED + "No hay un spawn principal establecido.");
            }
        } else if (args[0].equalsIgnoreCase("set")) {
            if (player.hasPermission("practicee.setspawn")) {
                spawns.put("default", player.getLocation());
                saveSpawns();
                player.sendMessage(ChatColor.GREEN + "Spawn principal establecido.");
            } else {
                player.sendMessage(ChatColor.RED + "No tienes permiso para establecer un spawn.");
            }
        } else if (args[0].equalsIgnoreCase("manager")) {
            if (spawns.isEmpty()) {
                player.sendMessage(ChatColor.AQUA + "No hay spawns creados.");
            } else {
                player.sendMessage(ChatColor.AQUA + "Spawns disponibles:");
                for (String spawnName : spawns.keySet()) {
                    player.sendMessage(ChatColor.GRAY + "- " + spawnName);
                }
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
            String spawnName = args[1];
            if (spawns.containsKey(spawnName)) {
                spawns.remove(spawnName);
                saveSpawns();
                player.sendMessage(ChatColor.GREEN + "Spawn " + spawnName + " eliminado.");
            } else {
                player.sendMessage(ChatColor.RED + "No existe un spawn con ese nombre.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Uso correcto: /spawn, /spawn set, /spawn manager o /spawn delete <nombre>");
        }
        return true;
    }
}