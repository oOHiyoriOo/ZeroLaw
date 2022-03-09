package gg.discord.zerotwo.zerolaw;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private Logger log;
    private File customConfigFile;


    @Override
    public void onEnable() {
        // Plugin startup logic
        log = getLogger();

        // create needed configs.
        ArrayList<String> configs = new ArrayList<>();
        //configs.add("interaction.yml");
        //configs.add("entity.yml");
        configs.add("blocks.yml");

        for(String config : configs){
            customConfigFile = new File(getDataFolder(), config);
            if (!customConfigFile.exists()) {
                customConfigFile.getParentFile().mkdirs();
                saveResource(config, false);
            }
        }

        getServer().getPluginManager().registerEvents(new onItemRename(this,log), this);
        getServer().getPluginManager().registerEvents(new onBlockBreak(this,log), this);
        getServer().getPluginManager().registerEvents(new PlayerBanEvent(this,log), this);
        // getServer().getPluginManager().registerEvents(new DoYourJob(this,log), this);

        log.info("ZeroLaw is bready to die!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("ZeroLaw dropped bread D:");
    }
}
