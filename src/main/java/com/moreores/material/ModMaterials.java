package com.moreores.material;

import static com.moreores.material.OreBundle.*;

/**
 * Central registry of all OreMaterial definitions for the mod.
 */
public class ModMaterials {

    public static final OreMaterial TITANIUM = OreMaterial.builder("titanium")
            .miningLevel(3)
            .hardness(5.0f).resistance(10.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(37)
            .armorProtection(3, 8, 6, 3)
            .armorToughness(2.0f)
            .armorKnockbackRes(0.1f)
            .toolDurability(1800)
            .toolMiningSpeed(9.0f)
            .toolAttackDamage(3.0f)
            .toolEnchantability(12)
            .color(0x98B4C8)
            .build();

    public static final OreMaterial PLATINUM = OreMaterial.builder("platinum")
            .miningLevel(3)
            .hardness(5.0f).resistance(10.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(35)
            .armorProtection(3, 8, 6, 3)
            .armorToughness(2.0f)
            .armorKnockbackRes(0.1f)
            .toolDurability(1600)
            .toolMiningSpeed(8.5f)
            .toolAttackDamage(2.5f)
            .toolEnchantability(16)
            .color(0xE8E8E8)
            .build();

    public static final OreMaterial NICKEL = OreMaterial.builder("nickel")
            .miningLevel(2)
            .hardness(4.0f).resistance(8.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(20)
            .armorProtection(2, 6, 5, 2)
            .armorToughness(1.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(750)
            .toolMiningSpeed(6.5f)
            .toolAttackDamage(2.0f)
            .toolEnchantability(10)
            .color(0xC0C080)
            .build();

    public static final OreMaterial ALUMINUM = OreMaterial.builder("aluminum")
            .miningLevel(1)
            .hardness(2.0f).resistance(4.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(14)
            .armorProtection(1, 4, 3, 1)
            .armorToughness(0.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(300)
            .toolMiningSpeed(8.0f)
            .toolAttackDamage(1.0f)
            .toolEnchantability(14)
            .color(0xC0D0E0)
            .build();

    public static final OreMaterial SILVER = OreMaterial.builder("silver")
            .miningLevel(2)
            .hardness(4.0f).resistance(7.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(18)
            .armorProtection(2, 6, 4, 2)
            .armorToughness(0.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(600)
            .toolMiningSpeed(7.0f)
            .toolAttackDamage(1.5f)
            .toolEnchantability(20)
            .color(0xDDDDDD)
            .build();

    public static final OreMaterial BRONZE = OreMaterial.builder("bronze")
            .miningLevel(1)
            .hardness(3.5f).resistance(7.0f)
            .with(INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(16)
            .armorProtection(2, 5, 4, 2)
            .armorToughness(0.5f)
            .armorKnockbackRes(0.0f)
            .toolDurability(450)
            .toolMiningSpeed(6.0f)
            .toolAttackDamage(2.0f)
            .toolEnchantability(9)
            .color(0xCD7F32)
            .build();

    public static final OreMaterial STEEL = OreMaterial.builder("steel")
            .miningLevel(2)
            .hardness(5.0f).resistance(9.0f)
            .with(INGOT, STORAGE_BLOCK,
                    TOOLS, SWORD, DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(25)
            .armorProtection(3, 7, 5, 2)
            .armorToughness(1.5f)
            .armorKnockbackRes(0.0f)
            .toolDurability(900)
            .toolMiningSpeed(7.5f)
            .toolAttackDamage(2.5f)
            .toolEnchantability(8)
            .color(0x708090)
            .build();

    public static final OreMaterial TIN = OreMaterial.builder("tin")
            .miningLevel(1)
            .hardness(2.5f).resistance(5.0f)
            .with(ORE, DEEPSLATE_ORE, RAW_ITEM, INGOT, STORAGE_BLOCK,
                    TOOLS,
                    SLABS, STAIRS, WALLS)
            .toolDurability(200)
            .toolMiningSpeed(5.5f)
            .toolAttackDamage(1.0f)
            .toolEnchantability(8)
            .color(0xC8C8B8)
            .build();

    public static final OreMaterial COPPER_ALLOY = OreMaterial.builder("copper_alloy")
            .miningLevel(1)
            .hardness(3.0f).resistance(6.0f)
            .with(INGOT, TOOLS)
            .toolDurability(350)
            .toolMiningSpeed(6.0f)
            .toolAttackDamage(1.5f)
            .toolEnchantability(10)
            .color(0xB87333)
            .build();

    public static final OreMaterial RUBY = OreMaterial.builder("ruby")
            .miningLevel(2)
            .hardness(4.0f).resistance(8.0f)
            .with(GEM, STORAGE_BLOCK,
                    DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(22)
            .armorProtection(2, 6, 5, 2)
            .armorToughness(1.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(600)
            .toolMiningSpeed(6.0f)
            .toolAttackDamage(2.0f)
            .toolEnchantability(18)
            .color(0xE0115F)
            .build();

    public static final OreMaterial SAPPHIRE = OreMaterial.builder("sapphire")
            .miningLevel(2)
            .hardness(4.0f).resistance(8.0f)
            .with(GEM, STORAGE_BLOCK,
                    DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(22)
            .armorProtection(2, 6, 5, 2)
            .armorToughness(1.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(600)
            .toolMiningSpeed(6.0f)
            .toolAttackDamage(2.0f)
            .toolEnchantability(18)
            .color(0x0F52BA)
            .build();

    public static final OreMaterial TOPAZ = OreMaterial.builder("topaz")
            .miningLevel(1)
            .hardness(3.0f).resistance(6.0f)
            .with(GEM, STORAGE_BLOCK,
                    DAGGER, ARMOR,
                    SLABS, STAIRS, WALLS)
            .armorDurability(14)
            .armorProtection(1, 4, 3, 1)
            .armorToughness(0.0f)
            .armorKnockbackRes(0.0f)
            .toolDurability(300)
            .toolMiningSpeed(5.5f)
            .toolAttackDamage(1.5f)
            .toolEnchantability(14)
            .color(0xFFC87C)
            .build();

    public static final OreMaterial MOON_ROCK = OreMaterial.builder("moon_rock")
            .miningLevel(2)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0xC8C8D0)
            .build();

    public static final OreMaterial PETRIFIED_OAK = OreMaterial.builder("petrified_oak")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0x7A5C3A)
            .build();

    public static final OreMaterial PETRIFIED_SPRUCE = OreMaterial.builder("petrified_spruce")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0x5C4228)
            .build();

    public static final OreMaterial PETRIFIED_BIRCH = OreMaterial.builder("petrified_birch")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0xD4C9A8)
            .build();

    public static final OreMaterial PETRIFIED_JUNGLE = OreMaterial.builder("petrified_jungle")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0x8B5E3C)
            .build();

    public static final OreMaterial PETRIFIED_ACACIA = OreMaterial.builder("petrified_acacia")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0xBA6337)
            .build();

    public static final OreMaterial PETRIFIED_DARK_OAK = OreMaterial.builder("petrified_dark_oak")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0x3B2A1A)
            .build();

    public static final OreMaterial PETRIFIED_MANGROVE = OreMaterial.builder("petrified_mangrove")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0x7D2C2C)
            .build();

    public static final OreMaterial PETRIFIED_CHERRY = OreMaterial.builder("petrified_cherry")
            .miningLevel(1)
            .hardness(4.0f).resistance(8.0f)
            .with(STORAGE_BLOCK, SLABS, STAIRS, WALLS)
            .color(0xE8B4C8)
            .build();

    public static final OreMaterial[] ALL_MATERIALS = {
            TITANIUM, PLATINUM, NICKEL, ALUMINUM, SILVER,
            BRONZE, STEEL, TIN, COPPER_ALLOY,
            RUBY, SAPPHIRE, TOPAZ,
            MOON_ROCK,
            PETRIFIED_OAK, PETRIFIED_SPRUCE, PETRIFIED_BIRCH, PETRIFIED_JUNGLE,
            PETRIFIED_ACACIA, PETRIFIED_DARK_OAK, PETRIFIED_MANGROVE, PETRIFIED_CHERRY
    };
}
