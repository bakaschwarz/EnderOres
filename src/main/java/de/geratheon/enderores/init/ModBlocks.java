package de.geratheon.enderores.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.geratheon.enderores.block.BlockOreEnderOre;
import de.geratheon.enderores.block.BlockOreEnderOres;
import de.geratheon.enderores.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockOreEnderOres enderOre = new BlockOreEnderOre();

    public static void init() {
        GameRegistry.registerBlock(enderOre, "enderOre");
    }
}