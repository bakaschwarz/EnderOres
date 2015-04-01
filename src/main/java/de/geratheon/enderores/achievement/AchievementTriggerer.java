package de.geratheon.enderores.achievement;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import de.geratheon.enderores.init.ModItems;

public final class AchievementTriggerer {
    @SubscribeEvent
    public void onItemPickedUp(ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().getItem() == ModItems.enderPearlNugget) {
            event.player.addStat(ModAchievements.miniEnderPearls, 1);
        }
    }
}