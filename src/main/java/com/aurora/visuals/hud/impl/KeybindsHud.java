package com.aurora.visuals.hud.impl;

import com.aurora.visuals.core.AuroraLang;
import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class KeybindsHud extends HudModule {
    public KeybindsHud() {
        super("aurora.module.keybinds", "aurora.module.keybinds.desc", 10, 140, 120, 58);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        TextRenderer tr = client.textRenderer;
        Theme theme = ThemeManager.getCurrentTheme();

        int x = this.getX();
        int y = this.getY();
        int w = this.getWidth();
        int h = this.getHeight();

        context.fill(x, y, x + w, y + h, 0x9011111A);
        context.fill(x, y, x + w, y + 2, theme.getAccentStart());

        context.drawText(tr, AuroraLang.tr("aurora.module.keybinds"), x + 6, y + 6, theme.getTextColor(), true);
        context.drawText(tr, "ClickGUI: [RSHIFT]", x + 6, y + 20, theme.getSecondaryTextColor(), true);
        context.drawText(tr, "HUD Editor: [H]", x + 6, y + 32, theme.getSecondaryTextColor(), true);
        context.drawText(tr, "Zoom: [C]", x + 6, y + 44, theme.getSecondaryTextColor(), true);
    }
}
