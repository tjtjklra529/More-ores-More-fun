package com.moreores.registry;

import com.moreores.fluid.OilFluid;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {

    public static void initialize() {
        Registry.register(Registries.FLUID, new Identifier("moreores", "oil_still"), OilFluid.STILL);
        Registry.register(Registries.FLUID, new Identifier("moreores", "oil_flowing"), OilFluid.FLOWING);

        // Register oil bucket
        BucketItem oilBucket = new BucketItem(OilFluid.STILL, new FabricItemSettings()
                .recipeRemainder(Items.BUCKET)
                .maxCount(1));
        Registry.register(Registries.ITEM, new Identifier("moreores", "oil_bucket"), oilBucket);
        ModItems.ITEMS.put("oil_bucket", oilBucket);
    }
}
