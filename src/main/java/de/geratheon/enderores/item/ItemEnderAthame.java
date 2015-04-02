package de.geratheon.enderores.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.geratheon.enderores.achievement.ModAchievements;
import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemEnderAthame extends ItemEnderOres {
    private static final int MAX_PEARLS = 100;

    private static IIcon iconEmpty;
    private static IIcon iconPartiallyFilled;
    private static IIcon iconFull;

    public ItemEnderAthame() {
        super();
        this.setUnlocalizedName("enderAthame");
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        iconEmpty = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "enderAthameEmpty");
        iconPartiallyFilled = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "enderAthamePartially");
        iconFull = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "enderAthameFull");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack itemStack, int pass)
    {
        if (itemStack.stackTagCompound != null) {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");

            if (pearls == 0) {
                return iconEmpty;
            } else if (pearls > 0 && pearls < 5) {
                return iconPartiallyFilled;
            } else {
                return iconFull;
            }
        } else {
            return iconEmpty;
        }
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int meta) {
        return 1;
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        initNBT(itemStack);
    }

    private void initNBT(ItemStack itemStack) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setInteger("pearls", 0);
        itemStack.stackTagCompound.setInteger("kills", 0);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4) {
        list.add(EnumChatFormatting.GRAY + "Onehits Endermen for each mini pearl");
        list.add(EnumChatFormatting.GRAY + "Sneak + Use to refill from inventory");

        if (itemStack.stackTagCompound != null) {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");
            int kills = itemStack.stackTagCompound.getInteger("kills");

            if (pearls == 0) {
                list.add(EnumChatFormatting.RED + "Pearls: " + pearls);
            } else if (pearls > 0 && pearls < 5) {
                list.add(EnumChatFormatting.YELLOW + "Pearls: " + pearls);
            } else {
                list.add(EnumChatFormatting.GREEN + "Pearls: " + pearls);
            }

            list.add(EnumChatFormatting.BLUE + "Endermen killed: " + kills);
        } else {
            list.add(EnumChatFormatting.RED + "Pearls: 0");
        }
    }

    // consumes mini ender pearl in inventory
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (itemStack.stackTagCompound != null) {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");

            if (pearls < MAX_PEARLS) {
                if (player.inventory.hasItem(ModItems.enderPearlNugget)) {
                    player.inventory.consumeInventoryItem(ModItems.enderPearlNugget);
                    itemStack.stackTagCompound.setInteger("pearls", pearls + 1);
                }
            }
        } else {
            initNBT(itemStack);
            onItemRightClick(itemStack, world, player);
        }

        return itemStack;
    }

    // consumes mini ender pearl in athame, kills an ender man, guarantees ender pearl
    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1);

        if (itemStack.stackTagCompound != null) {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");
            int kills = itemStack.stackTagCompound.getInteger("kills");

            if (pearls > 0) {
                if (entity instanceof EntityEnderman) {
                    entity.playSound("mob.endermen.death", 1F, 1F);
                    if (!entity.worldObj.isRemote) {
                        entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.ender_pearl)));
                    }
                    entity.setDead();

                    // todo: find a better way to guarantee an ender pearl drop
                    //entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 9001);

                    itemStack.stackTagCompound.setInteger("pearls", pearls - 1);
                    itemStack.stackTagCompound.setInteger("kills", kills + 1);
                    if (kills + 1 >= 1000) {
                        player.addStat(ModAchievements.enderAthameKills, 1);
                    }
                }
            }
        } else {
            initNBT(itemStack);
            onLeftClickEntity(itemStack, player, entity);
        }

        return true;
    }
}