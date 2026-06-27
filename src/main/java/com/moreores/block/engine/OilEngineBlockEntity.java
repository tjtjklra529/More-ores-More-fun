package com.moreores.block.engine;

import com.moreores.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OilEngineBlockEntity extends BlockEntity {

    public static final int MAX_FUEL = 800;

    private int fuelTicks = 0;
    private boolean active = false;

    public OilEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OIL_ENGINE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OilEngineBlockEntity be) {
        if (world.isClient) return;

        boolean wasActive = be.active;

        if (be.active) {
            if (be.fuelTicks > 0) {
                be.fuelTicks--;
                if (be.fuelTicks == 0) {
                    be.active = false;
                }
            } else {
                be.active = false;
            }
        }

        if (wasActive != be.active) {
            world.setBlockState(pos, state.with(OilEngineBlock.ACTIVE, be.active), 3);
        }

        if (wasActive != be.active || (be.active && be.fuelTicks % 20 == 0)) {
            be.markDirty();
        }
    }

    public int getFuelTicks() {
        return fuelTicks;
    }

    public boolean isActive() {
        return active;
    }

    public void addFuel(int ticks) {
        this.fuelTicks = Math.min(MAX_FUEL, this.fuelTicks + ticks);
        if (this.fuelTicks > 0) {
            this.active = true;
        }
        markDirty();
    }

    public void setActive(boolean active) {
        this.active = active;
        markDirty();
    }

    public int getComparatorOutput() {
        // 0-15 based on fuel level
        return (int) ((fuelTicks / (float) MAX_FUEL) * 15);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        fuelTicks = nbt.getInt("FuelTicks");
        active = nbt.getBoolean("Active");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("FuelTicks", fuelTicks);
        nbt.putBoolean("Active", active);
    }
}
