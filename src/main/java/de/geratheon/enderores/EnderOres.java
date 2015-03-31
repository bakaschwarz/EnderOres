package de.geratheon.enderores;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.geratheon.enderores.handler.ConfigHandler;
import de.geratheon.enderores.init.ModBlocks;
import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.reference.Reference;
import de.geratheon.enderores.util.LogHelper;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
public class EnderOres {

    @Mod.Instance(Reference.MOD_ID)
    public static EnderOres instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // config stuff
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        // register items, blocks and fluids
        ModItems.init();
        ModBlocks.init();

        LogHelper.info("Pre Initialization complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogHelper.info("Initialization complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post Initialization complete!");
    }
}