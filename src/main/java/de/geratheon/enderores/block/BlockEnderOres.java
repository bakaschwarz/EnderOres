package de.geratheon.enderores.block;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.geratheon.enderores.creativetab.CreativeTabEnderOres;
import de.geratheon.enderores.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockEnderOres extends Block {
    public BlockEnderOres(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(CreativeTabEnderOres.ENDER_ORES_TAB);
    }

    public BlockEnderOres() {
        this(Material.rock);
        this.setCreativeTab(CreativeTabEnderOres.ENDER_ORES_TAB);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedname) {
        return unlocalizedname.substring(unlocalizedname.indexOf(".") + 1);
    }
}