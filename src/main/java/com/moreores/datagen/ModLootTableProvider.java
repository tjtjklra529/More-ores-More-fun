package com.moreores.datagen;

import com.moreores.material.ModMaterials;
import com.moreores.material.OreMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/**
 * Datagen loot table provider for all moreores blocks.
 * Note: Static JSON loot table files under data/moreores/loot_tables/blocks/ are the
 * primary source of truth. This class generates the same tables programmatically.
 */
public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (OreMaterial mat : ModMaterials.ALL_MATERIALS) {
            String name = mat.getName();

            // Ore blocks
            if (mat.hasOre()) {
                Block oreBlock = getBlock("moreores", name + "_ore");
                if (oreBlock != null) {
                    if (mat.hasGem()) {
                        // Gem ore: drop gem with fortune
                        Item gemItem = getItem("moreores", name);
                        if (gemItem != null) addDrop(oreBlock, oreDrops(oreBlock, gemItem));
                    } else {
                        // Metal ore: drop raw with fortune
                        Item rawItem = getItem("moreores", "raw_" + name);
                        if (rawItem != null) addDrop(oreBlock, oreDrops(oreBlock, rawItem));
                    }
                }
            }

            if (mat.hasDeepslateOre()) {
                Block dsOreBlock = getBlock("moreores", "deepslate_" + name + "_ore");
                if (dsOreBlock != null) {
                    if (mat.hasGem()) {
                        Item gemItem = getItem("moreores", name);
                        if (gemItem != null) addDrop(dsOreBlock, oreDrops(dsOreBlock, gemItem));
                    } else {
                        Item rawItem = getItem("moreores", "raw_" + name);
                        if (rawItem != null) addDrop(dsOreBlock, oreDrops(dsOreBlock, rawItem));
                    }
                }
            }

            // Storage block (self drop)
            if (mat.hasStorageBlock()) {
                String blockId = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
                Block storageBlock = getBlock("moreores", blockId);
                if (storageBlock != null) addDrop(storageBlock);
            }

            // Slabs (self drop)
            if (mat.hasSlabs()) {
                Block slabBlock = getBlock("moreores", name + "_slab");
                if (slabBlock != null) addDrop(slabBlock, slabDrops(slabBlock));
            }

            // Stairs (self drop)
            if (mat.hasStairs()) {
                Block stairsBlock = getBlock("moreores", name + "_stairs");
                if (stairsBlock != null) addDrop(stairsBlock);
            }

            // Walls (self drop)
            if (mat.hasWalls()) {
                Block wallBlock = getBlock("moreores", name + "_wall");
                if (wallBlock != null) addDrop(wallBlock);
            }
        }
    }

    private Block getBlock(String namespace, String path) {
        Identifier id = new Identifier(namespace, path);
        if (Registries.BLOCK.containsId(id)) {
            return Registries.BLOCK.get(id);
        }
        return null;
    }

    private Item getItem(String namespace, String path) {
        Identifier id = new Identifier(namespace, path);
        if (Registries.ITEM.containsId(id)) {
            return Registries.ITEM.get(id);
        }
        return null;
    }
}
