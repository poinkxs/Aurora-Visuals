package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ArmorAndCountersHud extends HudModule {
    public ArmorAndCountersHud() {
        super("aurora.module.armor", "aurora.module.armor.desc", 150, 350, 180, 24);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), theme.getPanelColor());
    }
}
