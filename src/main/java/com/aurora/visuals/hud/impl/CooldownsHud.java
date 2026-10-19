package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CooldownsHud extends HudModule {
    private static final Item[] TRACKED_ITEMS = {
            Items.ENDER_PEARL, Items.CHORUS_FRUIT, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.SHIELD, Items.WIND_CHARGE
    };

    public CooldownsHud() {
        super("aurora.module.cooldowns", "aurora.module.cooldowns.desc", 145, 10, 110, 44);
    }

    @Override
    public void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Theme theme = ThemeManager.getCurrentTheme();
        int x = getX();
        int y = getY();

        int activeCount = 0;
        for (Item item : TRACKED_ITEMS) {
            if (client.player.getItemCooldownManager().isCoolingDown(new ItemStack(item))) {
                activeCount++;
            }
        }

        int height = Math.max(28, 18 + activeCount * 14);
        context.fill(x, y, x + getWidth(), y + height, theme.getPanelColor());
        context.drawBorder(x, y, getWidth(), height, theme.getAccentStart());
        context.drawText(client.textRenderer, "Cooldowns", x + 6, y + 6, theme.getAccentStart(), true);

        if (activeCount == 0) {
            context.drawText(client.textRenderer, "Ready", x + 6, y + 17, 0xFF888888, true);
            return;
        }

        int offsetY = 17;
        for (Item item : TRACKED_ITEMS) {
            ItemStack stack = new ItemStack(item);
            float progress = client.player.getItemCooldownManager().getCooldownProgress(stack, delta);
            if (progress > 0.0f) {
                String pct = (int) (progress * 100) + "%";
                context.drawText(client.textRenderer, item.getName().getString() + ": " + pct, x + 6, y + offsetY, theme.getTextColor(), true);
                offsetY += 12;
            }
        }
    }
}
