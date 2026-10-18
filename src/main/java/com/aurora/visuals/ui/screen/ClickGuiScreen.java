package com.aurora.visuals.ui.screen;

import com.aurora.visuals.core.AuroraLang;
import com.aurora.visuals.module.Category;
import com.aurora.visuals.module.Module;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

/*
 * Поддерживает поиск, категории, карточки модулей и смену тем.
 */
public class ClickGuiScreen extends Screen {
    private Category selectedCategory = Category.HUD;
    private String searchQuery = "";
    private float openAnimation = 0.0f;

    // Сохраняем координаты карточек для обработки кликов
    private record ModuleCard(Module module, int x, int y, int width, int height) {}
    private final java.util.List<ModuleCard> renderedCards = new java.util.ArrayList<>();

    public ClickGuiScreen() {
        super(Text.literal("Aurora Visuals ClickGUI"));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        openAnimation = Math.min(1.0f, openAnimation + delta * 0.15f);
        
        Theme theme = ThemeManager.getCurrentTheme();
        
        // Затемнение фона
        context.fill(0, 0, this.width, this.height, (int)((openAnimation * 150) << 24));

        int guiWidth = 540;
        int guiHeight = 320;
        int startX = (this.width - guiWidth) / 2;
        int startY = (this.height - guiHeight) / 2;

        // Главная панель
        context.fill(startX, startY, startX + guiWidth, startY + guiHeight, theme.getPanelColor());

        // Заголовок категории
        context.drawText(this.textRenderer, AuroraLang.tr("aurora.category." + selectedCategory.name().toLowerCase()), startX + 160, startY + 20, theme.getTextColor(), true);

        // Очищаем список кликабельных карточек перед отрисовкой
        renderedCards.clear();

        // Список модулей выбранной категории
        List<Module> modules = ModuleManager.getModulesByCategory(selectedCategory);
        int renderY = startY + 60;
        int renderX = startX + 160;
        int cardWidth = 175;
        int cardHeight = 36;

        for (Module module : modules) {
            boolean enabled = module.isEnabled();
            int cardBg = enabled ? theme.getAccentStart() : 0x11111A;
            
            context.fill(renderX, renderY, renderX + cardWidth, renderY + cardHeight, cardBg);
            
            // Используем правильный перевод названия модуля
            String displayName = AuroraLang.tr(module.getNameKey());
            context.drawText(this.textRenderer, displayName, renderX + 12, renderY + 12, theme.getTextColor(), true);
            
            // Сохраняем карточку для отслеживания кликов мыши
            renderedCards.add(new ModuleCard(module, renderX, renderY, cardWidth, cardHeight));

            renderX += 185;
            if (renderX + cardWidth > startX + guiWidth) {
                renderX = startX + 160;
                renderY += 44;
            }
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Левая кнопка мыши
            for (ModuleCard card : renderedCards) {
                if (mouseX >= card.x && mouseX <= card.x + card.width &&
                    mouseY >= card.y && mouseY <= card.y + card.height) {
                    card.module.toggle(); // Переключаем модуль при клике на карточку
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
