package com.moreores.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

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
        // Static field initialisation (triggered by class-load above) is
        // sufficient. Biome objects come from the worldgen data-pack JSONs.
    }
}
