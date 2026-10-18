package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeybindsHud extends HudModule {
    public KeybindsHud() {
        super("aurora.module.keybinds", "aurora.module.keybinds.desc", 350, 50, 130, 70);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawText(client.textRenderer, "Hot Keys", getX() + 8, getY() + 6, theme.getTextColor(), true);
        context.drawText(client.textRenderer, "HUD      M", getX() + 8, getY() + 26, theme.getTextSecondary(), true);
        context.drawText(client.textRenderer, "Zoom     C", getX() + 8, getY() + 44, theme.getTextSecondary(), true);
    }
}
