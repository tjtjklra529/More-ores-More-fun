package com.moreores.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/**
 * The oil fluid block - what you see when oil fills a space in the world.
 */
public class OilFluidBlock extends FluidBlock {

    public OilFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 15;
    }
}
