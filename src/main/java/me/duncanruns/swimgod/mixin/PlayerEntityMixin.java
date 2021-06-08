package me.duncanruns.swimgod.mixin;

import me.duncanruns.swimgod.SwimGod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    /**
     * @author DuncanRuns
     * @reason bye bye stinky fall damage
     */
    @Overwrite
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Inject(method = "getBlockBreakingSpeed", at = @At("HEAD"))
    private void isGettingBreakSpeedMixin(BlockState block, CallbackInfoReturnable<Float> info) {
        SwimGod.isGettingBreakSpeed = true;
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void forceSwimUpMixin(Vec3d movementInput, CallbackInfo info) {
        SwimGod.forceSwimUp = true;
    }
}
