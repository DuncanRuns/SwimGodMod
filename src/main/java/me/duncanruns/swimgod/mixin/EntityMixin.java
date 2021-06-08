package me.duncanruns.swimgod.mixin;

import me.duncanruns.swimgod.SwimGod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    protected boolean touchingWater;

    @Shadow
    protected abstract boolean isBeingRainedOn();

    @Shadow
    protected abstract boolean isInsideBubbleColumn();

    @Inject(method = "isTouchingWater", cancellable = true, at = @At("HEAD"))
    private void alwaysInWaterAMixin(CallbackInfoReturnable<Boolean> info) {
        if (((Object) this) instanceof PlayerEntity) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "isSubmergedInWater", cancellable = true, at = @At("HEAD"))
    private void alwaysInWaterBMixin(CallbackInfoReturnable<Boolean> info) {
        if (((Object) this) instanceof PlayerEntity) {
            if (SwimGod.isPlayingTickable) {
                SwimGod.isPlayingTickable = false;
            } else {
                info.setReturnValue(true);
            }
        }
    }

    @Inject(method = "isSubmergedIn", cancellable = true, at = @At("HEAD"))
    private void alwaysinWaterCMixin(Tag<Fluid> tag, CallbackInfoReturnable<Boolean> info) {
        if (((Object) this) instanceof PlayerEntity && tag.equals(FluidTags.WATER)) {
            if (SwimGod.isGettingBreakSpeed || SwimGod.isRenderingBubbles) {
                SwimGod.isGettingBreakSpeed = false;
                SwimGod.isRenderingBubbles = false;
            } else {
                info.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getFluidHeight", cancellable = true, at = @At("HEAD"))
    private void alwaysInWaterDMixin(Tag<Fluid> fluid, CallbackInfoReturnable<Double> info) {
        if (((Object) this) instanceof PlayerEntity && fluid.equals(FluidTags.WATER)) {
            info.setReturnValue(1000.0D);
        }
    }

    @Inject(method = "isWet", cancellable = true, at = @At("HEAD"))
    private void notWetOutsideWater(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(touchingWater || isBeingRainedOn() || isInsideBubbleColumn());
    }
}
