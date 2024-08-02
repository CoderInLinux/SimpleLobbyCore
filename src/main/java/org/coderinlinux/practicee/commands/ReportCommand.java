package org.coderinlinux.practicee.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportCommand implements CommandExecutor {

    private static final String DISCORD_WEBHOOK = "https://discordapp.com/api/webhooks/1268651073367838780/UdeM26XP5iboQ1SwiRA6qZBQMcrPii4c72dER2Es_TAzBWglKKzx4jSdmBsoRuvrYJs9";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser usado por jugadores");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Uso correcto: /report <jugador> <razón>");
            return true;
        }
        Player player = (Player) sender;
        OfflinePlayer reportedPlayer = Bukkit.getOfflinePlayer(args[0]);
        if (player.getUniqueId().equals(reportedPlayer.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "No puedes reportarte a ti mismo");
            return true;
        }
        String reason = String.join(" ", args);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String message = "**Nuevo reporte:**\n" +
                "* **Reportado por:** " + player.getName() + "\n" +
                "* **Jugador reportado:** " + reportedPlayer.getName() + "\n" +
                "* **Razón:** " + reason + "\n" +
                "* **Hora:** " + time;
        String encodeMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        try {
            new URL(DISCORD_WEBHOOK + "?content=" + encodeMessage);
            sender.sendMessage(ChatColor.GREEN + "Reporte enviado correctamente");
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "Error al enviar el reporte " + e.getMessage());
        }
        return true;
    }
}