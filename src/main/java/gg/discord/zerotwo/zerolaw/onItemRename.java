package gg.discord.zerotwo.zerolaw;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;
import java.util.logging.Logger;

public final class onItemRename implements Listener {
    private final Main plugin;
    private Logger log;

    public onItemRename(Main plugin, Logger log){
        this.plugin = plugin;
        this.log = log;
    }

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked(); // can only be player!
        try {
            if (e.getInventory().getType() == InventoryType.ANVIL &&
                    e.getCurrentItem() != null &&
                    e.getCurrentItem().getType() != Material.AIR) {

                if(e.getSlotType() == InventoryType.SlotType.RESULT &&
                    e.getCurrentItem().getItemMeta().displayName() != null) {
                    this.log.info(player.getName()+" Renamed ["+e.getCurrentItem().getType().toString()+"] to "+e.getCurrentItem().getItemMeta().displayName());
                    // player.sendMessage(Objects.requireNonNull(e.getCurrentItem().getItemMeta().displayName()));
                }

            }
        }catch(Exception err){
            log.warning(err.getMessage());
        }

    }
}
