package me.duncanruns.swimgod.mixin;

import me.duncanruns.swimgod.SwimGod;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidState.class)
public abstract class FluidStateMixin {
    @Inject(method="isEmpty",at=@At("HEAD"),cancellable = true)
    private void forceSwimUpMixin(CallbackInfoReturnable<Boolean> info){
        if(SwimGod.forceSwimUp){
            SwimGod.forceSwimUp = false;
            info.setReturnValue(false);
        }
    }
}
