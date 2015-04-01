package de.geratheon.enderores;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.geratheon.enderores.handler.ConfigHandler;
import de.geratheon.enderores.achievement.ModAchivements;
import de.geratheon.enderores.init.ModBlocks;
import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.init.ModRecipes;
import de.geratheon.enderores.reference.Reference;
import de.geratheon.enderores.util.LogHelper;
import de.geratheon.enderores.worldgen.OreGenEnderOres;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
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

        // ore gen
        OreGenEnderOres oregen = new OreGenEnderOres();
        GameRegistry.registerWorldGenerator(oregen, 0);

        // register achievements
        ModAchivements.init();

        LogHelper.info("Pre Initialization complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register recipes
        ModRecipes.init();

        LogHelper.info("Initialization complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post Initialization complete!");
    }
}