package com.moreores.registry;

import com.moreores.material.ModMaterials;
import com.moreores.material.OreMaterial;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> MAIN_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP, new Identifier("moreores", "main"));
    public static final RegistryKey<ItemGroup> JOKE_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP, new Identifier("moreores", "joke"));

    public static ItemGroup MAIN_GROUP;
    public static ItemGroup JOKE_GROUP;

    public static void initialize() {
        MAIN_GROUP = Registry.register(Registries.ITEM_GROUP, MAIN_KEY,
                FabricItemGroup.builder()
                        .icon(() -> {
                            Item icon = ModItems.ITEMS.get("titanium_ingot");
                            return icon != null ? new ItemStack(icon) : ItemStack.EMPTY;
                        })
                        .displayName(Text.translatable("itemGroup.moreores.main"))
                        .build());

        JOKE_GROUP = Registry.register(Registries.ITEM_GROUP, JOKE_KEY,
                FabricItemGroup.builder()
                        .icon(() -> {
                            Item icon = ModItems.ITEMS.get("diamond_minecart");
                            return icon != null ? new ItemStack(icon) : ItemStack.EMPTY;
                        })
                        .displayName(Text.translatable("itemGroup.moreores.joke"))
                        .build());

        ItemGroupEvents.modifyEntriesEvent(MAIN_KEY).register(entries -> {
            for (OreMaterial mat : ModMaterials.ALL_MATERIALS) {
                String n = mat.getName();
                addBlock(entries, n + "_ore");
                addBlock(entries, "deepslate_" + n + "_ore");
                addItem(entries, "raw_" + n);
                addItem(entries, n + "_ingot");
                addItem(entries, n);
                addBlock(entries, n + "_block");
                addBlock(entries, n);
                addBlock(entries, n + "_slab");
                addBlock(entries, n + "_stairs");
                addBlock(entries, n + "_wall");
                addBlock(entries, n + "_door");
                addBlock(entries, n + "_trapdoor");
                addBlock(entries, n + "_button");
                addBlock(entries, n + "_pressure_plate");
                addItem(entries, n + "_pickaxe");
                addItem(entries, n + "_axe");
                addItem(entries, n + "_shovel");
                addItem(entries, n + "_hoe");
                addItem(entries, n + "_sword");
                addItem(entries, n + "_dagger");
                addItem(entries, n + "_helmet");
                addItem(entries, n + "_chestplate");
                addItem(entries, n + "_leggings");
                addItem(entries, n + "_boots");
            }

            for (String gem : new String[]{
                    "ruby", "sapphire", "topaz", "amber", "jade", "opal",
                    "garnet", "peridot", "aquamarine", "tourmaline", "citrine",
                    "sulfur", "cinnabar", "fluorite", "malachite", "alexandrite",
                    "tanzanite", "onyx", "zircon", "rhodonite", "morganite", "moldavite"}) {
                addBlock(entries, gem + "_lamp");
            }

            addItem(entries, "oil_bucket");
            addItem(entries, "refined_fuel");
            addBlock(entries, "oil_engine");
            addBlock(entries, "oil_furnace");
            addBlock(entries, "oil_crusher");
            addBlock(entries, "refinery");
            addBlock(entries, "repair_station");
        });

        ItemGroupEvents.modifyEntriesEvent(JOKE_KEY).register(entries -> {
            addItem(entries, "diamond_minecart");
            addItem(entries, "barrier_boat");
            addItem(entries, "golden_apple_juice");
        });
    }

    private static void addItem(ItemGroup.Entries entries, String name) {
        Item item = ModItems.ITEMS.get(name);
        if (item != null) entries.add(item);
    }

    private static void addBlock(ItemGroup.Entries entries, String name) {
        Block block = ModBlocks.BLOCKS.get(name);
        if (block != null) {
            Item bi = Item.fromBlock(block);
            if (bi != net.minecraft.item.Items.AIR) entries.add(bi);
        }
    }
}
