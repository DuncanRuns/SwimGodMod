package me.duncanruns.swimgod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {
    @Inject(method = "renderUnderwaterOverlay", cancellable = true, at = @At("HEAD"))
    private static void cancelWaterOverlayMixin(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo info) {
        try {
            if (!((EntityAccessor) minecraftClient.player).getField_25599().equals(FluidTags.WATER)) {
                info.cancel();
            }
        } catch (NullPointerException ignored){
            info.cancel();
        }
    }
}
