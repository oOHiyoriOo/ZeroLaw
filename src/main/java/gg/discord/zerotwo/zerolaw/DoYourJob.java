package gg.discord.zerotwo.zerolaw;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DoYourJob implements Listener {
    private final Main plugin;
    private Logger log;

    private FileConfiguration BlocksBlacklist;

    public DoYourJob(Main plugin, Logger log){
        this.plugin = plugin;
        this.log = log;

        BlocksBlacklist = new YamlConfiguration();
        try {
            BlocksBlacklist.load(new File(this.plugin.getDataFolder(), "blocks.yml"));
        } catch (IOException | InvalidConfigurationException e) {
            log.warning(""+e.getMessage());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        String perm;
        for (PermissionAttachmentInfo pio : player.getEffectivePermissions()) {
            perm = pio.getPermission();

            if(perm.startsWith("group.") &&
                    BlocksBlacklist.contains(perm) &&
                    BlocksBlacklist.getStringList(perm).contains(e.getBlock().getType().toString().toLowerCase()) ){ // 1. should be the highest.
                e.setDropItems(false);
                break;
            }
        }
    }
}
