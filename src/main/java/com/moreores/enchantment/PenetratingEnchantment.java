package com.moreores.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * Penetrating enchantment: deals bonus damage on hit.
 * Only applicable to daggers.
 * Level 1-5: each level adds 0.5 damage (half a heart).
 */
public class PenetratingEnchantment extends Enchantment {

    private static final TagKey<Item> DAGGERS_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("moreores", "daggers"));

    public PenetratingEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 20;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isIn(DAGGERS_TAG);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingTarget) {
            // Deal bonus damage: 0.5 per level
            float bonusDamage = level * 0.5f;
            livingTarget.damage(user.getDamageSources().mobAttack(user), bonusDamage);
        }
    }
}
