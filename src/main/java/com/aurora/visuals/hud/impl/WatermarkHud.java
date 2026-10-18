package com.aurora.visuals.hud.impl;

import com.aurora.visuals.AuroraVisualsClient;
import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class WatermarkHud extends HudModule {
    public WatermarkHud() {
        super("aurora.module.watermark", "aurora.module.watermark.desc", 10, 10, 140, 24);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();

        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
        context.drawBorder(getX(), getY(), getWidth(), getHeight(), theme.getAccentStart());
        
        String text = AuroraVisualsClient.MOD_NAME + " v" + AuroraVisualsClient.MOD_VERSION;
        context.drawText(client.textRenderer, text, getX() + 10, getY() + 8, theme.getTextColor(), true);
    }
                         }
