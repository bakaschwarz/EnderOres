package de.geratheon.enderores.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreEnderOres extends BlockEnderOres {
    private Item drop;
    private int meta;
    private int least_quantity;
    private int most_quantity;

    protected BlockOreEnderOres(Material material, Item drop, int meta, int least_quantity, int most_quantity) {
        super(material);
        this.drop = drop;
        this.meta = meta;
        this.least_quantity = least_quantity;
        this.most_quantity = most_quantity;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return this.drop;
    }

    @Override
    public int damageDropped(int metadata) {
        return this.meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if (this.least_quantity >= this.most_quantity)
            return this.least_quantity;
        return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
    }
}
