package me.duncanruns.swimgod.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method = "getDepthStrider", cancellable = true, at = @At("HEAD"))
    private static void alwaysDepthStriderMixin(LivingEntity entity, CallbackInfoReturnable<Integer> info) {
        if (entity instanceof PlayerEntity) {
            info.setReturnValue(3);
        }
    }
}
