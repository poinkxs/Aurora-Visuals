package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class InfoBlockHud extends HudModule {
    public InfoBlockHud() {
        super("aurora.module.infoblock", "aurora.module.infoblock.desc", 10, 150, 150, 30);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        String coords = String.format("XYZ: %d, %d, %d", (int)client.player.getX(), (int)client.player.getY(), (int)client.player.getZ());
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawText(client.textRenderer, coords, getX() + 8, getY() + 10, theme.getTextColor(), true);
    }
}
