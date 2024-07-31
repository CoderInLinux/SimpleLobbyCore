package org.coderinlinux.practicee.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class JoinEvent implements Listener {
    public Location lobby = new Location(Bukkit.getWorld("world"), 0, 1, 0);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("lobby.join")) {
            event.setJoinMessage("§c" + player.getName() + " §cSe ha unido");
        } else {
            event.setJoinMessage("§b" + player.getName() + " §eSe ha unido al Lobby");
        }

        setPlayer(player);
        setScoreboard(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("lobby.quit")) {
            event.setQuitMessage("§c" + player.getName() + " §cHa abandonado el servidor");
        } else {
            event.setQuitMessage("§b" + player.getName() + " §eHa abandonado el Lobby");
        }
    }

    public void setPlayer(Player player){
        player.teleport(lobby);

        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0);
        player.setLevel(0);
    }

    public void setScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("general", "dummy");

        objective.setDisplayName("§3Sever Name");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("          ").setScore(12);
        objective.getScore("§6Usuario §7: §3" + player.getName()).setScore(11);
        objective.getScore("  ").setScore(10);
        objective.getScore(" ").setScore(9);
        if (player.hasPermission("admin.use")){
            objective.getScore("§6Range §7:§c Administrator").setScore(9);
        } else {
            objective.getScore("§6Range §7:§7 User").setScore(9);
        }
        objective.getScore("      ").setScore(8);
        objective.getScore("§bMyServer.com").setScore(7);

        player.setScoreboard(scoreboard);
    }
}
