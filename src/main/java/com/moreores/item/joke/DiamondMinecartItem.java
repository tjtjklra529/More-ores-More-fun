package com.moreores.item.joke;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Diamond Minecart - a special minecart that displays a diamond block.
 * Creative joke item - place on rail to spawn a minecart with diamond block display.
 */
public class DiamondMinecartItem extends Item {

    public DiamondMinecartItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (!(state.getBlock() instanceof AbstractRailBlock)) {
            return ActionResult.FAIL;
        }

        if (!world.isClient) {
            MinecartEntity minecart = new MinecartEntity(world, pos.getX() + 0.5, pos.getY() + 0.0625, pos.getZ() + 0.5);

            // Set custom display block (diamond block) via NBT
            NbtCompound nbt = new NbtCompound();
            minecart.writeNbt(nbt);
            // DisplayTile stores the block state ID for the displayed block
            nbt.putInt("DisplayTile", net.minecraft.registry.Registries.BLOCK.getRawId(
                    net.minecraft.block.Blocks.DIAMOND_BLOCK));
            nbt.putInt("DisplayOffset", 6);
            minecart.readNbt(nbt);

            minecart.setCustomName(net.minecraft.text.Text.translatable("item.moreores.diamond_minecart"));

            world.spawnEntity(minecart);
            if (!context.getPlayer().isCreative()) {
                context.getStack().decrement(1);
            }
        }

        return ActionResult.SUCCESS;
    }
}
