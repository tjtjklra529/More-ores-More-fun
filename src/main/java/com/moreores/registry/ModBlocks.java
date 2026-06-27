package com.moreores.registry;

import com.moreores.block.engine.*;
import com.moreores.fluid.OilFluid;
import com.moreores.fluid.OilFluidBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {

    public static final Map<String, Block> BLOCKS = new HashMap<>();

    // Oil fluid block
    public static OilFluidBlock OIL_FLUID_BLOCK;

    public static Block register(String name, Block block) {
        Block registered = Registry.register(Registries.BLOCK, new Identifier("moreores", name), block);
        BLOCKS.put(name, registered);
        // Register block item
        Registry.register(Registries.ITEM, new Identifier("moreores", name),
                new BlockItem(registered, new FabricItemSettings()));
        return registered;
    }

    public static Block registerNoItem(String name, Block block) {
        Block registered = Registry.register(Registries.BLOCK, new Identifier("moreores", name), block);
        BLOCKS.put(name, registered);
        return registered;
    }

    public static Block registerOreBlock(String name, float hardness, float resistance) {
        return register(name, new Block(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(BlockSoundGroup.STONE)));
    }

    public static Block registerStorageBlock(String name, float hardness, float resistance) {
        return register(name, new Block(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerStoneBlock(String name, float hardness, float resistance) {
        return register(name, new Block(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(BlockSoundGroup.STONE)));
    }

    public static SlabBlock registerSlab(String name, float hardness, float resistance, BlockSoundGroup sound) {
        SlabBlock slab = new SlabBlock(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(sound));
        register(name, slab);
        return slab;
    }

    public static StairsBlock registerStairs(String name, Block baseBlock, float hardness, float resistance, BlockSoundGroup sound) {
        StairsBlock stairs = new StairsBlock(baseBlock.getDefaultState(),
                FabricBlockSettings.create()
                        .strength(hardness, resistance)
                        .requiresTool()
                        .sounds(sound));
        register(name, stairs);
        return stairs;
    }

    public static WallBlock registerWall(String name, float hardness, float resistance, BlockSoundGroup sound) {
        WallBlock wall = new WallBlock(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(sound));
        register(name, wall);
        return wall;
    }

    public static void registerOilFluidBlock() {
        OIL_FLUID_BLOCK = new OilFluidBlock(OilFluid.STILL,
                FabricBlockSettings.create()
                        .strength(100f)
                        .dropsNothing()
                        .noCollision()
                        .liquid()
                        .replaceable());
        registerNoItem("oil_fluid", OIL_FLUID_BLOCK);
    }

    public static void registerEngineBlocks() {
        register("oil_engine", new OilEngineBlock(FabricBlockSettings.create()
                .strength(3.5f, 6f)
                .requiresTool()
                .sounds(BlockSoundGroup.METAL)));
        register("oil_furnace", new OilFurnaceBlock(FabricBlockSettings.create()
                .strength(3.5f, 6f)
                .requiresTool()
                .sounds(BlockSoundGroup.STONE)));
        register("oil_crusher", new OilCrusherBlock(FabricBlockSettings.create()
                .strength(3.5f, 6f)
                .requiresTool()
                .sounds(BlockSoundGroup.STONE)));
        register("refinery", new RefineryBlock(FabricBlockSettings.create()
                .strength(3.5f, 6f)
                .requiresTool()
                .sounds(BlockSoundGroup.METAL)));
        register("repair_station", new RepairStationBlock(FabricBlockSettings.create()
                .strength(3.5f, 6f)
                .requiresTool()
                .sounds(BlockSoundGroup.METAL)));
    }

    public static void initialize() {
        registerOilFluidBlock();
        registerEngineBlocks();
    }
}
