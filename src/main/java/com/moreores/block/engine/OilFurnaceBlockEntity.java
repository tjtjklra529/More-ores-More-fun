package com.moreores.block.engine;

import com.moreores.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class OilFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public OilFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OIL_FURNACE, pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.moreores.oil_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    /**
     * Server tick with oil-engine speed boost.
     * Calls parent tick, then if adjacent engine is active, calls it again for 2x speed.
     */
    public static void tick(World world, BlockPos pos, BlockState state, OilFurnaceBlockEntity be) {
        // Standard furnace tick
        AbstractFurnaceBlockEntity.tick(world, pos, state, be);

        // If adjacent oil engine is active, tick again for 2x speed
        if (isAdjacentEngineActive(world, pos)) {
            AbstractFurnaceBlockEntity.tick(world, pos, state, be);
        }
    }

    public static boolean isAdjacentEngineActive(World world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.offset(dir);
            if (world.getBlockState(neighborPos).getBlock() instanceof OilEngineBlock) {
                net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(neighborPos);
                if (be instanceof OilEngineBlockEntity engine) {
                    return engine.isActive();
                }
            }
        }
        return false;
    }
}
