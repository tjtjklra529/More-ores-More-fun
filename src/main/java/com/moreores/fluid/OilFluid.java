package com.moreores.fluid;

import com.moreores.registry.ModBlocks;
import com.moreores.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

/**
 * Oil fluid: black, viscous. Slower spreading than water.
 */
public abstract class OilFluid extends net.minecraft.fluid.FlowableFluid {

    public static final Still STILL = new Still();
    public static final Flowing FLOWING = new Flowing();

    @Override
    public Fluid getFlowing() {
        return FLOWING;
    }

    @Override
    public Fluid getStill() {
        return STILL;
    }

    @Override
    public Item getBucketItem() {
        Item bucket = ModItems.ITEMS.get("oil_bucket");
        return bucket != null ? bucket : Items.BUCKET;
    }

    @Override
    protected boolean isInfinite(net.minecraft.world.World world) {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        // Break block silently
        world.breakBlock(pos, true);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        // Slower spread than water (water is 4, oil is slightly slower)
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.OIL_FLUID_BLOCK.getDefaultState()
                .with(net.minecraft.block.FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == STILL || fluid == FLOWING;
    }

    @Override
    protected ParticleEffect getParticle() {
        return ParticleTypes.DRIPPING_LAVA;
    }

    @Override
    public float getBlastResistance() {
        return 100.0f;
    }

    @Override
    public int getTickRate(net.minecraft.world.WorldView world) {
        return 10;
    }

    @Override
    public boolean canBeReplacedWith(FluidState state, net.minecraft.world.BlockView world,
                                     BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    // --- Still ---

    public static class Still extends OilFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            // No extra properties for still fluid
        }
    }

    // --- Flowing ---

    public static class Flowing extends OilFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }
}
