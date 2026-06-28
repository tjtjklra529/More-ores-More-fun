package com.moreores.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

/**
 * Registry keys and noise parameters for the mod's custom overworld surface biomes.
 * Biomes are injected into the overworld MultiNoiseBiomeSource via OverworldBiomesMixin.
 */
public final class OverworldBiomeRegistry {

    public static final RegistryKey<Biome> TITANIUM_HIGHLANDS = key("titanium_highlands");
    public static final RegistryKey<Biome> CRYSTAL_PLAINS     = key("crystal_plains");
    public static final RegistryKey<Biome> VOLCANIC_FIELDS    = key("volcanic_fields");
    public static final RegistryKey<Biome> PLATINUM_TUNDRA    = key("platinum_tundra");
    public static final RegistryKey<Biome> AMBER_WETLANDS     = key("amber_wetlands");
    public static final RegistryKey<Biome> SILVER_TAIGA       = key("silver_taiga");
    public static final RegistryKey<Biome> COPPER_BADLANDS    = key("copper_badlands");
    public static final RegistryKey<Biome> NICKEL_STEPPE      = key("nickel_steppe");
    public static final RegistryKey<Biome> BRONZE_SAVANNA     = key("bronze_savanna");
    public static final RegistryKey<Biome> MOON_CRATER_FIELDS = key("moon_crater_fields");

    private OverworldBiomeRegistry() {}

    public static void register() {
        // Surface biomes use depth -0.1 to 0.1 (terrain surface layer).
        // Each biome occupies a distinct zone in temperature x humidity x continentalness space
        // to minimise overlap with vanilla biomes and each other.

        // Cold, dry, inland rocky plateau — titanium-grey
        add(TITANIUM_HIGHLANDS, -0.45f,-0.15f, -0.5f,-0.2f,  0.3f, 0.7f, -0.6f,-0.3f);
        // Temperate, moderate, inland plains — gem crystals
        add(CRYSTAL_PLAINS,     -0.1f,  0.2f,  -0.1f, 0.2f,  0.2f, 0.6f, -0.2f, 0.2f);
        // Hot, very dry, inland volcanic terrain
        add(VOLCANIC_FIELDS,    0.7f,  1.0f,  -0.8f,-0.5f,  0.1f, 0.5f, -0.4f, 0.0f);
        // Very cold, dry, inland platinum tundra
        add(PLATINUM_TUNDRA,   -1.0f, -0.6f,  -0.6f,-0.3f,  0.2f, 0.6f, -0.1f, 0.3f);
        // Warm, humid, low-lying amber wetlands
        add(AMBER_WETLANDS,     0.5f,  0.8f,   0.5f, 0.8f, -0.1f, 0.3f,  0.4f, 0.7f);
        // Cold, humid, inland silver taiga
        add(SILVER_TAIGA,      -0.5f, -0.2f,   0.2f, 0.5f,  0.2f, 0.6f,  0.0f, 0.4f);
        // Hot, very dry, coastal copper badlands
        add(COPPER_BADLANDS,   0.7f,  1.0f,  -0.7f,-0.4f,  0.0f, 0.4f,  0.3f, 0.7f);
        // Warm, dry, inland nickel steppe
        add(NICKEL_STEPPE,     0.2f,  0.5f,  -0.4f,-0.1f,  0.3f, 0.7f,  0.1f, 0.5f);
        // Hot, slightly dry, inland bronze savanna
        add(BRONZE_SAVANNA,    0.5f,  0.8f,  -0.2f, 0.1f,  0.1f, 0.5f,  0.2f, 0.6f);
        // Neutral, very dry, exposed highland — lunar craters
        add(MOON_CRATER_FIELDS,-0.2f,  0.2f,  -1.0f,-0.7f,  0.5f, 0.9f, -0.8f,-0.4f);
    }

    private static void add(RegistryKey<Biome> key,
                             float tMin, float tMax,
                             float hMin, float hMax,
                             float cMin, float cMax,
                             float eMin, float eMax) {
        OverworldCaveBiomeData.addCaveBiome(key, MultiNoiseUtil.createNoiseHypercube(
                MultiNoiseUtil.ParameterRange.of(tMin, tMax),
                MultiNoiseUtil.ParameterRange.of(hMin, hMax),
                MultiNoiseUtil.ParameterRange.of(cMin, cMax),
                MultiNoiseUtil.ParameterRange.of(eMin, eMax),
                MultiNoiseUtil.ParameterRange.of(-0.1f, 0.1f),  // surface depth
                MultiNoiseUtil.ParameterRange.of(-1.0f, 1.0f),  // any weirdness
                0.0f
        ));
    }

    private static RegistryKey<Biome> key(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier("moreores", name));
    }
}
