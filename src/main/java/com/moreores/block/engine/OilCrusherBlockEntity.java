package com.moreores.block.engine;

import com.moreores.registry.ModBlockEntities;
import com.moreores.screen.OilCrusherScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class OilCrusherBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public static final int PROCESSING_TICKS = 100;

    // Maps raw ore item name -> output ingot item name
    private static final Map<String, String> CRUSH_RECIPES = new HashMap<>();

    static {
        CRUSH_RECIPES.put("moreores:raw_titanium", "moreores:titanium_ingot");
        CRUSH_RECIPES.put("moreores:raw_platinum", "moreores:platinum_ingot");
        CRUSH_RECIPES.put("moreores:raw_nickel", "moreores:nickel_ingot");
        CRUSH_RECIPES.put("moreores:raw_aluminum", "moreores:aluminum_ingot");
        CRUSH_RECIPES.put("moreores:raw_silver", "moreores:silver_ingot");
        CRUSH_RECIPES.put("moreores:raw_tin", "moreores:tin_ingot");
        // Vanilla raws
        CRUSH_RECIPES.put("minecraft:raw_iron", "minecraft:iron_ingot");
        CRUSH_RECIPES.put("minecraft:raw_gold", "minecraft:gold_ingot");
        CRUSH_RECIPES.put("minecraft:raw_copper", "minecraft:copper_ingot");
    }

    public SimpleInventory inventory = new SimpleInventory(2); // slot 0 = input, slot 1 = output
    private int progress = 0;

    public OilCrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OIL_CRUSHER, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.moreores.oil_crusher");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new OilCrusherScreenHandler(syncId, playerInventory, this.inventory);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OilCrusherBlockEntity be) {
        if (world.isClient) return;

        if (!isAdjacentEngineActive(world, pos)) {
            be.progress = 0;
            return;
        }

        ItemStack input = be.inventory.getStack(0);
        if (input.isEmpty()) {
            be.progress = 0;
            return;
        }

        String inputId = Registries.ITEM.getId(input.getItem()).toString();
        String outputId = CRUSH_RECIPES.get(inputId);
        if (outputId == null) {
            be.progress = 0;
            return;
        }

        Item outputItem = Registries.ITEM.get(new Identifier(outputId));
        ItemStack outputStack = be.inventory.getStack(1);

        // Check output slot can accept 2 more items
        boolean canOutput = outputStack.isEmpty() ||
                (outputStack.getItem() == outputItem && outputStack.getCount() + 2 <= outputStack.getMaxCount());

        if (!canOutput) {
            be.progress = 0;
            return;
        }

        be.progress++;

        if (be.progress >= PROCESSING_TICKS) {
            be.progress = 0;
            input.decrement(1);

            // Output 2 ingots
            if (outputStack.isEmpty()) {
                be.inventory.setStack(1, new ItemStack(outputItem, 2));
            } else {
                outputStack.increment(2);
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

    public int getProgress() {
        return progress;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("Progress");
        NbtCompound invNbt = nbt.getCompound("Inventory");
        ItemStack input = ItemStack.fromNbt(invNbt.getCompound("Input"));
        ItemStack output = ItemStack.fromNbt(invNbt.getCompound("Output"));
        inventory.setStack(0, input);
        inventory.setStack(1, output);
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
