package de.geratheon.enderores.achievement;

import cpw.mods.fml.common.FMLCommonHandler;
import de.geratheon.enderores.init.ModBlocks;
import de.geratheon.enderores.init.ModItems;
import de.geratheon.enderores.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

import java.util.ArrayList;

public class ModAchievements {
    public static AchievementPage enderOresPage;

    private static ArrayList<Achievement> achievements = new ArrayList<Achievement>();

    public static Achievement miniEnderPearls = createAchievement("miniEnderPearls", -1, 0, ModBlocks.enderOre, (Achievement) null);
    public static Achievement compressedEnderPearls = createAchievement("compressedEnderPearls", 1, 0, ModItems.enderPearlCompressed, (Achievement) null);
    public static Achievement enderAthame = createAchievement("enderAthame", 3, 0, ModItems.enderAthame, compressedEnderPearls);

    public static void init() {
        miniEnderPearls = miniEnderPearls.registerStat();
        achievements.add(miniEnderPearls);

        compressedEnderPearls = compressedEnderPearls.registerStat();
        achievements.add(compressedEnderPearls);

        enderAthame = enderAthame.registerStat();
        achievements.add(enderAthame);

        enderOresPage = new AchievementPage(Reference.MOD_NAME, achievements.toArray(new Achievement[achievements.size()]));
        AchievementPage.registerAchievementPage(enderOresPage);

        FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
    }

    public static Achievement createAchievement(String unlocalizedName, int x, int y, Block icon, Achievement parent) {
        return new Achievement("achievement." + Reference.ACHIEVEMENT_PREFIX + unlocalizedName, Reference.ACHIEVEMENT_PREFIX + unlocalizedName, x, y, icon, parent);
    }

    public static Achievement createAchievement(String unlocalizedName, int x, int y, Item icon, Achievement parent) {
        return new Achievement("achievement." + Reference.ACHIEVEMENT_PREFIX + unlocalizedName, Reference.ACHIEVEMENT_PREFIX + unlocalizedName, x, y, icon, parent);
    }
}