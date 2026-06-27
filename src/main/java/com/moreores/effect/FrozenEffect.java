package com.moreores.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * The Frozen status effect. While active, movement is suppressed via FreezeMixin.
 * This effect is applied by the Freeze enchantment on daggers.
 */
public class FrozenEffect extends StatusEffect {

    public static FrozenEffect FROZEN;
    public static StatusEffect FREEZE_IMMUNITY;

    public FrozenEffect() {
        super(StatusEffectCategory.HARMFUL, 0x88CCFF);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Movement cancellation is handled by FreezeMixin
        // Zero out velocity every tick to ensure the entity stays still
        entity.setVelocity(0, Math.min(entity.getVelocity().y, 0), 0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Apply every tick
        return true;
    }

    public static void initialize() {
        FROZEN = new FrozenEffect();
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreores", "frozen"), FROZEN);

        FREEZE_IMMUNITY = new StatusEffect(StatusEffectCategory.BENEFICIAL, 0xAAFFFF) {
            @Override
            public boolean canApplyUpdateEffect(int duration, int amplifier) {
                return false;
            }
        };
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreores", "freeze_immunity"), FREEZE_IMMUNITY);
    }
}
