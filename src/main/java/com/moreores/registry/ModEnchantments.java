package com.moreores.registry;

import com.moreores.enchantment.FreezeEnchantment;
import com.moreores.enchantment.PenetratingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final FreezeEnchantment FREEZE = new FreezeEnchantment();
    public static final PenetratingEnchantment PENETRATING = new PenetratingEnchantment();

    public static void initialize() {
        Registry.register(Registries.ENCHANTMENT, new Identifier("moreores", "freeze"), FREEZE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("moreores", "penetrating"), PENETRATING);
    }
}
