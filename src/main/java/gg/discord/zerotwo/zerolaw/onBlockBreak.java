package gg.discord.zerotwo.zerolaw;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class onBlockBreak implements Listener {
    private final Main plugin;
    private Logger log;

    public onBlockBreak(Main plugin, Logger log){
        this.plugin = plugin;
        this.log = log;
    }

    @EventHandler
    public void  BlockBreak(BlockBreakEvent e) {
        ArrayList<Material> filter = new ArrayList<Material>();
        filter.add(Material.DIAMOND_ORE);
        filter.add(Material.DEEPSLATE_DIAMOND_ORE);

        Block target =  e.getBlock();
        Location loc = target.getLocation();

        if(filter.contains(e.getBlock().getType())){
            String BlockName = e.getBlock().getType().toString();
            log.info(""+e.getPlayer().getName()+" mined: "+BlockName+" at X: "+loc.getX()+" Y: "+loc.getY()+" Z: "+loc.getZ());
        }
    }
}
