package me.duncanruns.swimgod.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.AmbientSoundLoops;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AmbientSoundLoops.Underwater.class)
public abstract class AmbientSoundLoopsMixin extends MovingSoundInstance {

    protected AmbientSoundLoopsMixin(SoundEvent soundEvent, SoundCategory soundCategory) {
        super(soundEvent, soundCategory);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void silenceWaterMixin(ClientPlayerEntity clientPlayerEntity, CallbackInfo info) {
        volume = 0.0f;
        repeat = false;
        looping = false;
    }
}
