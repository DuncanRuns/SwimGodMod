package me.duncanruns.swimgod.mixin;

import me.duncanruns.swimgod.SwimGod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.AmbientSoundPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AmbientSoundPlayer.class)
public abstract class AmbientSoundPlayerMixin {

    @Shadow
    @Final
    private ClientPlayerEntity player;

    @Shadow private int ticksUntilPlay;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void isPlayingWaterSoundMixin(CallbackInfo info) {
        info.cancel();
    }
}
