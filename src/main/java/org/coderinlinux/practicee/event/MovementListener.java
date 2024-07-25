package org.coderinlinux.practicee.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.coderinlinux.practicee.Practicee;
import java.util.logging.Logger;

public class MovementListener implements Listener {
    public MovementListener(Practicee plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        Logger logger = plugin.getLogger();
    }

    @EventHandler
    public void movement(PlayerMoveEvent event) {
        Location loc = event.getPlayer().getLocation();
        loc.setY(loc.getY() - 1);
        Block block = loc.getBlock();
        Material material = block.getType();
        switch (material) {
            case WATER:
                block.setType(Material.ICE);
                break;
            case GRASS:
                block.setType(Material.ACACIA_DOOR);
                break;
            case SAND:
                block.setType(Material.BEDROCK);
                break;
        }
    }
}
