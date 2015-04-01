package de.geratheon.enderores.achievement;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.util.LogHelper;
import net.minecraft.item.ItemStack;

public final class AchievementTriggerer {
    @SubscribeEvent
    public void onItemPickedUp(ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().getItem() == ModItems.enderPearlNugget) {
            event.player.addStat(ModAchivements.miniEnderPearls, 1);
        }
        LogHelper.debug("Achievement should be triggered now");
    }
}