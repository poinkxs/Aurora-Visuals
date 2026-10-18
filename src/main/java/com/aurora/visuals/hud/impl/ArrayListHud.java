package com.aurora.visuals.hud.impl;

import com.aurora.visuals.core.AuroraLang;
import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.module.Module;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.Comparator;
import java.util.List;

public class ArrayListHud extends HudModule {
    public ArrayListHud() {
        super("aurora.module.arraylist", "aurora.module.arraylist.desc", 10, 30, 100, 100);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        TextRenderer tr = client.textRenderer;
        Theme theme = ThemeManager.getCurrentTheme();

        // Получаем включенные модули и сортируем по ширине их переведенного названия
        List<Module> enabledModules = ModuleManager.getModules().stream()
                .filter(Module::isEnabled)
                .sorted(Comparator.comparingInt((Module m) -> tr.getWidth(AuroraLang.tr(m.getNameKey()))).reversed())
                .toList();

        int currentY = this.getY();
        int maxWidth = 40;

        for (Module module : enabledModules) {
            // Переводим ключ в красивое название
            String displayName = AuroraLang.tr(module.getNameKey());
            int textWidth = tr.getWidth(displayName);
            int boxWidth = textWidth + 8;

            if (boxWidth > maxWidth) {
                maxWidth = boxWidth;
            }

            int boxX = this.getX();

            // Полупрозрачная плашка фона и боковая линия акцента
            context.fill(boxX, currentY, boxX + boxWidth, currentY + 12, 0x9011111A);
            context.fill(boxX, currentY, boxX + 2, currentY + 12, theme.getAccentStart());

            // Текст модуля
            context.drawText(tr, displayName, boxX + 5, currentY + 2, theme.getTextColor(), true);

            currentY += 13;
        }

        this.setWidth(maxWidth);
        this.setHeight(Math.max(20, currentY - this.getY()));
    }
}
