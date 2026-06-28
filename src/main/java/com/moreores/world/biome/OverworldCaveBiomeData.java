package com.moreores.world.biome;

import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Stores custom overworld cave biome entries to be injected into the overworld
 * MultiNoiseBiomeSource via {@link OverworldBiomesMixin}.
 */
public final class OverworldCaveBiomeData {

    private static final Map<RegistryKey<Biome>, MultiNoiseUtil.NoiseHypercube> OVERWORLD_CAVE_BIOMES = new HashMap<>();

    private OverworldCaveBiomeData() {}

    public static void addCaveBiome(RegistryKey<Biome> biome, MultiNoiseUtil.NoiseHypercube noisePoint) {
        Preconditions.checkArgument(biome != null, "Biome key must not be null");
        Preconditions.checkArgument(noisePoint != null, "NoiseHypercube must not be null");
        OVERWORLD_CAVE_BIOMES.put(biome, noisePoint);
    }

    /**
     * Returns the base entries with all registered cave biomes appended.
     * Called by {@link OverworldBiomesMixin}.
     */
    public static <T> MultiNoiseUtil.Entries<T> withModdedBiomeEntries(
            MultiNoiseUtil.Entries<T> entries,
            Function<RegistryKey<Biome>, T> mapper) {

        if (OVERWORLD_CAVE_BIOMES.isEmpty()) {
            return entries;
        }

        List<Pair<MultiNoiseUtil.NoiseHypercube, T>> list = new ArrayList<>(entries.getEntries());

        for (Map.Entry<RegistryKey<Biome>, MultiNoiseUtil.NoiseHypercube> entry : OVERWORLD_CAVE_BIOMES.entrySet()) {
            list.add(Pair.of(entry.getValue(), mapper.apply(entry.getKey())));
        }

        return new MultiNoiseUtil.Entries<>(Collections.unmodifiableList(list));
    }
}
