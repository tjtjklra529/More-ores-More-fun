package com.moreores.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import java.util.UUID;

/**
 * A dagger: faster than a sword but deals less damage.
 * Attack speed modifier: -2.0 (versus sword's -2.4 means daggers attack faster).
 */
public class DaggerItem extends SwordItem {

    private static final UUID DAGGER_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    private static final UUID DAGGER_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    private final float attackDamageBonus;
    private final float attackSpeed;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public DaggerItem(ToolMaterial toolMaterial, int attackDamageBonus, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamageBonus, attackSpeed, settings);
        this.attackDamageBonus = attackDamageBonus;
        this.attackSpeed = attackSpeed;

        float totalDamage = toolMaterial.getAttackDamage() + attackDamageBonus;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(DAGGER_ATTACK_DAMAGE_UUID,
                        "Weapon modifier", totalDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(DAGGER_ATTACK_SPEED_UUID,
                        "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
