package org.coderinlinux.practicee.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PlayerInventory inventory = player.getInventory();
            ItemStack itemstack = new ItemStack(Material.WOOD_SWORD, 1);
            ItemStack itemstack1 = new ItemStack(Material.WOOD_AXE, 1);
            ItemStack itemstack2 = new ItemStack(Material.WOOD_HOE, 1);
            ItemStack itemstack3 = new ItemStack(Material.WOOD_PICKAXE, 1);
            ItemStack itemstack4 = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack itemstack5 = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack itemstack6 = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack itemstack7 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            inventory.addItem(itemstack, itemstack1, itemstack2, itemstack3, itemstack4, itemstack5, itemstack6, itemstack7);
            player.sendMessage(ChatColor.GREEN + "You have obtained a basic kit");
        } else {
            sender.sendMessage(ChatColor.RED + "There has been an error in the delivery of your items, or you are not human");
            return false;
        }
        return false;
    }
}
