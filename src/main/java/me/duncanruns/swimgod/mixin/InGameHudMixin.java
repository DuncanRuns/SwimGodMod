package me.duncanruns.swimgod.mixin;

import me.duncanruns.swimgod.SwimGod;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Inject(method="renderStatusBars",at=@At("HEAD"))
    private void isRenderingBubblesMixin(MatrixStack matrixStack, CallbackInfo info){
        SwimGod.isRenderingBubbles = true;
    }
}
