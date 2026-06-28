package com.moreores.client;

import com.moreores.fluid.OilFluid;
import com.moreores.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class MoreOresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Oil fluid renderer — dark brown
        FluidRenderHandlerRegistry.INSTANCE.register(OilFluid.STILL, OilFluid.FLOWING,
                new SimpleFluidRenderHandler(
                        new Identifier("moreores", "block/oil_still"),
                        new Identifier("moreores", "block/oil_flowing"),
                        0x3A2000
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                OilFluid.STILL, OilFluid.FLOWING);

        // Machine GUI screens
        HandledScreens.register(ModScreenHandlers.OIL_CRUSHER, OilCrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.REFINERY, RefineryScreen::new);
        HandledScreens.register(ModScreenHandlers.REPAIR_STATION, RepairStationScreen::new);
    }
}
