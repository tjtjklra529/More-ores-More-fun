package com.moreores.world.gen;

import com.moreores.registry.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

/**
 * Registers surface crater generation in overworld biomes.
 *
 * Craters appear at y=60-80, ~1 per 2500 chunks.
 * Bowl depression replaces surface blocks with moon_rock.
 * Center contains small deposit of moon_rock and rare titanium/platinum ores.
 *
 * This uses a data-driven placed feature referenced by key.
 * The actual feature JSON lives in data/moreores/worldgen/
 */
public class CraterGeneration {

    private static final RegistryKey<PlacedFeature> MOON_CRATER =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "moon_crater"));

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.SURFACE_STRUCTURES,
                MOON_CRATER
        );
    }
}
