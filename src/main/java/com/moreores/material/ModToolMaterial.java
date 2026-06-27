package com.moreores.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.BlockTags;

/**
 * Implements ToolMaterial for a given OreMaterial.
 */
public class ModToolMaterial implements ToolMaterial {

    private final OreMaterial material;
    private final Ingredient repairIngredient;

    public ModToolMaterial(OreMaterial material, Ingredient repairIngredient) {
        this.material = material;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return material.getToolDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return material.getToolMiningSpeed();
    }

    @Override
    public float getAttackDamage() {
        return material.getToolAttackDamage();
    }

    @Override
    public int getMiningLevel() {
        return material.getMiningLevel();
    }

    @Override
    public int getEnchantability() {
        return material.getToolEnchantability();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
