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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Repairs tools/armor using refined fuel. Requires adjacent active oil engine.
 * slot 0 = damaged item, slot 1 = refined_fuel, output = slot 0 repaired.
 * Each refined_fuel repairs 25% max durability per 40-tick cycle.
 */
public class RepairStationBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public static final int PROCESSING_TICKS = 40;
    private static final int REPAIR_PERCENT = 25; // percent of max durability per fuel

    public SimpleInventory inventory = new SimpleInventory(2); // slot 0 = tool, slot 1 = refined fuel
    private int progress = 0;

    public RepairStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REPAIR_STATION, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.moreores.repair_station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new com.moreores.screen.RepairStationScreenHandler(syncId, playerInventory, this.inventory);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RepairStationBlockEntity be) {
        if (world.isClient) return;

        if (!isAdjacentEngineActive(world, pos)) {
            be.progress = 0;
            return;
        }

        ItemStack toolStack = be.inventory.getStack(0);
        ItemStack fuelStack = be.inventory.getStack(1);

        Item refinedFuel = ModItems.ITEMS.get("refined_fuel");
        if (toolStack.isEmpty() || fuelStack.isEmpty() || refinedFuel == null
                || fuelStack.getItem() != refinedFuel) {
            be.progress = 0;
            return;
        }

        // Only repair damageable items that are actually damaged
        if (!toolStack.isDamageable() || toolStack.getDamage() == 0) {
            be.progress = 0;
            return;
        }

        be.progress++;
        if (be.progress >= PROCESSING_TICKS) {
            be.progress = 0;
            int maxDur = toolStack.getMaxDamage();
            int repairAmount = Math.max(1, maxDur * REPAIR_PERCENT / 100);
            int newDamage = Math.max(0, toolStack.getDamage() - repairAmount);
            toolStack.setDamage(newDamage);
            fuelStack.decrement(1);
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
        inventory.setStack(0, ItemStack.fromNbt(invNbt.getCompound("Tool")));
        inventory.setStack(1, ItemStack.fromNbt(invNbt.getCompound("Fuel")));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Progress", progress);
        NbtCompound invNbt = new NbtCompound();
        invNbt.put("Tool", inventory.getStack(0).writeNbt(new NbtCompound()));
        invNbt.put("Fuel", inventory.getStack(1).writeNbt(new NbtCompound()));
        nbt.put("Inventory", invNbt);
    }
}
