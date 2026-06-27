package com.moreores.registry;

import com.moreores.block.engine.*;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static BlockEntityType<OilEngineBlockEntity> OIL_ENGINE;
    public static BlockEntityType<OilFurnaceBlockEntity> OIL_FURNACE;
    public static BlockEntityType<OilCrusherBlockEntity> OIL_CRUSHER;
    public static BlockEntityType<RefineryBlockEntity> REFINERY;
    public static BlockEntityType<RepairStationBlockEntity> REPAIR_STATION;

    public static void initialize() {
        OIL_ENGINE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("moreores", "oil_engine"),
                FabricBlockEntityTypeBuilder.create(OilEngineBlockEntity::new,
                        (net.minecraft.block.Block) ModBlocks.BLOCKS.get("oil_engine")).build()
        );

        OIL_FURNACE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("moreores", "oil_furnace"),
                FabricBlockEntityTypeBuilder.create(OilFurnaceBlockEntity::new,
                        (net.minecraft.block.Block) ModBlocks.BLOCKS.get("oil_furnace")).build()
        );

        OIL_CRUSHER = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("moreores", "oil_crusher"),
                FabricBlockEntityTypeBuilder.create(OilCrusherBlockEntity::new,
                        (net.minecraft.block.Block) ModBlocks.BLOCKS.get("oil_crusher")).build()
        );

        REFINERY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("moreores", "refinery"),
                FabricBlockEntityTypeBuilder.create(RefineryBlockEntity::new,
                        (net.minecraft.block.Block) ModBlocks.BLOCKS.get("refinery")).build()
        );

        REPAIR_STATION = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("moreores", "repair_station"),
                FabricBlockEntityTypeBuilder.create(RepairStationBlockEntity::new,
                        (net.minecraft.block.Block) ModBlocks.BLOCKS.get("repair_station")).build()
        );
    }
}
