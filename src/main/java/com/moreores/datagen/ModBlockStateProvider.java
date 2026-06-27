package com.moreores.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

/**
 * Datagen model provider stub.
 * All blockstates, block models, and item models are provided as static JSON files
 * under assets/moreores/. This class exists only to satisfy the datagen entrypoint.
 */
public class ModBlockStateProvider extends FabricModelProvider {

    public ModBlockStateProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        // All blockstate/model JSON files are committed statically — no generation needed.
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        // All item model JSON files are committed statically — no generation needed.
    }
}
