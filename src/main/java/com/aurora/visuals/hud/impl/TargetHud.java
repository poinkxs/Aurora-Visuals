package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class TargetHud extends HudModule {
    public TargetHud() {
        super("aurora.module.targethud", "aurora.module.targethud.desc", 200, 200, 128, 44);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();

        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawBorder(getX(), getY(), getWidth(), getHeight(), theme.getAccentStart());
        context.drawText(client.textRenderer, "Target: Player", getX() + 8, getY() + 8, theme.getTextColor(), true);
        context.fill(getX() + 8, getY() + 28, getX() + 120, getY() + 34, 0xFF1A1A28);
        context.fill(getX() + 8, getY() + 28, getX() + 90, getY() + 34, theme.getAccentStart());
    }
}
