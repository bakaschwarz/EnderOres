package de.geratheon.enderores.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.geratheon.enderores.item.*;
import de.geratheon.enderores.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemEnderOres enderPearlNugget = new ItemEnderPearlNugget();
    public static final ItemEnderOres enderPearlCompressed = new ItemEnderPearlCompressed();
    public static final ItemEnderOres enderAthame = new ItemEnderAthame();
    public static final ItemEnderOres enderMirror = new ItemEnderMirror();
    public static final ItemEnderOres enderMirrorComponent = new ItemEnderMirrorComponent();

    public static void init() {
        GameRegistry.registerItem(enderPearlNugget, "enderPearlMini");
        GameRegistry.registerItem(enderPearlCompressed, "enderPearlCompressed");
        GameRegistry.registerItem(enderAthame, "enderAthame");
        GameRegistry.registerItem(enderMirror, "enderMirror");
        GameRegistry.registerItem(enderMirrorComponent, "enderMirrorComponent");
    }
}
