package com.aurora.visuals.mixin;

import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HeldItemRenderer.class)
public class MixinHeldItemRenderer {
    // Заглушка под трансформации рук без аллокаций
}
