package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class InventoryPreviewHud extends HudModule {
    public InventoryPreviewHud() {
        super("aurora.module.inventory", "aurora.module.inventory.desc", 350, 140, 140, 80);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawText(client.textRenderer, "Inventory", getX() + 8, getY() + 6, theme.getTextColor(), true);
    }
}
