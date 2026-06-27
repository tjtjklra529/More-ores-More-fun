package com.moreores.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<OilCrusherScreenHandler> OIL_CRUSHER;
    public static ScreenHandlerType<RefineryScreenHandler> REFINERY;
    public static ScreenHandlerType<RepairStationScreenHandler> REPAIR_STATION;

    public static void initialize() {
        OIL_CRUSHER = ScreenHandlerRegistry.registerSimple(
                new Identifier("moreores", "oil_crusher"),
                OilCrusherScreenHandler::new
        );
        REFINERY = ScreenHandlerRegistry.registerSimple(
                new Identifier("moreores", "refinery"),
                RefineryScreenHandler::new
        );
        REPAIR_STATION = ScreenHandlerRegistry.registerSimple(
                new Identifier("moreores", "repair_station"),
                RepairStationScreenHandler::new
        );
    }
}
