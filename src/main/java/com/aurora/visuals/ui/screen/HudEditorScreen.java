package com.aurora.visuals.ui.screen;

import com.aurora.visuals.core.AuroraLang;
import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/**
 * Экран перетаскивания и настройки HUD-элементов.
 */
public class HudEditorScreen extends Screen {
    private HudModule draggedModule = null;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;

    public HudEditorScreen() {
        super(Text.literal("Aurora HUD Editor"));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Theme theme = ThemeManager.getCurrentTheme();

        // Полупрозрачный фон с сеткой
        context.fill(0, 0, this.width, this.height, 0x88000000);
        context.drawText(this.textRenderer, AuroraLang.tr("aurora.gui.edit_hud"), 10, 10, theme.getTextColor(), true);

        // Рендер всех HUD модулей с рамками для перетаскивания
        for (HudModule hm : ModuleManager.getHudModules()) {
            if (!hm.isEnabled()) continue;

            if (draggedModule == hm) {
                hm.setX(mouseX - dragOffsetX);
                hm.setY(mouseY - dragOffsetY);
            }

            hm.render(context, delta);

            // Рамка выделения в редакторе
            context.drawBorder(hm.getX() - 2, hm.getY() - 2, hm.getWidth() + 4, hm.getHeight() + 4, theme.getAccentStart());
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (HudModule hm : ModuleManager.getHudModules()) {
            if (!hm.isEnabled()) continue;
            if (mouseX >= hm.getX() && mouseX <= hm.getX() + hm.getWidth() &&
                mouseY >= hm.getY() && mouseY <= hm.getY() + hm.getHeight()) {
                draggedModule = hm;
                dragOffsetX = (int)(mouseX - hm.getX());
                dragOffsetY = (int)(mouseY - hm.getY());
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        draggedModule = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
