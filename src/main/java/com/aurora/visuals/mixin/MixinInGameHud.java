package com.aurora.visuals.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    // Интеграция с нативным HUD через Fabric API
}
