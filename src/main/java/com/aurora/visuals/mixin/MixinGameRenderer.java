package com.aurora.visuals.mixin;

import com.aurora.visuals.AuroraVisualsClient;
import com.aurora.visuals.ZoomModule;
import com.aurora.visuals.module.Module;
import com.aurora.visuals.module.ModuleManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        boolean keyZoom = AuroraVisualsClient.zoomKey != null && AuroraVisualsClient.zoomKey.isPressed();
        Module zoomMod = ModuleManager.getModule(ZoomModule.class);
        boolean modZoom = zoomMod != null && zoomMod.isEnabled();

        if (keyZoom || modZoom) {
            cir.setReturnValue(cir.getReturnValue() * 0.45f);
        }
    }
}
