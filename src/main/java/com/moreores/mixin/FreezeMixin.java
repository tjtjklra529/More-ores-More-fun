package com.moreores.mixin;

import com.moreores.effect.FrozenEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin on LivingEntity to prevent movement while the Frozen effect is active.
 * Injects into travel() to zero out velocity and into jump() to cancel jumping.
 */
@Mixin(LivingEntity.class)
public abstract class FreezeMixin {

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void onTravel(Vec3d movementInput, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (FrozenEffect.FROZEN != null && self.hasStatusEffect(FrozenEffect.FROZEN)) {
            // Allow gravity but cancel horizontal movement
            double currentY = self.getVelocity().y;
            self.setVelocity(0, Math.min(currentY, 0), 0);
            ci.cancel();
        }
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void onJump(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (FrozenEffect.FROZEN != null && self.hasStatusEffect(FrozenEffect.FROZEN)) {
            ci.cancel();
        }
    }
}
