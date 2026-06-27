package com.moreores.material;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * Defines a single ore/material with all its properties.
 * Uses the Builder pattern for construction.
 */
public class OreMaterial {

    private final String name;
    private final int miningLevel;
    private final float hardness;
    private final float resistance;
    private final Set<OreBundle> bundles;

    // Armor stats
    private final int armorDurabilityMultiplier;
    private final int[] armorProtection; // [head, chest, legs, feet]
    private final float armorToughness;
    private final float armorKnockbackRes;

    // Tool stats
    private final int toolDurability;
    private final float toolMiningSpeed;
    private final float toolAttackDamage;
    private final int toolEnchantability;

    // Color hint (for future texture generation)
    private final int color;

    private OreMaterial(Builder builder) {
        this.name = builder.name;
        this.miningLevel = builder.miningLevel;
        this.hardness = builder.hardness;
        this.resistance = builder.resistance;
        this.bundles = builder.bundles.isEmpty()
                ? EnumSet.noneOf(OreBundle.class)
                : EnumSet.copyOf(builder.bundles);

        this.armorDurabilityMultiplier = builder.armorDurabilityMultiplier;
        this.armorProtection = builder.armorProtection;
        this.armorToughness = builder.armorToughness;
        this.armorKnockbackRes = builder.armorKnockbackRes;

        this.toolDurability = builder.toolDurability;
        this.toolMiningSpeed = builder.toolMiningSpeed;
        this.toolAttackDamage = builder.toolAttackDamage;
        this.toolEnchantability = builder.toolEnchantability;

        this.color = builder.color;
    }

    // --- Accessors ---

    public String getName() { return name; }
    public int getMiningLevel() { return miningLevel; }
    public float getHardness() { return hardness; }
    public float getResistance() { return resistance; }

    public boolean has(OreBundle bundle) { return bundles.contains(bundle); }

    public boolean hasOre() { return bundles.contains(OreBundle.ORE); }
    public boolean hasDeepslateOre() { return bundles.contains(OreBundle.DEEPSLATE_ORE); }
    public boolean hasRawItem() { return bundles.contains(OreBundle.RAW_ITEM); }
    public boolean hasIngot() { return bundles.contains(OreBundle.INGOT); }
    public boolean hasGem() { return bundles.contains(OreBundle.GEM); }
    public boolean hasStorageBlock() { return bundles.contains(OreBundle.STORAGE_BLOCK); }
    public boolean hasTools() { return bundles.contains(OreBundle.TOOLS); }
    public boolean hasSword() { return bundles.contains(OreBundle.SWORD); }
    public boolean hasDagger() { return bundles.contains(OreBundle.DAGGER); }
    public boolean hasArmor() { return bundles.contains(OreBundle.ARMOR); }
    public boolean hasSlabs() { return bundles.contains(OreBundle.SLABS); }
    public boolean hasStairs() { return bundles.contains(OreBundle.STAIRS); }
    public boolean hasWalls() { return bundles.contains(OreBundle.WALLS); }
    public boolean hasDoor() { return bundles.contains(OreBundle.DOOR); }
    public boolean hasTrapdoor() { return bundles.contains(OreBundle.TRAPDOOR); }
    public boolean hasButton() { return bundles.contains(OreBundle.BUTTON); }
    public boolean hasPressurePlate() { return bundles.contains(OreBundle.PRESSURE_PLATE); }

    public int getArmorDurabilityMultiplier() { return armorDurabilityMultiplier; }
    public int[] getArmorProtection() { return armorProtection; }
    public float getArmorToughness() { return armorToughness; }
    public float getArmorKnockbackRes() { return armorKnockbackRes; }

    public int getToolDurability() { return toolDurability; }
    public float getToolMiningSpeed() { return toolMiningSpeed; }
    public float getToolAttackDamage() { return toolAttackDamage; }
    public int getToolEnchantability() { return toolEnchantability; }

    public int getColor() { return color; }

    // --- Builder ---

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public static class Builder {
        private final String name;
        private int miningLevel = 1;
        private float hardness = 3.0f;
        private float resistance = 6.0f;
        private final Set<OreBundle> bundles = EnumSet.noneOf(OreBundle.class);

        private int armorDurabilityMultiplier = 15;
        private int[] armorProtection = {2, 5, 4, 2};
        private float armorToughness = 0.0f;
        private float armorKnockbackRes = 0.0f;

        private int toolDurability = 250;
        private float toolMiningSpeed = 5.0f;
        private float toolAttackDamage = 1.5f;
        private int toolEnchantability = 10;

        private int color = 0xFFFFFF;

        private Builder(String name) {
            this.name = name;
        }

        public Builder miningLevel(int level) {
            this.miningLevel = level;
            return this;
        }

        public Builder hardness(float hardness) {
            this.hardness = hardness;
            return this;
        }

        public Builder resistance(float resistance) {
            this.resistance = resistance;
            return this;
        }

        public Builder with(OreBundle... bundles) {
            this.bundles.addAll(Arrays.asList(bundles));
            return this;
        }

        public Builder armorDurability(int multiplier) {
            this.armorDurabilityMultiplier = multiplier;
            return this;
        }

        public Builder armorProtection(int head, int chest, int legs, int feet) {
            this.armorProtection = new int[]{head, chest, legs, feet};
            return this;
        }

        public Builder armorToughness(float toughness) {
            this.armorToughness = toughness;
            return this;
        }

        public Builder armorKnockbackRes(float knockbackRes) {
            this.armorKnockbackRes = knockbackRes;
            return this;
        }

        public Builder toolDurability(int durability) {
            this.toolDurability = durability;
            return this;
        }

        public Builder toolMiningSpeed(float speed) {
            this.toolMiningSpeed = speed;
            return this;
        }

        public Builder toolAttackDamage(float damage) {
            this.toolAttackDamage = damage;
            return this;
        }

        public Builder toolEnchantability(int enchantability) {
            this.toolEnchantability = enchantability;
            return this;
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public OreMaterial build() {
            return new OreMaterial(this);
        }
    }
}
