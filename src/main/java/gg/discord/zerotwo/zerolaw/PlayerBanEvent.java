package gg.discord.zerotwo.zerolaw;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class PlayerBanEvent implements Listener {
    private final Main plugin;
    private Logger log;

    public PlayerBanEvent(Main plugin, Logger log){
        this.plugin = plugin;
        this.log = log;
    }

    @EventHandler
    public void onPlayerBan(PlayerCommandPreprocessEvent e){
        String command = e.getMessage();
        if(command.startsWith("/ban ") || command.startsWith("/tempban")){
            ArrayList<String> args = new ArrayList<>(List.of(command.split(" ")));
            args.remove(0); // removing the /ban
            String name = args.get(0);
            args.remove(0); // removing the name

            String time = null;
            if(command.startsWith("/tempban")){
                time = args.get(0);
                args.remove(0);
            }

            String Reason = String.join(" ",args);
            String source = e.getPlayer().getName();

            String message = time != null ? ( name+" was banned by "+source+" for: "+time+" with reason: "+Reason): ( name+" was banned by "+source+" for: "+Reason);

            Bukkit.getScheduler().runTaskAsynchronously(this.plugin, new DRunnable(message){
                @Override
                public void run() {
                    DHook webhook = new DHook("<webhook>");
                    webhook.setContent(this.msg);
                    webhook.setTts(false);
                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        log.warning("Failed to Post to Discord!");
                    }
                }
            });
        }else if(command.startsWith("/nick")){
            String source = e.getPlayer().getName();

            ArrayList<String> args = new ArrayList<>(List.of(command.split(" ")));
            args.remove(0); // removing the command
            String name = null;
            String target = null;


            if(args.size() == 1){ // own name
                name = args.get(0);
            }else{ // other name
                target = args.get(0);
                name = args.get(1);
            }

            String message = target == null ? (source+" new name is: "+name) : (source+" changed the name of: "+target+" to: "+name);

            Bukkit.getScheduler().runTaskAsynchronously(this.plugin, new DRunnable(message){
                @Override
                public void run() {
                    DHook webhook = new DHook("https://discord.com/api/webhooks/949630924063113216/SWKiOzODY-SoRuajARSEfaubiLCbQPf77GOE7sa-Zd9dAe3PVhi6gtZbdMX9EGjXd-Y4");
                    webhook.setContent(this.msg);
                    webhook.setTts(false);
                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        log.warning("Failed to Post to Discord!");
                    }
                }
            });
        }
    }
}
