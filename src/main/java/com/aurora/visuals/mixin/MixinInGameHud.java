package com.aurora.visuals.mixin;

import com.aurora.visuals.hud.impl.CustomCrosshairHud;
import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ModuleManager.renderHudModules(context, tickCounter.getTickDelta(false));
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void onRenderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        for (HudModule hm : ModuleManager.getHudModules()) {
            if (hm instanceof CustomCrosshairHud && hm.isEnabled()) {
                ci.cancel();
                return;
            }
        }
    }
}
