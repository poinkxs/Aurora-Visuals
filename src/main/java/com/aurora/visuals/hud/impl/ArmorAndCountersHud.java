package com.aurora.visuals.hud.impl;

import com.aurora.visuals.module.HudModule;
import com.aurora.visuals.ui.theme.Theme;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArmorAndCountersHud extends HudModule {
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    public ArmorAndCountersHud() {
        super("aurora.module.armor", "aurora.module.armor.desc", 10, 210, 88, 24);
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

        int offsetX = 6;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = client.player.getEquippedStack(slot);
            if (!stack.isEmpty()) {
                context.drawItem(stack, x + offsetX, y + 4);
                context.drawStackOverlay(client.textRenderer, stack, x + offsetX, y + 4);
            }
            offsetX += 20;
        }
    }
}
