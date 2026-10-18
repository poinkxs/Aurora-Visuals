package com.aurora.visuals.mixin;

import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.module.impl.FullbrightModule;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    private void modifyLightmap(Args args) {
        if (FullbrightModule.isActive()) {
            // Выкручиваем яркость и освещение на максимум по всей карте
            args.set(2, 0xFFFFFFFF);
        }
    }
}
