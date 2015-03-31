package de.geratheon.enderores.creativetab;

import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEnderOres {
    public static final CreativeTabs ENDER_ORES_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return ModItems.enderPearlNugget;
        }
    };
}