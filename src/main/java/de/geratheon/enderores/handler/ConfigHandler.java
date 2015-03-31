package de.geratheon.enderores.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.geratheon.enderores.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean generateEnderOre;
    public static int maxVeinSizeEnderOre;
    public static int chancesToSpawnEnderOre;
    public static int minYEnderOre;
    public static int maxYEnderOre;

    public static void init(File cfgFile) {
        if(config == null) {
            config = new Configuration(cfgFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        generateEnderOre = config.getBoolean("generateEnderOres", Configuration.CATEGORY_GENERAL, true, "Set whether the mod should generate ender ore or not. (Protip: It should.)");
        maxVeinSizeEnderOre = config.getInt("maxVeinSizeEnderOre", Configuration.CATEGORY_GENERAL, 3, 0, 100, "Sets the maximum vein size for the ender ore.");
        chancesToSpawnEnderOre = config.getInt("rarityEnderOre", Configuration.CATEGORY_GENERAL, 50, 1, 100, "Sets the rarity of the ender ore. (Note: 1 is absolutely rare and 100 is absolutely common.");
        minYEnderOre = config.getInt("minYEnderOre", Configuration.CATEGORY_GENERAL, 0, 0, 256, "Sets the minimum y value where ender ore can occur.");
        maxYEnderOre = config.getInt("maxYEnderOre", Configuration.CATEGORY_GENERAL, 32, 0, 256, "Sets the maximum y value where ender ore can occur.");

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