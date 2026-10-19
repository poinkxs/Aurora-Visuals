package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class InventoryPreviewHud extends HudModule {
    public InventoryPreviewHud() {
        super("aurora.module.inventory", "aurora.module.inventory.desc", 10, 245, 170, 68);
    }

    @Override
    public void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Theme theme = ThemeManager.getCurrentTheme();
        int x = getX();
        int y = getY();

        context.fill(x, y, x + getWidth(), y + getHeight(), theme.getPanelColor());
        context.drawBorder(x, y, getWidth(), getHeight(), theme.getAccentStart());
        context.drawText(client.textRenderer, "Inventory", x + 6, y + 4, theme.getAccentStart(), true);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slot = 9 + row * 9 + col;
                ItemStack stack = client.player.getInventory().getStack(slot);
                int itemX = x + 4 + col * 18;
                int itemY = y + 14 + row * 18;
                if (!stack.isEmpty()) {
                    context.drawItem(stack, itemX, itemY);
                    context.drawStackOverlay(client.textRenderer, stack, itemX, itemY);
                }
            }
        }
    }
}
