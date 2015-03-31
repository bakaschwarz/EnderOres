package de.geratheon.enderores.block;

import de.geratheon.enderores.init.ModItems;
import net.minecraft.block.material.Material;

public class BlockOreEnderOre extends BlockOreEnderOres {
    public BlockOreEnderOre() {
        super(Material.rock, ModItems.enderPearlNugget, 0, 4, 5);
        this.setBlockName("enderOre");
        this.setHardness(2.5F);
        this.setHarvestLevel("pickaxe", 2);
    }
}