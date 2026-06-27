package com.moreores.world.biome;

/**
 * Data class representing a cave biome definition with all its properties.
 */
public class CaveBiomeDef {

    public final String name;
    public final CaveBiomeFamily family;
    public final int minY;
    public final int maxY;
    /** Rarity from 0.0 to 1.0 — lower values mean rarer. */
    public final float rarity;
    public final int fogColor;
    public final int waterColor;
    public final int waterFogColor;
    /** Names of ore blocks that spawn extra-densely in this biome. */
    public final String[] specialOres;
    public final boolean hasLava;
    public final boolean hasGlowstone;
    public final boolean hasMushrooms;
    public final boolean hasMoss;
    public final boolean hasCrystals;

    private CaveBiomeDef(Builder builder) {
        this.name = builder.name;
        this.family = builder.family;
        this.minY = builder.minY;
        this.maxY = builder.maxY;
        this.rarity = builder.rarity;
        this.fogColor = builder.fogColor;
        this.waterColor = builder.waterColor;
        this.waterFogColor = builder.waterFogColor;
        this.specialOres = builder.specialOres;
        this.hasLava = builder.hasLava;
        this.hasGlowstone = builder.hasGlowstone;
        this.hasMushrooms = builder.hasMushrooms;
        this.hasMoss = builder.hasMoss;
        this.hasCrystals = builder.hasCrystals;
    }

    public static Builder builder(String name, CaveBiomeFamily family) {
        return new Builder(name, family);
    }

    public static class Builder {
        private final String name;
        private final CaveBiomeFamily family;
        private int minY = -64;
        private int maxY = 0;
        private float rarity = 0.15f;
        private int fogColor = 0xC9D6FF;
        private int waterColor = 0x3F76E4;
        private int waterFogColor = 0x050533;
        private String[] specialOres = new String[0];
        private boolean hasLava = false;
        private boolean hasGlowstone = false;
        private boolean hasMushrooms = false;
        private boolean hasMoss = false;
        private boolean hasCrystals = false;

        private Builder(String name, CaveBiomeFamily family) {
            this.name = name;
            this.family = family;
        }

        public Builder depth(int minY, int maxY) {
            this.minY = minY;
            this.maxY = maxY;
            return this;
        }

        public Builder rarity(float rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder fogColor(int fogColor) {
            this.fogColor = fogColor;
            return this;
        }

        public Builder waterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public Builder waterFogColor(int waterFogColor) {
            this.waterFogColor = waterFogColor;
            return this;
        }

        public Builder specialOres(String... ores) {
            this.specialOres = ores;
            return this;
        }

        public Builder hasLava() {
            this.hasLava = true;
            return this;
        }

        public Builder hasGlowstone() {
            this.hasGlowstone = true;
            return this;
        }

        public Builder hasMushrooms() {
            this.hasMushrooms = true;
            return this;
        }

        public Builder hasMoss() {
            this.hasMoss = true;
            return this;
        }

        public Builder hasCrystals() {
            this.hasCrystals = true;
            return this;
        }

        public CaveBiomeDef build() {
            return new CaveBiomeDef(this);
        }
    }
}
