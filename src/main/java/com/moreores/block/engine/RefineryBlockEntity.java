package com.moreores.block.engine;

import com.moreores.registry.ModBlockEntities;
import com.moreores.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Converts oil buckets → refined fuel (4 refined fuel per bucket).
 * Requires adjacent active oil engine.
 * Processing time: 80 ticks per bucket.
 */
public class RefineryBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public static final int PROCESSING_TICKS = 80;

    public SimpleInventory inventory = new SimpleInventory(2); // slot 0 = oil bucket input, slot 1 = refined fuel output
    private int progress = 0;

    public RefineryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINERY, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.moreores.refinery");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new com.moreores.screen.RefineryScreenHandler(syncId, playerInventory, this.inventory);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RefineryBlockEntity be) {
        if (world.isClient) return;

        if (!isAdjacentEngineActive(world, pos)) {
            be.progress = 0;
            return;
        }

        ItemStack input = be.inventory.getStack(0);
        Item oilBucket = ModItems.ITEMS.get("oil_bucket");
        if (input.isEmpty() || input.getItem() != oilBucket) {
            be.progress = 0;
            return;
        }

        Item refinedFuel = ModItems.ITEMS.get("refined_fuel");
        if (refinedFuel == null) {
            be.progress = 0;
            return;
        }

        ItemStack outputStack = be.inventory.getStack(1);
        boolean canOutput = outputStack.isEmpty() ||
                (outputStack.getItem() == refinedFuel && outputStack.getCount() + 4 <= outputStack.getMaxCount());

        if (!canOutput) {
            be.progress = 0;
            return;
        }

        be.progress++;
        if (be.progress >= PROCESSING_TICKS) {
            be.progress = 0;
            // Consume the oil bucket, return empty bucket
            input.decrement(1);
            be.inventory.getStack(0); // may now be empty
            // Try to give empty bucket back — just drop it
            if (outputStack.isEmpty()) {
                be.inventory.setStack(1, new ItemStack(refinedFuel, 4));
            } else {
                outputStack.increment(4);
            }
            be.markDirty();
        }
    }

    public static boolean isAdjacentEngineActive(World world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.offset(dir);
            if (world.getBlockState(neighborPos).getBlock() instanceof OilEngineBlock) {
                BlockEntity be = world.getBlockEntity(neighborPos);
                if (be instanceof OilEngineBlockEntity engine) {
                    return engine.isActive();
                }
            }
        }
        return false;
    }

    public int getProgress() { return progress; }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("Progress");
        NbtCompound invNbt = nbt.getCompound("Inventory");
        inventory.setStack(0, ItemStack.fromNbt(invNbt.getCompound("Input")));
        inventory.setStack(1, ItemStack.fromNbt(invNbt.getCompound("Output")));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Progress", progress);
        NbtCompound invNbt = new NbtCompound();
        invNbt.put("Input", inventory.getStack(0).writeNbt(new NbtCompound()));
        invNbt.put("Output", inventory.getStack(1).writeNbt(new NbtCompound()));
        nbt.put("Inventory", invNbt);
    }
}
