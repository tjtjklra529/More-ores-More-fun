package com.moreores.item.joke;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

/**
 * Barrier Boat - a creative-mode joke boat.
 * Spawns a regular oak boat with a custom name as a humorous item.
 */
public class BarrierBoatItem extends Item {

    public BarrierBoatItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            HitResult hit = user.raycast(5.0, 0f, true);
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            if (hit instanceof BlockHitResult blockHit) {
                BlockPos pos = blockHit.getBlockPos();
                x = pos.getX() + 0.5;
                y = pos.getY() + 1.0;
                z = pos.getZ() + 0.5;
            }

            BoatEntity boat = new BoatEntity(world, x, y, z);
            boat.setCustomName(Text.translatable("item.moreores.barrier_boat"));
            boat.setCustomNameVisible(true);

            world.spawnEntity(boat);
            if (!user.isCreative()) {
                stack.decrement(1);
            }
        }

        return TypedActionResult.success(stack);
    }
}
