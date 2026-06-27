package com.moreores.world.biome;

import java.util.List;

/**
 * Defines all 30 cave biomes for the More Ores, More Fun mod.
 * Each biome has a depth range, rarity, visual colours, and optional special properties.
 */
public class ModCaveBiomes {

    // -------------------------------------------------------------------------
    // CRYSTAL / GEM CAVES (5)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef AMETHYST_CAVE = CaveBiomeDef
            .builder("amethyst_cave", CaveBiomeFamily.CRYSTAL_GEM)
            .depth(-64, -20).rarity(0.15f).fogColor(0x9B59B6).hasCrystals()
            .build();

    public static final CaveBiomeDef RUBY_CAVERN = CaveBiomeDef
            .builder("ruby_cavern", CaveBiomeFamily.CRYSTAL_GEM)
            .depth(-48, -10).rarity(0.12f).fogColor(0xC0392B).hasCrystals()
            .specialOres("ruby_ore")
            .build();

    public static final CaveBiomeDef SAPPHIRE_GROTTO = CaveBiomeDef
            .builder("sapphire_grotto", CaveBiomeFamily.CRYSTAL_GEM)
            .depth(-56, -16).rarity(0.12f).fogColor(0x2980B9).hasCrystals()
            .specialOres("sapphire_ore")
            .build();

    public static final CaveBiomeDef TOPAZ_HOLLOW = CaveBiomeDef
            .builder("topaz_hollow", CaveBiomeFamily.CRYSTAL_GEM)
            .depth(-32, 16).rarity(0.15f).fogColor(0xF39C12).hasCrystals()
            .specialOres("topaz_ore")
            .build();

    public static final CaveBiomeDef CRYSTAL_PALACE = CaveBiomeDef
            .builder("crystal_palace", CaveBiomeFamily.CRYSTAL_GEM)
            .depth(-64, -32).rarity(0.05f).fogColor(0xE8DAEF).hasCrystals()
            .specialOres("ruby_ore", "sapphire_ore", "topaz_ore")
            .build();

    // -------------------------------------------------------------------------
    // ORE INDUSTRIAL CAVES (5)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef TITANIUM_VEIN = CaveBiomeDef
            .builder("titanium_vein", CaveBiomeFamily.ORE_INDUSTRIAL)
            .depth(-64, 0).rarity(0.10f).fogColor(0x7F8C8D)
            .specialOres("titanium_ore", "deepslate_titanium_ore")
            .build();

    public static final CaveBiomeDef PLATINUM_LODE = CaveBiomeDef
            .builder("platinum_lode", CaveBiomeFamily.ORE_INDUSTRIAL)
            .depth(-64, -16).rarity(0.08f).fogColor(0xBDC3C7)
            .specialOres("platinum_ore")
            .build();

    public static final CaveBiomeDef NICKEL_SEAM = CaveBiomeDef
            .builder("nickel_seam", CaveBiomeFamily.ORE_INDUSTRIAL)
            .depth(-32, 32).rarity(0.13f).fogColor(0x8E8E5C)
            .specialOres("nickel_ore")
            .build();

    public static final CaveBiomeDef SILVER_MINE = CaveBiomeDef
            .builder("silver_mine", CaveBiomeFamily.ORE_INDUSTRIAL)
            .depth(-48, 16).rarity(0.12f).fogColor(0xD5D8DC)
            .specialOres("silver_ore")
            .build();

    public static final CaveBiomeDef TIN_SHAFT = CaveBiomeDef
            .builder("tin_shaft", CaveBiomeFamily.ORE_INDUSTRIAL)
            .depth(0, 64).rarity(0.18f).fogColor(0xAAB7B8)
            .specialOres("tin_ore")
            .build();

    // -------------------------------------------------------------------------
    // ORGANIC CAVES (4)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef FUNGAL_FOREST = CaveBiomeDef
            .builder("fungal_forest", CaveBiomeFamily.ORGANIC)
            .depth(-32, 32).rarity(0.15f).fogColor(0x27AE60).hasMushrooms()
            .build();

    public static final CaveBiomeDef MOSS_CAVERN = CaveBiomeDef
            .builder("moss_cavern", CaveBiomeFamily.ORGANIC)
            .depth(-16, 48).rarity(0.20f).fogColor(0x2ECC71).hasMoss()
            .build();

    public static final CaveBiomeDef ROOT_CAVE = CaveBiomeDef
            .builder("root_cave", CaveBiomeFamily.ORGANIC)
            .depth(-32, 16).rarity(0.18f).fogColor(0x6D4C41).hasMoss()
            .build();

    public static final CaveBiomeDef BIOLUMINESCENT_GROTTO = CaveBiomeDef
            .builder("bioluminescent_grotto", CaveBiomeFamily.ORGANIC)
            .depth(-48, 0).rarity(0.08f).fogColor(0x1ABC9C).hasMushrooms().hasCrystals()
            .build();

    // -------------------------------------------------------------------------
    // GEOLOGICAL CAVES (4)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef LIMESTONE_CAVERN = CaveBiomeDef
            .builder("limestone_cavern", CaveBiomeFamily.GEOLOGICAL)
            .depth(-16, 64).rarity(0.22f).fogColor(0xF0F0E0)
            .build();

    public static final CaveBiomeDef MARBLE_HALL = CaveBiomeDef
            .builder("marble_hall", CaveBiomeFamily.GEOLOGICAL)
            .depth(-48, 16).rarity(0.15f).fogColor(0xF8F8F8)
            .build();

    public static final CaveBiomeDef SLATE_CORRIDOR = CaveBiomeDef
            .builder("slate_corridor", CaveBiomeFamily.GEOLOGICAL)
            .depth(-32, 32).rarity(0.18f).fogColor(0x708090)
            .build();

    public static final CaveBiomeDef GRANITE_DOME = CaveBiomeDef
            .builder("granite_dome", CaveBiomeFamily.GEOLOGICAL)
            .depth(-16, 48).rarity(0.20f).fogColor(0xC67E6E)
            .build();

    // -------------------------------------------------------------------------
    // VOLCANIC CAVES (4)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef MAGMA_CHAMBER = CaveBiomeDef
            .builder("magma_chamber", CaveBiomeFamily.VOLCANIC)
            .depth(-64, -32).rarity(0.10f).fogColor(0xE74C3C).hasLava().hasGlowstone()
            .build();

    public static final CaveBiomeDef SULFUR_VENTS = CaveBiomeDef
            .builder("sulfur_vents", CaveBiomeFamily.VOLCANIC)
            .depth(-64, -16).rarity(0.12f).fogColor(0xF1C40F).hasLava()
            .build();

    public static final CaveBiomeDef BASALT_CAVERN = CaveBiomeDef
            .builder("basalt_cavern", CaveBiomeFamily.VOLCANIC)
            .depth(-64, 0).rarity(0.15f).fogColor(0x2C3E50).hasLava()
            .build();

    public static final CaveBiomeDef OBSIDIAN_VAULT = CaveBiomeDef
            .builder("obsidian_vault", CaveBiomeFamily.VOLCANIC)
            .depth(-64, -48).rarity(0.06f).fogColor(0x1A1A1A).hasLava().hasGlowstone()
            .build();

    // -------------------------------------------------------------------------
    // FLUID CAVES (3)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef OIL_RESERVOIR = CaveBiomeDef
            .builder("oil_reservoir", CaveBiomeFamily.FLUID)
            .depth(-64, -16).rarity(0.08f).fogColor(0x1C2833)
            .waterColor(0x2C2C2C).waterFogColor(0x111111)
            .build();

    public static final CaveBiomeDef BRINE_POOL = CaveBiomeDef
            .builder("brine_pool", CaveBiomeFamily.FLUID)
            .depth(-32, 16).rarity(0.12f).fogColor(0x5D6D7E)
            .waterColor(0x8AABB4).waterFogColor(0x4A7A84)
            .build();

    public static final CaveBiomeDef FLOODED_CAVERN = CaveBiomeDef
            .builder("flooded_cavern", CaveBiomeFamily.FLUID)
            .depth(-16, 32).rarity(0.18f).fogColor(0x2E86C1)
            .waterColor(0x3498DB).waterFogColor(0x1A5276)
            .build();

    // -------------------------------------------------------------------------
    // RUIN / STRUCTURE CAVES (3)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef ANCIENT_MINE = CaveBiomeDef
            .builder("ancient_mine", CaveBiomeFamily.RUIN)
            .depth(-48, 16).rarity(0.10f).fogColor(0x7D6608)
            .build();

    public static final CaveBiomeDef LOST_VAULT = CaveBiomeDef
            .builder("lost_vault", CaveBiomeFamily.RUIN)
            .depth(-64, -16).rarity(0.07f).fogColor(0x6C3483)
            .build();

    public static final CaveBiomeDef COLLAPSED_CITY = CaveBiomeDef
            .builder("collapsed_city", CaveBiomeFamily.RUIN)
            .depth(-32, 0).rarity(0.06f).fogColor(0x4A235A)
            .build();

    // -------------------------------------------------------------------------
    // DEEP ZONE (2)
    // -------------------------------------------------------------------------

    public static final CaveBiomeDef DEEP_DARK_EXTENSION = CaveBiomeDef
            .builder("deep_dark_extension", CaveBiomeFamily.DEEP_ZONE)
            .depth(-64, -48).rarity(0.08f).fogColor(0x0D0D0D)
            .build();

    public static final CaveBiomeDef VOID_EDGE = CaveBiomeDef
            .builder("void_edge", CaveBiomeFamily.DEEP_ZONE)
            .depth(-64, -56).rarity(0.04f).fogColor(0x000020)
            .build();

    // -------------------------------------------------------------------------
    // Master list of all cave biomes
    // -------------------------------------------------------------------------

    public static final List<CaveBiomeDef> ALL = List.of(
            AMETHYST_CAVE,
            RUBY_CAVERN,
            SAPPHIRE_GROTTO,
            TOPAZ_HOLLOW,
            CRYSTAL_PALACE,
            TITANIUM_VEIN,
            PLATINUM_LODE,
            NICKEL_SEAM,
            SILVER_MINE,
            TIN_SHAFT,
            FUNGAL_FOREST,
            MOSS_CAVERN,
            ROOT_CAVE,
            BIOLUMINESCENT_GROTTO,
            LIMESTONE_CAVERN,
            MARBLE_HALL,
            SLATE_CORRIDOR,
            GRANITE_DOME,
            MAGMA_CHAMBER,
            SULFUR_VENTS,
            BASALT_CAVERN,
            OBSIDIAN_VAULT,
            OIL_RESERVOIR,
            BRINE_POOL,
            FLOODED_CAVERN,
            ANCIENT_MINE,
            LOST_VAULT,
            COLLAPSED_CITY,
            DEEP_DARK_EXTENSION,
            VOID_EDGE
    );
}
