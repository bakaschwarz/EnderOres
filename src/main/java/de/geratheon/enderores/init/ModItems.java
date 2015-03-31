package de.geratheon.enderores.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.geratheon.enderores.item.ItemEnderOres;
import de.geratheon.enderores.item.ItemEnderPearlNugget;
import de.geratheon.enderores.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemEnderOres enderPearlNugget = new ItemEnderPearlNugget();

    public static void init() {
        GameRegistry.registerItem(enderPearlNugget, "enderPearlNugget");
    }
}