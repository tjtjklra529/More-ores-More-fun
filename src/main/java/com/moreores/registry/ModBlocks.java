package com.moreores.registry;

import com.moreores.block.GemLampBlock;
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

    public static DoorBlock registerDoor(String name, float hardness, float resistance, BlockSoundGroup sound) {
        DoorBlock door = new DoorBlock(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(sound)
                .nonOpaque(),
                BlockSetType.IRON);
        register(name, door);
        return door;
    }

    public static TrapdoorBlock registerTrapdoor(String name, float hardness, float resistance, BlockSoundGroup sound) {
        TrapdoorBlock trapdoor = new TrapdoorBlock(FabricBlockSettings.create()
                .strength(hardness, resistance)
                .requiresTool()
                .sounds(sound)
                .nonOpaque(),
                BlockSetType.IRON);
        register(name, trapdoor);
        return trapdoor;
    }

    public static ButtonBlock registerButton(String name, BlockSoundGroup sound) {
        ButtonBlock button = new ButtonBlock(FabricBlockSettings.create()
                .strength(0.5f)
                .noCollision()
                .sounds(sound),
                BlockSetType.STONE,
                20,
                false);
        register(name, button);
        return button;
    }

    public static PressurePlateBlock registerPressurePlate(String name, float hardness, BlockSoundGroup sound) {
        PressurePlateBlock plate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS,
                FabricBlockSettings.create()
                        .strength(hardness)
                        .requiresTool()
                        .noCollision()
                        .sounds(sound),
                BlockSetType.STONE);
        register(name, plate);
        return plate;
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

    // gem name -> light level
    private static final Object[][] GEM_LAMPS = {
        {"ruby",         14}, {"sapphire",    11}, {"topaz",       13},
        {"amber",        15}, {"jade",         9}, {"opal",        12},
        {"garnet",       11}, {"peridot",      10}, {"aquamarine", 12},
        {"tourmaline",   10}, {"citrine",      14}, {"sulfur",      8},
        {"cinnabar",     10}, {"fluorite",     13}, {"malachite",  9},
        {"alexandrite",  12}, {"tanzanite",    11}, {"onyx",        6},
        {"zircon",       11}, {"rhodonite",    10}, {"morganite",  12},
        {"moldavite",    13},
    };

    public static void registerGemLamps() {
        for (Object[] entry : GEM_LAMPS) {
            String gem = (String) entry[0];
            int light = (int) entry[1];
            register(gem + "_lamp", new GemLampBlock(
                    FabricBlockSettings.create()
                            .strength(1.5f, 3f)
                            .luminance(state -> light)
                            .sounds(BlockSoundGroup.GLASS)));
        }
    }

    public static void initialize() {
        registerOilFluidBlock();
        registerEngineBlocks();
        registerGemLamps();
    }
}
