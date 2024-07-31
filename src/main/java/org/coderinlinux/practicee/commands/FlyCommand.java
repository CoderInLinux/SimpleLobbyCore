package org.coderinlinux.practicee.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "Este comando solo es usado en jugadores");
            return true;
        }
        Player player = (Player) commandSender;
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatColor.GREEN + "Vuelo desactivado");
        } else {
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.RED + "Vuelo activado");
        }
        return true;
    }
}
