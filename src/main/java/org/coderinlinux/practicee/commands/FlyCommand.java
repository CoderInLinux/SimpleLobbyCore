package org.coderinlinux.practicee.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Solo los jugadores pueden usar este comando."); // More informative message
            return true;
        }
        Player player = (Player) commandSender;
        if (player.hasPermission("practicee.fly")) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(ChatColor.GREEN + "Vuelo desactivado."); // Consistent green for disabling
            } else {
                player.setAllowFlight(true);
                player.setFlying(true); // Set flying to true as well for immediate effect
                player.sendMessage(ChatColor.AQUA + "Vuelo activado."); // Use aqua for enabling
            }
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "No tienes permiso para volar.");
            return true;
        }
    }
}