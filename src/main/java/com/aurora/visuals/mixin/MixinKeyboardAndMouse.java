package com.aurora.visuals.mixin;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Keyboard.class)
public class MixinKeyboardAndMouse {
    // Перехват клавиш для биндов и GUI
}
