package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CustomCrosshairHud extends HudModule {
    public CustomCrosshairHud() {
        super("aurora.module.crosshair", "aurora.module.crosshair.desc", 0, 0, 16, 16);
    }

    @Override
    public void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Theme theme = ThemeManager.getCurrentTheme();
        int cx = client.getWindow().getScaledWidth() / 2;
        int cy = client.getWindow().getScaledHeight() / 2;

        int gap = client.player.isSprinting() ? 4 : 2;
        int len = 4;
        int color = theme.getAccentStart();

        // Центральная точка
        context.fill(cx - 1, cy - 1, cx + 1, cy + 1, 0xFFFFFFFF);
        // Верхняя, нижняя, левая и правая линии
        context.fill(cx - 1, cy - gap - len, cx + 1, cy - gap, color);
        context.fill(cx - 1, cy + gap, cx + 1, cy + gap + len, color);
        context.fill(cx - gap - len, cy - 1, cx - gap, cy + 1, color);
        context.fill(cx + gap, cy - 1, cx + gap + len, cy + 1, color);
    }
}
