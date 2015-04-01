package de.geratheon.enderores.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import de.geratheon.enderores.handler.ConfigHandler;
import de.geratheon.enderores.init.ModBlocks;
import de.geratheon.enderores.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreGenEnderOres implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider iChunkProvider, IChunkProvider iChunkProvider1) {
        switch (world.provider.dimensionId) {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
            default:
                // Mystcraft and other dimensions from mods
                generateDefault(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random random, int x, int z) {
        if (ConfigHandler.generateEnderOre) {
            addOreVein(ModBlocks.enderOre, world, random, x, z, 16, 16, ConfigHandler.maxVeinSizeEnderOre, ConfigHandler.chancesToSpawnEnderOre * 3, 0, 256);
        }
    }

    private void generateSurface(World world, Random random, int x, int z) {
        if (ConfigHandler.generateEnderOre) {
            addOreVein(ModBlocks.enderOre, world, random, x, z, 16, 16, ConfigHandler.maxVeinSizeEnderOre, ConfigHandler.chancesToSpawnEnderOre, ConfigHandler.minYEnderOre, ConfigHandler.maxYEnderOre);
        }
    }

    private void generateNether(World world, Random random, int x, int z) {
        if (ConfigHandler.generateEnderOre) {
            addOreVein(ModBlocks.enderOre, world, random, x, z, 16, 16, ConfigHandler.maxVeinSizeEnderOre, ConfigHandler.chancesToSpawnEnderOre * 2, 0, 128);
        }
    }

    private void generateDefault(World world, Random random, int x, int z) {
        // same behaviour as overworld
        generateSurface(world, random, x, z);
    }

    private void addOreVein(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY ){
        for(int i = 0; i < chancesToSpawn; i++){
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY -minY);
            int posZ = blockZPos + random.nextInt(maxZ);
            new WorldGenMinable(block, maxVeinSize, Blocks.stone).generate(world, random, posX, posY, posZ);
            new WorldGenMinable(block, maxVeinSize, Blocks.netherrack).generate(world, random, posX, posY, posZ);
            new WorldGenMinable(block, maxVeinSize, Blocks.end_stone).generate(world, random, posX, posY, posZ);
        }
    }
}