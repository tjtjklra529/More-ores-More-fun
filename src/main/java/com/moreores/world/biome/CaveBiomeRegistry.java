package com.moreores.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

/**
 * Declares {@link RegistryKey} constants for all 30 custom cave biomes.
 * The actual biome data is provided by the worldgen JSON files under
 * data/moreores/worldgen/biome/. This class exists so that other systems
 * (feature placement, BiomeModifications, etc.) can reference biomes by key
 * without magic strings.
 */
public class CaveBiomeRegistry {

    // -------------------------------------------------------------------------
    // CRYSTAL / GEM CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> AMETHYST_CAVE =
            key("amethyst_cave");
    public static final RegistryKey<Biome> RUBY_CAVERN =
            key("ruby_cavern");
    public static final RegistryKey<Biome> SAPPHIRE_GROTTO =
            key("sapphire_grotto");
    public static final RegistryKey<Biome> TOPAZ_HOLLOW =
            key("topaz_hollow");
    public static final RegistryKey<Biome> CRYSTAL_PALACE =
            key("crystal_palace");

    // -------------------------------------------------------------------------
    // ORE INDUSTRIAL CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> TITANIUM_VEIN =
            key("titanium_vein");
    public static final RegistryKey<Biome> PLATINUM_LODE =
            key("platinum_lode");
    public static final RegistryKey<Biome> NICKEL_SEAM =
            key("nickel_seam");
    public static final RegistryKey<Biome> SILVER_MINE =
            key("silver_mine");
    public static final RegistryKey<Biome> TIN_SHAFT =
            key("tin_shaft");

    // -------------------------------------------------------------------------
    // ORGANIC CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> FUNGAL_FOREST =
            key("fungal_forest");
    public static final RegistryKey<Biome> MOSS_CAVERN =
            key("moss_cavern");
    public static final RegistryKey<Biome> ROOT_CAVE =
            key("root_cave");
    public static final RegistryKey<Biome> BIOLUMINESCENT_GROTTO =
            key("bioluminescent_grotto");

    // -------------------------------------------------------------------------
    // GEOLOGICAL CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> LIMESTONE_CAVERN =
            key("limestone_cavern");
    public static final RegistryKey<Biome> MARBLE_HALL =
            key("marble_hall");
    public static final RegistryKey<Biome> SLATE_CORRIDOR =
            key("slate_corridor");
    public static final RegistryKey<Biome> GRANITE_DOME =
            key("granite_dome");

    // -------------------------------------------------------------------------
    // VOLCANIC CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> MAGMA_CHAMBER =
            key("magma_chamber");
    public static final RegistryKey<Biome> SULFUR_VENTS =
            key("sulfur_vents");
    public static final RegistryKey<Biome> BASALT_CAVERN =
            key("basalt_cavern");
    public static final RegistryKey<Biome> OBSIDIAN_VAULT =
            key("obsidian_vault");

    // -------------------------------------------------------------------------
    // FLUID CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> OIL_RESERVOIR =
            key("oil_reservoir");
    public static final RegistryKey<Biome> BRINE_POOL =
            key("brine_pool");
    public static final RegistryKey<Biome> FLOODED_CAVERN =
            key("flooded_cavern");

    // -------------------------------------------------------------------------
    // RUIN / STRUCTURE CAVES
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> ANCIENT_MINE =
            key("ancient_mine");
    public static final RegistryKey<Biome> LOST_VAULT =
            key("lost_vault");
    public static final RegistryKey<Biome> COLLAPSED_CITY =
            key("collapsed_city");

    // -------------------------------------------------------------------------
    // DEEP ZONE
    // -------------------------------------------------------------------------
    public static final RegistryKey<Biome> DEEP_DARK_EXTENSION =
            key("deep_dark_extension");
    public static final RegistryKey<Biome> VOID_EDGE =
            key("void_edge");

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static RegistryKey<Biome> key(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier("moreores", name));
    }

    /**
     * Called from {@code MoreOres.onInitialize()}.
     * The biome data itself is loaded from JSON by the game's dynamic registry.
     * This method forces the static fields to be initialised so the keys are
     * available for feature-registration code that runs immediately after.
     */
    public static void register() {
        // Inject each cave biome into the overworld MultiNoiseBiomeSource.
        // Parameters: temperature, humidity, continentalness, erosion, depth, weirdness (each as [min,max] range)
        // All underground biomes use depth > 0.2; spread across unique temp/humidity zones.
        // Weirdness is left open (-1..1) so they appear regardless of terrain weirdness.

        // CRYSTAL / GEM CAVES — shallow underground, varied temperatures
        add(AMETHYST_CAVE,         -0.7f,-0.3f,  0.1f, 0.5f,  0.0f, 0.4f,  -0.1f, 0.5f,  0.2f, 0.5f);
        add(RUBY_CAVERN,            0.4f, 0.8f, -0.3f, 0.1f,  0.0f, 0.4f,  -0.1f, 0.5f, -0.4f, 0.0f);
        add(SAPPHIRE_GROTTO,       -0.9f,-0.5f, -0.4f, 0.0f,  0.0f, 0.4f,  -0.3f, 0.3f,  0.0f, 0.4f);
        add(TOPAZ_HOLLOW,           0.2f, 0.6f,  0.2f, 0.6f,  0.0f, 0.4f,   0.1f, 0.5f, -0.2f, 0.2f);
        add(CRYSTAL_PALACE,        -0.4f, 0.0f, -0.1f, 0.3f,  0.2f, 0.6f,  -0.4f, 0.0f,  0.2f, 0.6f);

        // ORE CAVES — mid-depth, moderate values
        add(TITANIUM_VEIN,         -0.2f, 0.2f, -0.2f, 0.2f,  0.2f, 0.6f,  -0.2f, 0.2f, -0.2f, 0.2f);
        add(PLATINUM_LODE,          0.1f, 0.5f, -0.1f, 0.3f,  0.2f, 0.6f,   0.1f, 0.5f,  0.0f, 0.4f);
        add(NICKEL_SEAM,            0.3f, 0.7f, -0.3f, 0.1f,  0.2f, 0.6f,   0.0f, 0.4f,  0.2f, 0.6f);
        add(SILVER_MINE,           -0.6f,-0.2f,  0.0f, 0.4f,  0.2f, 0.6f,  -0.3f, 0.1f, -0.5f,-0.1f);
        add(TIN_SHAFT,              0.5f, 0.9f,  0.1f, 0.5f,  0.2f, 0.6f,  -0.4f, 0.0f,  0.4f, 0.8f);

        // ORGANIC CAVES — warm and humid shallow areas
        add(FUNGAL_FOREST,          0.2f, 0.6f,  0.5f, 0.9f,  0.0f, 0.4f,  -0.2f, 0.2f, -0.6f,-0.2f);
        add(MOSS_CAVERN,            0.0f, 0.4f,  0.4f, 0.8f,  0.0f, 0.4f,  -0.4f, 0.0f, -0.4f, 0.0f);
        add(ROOT_CAVE,              0.1f, 0.5f,  0.6f, 1.0f,  0.0f, 0.4f,   0.0f, 0.4f, -0.8f,-0.4f);
        add(BIOLUMINESCENT_GROTTO, -0.3f, 0.1f,  0.3f, 0.7f,  0.2f, 0.6f,  -0.5f,-0.1f, -0.2f, 0.2f);

        // GEOLOGICAL CAVES — cool and dry
        add(LIMESTONE_CAVERN,      -0.4f, 0.0f, -0.9f,-0.5f,  0.2f, 0.6f,  -0.4f, 0.0f,  0.4f, 0.8f);
        add(MARBLE_HALL,           -0.6f,-0.2f, -0.8f,-0.4f,  0.2f, 0.6f,  -0.6f,-0.2f,  0.6f, 1.0f);
        add(SLATE_CORRIDOR,        -0.1f, 0.3f, -0.7f,-0.3f,  0.2f, 0.6f,  -0.2f, 0.2f,  0.2f, 0.6f);
        add(GRANITE_DOME,          -0.2f, 0.2f, -0.6f,-0.2f,  0.2f, 0.6f,   0.0f, 0.4f,  0.0f, 0.4f);

        // VOLCANIC CAVES — hot, deep
        add(MAGMA_CHAMBER,          0.6f, 1.0f, -0.4f, 0.0f,  0.5f, 0.9f,  -0.3f, 0.1f,  0.5f, 0.9f);
        add(SULFUR_VENTS,           0.5f, 0.9f,  0.2f, 0.6f,  0.5f, 0.9f,  -0.1f, 0.3f,  0.3f, 0.7f);
        add(BASALT_CAVERN,          0.4f, 0.8f, -0.2f, 0.2f,  0.5f, 0.9f,   0.2f, 0.6f,  0.6f, 1.0f);
        add(OBSIDIAN_VAULT,         0.7f, 1.0f,  0.4f, 0.8f,  0.7f, 1.0f,   0.4f, 0.8f,  0.6f, 1.0f);

        // FLUID CAVES — medium depth
        add(OIL_RESERVOIR,          0.0f, 0.4f, -0.3f, 0.1f,  0.2f, 0.6f,   0.3f, 0.7f, -0.7f,-0.3f);
        add(BRINE_POOL,            -0.5f,-0.1f, -0.5f,-0.1f,  0.2f, 0.6f,   0.4f, 0.8f, -0.9f,-0.5f);
        add(FLOODED_CAVERN,        -0.7f,-0.3f,  0.4f, 0.8f,  0.2f, 0.6f,   0.5f, 0.9f, -1.0f,-0.6f);

        // RUIN CAVES — mid-deep, inland
        add(ANCIENT_MINE,          -0.1f, 0.3f, -0.2f, 0.2f,  0.4f, 0.8f,  -0.8f,-0.4f,  0.0f, 0.4f);
        add(LOST_VAULT,            -0.3f, 0.1f, -0.1f, 0.3f,  0.4f, 0.8f,  -1.0f,-0.6f,  0.0f, 0.4f);
        add(COLLAPSED_CITY,         0.1f, 0.5f,  0.0f, 0.4f,  0.4f, 0.8f,  -0.9f,-0.5f,  0.2f, 0.6f);

        // DEEP ZONE — very deep
        add(DEEP_DARK_EXTENSION,   -0.6f, 0.6f, -0.6f, 0.6f,  0.7f, 1.0f,  -0.6f, 0.6f, -0.6f, 0.6f);
        add(VOID_EDGE,             -1.0f, 1.0f, -1.0f, 1.0f,  0.9f, 1.0f,  -1.0f, 1.0f, -1.0f, 1.0f);
    }

    // temp, humidity, continentalness, erosion, depth — each as [min, max]
    private static void add(RegistryKey<Biome> key,
                             float tMin, float tMax,
                             float hMin, float hMax,
                             float cMin, float cMax,
                             float eMin, float eMax,
                             float dMin, float dMax) {
        OverworldCaveBiomeData.addCaveBiome(key, MultiNoiseUtil.createNoiseHypercube(
                MultiNoiseUtil.ParameterRange.of(tMin, tMax),  // temperature
                MultiNoiseUtil.ParameterRange.of(hMin, hMax),  // humidity
                MultiNoiseUtil.ParameterRange.of(cMin, cMax),  // continentalness
                MultiNoiseUtil.ParameterRange.of(eMin, eMax),  // erosion
                MultiNoiseUtil.ParameterRange.of(dMin, dMax),  // depth
                MultiNoiseUtil.ParameterRange.of(-1.0f, 1.0f), // weirdness (any)
                0.0f                                            // offset
        ));
    }
}
