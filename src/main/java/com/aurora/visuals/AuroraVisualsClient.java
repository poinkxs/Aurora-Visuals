package com.aurora.visuals;

import com.aurora.visuals.core.AuroraLang;
import com.aurora.visuals.core.LiteModeManager;
import com.aurora.visuals.core.SlowTickUpdater;
import com.aurora.visuals.hud.HudManager;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.screen.ClickGuiScreen;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuroraVisualsClient implements ClientModInitializer {
    public static final String MOD_ID = "aurora";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static KeyBinding clickGuiKey;
    public static KeyBinding zoomKey;
    private static boolean isZoomingState = false;

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Aurora Visuals] Инициализация клиентской части мода...");

        // Инициализация менеджеров
        ThemeManager.init();
        ModuleManager.init();
        HudManager.init();

        // Регистрация клавиши открытия ClickGUI на цифру "0" (GLFW_KEY_0)
        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aurora.clickgui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_0,
                "category.aurora.visuals"
        ));

        // Регистрация клавиши зума на "C"
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aurora.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.aurora.visuals"
        ));

        // Обработка нажатий клавиш в каждом тике игры
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Открытие ClickGUI по нажатию клавиши 0
            while (clickGuiKey.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }

            // Отслеживание зума
            isZoomingState = zoomKey.isPressed();
        });

        // Отрисовка HUD элементов поверх экрана игры
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            HudManager.renderAll(drawContext, tickDelta);
        });

        // Запуск фоновых задач
        SlowTickUpdater.init();
        LiteModeManager.init();

        LOGGER.info("[Aurora Visuals] Клиент успешно инициализирован!");
    }

    public static boolean isZooming() {
        return isZoomingState;
    }
}
