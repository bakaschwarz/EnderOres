package de.geratheon.enderores.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.geratheon.enderores.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean generateEnderOre = true;
    public static int maxVeinSizeEnderOre = 5;
    public static int chancesToSpawnEnderOre = 50;

    public static void init(File cfgFile) {
        if(config == null) {
            config = new Configuration(cfgFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        generateEnderOre = config.getBoolean("generateEnderOres", Configuration.CATEGORY_GENERAL, true, "Set whether the mod should generate ender ore or not. (Protip: It should.)");
        maxVeinSizeEnderOre = config.getInt("maxVeinSizeEnderOre", Configuration.CATEGORY_GENERAL, 5, 0, 100, "Sets the maximum vein size for the ender ore.");
        chancesToSpawnEnderOre = config.getInt("rarityEnderOre", Configuration.CATEGORY_GENERAL, 50, 1, 100, "Sets the rarity of the ender ore. (Note: 1 is absolutely rare and 100 is absolutely common.");

        if(config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}