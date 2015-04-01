package de.geratheon.enderores.achievement;

import cpw.mods.fml.common.FMLCommonHandler;
import de.geratheon.enderores.init.ModBlocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class ModAchievements {
    public static Achievement miniEnderPearls = new Achievement("achievement.enderores.miniEnderPearls", "enderores.miniEnderPearls", 0, 6, ModBlocks.enderOre, AchievementList.acquireIron);

    public static void init() {
        miniEnderPearls = miniEnderPearls.registerStat();

        FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
    }
}
