package com.moreores;

import com.moreores.effect.FrozenEffect;
import com.moreores.material.MaterialRegistry;
import com.moreores.material.ModMaterials;
import com.moreores.registry.ModBlocks;
import com.moreores.registry.ModEnchantments;
import com.moreores.registry.ModFluids;
import com.moreores.registry.ModItemGroups;
import com.moreores.registry.ModItems;
import com.moreores.world.biome.CaveBiomeFeatures;
import com.moreores.world.biome.CaveBiomeRegistry;
import com.moreores.world.gen.ModOreGeneration;
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

        // 4. Register all material-driven items and blocks
        MaterialRegistry.registerAll(ModMaterials.ALL_MATERIALS);

        // 5. Register enchantments
        ModEnchantments.initialize();

        // 6. Register creative tab (depends on all items being registered)
        ModItemGroups.initialize();

        // 7. Register world generation ore features
        ModOreGeneration.generateOres();

        // 8. Register cave biome keys (data-driven via worldgen JSON)
        CaveBiomeRegistry.register();

        // 9. Add bonus ore features to cave biomes via BiomeModifications
        CaveBiomeFeatures.register();

        LOGGER.info("More Ores, More Fun initialized successfully.");
    }
}
