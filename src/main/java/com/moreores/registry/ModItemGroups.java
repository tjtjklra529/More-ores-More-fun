package com.moreores.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static ItemGroup MAIN_GROUP;
    public static ItemGroup JOKE_GROUP;

    public static void initialize() {
        MAIN_GROUP = FabricItemGroup.builder()
                .icon(() -> {
                    // Use titanium ingot as icon if registered, else fallback
                    net.minecraft.item.Item icon = ModItems.ITEMS.get("titanium_ingot");
                    if (icon != null) return new ItemStack(icon);
                    return ItemStack.EMPTY;
                })
                .displayName(Text.translatable("itemGroup.moreores.main"))
                .entries((context, entries) -> {
                    // Ores
                    addIfPresent(entries, "titanium_ore");
                    addIfPresent(entries, "deepslate_titanium_ore");
                    addIfPresent(entries, "platinum_ore");
                    addIfPresent(entries, "deepslate_platinum_ore");
                    addIfPresent(entries, "nickel_ore");
                    addIfPresent(entries, "deepslate_nickel_ore");
                    addIfPresent(entries, "aluminum_ore");
                    addIfPresent(entries, "deepslate_aluminum_ore");
                    addIfPresent(entries, "silver_ore");
                    addIfPresent(entries, "deepslate_silver_ore");
                    addIfPresent(entries, "tin_ore");
                    addIfPresent(entries, "deepslate_tin_ore");

                    // Raw items
                    addIfPresent(entries, "raw_titanium");
                    addIfPresent(entries, "raw_platinum");
                    addIfPresent(entries, "raw_nickel");
                    addIfPresent(entries, "raw_aluminum");
                    addIfPresent(entries, "raw_silver");
                    addIfPresent(entries, "raw_tin");

                    // Ingots
                    addIfPresent(entries, "titanium_ingot");
                    addIfPresent(entries, "platinum_ingot");
                    addIfPresent(entries, "nickel_ingot");
                    addIfPresent(entries, "aluminum_ingot");
                    addIfPresent(entries, "silver_ingot");
                    addIfPresent(entries, "bronze_ingot");
                    addIfPresent(entries, "steel_ingot");
                    addIfPresent(entries, "tin_ingot");
                    addIfPresent(entries, "copper_alloy_ingot");

                    // Gems
                    addIfPresent(entries, "ruby");
                    addIfPresent(entries, "sapphire");
                    addIfPresent(entries, "topaz");

                    // Storage blocks
                    addIfPresent(entries, "titanium_block");
                    addIfPresent(entries, "platinum_block");
                    addIfPresent(entries, "nickel_block");
                    addIfPresent(entries, "aluminum_block");
                    addIfPresent(entries, "silver_block");
                    addIfPresent(entries, "bronze_block");
                    addIfPresent(entries, "steel_block");
                    addIfPresent(entries, "tin_block");
                    addIfPresent(entries, "ruby_block");
                    addIfPresent(entries, "sapphire_block");
                    addIfPresent(entries, "topaz_block");

                    // Tools
                    addIfPresent(entries, "titanium_pickaxe");
                    addIfPresent(entries, "titanium_axe");
                    addIfPresent(entries, "titanium_shovel");
                    addIfPresent(entries, "titanium_hoe");
                    addIfPresent(entries, "titanium_sword");
                    addIfPresent(entries, "titanium_dagger");
                    addIfPresent(entries, "platinum_pickaxe");
                    addIfPresent(entries, "platinum_axe");
                    addIfPresent(entries, "platinum_shovel");
                    addIfPresent(entries, "platinum_hoe");
                    addIfPresent(entries, "platinum_sword");
                    addIfPresent(entries, "platinum_dagger");
                    addIfPresent(entries, "nickel_pickaxe");
                    addIfPresent(entries, "nickel_axe");
                    addIfPresent(entries, "nickel_shovel");
                    addIfPresent(entries, "nickel_hoe");
                    addIfPresent(entries, "nickel_sword");
                    addIfPresent(entries, "nickel_dagger");
                    addIfPresent(entries, "aluminum_pickaxe");
                    addIfPresent(entries, "aluminum_axe");
                    addIfPresent(entries, "aluminum_shovel");
                    addIfPresent(entries, "aluminum_hoe");
                    addIfPresent(entries, "aluminum_sword");
                    addIfPresent(entries, "aluminum_dagger");
                    addIfPresent(entries, "silver_pickaxe");
                    addIfPresent(entries, "silver_axe");
                    addIfPresent(entries, "silver_shovel");
                    addIfPresent(entries, "silver_hoe");
                    addIfPresent(entries, "silver_sword");
                    addIfPresent(entries, "silver_dagger");
                    addIfPresent(entries, "bronze_pickaxe");
                    addIfPresent(entries, "bronze_axe");
                    addIfPresent(entries, "bronze_shovel");
                    addIfPresent(entries, "bronze_hoe");
                    addIfPresent(entries, "bronze_sword");
                    addIfPresent(entries, "bronze_dagger");
                    addIfPresent(entries, "steel_pickaxe");
                    addIfPresent(entries, "steel_axe");
                    addIfPresent(entries, "steel_shovel");
                    addIfPresent(entries, "steel_hoe");
                    addIfPresent(entries, "steel_sword");
                    addIfPresent(entries, "steel_dagger");
                    addIfPresent(entries, "tin_pickaxe");
                    addIfPresent(entries, "tin_axe");
                    addIfPresent(entries, "tin_shovel");
                    addIfPresent(entries, "tin_hoe");
                    addIfPresent(entries, "copper_alloy_pickaxe");
                    addIfPresent(entries, "copper_alloy_axe");
                    addIfPresent(entries, "copper_alloy_shovel");
                    addIfPresent(entries, "copper_alloy_hoe");
                    addIfPresent(entries, "ruby_dagger");
                    addIfPresent(entries, "sapphire_dagger");
                    addIfPresent(entries, "topaz_dagger");

                    // Armor
                    addIfPresent(entries, "titanium_helmet");
                    addIfPresent(entries, "titanium_chestplate");
                    addIfPresent(entries, "titanium_leggings");
                    addIfPresent(entries, "titanium_boots");
                    addIfPresent(entries, "platinum_helmet");
                    addIfPresent(entries, "platinum_chestplate");
                    addIfPresent(entries, "platinum_leggings");
                    addIfPresent(entries, "platinum_boots");
                    addIfPresent(entries, "nickel_helmet");
                    addIfPresent(entries, "nickel_chestplate");
                    addIfPresent(entries, "nickel_leggings");
                    addIfPresent(entries, "nickel_boots");
                    addIfPresent(entries, "aluminum_helmet");
                    addIfPresent(entries, "aluminum_chestplate");
                    addIfPresent(entries, "aluminum_leggings");
                    addIfPresent(entries, "aluminum_boots");
                    addIfPresent(entries, "silver_helmet");
                    addIfPresent(entries, "silver_chestplate");
                    addIfPresent(entries, "silver_leggings");
                    addIfPresent(entries, "silver_boots");
                    addIfPresent(entries, "bronze_helmet");
                    addIfPresent(entries, "bronze_chestplate");
                    addIfPresent(entries, "bronze_leggings");
                    addIfPresent(entries, "bronze_boots");
                    addIfPresent(entries, "steel_helmet");
                    addIfPresent(entries, "steel_chestplate");
                    addIfPresent(entries, "steel_leggings");
                    addIfPresent(entries, "steel_boots");
                    addIfPresent(entries, "ruby_helmet");
                    addIfPresent(entries, "ruby_chestplate");
                    addIfPresent(entries, "ruby_leggings");
                    addIfPresent(entries, "ruby_boots");
                    addIfPresent(entries, "sapphire_helmet");
                    addIfPresent(entries, "sapphire_chestplate");
                    addIfPresent(entries, "sapphire_leggings");
                    addIfPresent(entries, "sapphire_boots");
                    addIfPresent(entries, "topaz_helmet");
                    addIfPresent(entries, "topaz_chestplate");
                    addIfPresent(entries, "topaz_leggings");
                    addIfPresent(entries, "topaz_boots");

                    // Building blocks
                    addIfPresent(entries, "moon_rock");
                    addIfPresent(entries, "moon_rock_slab");
                    addIfPresent(entries, "moon_rock_stairs");
                    addIfPresent(entries, "moon_rock_wall");

                    addIfPresent(entries, "petrified_oak");
                    addIfPresent(entries, "petrified_oak_slab");
                    addIfPresent(entries, "petrified_oak_stairs");
                    addIfPresent(entries, "petrified_oak_wall");
                    addIfPresent(entries, "petrified_spruce");
                    addIfPresent(entries, "petrified_spruce_slab");
                    addIfPresent(entries, "petrified_spruce_stairs");
                    addIfPresent(entries, "petrified_spruce_wall");
                    addIfPresent(entries, "petrified_birch");
                    addIfPresent(entries, "petrified_birch_slab");
                    addIfPresent(entries, "petrified_birch_stairs");
                    addIfPresent(entries, "petrified_birch_wall");
                    addIfPresent(entries, "petrified_jungle");
                    addIfPresent(entries, "petrified_jungle_slab");
                    addIfPresent(entries, "petrified_jungle_stairs");
                    addIfPresent(entries, "petrified_jungle_wall");
                    addIfPresent(entries, "petrified_acacia");
                    addIfPresent(entries, "petrified_acacia_slab");
                    addIfPresent(entries, "petrified_acacia_stairs");
                    addIfPresent(entries, "petrified_acacia_wall");
                    addIfPresent(entries, "petrified_dark_oak");
                    addIfPresent(entries, "petrified_dark_oak_slab");
                    addIfPresent(entries, "petrified_dark_oak_stairs");
                    addIfPresent(entries, "petrified_dark_oak_wall");
                    addIfPresent(entries, "petrified_mangrove");
                    addIfPresent(entries, "petrified_mangrove_slab");
                    addIfPresent(entries, "petrified_mangrove_stairs");
                    addIfPresent(entries, "petrified_mangrove_wall");
                    addIfPresent(entries, "petrified_cherry");
                    addIfPresent(entries, "petrified_cherry_slab");
                    addIfPresent(entries, "petrified_cherry_stairs");
                    addIfPresent(entries, "petrified_cherry_wall");

                    // Fluids / misc
                    addIfPresent(entries, "oil_bucket");

                    // Engine blocks
                    addIfPresent(entries, "oil_engine");
                    addIfPresent(entries, "oil_furnace");
                    addIfPresent(entries, "oil_crusher");
                })
                .build();

        Registry.register(Registries.ITEM_GROUP, new Identifier("moreores", "main"), MAIN_GROUP);

        // Joke items group (creative only)
        JOKE_GROUP = FabricItemGroup.builder()
                .icon(() -> {
                    net.minecraft.item.Item icon = ModItems.ITEMS.get("diamond_minecart");
                    if (icon != null) return new ItemStack(icon);
                    return ItemStack.EMPTY;
                })
                .displayName(Text.translatable("itemGroup.moreores.joke"))
                .entries((context, entries) -> {
                    addItemIfPresent(entries, "diamond_minecart");
                    addItemIfPresent(entries, "barrier_boat");
                    addItemIfPresent(entries, "golden_apple_juice");
                })
                .build();

        Registry.register(Registries.ITEM_GROUP, new Identifier("moreores", "joke"), JOKE_GROUP);
    }

    private static void addItemIfPresent(ItemGroup.Entries entries, String name) {
        net.minecraft.item.Item item = ModItems.ITEMS.get(name);
        if (item != null) {
            entries.add(item);
        }
    }

    private static void addIfPresent(ItemGroup.Entries entries, String name) {
        net.minecraft.item.Item item = ModItems.ITEMS.get(name);
        if (item != null) {
            entries.add(item);
            return;
        }
        // Check blocks
        net.minecraft.block.Block block = ModBlocks.BLOCKS.get(name);
        if (block != null) {
            net.minecraft.item.BlockItem bi = (net.minecraft.item.BlockItem)
                    net.minecraft.item.Item.fromBlock(block);
            entries.add(bi);
        }
    }
}
