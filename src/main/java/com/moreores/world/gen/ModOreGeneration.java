package com.moreores.world.gen;

import com.moreores.registry.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

/**
 * Registers ore vein generation in the world using Fabric's BiomeModifications API.
 * Features are registered as data-driven placed features referenced by key.
 *
 * For full data-driven generation, JSON files would live in:
 *   data/moreores/worldgen/configured_feature/
 *   data/moreores/worldgen/placed_feature/
 *
 * Here we register the placements programmatically via BiomeModifications.
 */
public class ModOreGeneration {

    // Placed feature registry keys — these correspond to JSON files that would be
    // bundled with the mod for data-driven generation.
    private static final RegistryKey<PlacedFeature> TITANIUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "titanium_ore"));
    private static final RegistryKey<PlacedFeature> PLATINUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "platinum_ore"));
    private static final RegistryKey<PlacedFeature> NICKEL_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "nickel_ore"));
    private static final RegistryKey<PlacedFeature> ALUMINUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "aluminum_ore"));
    private static final RegistryKey<PlacedFeature> SILVER_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "silver_ore"));
    private static final RegistryKey<PlacedFeature> TIN_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "tin_ore"));

    // Deepslate variants
    private static final RegistryKey<PlacedFeature> DEEPSLATE_TITANIUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_titanium_ore"));
    private static final RegistryKey<PlacedFeature> DEEPSLATE_PLATINUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_platinum_ore"));
    private static final RegistryKey<PlacedFeature> DEEPSLATE_NICKEL_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_nickel_ore"));
    private static final RegistryKey<PlacedFeature> DEEPSLATE_ALUMINUM_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_aluminum_ore"));
    private static final RegistryKey<PlacedFeature> DEEPSLATE_SILVER_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_silver_ore"));
    private static final RegistryKey<PlacedFeature> DEEPSLATE_TIN_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("moreores", "deepslate_tin_ore"));

    /**
     * Registers all ore generation modifications into overworld biomes.
     *
     * Generation specs:
     * - TITANIUM:  y=-64 to y=16,  vein size 4-8,  rare      (1-2 veins per chunk)
     * - PLATINUM:  y=-64 to y=0,   vein size 3-6,  very rare (1 vein per chunk)
     * - NICKEL:    y=-32 to y=48,  vein size 5-10, uncommon  (3-4 veins per chunk)
     * - ALUMINUM:  y=0   to y=64,  vein size 6-12, common    (6-8 veins per chunk)
     * - SILVER:    y=-48 to y=32,  vein size 4-8,  uncommon  (2-3 veins per chunk)
     * - TIN:       y=0   to y=80,  vein size 8-16, common    (8-10 veins per chunk)
     *
     * Moon Rock crater generation: planned for future implementation.
     */
    public static void generateOres() {
        // Titanium — rare, deep
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                TITANIUM_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_TITANIUM_ORE_PLACED
        );

        // Platinum — very rare, very deep
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                PLATINUM_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_PLATINUM_ORE_PLACED
        );

        // Nickel — uncommon, mid-depth
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                NICKEL_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_NICKEL_ORE_PLACED
        );

        // Aluminum — common, near surface
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ALUMINUM_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_ALUMINUM_ORE_PLACED
        );

        // Silver — uncommon, mid-depth
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                SILVER_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_SILVER_ORE_PLACED
        );

        // Tin — common, near surface
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                TIN_ORE_PLACED
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                DEEPSLATE_TIN_ORE_PLACED
        );

        // TODO: Moon Rock crater generation — surface feature, future implementation
    }
}
