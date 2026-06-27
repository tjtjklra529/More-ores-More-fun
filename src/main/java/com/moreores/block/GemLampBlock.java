package com.moreores.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

/**
 * A glowing lamp block crafted from gem materials.
 * Light level is set at registration time based on the gem type.
 */
public class GemLampBlock extends Block {

    public GemLampBlock(Settings settings) {
        super(settings);
    }
}
