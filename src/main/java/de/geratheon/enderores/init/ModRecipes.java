package de.geratheon.enderores.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {
    public static void init() {
        // shaped
        GameRegistry.addRecipe(new ItemStack(Items.ender_pearl),
                "ss",
                "ss",
                's', new ItemStack(ModItems.enderPearlNugget));

        GameRegistry.addRecipe(new ItemStack(ModItems.enderPearlCompressed),
                "ss",
                "ss",
                's', new ItemStack(Items.ender_pearl));

        GameRegistry.addRecipe(new ItemStack(ModItems.enderAthame),
                "s",
                "s",
                "a",
                's', new ItemStack(ModItems.enderPearlCompressed),
                'a', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(ModItems.enderMirrorComponent),
                "sss",
                "aaa",
                "ddd",
                's', new ItemStack(Blocks.glass),
                'a', new ItemStack(Items.iron_ingot),
                'd', new ItemStack(ModItems.enderPearlCompressed));

        GameRegistry.addRecipe(new ItemStack(ModItems.enderMirror),
                "s",
                "a",
                's', new ItemStack(ModItems.enderMirrorComponent),
                'a', new ItemStack(ModItems.enderPearlNugget));

        // shapeless
        // GameRegistry.addShapelessRecipe(new ItemStack(ModItems.enderPearlNugget, 4), new ItemStack(Items.ender_pearl));
    }
}