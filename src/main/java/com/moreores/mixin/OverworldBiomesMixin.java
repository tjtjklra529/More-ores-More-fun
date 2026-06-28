package com.moreores.mixin;

import com.moreores.world.biome.OverworldCaveBiomeData;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(targets = "net/minecraft/world/biome/source/MultiNoiseBiomeSourceParameterList$Preset$2")
public class OverworldBiomesMixin {

    @Inject(method = "apply", at = @At("RETURN"), cancellable = true)
    public <T> void apply(Function<RegistryKey<Biome>, T> mapper,
                          CallbackInfoReturnable<MultiNoiseUtil.Entries<T>> cir) {
        cir.setReturnValue(OverworldCaveBiomeData.withModdedBiomeEntries(cir.getReturnValue(), mapper));
    }
}
