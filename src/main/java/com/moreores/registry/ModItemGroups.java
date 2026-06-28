package com.moreores.registry;

import com.moreores.material.ModMaterials;
import com.moreores.material.OreMaterial;
import com.moreores.material.OreBundle;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
                    Item icon = ModItems.ITEMS.get("titanium_ingot");
                    return icon != null ? new ItemStack(icon) : ItemStack.EMPTY;
                })
                .displayName(Text.translatable("itemGroup.moreores.main"))
                .entries((context, entries) -> {
                    // Add all material-driven content dynamically
                    for (OreMaterial mat : ModMaterials.ALL_MATERIALS) {
                        String n = mat.getName();
                        // Ores
                        addBlock(entries, n + "_ore");
                        addBlock(entries, "deepslate_" + n + "_ore");
                        // Raw item
                        addItem(entries, "raw_" + n);
                        // Ingot or gem
                        addItem(entries, n + "_ingot");
                        addItem(entries, n);  // gems use plain name
                        // Storage block
                        addBlock(entries, n + "_block");
                        addBlock(entries, n);  // stone-type main block (moon_rock, petrified_*)
                        // Building variants
                        addBlock(entries, n + "_slab");
                        addBlock(entries, n + "_stairs");
                        addBlock(entries, n + "_wall");
                        addBlock(entries, n + "_door");
                        addBlock(entries, n + "_trapdoor");
                        addBlock(entries, n + "_button");
                        addBlock(entries, n + "_pressure_plate");
                        // Tools
                        addItem(entries, n + "_pickaxe");
                        addItem(entries, n + "_axe");
                        addItem(entries, n + "_shovel");
                        addItem(entries, n + "_hoe");
                        addItem(entries, n + "_sword");
                        addItem(entries, n + "_dagger");
                        // Armor
                        addItem(entries, n + "_helmet");
                        addItem(entries, n + "_chestplate");
                        addItem(entries, n + "_leggings");
                        addItem(entries, n + "_boots");
                    }

                    // Gem lamps
                    for (String gem : new String[]{
                            "ruby", "sapphire", "topaz", "amber", "jade", "opal",
                            "garnet", "peridot", "aquamarine", "tourmaline", "citrine",
                            "sulfur", "cinnabar", "fluorite", "malachite", "alexandrite",
                            "tanzanite", "onyx", "zircon", "rhodonite", "morganite", "moldavite"}) {
                        addBlock(entries, gem + "_lamp");
                    }

                    // Oil / engine system
                    addItem(entries, "oil_bucket");
                    addItem(entries, "refined_fuel");
                    addBlock(entries, "oil_engine");
                    addBlock(entries, "oil_furnace");
                    addBlock(entries, "oil_crusher");
                    addBlock(entries, "refinery");
                    addBlock(entries, "repair_station");
                })
                .build();

        Registry.register(Registries.ITEM_GROUP, new Identifier("moreores", "main"), MAIN_GROUP);

        JOKE_GROUP = FabricItemGroup.builder()
                .icon(() -> {
                    Item icon = ModItems.ITEMS.get("diamond_minecart");
                    return icon != null ? new ItemStack(icon) : ItemStack.EMPTY;
                })
                .displayName(Text.translatable("itemGroup.moreores.joke"))
                .entries((context, entries) -> {
                    addItem(entries, "diamond_minecart");
                    addItem(entries, "barrier_boat");
                    addItem(entries, "golden_apple_juice");
                })
                .build();

        Registry.register(Registries.ITEM_GROUP, new Identifier("moreores", "joke"), JOKE_GROUP);
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
