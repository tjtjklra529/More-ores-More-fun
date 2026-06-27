package com.moreores.datagen;

import com.moreores.material.ModMaterials;
import com.moreores.material.OreMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/**
 * Datagen blockstate and block model provider for all moreores blocks.
 * Note: Static JSON files under assets/moreores/blockstates/ and models/block/ are
 * the primary source of truth.
 */
public class ModBlockStateProvider extends FabricModelProvider {

    public ModBlockStateProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        for (OreMaterial mat : ModMaterials.ALL_MATERIALS) {
            String name = mat.getName();

            // Ore blocks
            if (mat.hasOre()) {
                Block oreBlock = getBlock("moreores", name + "_ore");
                if (oreBlock != null) gen.registerSimpleCubeAll(oreBlock);
            }
            if (mat.hasDeepslateOre()) {
                Block dsOreBlock = getBlock("moreores", "deepslate_" + name + "_ore");
                if (dsOreBlock != null) gen.registerSimpleCubeAll(dsOreBlock);
            }

            // Storage block
            if (mat.hasStorageBlock()) {
                String blockId = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
                Block storageBlock = getBlock("moreores", blockId);
                if (storageBlock != null) gen.registerSimpleCubeAll(storageBlock);
            }

            // Slabs
            if (mat.hasSlabs()) {
                Block slabBlock = getBlock("moreores", name + "_slab");
                String blockId = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
                Block fullBlock = getBlock("moreores", blockId);
                if (slabBlock != null && fullBlock != null) {
                    gen.registerSlab(slabBlock, Registries.BLOCK.getId(fullBlock), Registries.BLOCK.getId(fullBlock), Registries.BLOCK.getId(fullBlock));
                }
            }

            // Stairs
            if (mat.hasStairs()) {
                Block stairsBlock = getBlock("moreores", name + "_stairs");
                String blockId = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
                Block fullBlock = getBlock("moreores", blockId);
                if (stairsBlock != null && fullBlock != null) {
                    gen.registerStairs(stairsBlock, Registries.BLOCK.getId(fullBlock));
                }
            }

            // Walls
            if (mat.hasWalls()) {
                Block wallBlock = getBlock("moreores", name + "_wall");
                String blockId = (mat.hasIngot() || mat.hasGem()) ? name + "_block" : name;
                Block fullBlock = getBlock("moreores", blockId);
                if (wallBlock != null && fullBlock != null) {
                    gen.registerWall(wallBlock, Registries.BLOCK.getId(fullBlock));
                }
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        // Item models are handled by static JSON files in assets/moreores/models/item/
        // The datagen for items is intentionally left minimal since flat generated models
        // are trivially declared in static JSON.
    }

    private Block getBlock(String namespace, String path) {
        Identifier id = new Identifier(namespace, path);
        if (Registries.BLOCK.containsId(id)) {
            return Registries.BLOCK.get(id);
        }
        return null;
    }
}
