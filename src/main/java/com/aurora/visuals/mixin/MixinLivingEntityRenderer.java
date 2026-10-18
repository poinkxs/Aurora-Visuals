package com.aurora.visuals.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityRenderer.class)
public class MixinLivingEntityRenderer {
    // Кастомизация цвета получения урона сущностями
}
