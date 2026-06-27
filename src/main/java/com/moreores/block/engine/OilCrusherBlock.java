package com.moreores.block.engine;

import com.moreores.registry.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OilCrusherBlock extends BlockWithEntity {

    public OilCrusherBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OilCrusherBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, ModBlockEntities.OIL_CRUSHER, OilCrusherBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof OilCrusherBlockEntity crusher) {
            ItemStack heldStack = player.getStackInHand(hand);

            if (heldStack.isEmpty()) {
                // Take output
                ItemStack output = crusher.inventory.getStack(1);
                if (!output.isEmpty()) {
                    player.giveItemStack(output.copy());
                    crusher.inventory.setStack(1, ItemStack.EMPTY);
                    crusher.markDirty();
                    return ActionResult.SUCCESS;
                }
            } else {
                // Try to insert input
                ItemStack input = crusher.inventory.getStack(0);
                if (input.isEmpty()) {
                    ItemStack toInsert = heldStack.copy();
                    toInsert.setCount(1);
                    crusher.inventory.setStack(0, toInsert);
                    if (!player.isCreative()) {
                        heldStack.decrement(1);
                    }
                    crusher.markDirty();
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof OilCrusherBlockEntity crusher) {
                ItemScatterer.spawn(world, pos, crusher.inventory);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
