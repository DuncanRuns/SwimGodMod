package me.duncanruns.swimgod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "hasStatusEffect", cancellable = true, at = @At("HEAD"))
    private void alwaysDolphinMixin(StatusEffect effect, CallbackInfoReturnable<Boolean> info) {
        if (((Object) this) instanceof PlayerEntity && effect.equals(StatusEffects.DOLPHINS_GRACE) && ((PlayerEntity) (Object) this).isInSwimmingPose()) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "getNextAirUnderwater", cancellable = true, at = @At("HEAD"))
    private void nextAirMixin(int air, CallbackInfoReturnable<Integer> info) {
        if (((Object) this) instanceof PlayerEntity && !(((EntityAccessor) this).getField_25599() == FluidTags.WATER)) {
            info.setReturnValue(Math.min(((LivingEntity) (Object) this).getMaxAir(), air + 4));
        }
    }
}
