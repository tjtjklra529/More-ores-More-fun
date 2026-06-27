package com.moreores.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModOreGeneration {

    // All ore names that have placed_feature JSON files
    private static final String[] STONE_ORES = {
        "titanium", "platinum", "nickel", "aluminum", "silver", "tin",
        "lead", "cobalt", "chromium", "tungsten", "zinc", "manganese",
        "vanadium", "uranium", "lithium", "palladium", "osmium", "iridium",
        "bismuth", "amber", "jade", "opal", "garnet", "peridot",
        "aquamarine", "tourmaline", "citrine",
        // gems from original materials
        "ruby", "sapphire", "topaz"
    };

    private static final String[] DEEPSLATE_ORES = {
        "titanium", "platinum", "nickel", "aluminum", "silver", "tin",
        "lead", "cobalt", "chromium", "tungsten", "zinc", "manganese",
        "vanadium", "uranium", "lithium", "palladium", "osmium", "iridium",
        "amber", "jade", "opal", "garnet", "peridot",
        "aquamarine", "tourmaline", "citrine",
        // gems from original materials
        "ruby", "sapphire", "topaz"
        // bismuth intentionally excluded (no deepslate variant)
    };

    public static void generateOres() {
        for (String name : STONE_ORES) {
            RegistryKey<PlacedFeature> key = RegistryKey.of(
                    RegistryKeys.PLACED_FEATURE,
                    new Identifier("moreores", name + "_ore")
            );
            BiomeModifications.addFeature(
                    BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    key
            );
        }
        for (String name : DEEPSLATE_ORES) {
            RegistryKey<PlacedFeature> key = RegistryKey.of(
                    RegistryKeys.PLACED_FEATURE,
                    new Identifier("moreores", "deepslate_" + name + "_ore")
            );
            BiomeModifications.addFeature(
                    BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    key
            );
        }
    }
}
