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

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private Category selectedCategory = Category.HUD;
    private float openAnimation = 0.0f;

    private record ModuleCard(Module module, int x, int y, int width, int height) {}
    private record CategoryTab(Category category, int x, int y, int width, int height) {}

    private final List<ModuleCard> renderedCards = new ArrayList<>();
    private final List<CategoryTab> categoryTabs = new ArrayList<>();

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

        int alpha = (int)(openAnimation * 150);
        context.fill(0, 0, this.width, this.height, (alpha << 24));

        int guiWidth = 540;
        int guiHeight = 320;
        int startX = (this.width - guiWidth) / 2;
        int startY = (this.height - guiHeight) / 2;

        // Главная панель и боковая панель категорий
        context.fill(startX, startY, startX + guiWidth, startY + guiHeight, theme.getPanelColor());
        context.fill(startX, startY, startX + 140, startY + guiHeight, 0xFF141422);

        // Отрисовка кнопок категорий слева
        categoryTabs.clear();
        int catY = startY + 50;
        for (Category cat : Category.values()) {
            boolean isSelected = (cat == selectedCategory);
            int tabColor = isSelected ? theme.getAccentStart() : 0xFF1F1F35;
            context.fill(startX + 10, catY, startX + 130, catY + 28, tabColor);
            
            String catName = AuroraLang.tr("aurora.category." + cat.name().toLowerCase());
            context.drawText(this.textRenderer, catName, startX + 20, catY + 10, theme.getTextColor(), true);
            
            categoryTabs.add(new CategoryTab(cat, startX + 10, catY, 120, 28));
            catY += 34;
        }

        // Заголовок текущей категории
        context.drawText(this.textRenderer, AuroraLang.tr("aurora.category." + selectedCategory.name().toLowerCase()), startX + 160, startY + 20, theme.getTextColor(), true);

        // Список модулей выбранной категории
        renderedCards.clear();
        List<Module> modules = ModuleManager.getModulesByCategory(selectedCategory);
        int renderY = startY + 60;
        int renderX = startX + 160;
        int cardWidth = 175;
        int cardHeight = 36;

        for (Module module : modules) {
            boolean enabled = module.isEnabled();
            // Добавлен альфа-канал FF, чтобы выключенные карточки были видны
            int cardBg = enabled ? theme.getAccentStart() : 0xFF11111A;
            
            context.fill(renderX, renderY, renderX + cardWidth, renderY + cardHeight, cardBg);
            
            String displayName = AuroraLang.tr(module.getNameKey());
            context.drawText(this.textRenderer, displayName, renderX + 12, renderY + 12, theme.getTextColor(), true);
            
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
        if (button == 0) {
            // Проверка клика по категориям
            for (CategoryTab tab : categoryTabs) {
                if (mouseX >= tab.x && mouseX <= tab.x + tab.width &&
                    mouseY >= tab.y && mouseY <= tab.y + tab.height) {
                    selectedCategory = tab.category;
                    return true;
                }
            }
            // Проверка клика по модулям
            for (ModuleCard card : renderedCards) {
                if (mouseX >= card.x && mouseX <= card.x + card.width &&
                    mouseY >= card.y && mouseY <= card.y + card.height) {
                    card.module.toggle();
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
