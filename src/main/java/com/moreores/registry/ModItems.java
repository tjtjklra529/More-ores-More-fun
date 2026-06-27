package com.moreores.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModItems {

    public static final Map<String, Item> ITEMS = new HashMap<>();

    public static Item register(String name, Item item) {
        Item registered = Registry.register(Registries.ITEM, new Identifier("moreores", name), item);
        ITEMS.put(name, registered);
        return registered;
    }

    public static Item registerSimple(String name) {
        return register(name, new Item(new FabricItemSettings()));
    }

    public static void initialize() {
        // Items are registered via MaterialRegistry
    }
}
