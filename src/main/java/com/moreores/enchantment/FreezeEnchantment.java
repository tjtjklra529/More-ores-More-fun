package com.moreores.enchantment;

import com.moreores.effect.FrozenEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * Freeze enchantment: applies FrozenEffect on hit.
 * Only applicable to daggers (items with tag moreores:daggers).
 * Level 1-3; each level increases freeze duration by 1 second.
 */
public class FreezeEnchantment extends Enchantment {

    private static final TagKey<Item> DAGGERS_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("moreores", "daggers"));

    public FreezeEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinPower(int level) {
        return 15 + (level - 1) * 9;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 50;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isIn(DAGGERS_TAG);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingTarget) {
            // Don't apply freeze if target has freeze immunity
            if (livingTarget.hasStatusEffect(FrozenEffect.FREEZE_IMMUNITY)) {
                return;
            }
            // Apply frozen effect: level seconds (20 ticks per second)
            int freezeDuration = level * 20;
            livingTarget.addStatusEffect(new StatusEffectInstance(FrozenEffect.FROZEN, freezeDuration, level - 1));

            // Apply freeze immunity for 8.5 seconds to prevent re-freezing immediately
            livingTarget.addStatusEffect(new StatusEffectInstance(FrozenEffect.FREEZE_IMMUNITY, 170, 0, false, false, false));
        }
    }
}
