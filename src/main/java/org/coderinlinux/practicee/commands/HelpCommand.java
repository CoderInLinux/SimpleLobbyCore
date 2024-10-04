package org.coderinlinux.practicee.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
            sender.sendMessage(ChatColor.DARK_GREEN + "------------------------------");
            sender.sendMessage(ChatColor.GREEN + "/enrich: Comando que te da 64 diamantes");
            sender.sendMessage(ChatColor.GREEN + "/fly: Comando el cual se activa y desactiva con el mismo comando");
            sender.sendMessage(ChatColor.GREEN + "/kit: Comando el cual te da un kit basico de armadura y herramientas de madera y cuero");
            sender.sendMessage(ChatColor.GREEN + "/report <jugador> <razón>: Comando el cual sirve para reportar jugadores y este es enviado mendiante un webhook a un canal de discord");
            sender.sendMessage(ChatColor.GREEN + "/Spawn <set> <delete> <manager>: Comando el cual te permite administrar tus spawns");
            sender.sendMessage(ChatColor.DARK_GREEN + "------------------------------");
        return true;
    }
}
