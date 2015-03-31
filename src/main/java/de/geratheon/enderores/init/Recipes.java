package de.geratheon.enderores.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
    public static void init() {
        // shaped
        GameRegistry.addRecipe(new ItemStack(Items.ender_pearl),
                "ss",
                "ss",
                's', new ItemStack(ModItems.enderPearlNugget));

        // shapeless
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.enderPearlNugget, 9), new ItemStack(Items.ender_pearl));
    }
}