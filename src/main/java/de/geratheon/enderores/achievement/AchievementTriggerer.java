package de.geratheon.enderores.achievement;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import de.geratheon.enderores.init.ModItems;

public final class AchievementTriggerer {
    @SubscribeEvent
    public void onItemPickedUp(ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().getItem() == ModItems.enderPearlNugget) {
            event.player.addStat(ModAchievements.miniEnderPearls, 1);
        }
    }

    @SubscribeEvent
    public void onItemCrafted(ItemCraftedEvent event) {
        if (event.crafting.getItem() == ModItems.enderPearlCompressed) {
            event.player.addStat(ModAchievements.compressedEnderPearls, 1);
        }

        if (event.crafting.getItem() == ModItems.enderAthame) {
            event.player.addStat(ModAchievements.enderAthame, 1);
        }

        if (event.crafting.getItem() == ModItems.enderMirror) {
            event.player.addStat(ModAchievements.enderMirror, 1);
        }
    }
}