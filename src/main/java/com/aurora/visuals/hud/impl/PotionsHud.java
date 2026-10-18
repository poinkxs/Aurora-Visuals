package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PotionsHud extends HudModule {
    public PotionsHud() {
        super("aurora.module.potions", "aurora.module.potions.desc", 10, 190, 130, 50);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawText(client.textRenderer, "Potions", getX() + 8, getY() + 6, theme.getTextColor(), true);
        context.drawText(client.textRenderer, "Strength II  oo", getX() + 8, getY() + 26, theme.getTextSecondary(), true);
    }
}
