package de.geratheon.enderores.item;

import de.geratheon.enderores.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemEnderMirror extends ItemEnderOres {
    private static final int MAX_PEARLS = 100;

    public ItemEnderMirror() {
        super();
        this.setUnlocalizedName("enderMirror");
        this.setMaxStackSize(1);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        initNBT(itemStack);
    }

    private void initNBT(ItemStack itemStack) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setInteger("pearls", 0);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4) {
        list.add(EnumChatFormatting.GRAY + "Uses mini ender pearls to access");
        list.add(EnumChatFormatting.GRAY + "your personal ender chest!");
        list.add(EnumChatFormatting.GRAY + "Sneak + Use to refill from inventory");

        if (itemStack.stackTagCompound != null) {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");

            if (pearls == 0) {
                list.add(EnumChatFormatting.RED + "Pearls: " + pearls);
            } else if (pearls > 0 && pearls < 5) {
                list.add(EnumChatFormatting.YELLOW + "Pearls: " + pearls);
            } else {
                list.add(EnumChatFormatting.GREEN + "Pearls: " + pearls);
            }

        } else {
            initNBT(itemStack);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player.isSneaking()) {
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
        } else {
            int pearls = itemStack.stackTagCompound.getInteger("pearls");

            if (pearls > 0) {
                Random random = new Random();
                float chanceToUsePearl = random.nextFloat();

                if (chanceToUsePearl <= 0.50f) {
                    itemStack.stackTagCompound.setInteger("pearls", pearls - 1);
                }

                player.displayGUIChest(player.getInventoryEnderChest());
            }
        }

        return itemStack;
    }

}