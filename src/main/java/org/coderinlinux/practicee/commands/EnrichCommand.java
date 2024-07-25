package org.coderinlinux.practicee.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EnrichCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PlayerInventory inventory = player.getInventory();
            ItemStack itemstack = new ItemStack(Material.DIAMOND, 64);
            inventory.addItem(itemstack);
            player.sendMessage("You got 64 diamonds");
        } else {
            sender.sendMessage("Only players can use this command");
            return false;
        }
        return false;
    }
}
