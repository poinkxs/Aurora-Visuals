package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CooldownsHud extends HudModule {
    public CooldownsHud() {
        super("aurora.module.cooldowns", "aurora.module.cooldowns.desc", 250, 260, 140, 36);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawText(client.textRenderer, "Cooldowns", getX() + 8, getY() + 12, theme.getTextColor(), true);
    }
}
