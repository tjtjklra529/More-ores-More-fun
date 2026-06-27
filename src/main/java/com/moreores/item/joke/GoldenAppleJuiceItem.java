package com.moreores.item.joke;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

/**
 * Golden Apple Juice - drinkable item giving Absorption IV and Regeneration IV for 30 seconds each.
 */
public class GoldenAppleJuiceItem extends Item {

    public GoldenAppleJuiceItem(Settings settings) {
        super(settings);
    }

    @Override
    public net.minecraft.util.UseAction getUseAction(ItemStack stack) {
        return net.minecraft.util.UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // Absorption IV for 30 seconds (600 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 3));
            // Regeneration IV for 30 seconds (600 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 3));

            if (!player.isCreative()) {
                stack.decrement(1);
            }
        }
        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    @Override
    public net.minecraft.util.TypedActionResult<ItemStack> use(World world, PlayerEntity user, net.minecraft.util.Hand hand) {
        user.setCurrentHand(hand);
        return net.minecraft.util.TypedActionResult.consume(user.getStackInHand(hand));
    }
}
