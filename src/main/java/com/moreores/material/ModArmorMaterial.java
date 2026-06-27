package com.moreores.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Implements ArmorMaterial for a given OreMaterial.
 */
public class ModArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    private final OreMaterial material;
    private final Ingredient repairIngredient;

    public ModArmorMaterial(OreMaterial material, Ingredient repairIngredient) {
        this.material = material;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getEquipmentSlot().getEntitySlotId()] * material.getArmorDurabilityMultiplier();
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        int[] protection = material.getArmorProtection();
        return switch (type) {
            case HELMET -> protection[0];
            case CHESTPLATE -> protection[1];
            case LEGGINGS -> protection[2];
            case BOOTS -> protection[3];
        };
    }

    @Override
    public int getEnchantability() {
        return material.getToolEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public String getName() {
        return "moreores:" + material.getName();
    }

    @Override
    public float getToughness() {
        return material.getArmorToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return material.getArmorKnockbackRes();
    }
}
