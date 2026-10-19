package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.Collection;

public class PotionsHud extends HudModule {
    public PotionsHud() {
        super("aurora.module.potions", "aurora.module.potions.desc", 10, 140, 120, 50);
    }

    @Override
    public void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Theme theme = ThemeManager.getCurrentTheme();
        Collection<StatusEffectInstance> effects = client.player.getStatusEffects();

        int height = Math.max(28, 20 + effects.size() * 12);
        int x = getX();
        int y = getY();

        context.fill(x, y, x + getWidth(), y + height, theme.getPanelColor());
        context.drawBorder(x, y, getWidth(), height, theme.getAccentStart());
        context.drawText(client.textRenderer, "Potions", x + 6, y + 6, theme.getAccentStart(), true);

        if (effects.isEmpty()) {
            context.drawText(client.textRenderer, "None", x + 6, y + 18, 0xFF888888, true);
            return;
        }

        int offsetY = 18;
        for (StatusEffectInstance effect : effects) {
            String name = effect.getEffectType().value().getName().getString();
            int amp = effect.getAmplifier() + 1;
            int sec = effect.getDuration() / 20;
            String line = name + " " + amp + " (" + (sec / 60) + ":" + String.format("%02d", sec % 60) + ")";
            context.drawText(client.textRenderer, line, x + 6, y + offsetY, theme.getTextColor(), true);
            offsetY += 12;
        }
    }
}
