package com.moreores;

import com.moreores.effect.FrozenEffect;
import com.moreores.item.joke.BarrierBoatItem;
import com.moreores.item.joke.DiamondMinecartItem;
import com.moreores.item.joke.GoldenAppleJuiceItem;
import com.moreores.material.MaterialRegistry;
import com.moreores.material.ModMaterials;
import com.moreores.registry.ModBlockEntities;
import com.moreores.registry.ModBlocks;
import com.moreores.screen.ModScreenHandlers;
import com.moreores.registry.ModEnchantments;
import com.moreores.registry.ModFluids;
import com.moreores.registry.ModItemGroups;
import com.moreores.registry.ModItems;
import com.moreores.world.biome.CaveBiomeFeatures;
import com.moreores.world.biome.CaveBiomeRegistry;
import com.moreores.world.gen.CraterGeneration;
import com.moreores.world.gen.ModOreGeneration;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreOres implements ModInitializer {

    public static final String MOD_ID = "moreores";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing More Ores, More Fun...");

        // 1. Register status effects first (needed by enchantments and mixin)
        FrozenEffect.initialize();

        // 2. Register fluids (OilFluid statics referenced by OilFluidBlock)
        ModFluids.initialize();

        // 3. Register the oil fluid block (depends on OilFluid.STILL being set)
        ModBlocks.initialize();

        // 3b. Register block entity types (depends on engine blocks being registered)
        ModBlockEntities.initialize();

        // 3c. Register screen handlers
        ModScreenHandlers.initialize();

        // 4. Register all material-driven items and blocks
        MaterialRegistry.registerAll(ModMaterials.ALL_MATERIALS);

        // 5. Register enchantments
        ModEnchantments.initialize();

        // 6. Register creative tab (depends on all items being registered)
        ModItemGroups.initialize();

        // 5b. Register joke items
        ModItems.register("diamond_minecart", new DiamondMinecartItem(new FabricItemSettings().maxCount(1)));
        ModItems.register("barrier_boat", new BarrierBoatItem(new FabricItemSettings().maxCount(1)));
        ModItems.register("golden_apple_juice", new GoldenAppleJuiceItem(new FabricItemSettings().maxCount(1)));

        // 7. Register world generation ore features
        ModOreGeneration.generateOres();

        // 8. Register cave biome keys (data-driven via worldgen JSON)
        CaveBiomeRegistry.register();

        // 9. Add bonus ore features to cave biomes via BiomeModifications
        CaveBiomeFeatures.register();

        // 10. Register crater generation
        CraterGeneration.register();

        LOGGER.info("More Ores, More Fun initialized successfully.");
    }
}
