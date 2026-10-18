package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.module.Module;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ArrayListHud extends HudModule {
    public ArrayListHud() {
        super("aurora.module.arraylist", "aurora.module.arraylist.desc", 10, 40, 120, 100);
    }

    @Override
    public void render(DrawContext context, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();
        MinecraftClient client = MinecraftClient.getInstance();
        int currentY = getY();

        for (Module m : ModuleManager.getModules()) {
            if (!m.isEnabled()) continue;
            String name = m.getNameKey();
            context.fill(getX(), currentY, getX() + 110, currentY + 16, theme.getPanelColor());
            context.drawText(client.textRenderer, name, getX() + 6, currentY + 4, theme.getTextColor(), true);
            currentY += 18;
        }
    }
}
