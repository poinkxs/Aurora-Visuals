package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.screen.HudEditorScreen;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;

public class TargetHud extends HudModule {
    public TargetHud() {
        super("aurora.module.targethud", "aurora.module.targethud.desc", 260, 200, 136, 46);
    }

    @Override
    public void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        LivingEntity target = null;
        if (client.targetedEntity instanceof LivingEntity living) {
            target = living;
        } else if (client.currentScreen instanceof HudEditorScreen) {
            target = client.player;
        }

        if (target == null) return;

        Theme theme = ThemeManager.getCurrentTheme();
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();

        context.fill(x, y, x + w, y + h, theme.getPanelColor());
        context.drawBorder(x, y, w, h, theme.getAccentStart());

        String name = target.getName().getString();
        float hp = Math.max(0, target.getHealth());
        float maxHp = Math.max(1, target.getMaxHealth());
        float ratio = Math.min(1.0f, hp / maxHp);

        String hpText = String.format("%.1f / %.1f HP", hp, maxHp);

        context.drawText(client.textRenderer, name, x + 8, y + 6, theme.getTextColor(), true);
        context.drawText(client.textRenderer, hpText, x + 8, y + 17, 0xFFAAAAAA, true);

        int barWidth = w - 16;
        int filled = (int) (barWidth * ratio);
        context.fill(x + 8, y + 32, x + 8 + barWidth, y + 38, 0xFF1A1A28);
        context.fill(x + 8, y + 32, x + 8 + filled, y + 38, theme.getAccentStart());
    }
}
