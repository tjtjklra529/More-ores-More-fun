package com.moreores.material;

import com.moreores.item.DaggerItem;
import com.moreores.registry.ModBlocks;
import com.moreores.registry.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

/**
 * Iterates all registered OreMaterials and registers all items/blocks
 * according to the OreBundle flags on each material.
 */
public class MaterialRegistry {

    public static void registerAll(OreMaterial[] materials) {
        for (OreMaterial material : materials) {
            register(material);
        }
    }

    private static void register(OreMaterial material) {
        String name = material.getName();
        float hardness = material.getHardness();
        float resistance = material.getResistance();

        // Determine sound group for storage/building blocks
        // Petrified wood and moon rock use stone sounds, metals use metal sounds
        boolean isStoneType = name.startsWith("petrified_") || name.equals("moon_rock");
        BlockSoundGroup primarySound = isStoneType ? BlockSoundGroup.STONE : BlockSoundGroup.METAL;

        // Main block (storage block / main material block)
        Block mainBlock = null;
        if (material.hasStorageBlock()) {
            if (isStoneType) {
                mainBlock = ModBlocks.registerStoneBlock(name, hardness, resistance);
            } else {
                mainBlock = ModBlocks.registerStorageBlock(name + "_block", hardness, resistance);
            }
        }

        // Ore blocks
        if (material.hasOre()) {
            ModBlocks.registerOreBlock(name + "_ore", hardness, resistance);
        }
        if (material.hasDeepslateOre()) {
            ModBlocks.registerOreBlock("deepslate_" + name + "_ore", hardness + 1.5f, resistance + 1.5f);
        }

        // Building shapes (use mainBlock as base for stairs)
        Block baseBlock = mainBlock != null ? mainBlock : new Block(FabricBlockSettings.create().strength(hardness, resistance));

        if (material.hasSlabs()) {
            ModBlocks.registerSlab(name + "_slab", hardness, resistance, primarySound);
        }
        if (material.hasStairs()) {
            ModBlocks.registerStairs(name + "_stairs", baseBlock, hardness, resistance, primarySound);
        }
        if (material.hasWalls()) {
            ModBlocks.registerWall(name + "_wall", hardness, resistance, primarySound);
        }
        if (material.hasDoor()) {
            ModBlocks.registerDoor(name + "_door", hardness, resistance, primarySound);
        }
        if (material.hasTrapdoor()) {
            ModBlocks.registerTrapdoor(name + "_trapdoor", hardness, resistance, primarySound);
        }
        if (material.hasButton()) {
            ModBlocks.registerButton(name + "_button", primarySound);
        }
        if (material.hasPressurePlate()) {
            ModBlocks.registerPressurePlate(name + "_pressure_plate", 0.5f, primarySound);
        }

        // Raw item (vanilla convention: raw_titanium, not titanium_raw)
        if (material.hasRawItem()) {
            ModItems.registerSimple("raw_" + name);
        }

        // Ingot or gem
        Item repairItem = null;
        if (material.hasIngot()) {
            repairItem = ModItems.registerSimple(name + "_ingot");
        } else if (material.hasGem()) {
            repairItem = ModItems.registerSimple(name);
        }

        // Tool and armor materials (built lazily using the repair item once registered)
        final Item finalRepairItem = repairItem;
        Ingredient repairIngredient = finalRepairItem != null
                ? Ingredient.ofItems(finalRepairItem)
                : Ingredient.EMPTY;

        ModToolMaterial toolMaterial = new ModToolMaterial(material, repairIngredient);
        ModArmorMaterial armorMaterial = new ModArmorMaterial(material, repairIngredient);

        // Tools
        if (material.hasTools()) {
            ModItems.register(name + "_pickaxe",
                    new PickaxeItem(toolMaterial, 1, -2.8f, new FabricItemSettings()));
            ModItems.register(name + "_axe",
                    new AxeItem(toolMaterial, 6.0f, -3.1f, new FabricItemSettings()));
            ModItems.register(name + "_shovel",
                    new ShovelItem(toolMaterial, 1.5f, -3.0f, new FabricItemSettings()));
            ModItems.register(name + "_hoe",
                    new HoeItem(toolMaterial, -2, 0.0f, new FabricItemSettings()));
        }

        // Sword
        if (material.hasSword()) {
            ModItems.register(name + "_sword",
                    new SwordItem(toolMaterial, 3, -2.4f, new FabricItemSettings()));
        }

        // Dagger
        if (material.hasDagger()) {
            ModItems.register(name + "_dagger",
                    new DaggerItem(toolMaterial, 1, -2.0f, new FabricItemSettings()));
        }

        // Armor
        if (material.hasArmor()) {
            ModItems.register(name + "_helmet",
                    new ArmorItem(armorMaterial, ArmorItem.Type.HELMET, new FabricItemSettings()));
            ModItems.register(name + "_chestplate",
                    new ArmorItem(armorMaterial, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
            ModItems.register(name + "_leggings",
                    new ArmorItem(armorMaterial, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
            ModItems.register(name + "_boots",
                    new ArmorItem(armorMaterial, ArmorItem.Type.BOOTS, new FabricItemSettings()));
        }
    }
}
