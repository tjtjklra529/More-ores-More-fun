package com.moreores.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers extra ore-generation features for each cave biome that declares
 * {@link CaveBiomeDef#specialOres}. Uses the Fabric BiomeModifications API so
 * features are injected at world-gen time without replacing the full biome.
 *
 * <p>To add a new configured feature reference, add a {@code RegistryKey<PlacedFeature>}
 * constant following the pattern in {@link net.minecraft.world.gen.feature.OreConfiguredFeatures}
 * and include it in the {@link #ORE_FEATURE_KEYS} map below.</p>
 */
public class CaveBiomeFeatures {

    /**
     * Maps ore-block names (as declared in {@link CaveBiomeDef#specialOres}) to
     * the registry path of the corresponding placed-feature JSON under
     * {@code data/moreores/worldgen/placed_feature/}. Add entries here as new
     * placed features are created for cave-biome bonus ore spawning.
     */
    private static final Map<String, String> ORE_FEATURE_KEYS = new HashMap<>();

    static {
        ORE_FEATURE_KEYS.put("ruby_ore",                "cave_bonus_ruby_ore");
        ORE_FEATURE_KEYS.put("sapphire_ore",            "cave_bonus_sapphire_ore");
        ORE_FEATURE_KEYS.put("topaz_ore",               "cave_bonus_topaz_ore");
        ORE_FEATURE_KEYS.put("titanium_ore",            "cave_bonus_titanium_ore");
        ORE_FEATURE_KEYS.put("deepslate_titanium_ore",  "cave_bonus_deepslate_titanium_ore");
        ORE_FEATURE_KEYS.put("platinum_ore",            "cave_bonus_platinum_ore");
        ORE_FEATURE_KEYS.put("nickel_ore",              "cave_bonus_nickel_ore");
        ORE_FEATURE_KEYS.put("silver_ore",              "cave_bonus_silver_ore");
        ORE_FEATURE_KEYS.put("tin_ore",                 "cave_bonus_tin_ore");
    }

    /**
     * Called from {@code MoreOres.onInitialize()} after {@link CaveBiomeRegistry#register()}.
     *
     * <p>For each cave biome that has {@code specialOres}, we register
     * BiomeModification callbacks that inject the matching placed features into
     * that biome's underground-decoration generation step.
     *
     * <p>Note: the placed-feature JSON files referenced here must exist at
     * {@code data/moreores/worldgen/placed_feature/<feature_name>.json} for
     * feature injection to succeed during world-gen. This method is written to
     * gracefully skip entries whose feature key is not mapped yet.
     */
    public static void register() {
        for (CaveBiomeDef def : ModCaveBiomes.ALL) {
            if (def.specialOres == null || def.specialOres.length == 0) {
                continue;
            }

            RegistryKey<Biome> biomeKey = getBiomeKey(def.name);
            if (biomeKey == null) {
                continue;
            }

            for (String oreName : def.specialOres) {
                String featurePath = ORE_FEATURE_KEYS.get(oreName);
                if (featurePath == null) {
                    // Placed feature not yet defined; skip silently.
                    continue;
                }

                net.minecraft.registry.RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> featureKey =
                        net.minecraft.registry.RegistryKey.of(
                                net.minecraft.registry.RegistryKeys.PLACED_FEATURE,
                                new net.minecraft.util.Identifier("moreores", featurePath));

                final RegistryKey<Biome> finalBiomeKey = biomeKey;
                BiomeModifications.addFeature(
                        BiomeSelectors.includeByKey(finalBiomeKey),
                        GenerationStep.Feature.UNDERGROUND_ORES,
                        featureKey);
            }
        }
    }

    /** Resolves a biome name string to the corresponding {@link RegistryKey}. */
    private static RegistryKey<Biome> getBiomeKey(String name) {
        switch (name) {
            case "amethyst_cave":           return CaveBiomeRegistry.AMETHYST_CAVE;
            case "ruby_cavern":             return CaveBiomeRegistry.RUBY_CAVERN;
            case "sapphire_grotto":         return CaveBiomeRegistry.SAPPHIRE_GROTTO;
            case "topaz_hollow":            return CaveBiomeRegistry.TOPAZ_HOLLOW;
            case "crystal_palace":          return CaveBiomeRegistry.CRYSTAL_PALACE;
            case "titanium_vein":           return CaveBiomeRegistry.TITANIUM_VEIN;
            case "platinum_lode":           return CaveBiomeRegistry.PLATINUM_LODE;
            case "nickel_seam":             return CaveBiomeRegistry.NICKEL_SEAM;
            case "silver_mine":             return CaveBiomeRegistry.SILVER_MINE;
            case "tin_shaft":               return CaveBiomeRegistry.TIN_SHAFT;
            case "fungal_forest":           return CaveBiomeRegistry.FUNGAL_FOREST;
            case "moss_cavern":             return CaveBiomeRegistry.MOSS_CAVERN;
            case "root_cave":               return CaveBiomeRegistry.ROOT_CAVE;
            case "bioluminescent_grotto":   return CaveBiomeRegistry.BIOLUMINESCENT_GROTTO;
            case "limestone_cavern":        return CaveBiomeRegistry.LIMESTONE_CAVERN;
            case "marble_hall":             return CaveBiomeRegistry.MARBLE_HALL;
            case "slate_corridor":          return CaveBiomeRegistry.SLATE_CORRIDOR;
            case "granite_dome":            return CaveBiomeRegistry.GRANITE_DOME;
            case "magma_chamber":           return CaveBiomeRegistry.MAGMA_CHAMBER;
            case "sulfur_vents":            return CaveBiomeRegistry.SULFUR_VENTS;
            case "basalt_cavern":           return CaveBiomeRegistry.BASALT_CAVERN;
            case "obsidian_vault":          return CaveBiomeRegistry.OBSIDIAN_VAULT;
            case "oil_reservoir":           return CaveBiomeRegistry.OIL_RESERVOIR;
            case "brine_pool":              return CaveBiomeRegistry.BRINE_POOL;
            case "flooded_cavern":          return CaveBiomeRegistry.FLOODED_CAVERN;
            case "ancient_mine":            return CaveBiomeRegistry.ANCIENT_MINE;
            case "lost_vault":              return CaveBiomeRegistry.LOST_VAULT;
            case "collapsed_city":          return CaveBiomeRegistry.COLLAPSED_CITY;
            case "deep_dark_extension":     return CaveBiomeRegistry.DEEP_DARK_EXTENSION;
            case "void_edge":               return CaveBiomeRegistry.VOID_EDGE;
            default:                        return null;
        }
    }
}
