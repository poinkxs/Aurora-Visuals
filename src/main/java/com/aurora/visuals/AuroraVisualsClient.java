package com.aurora.visuals;

import com.aurora.visuals.config.ConfigManager;
import com.aurora.visuals.core.LiteModeManager;
import com.aurora.visuals.core.SlowTickUpdater;
import com.aurora.visuals.module.ModuleManager;
import com.aurora.visuals.ui.render.NinePatchAtlas;
import com.aurora.visuals.ui.screen.ClickGuiScreen;
import com.aurora.visuals.ui.screen.HudEditorScreen;
import com.aurora.visuals.ui.theme.ThemeManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuroraVisualsClient implements ClientModInitializer {

    public static final String MOD_ID = "aurora";
    public static final String MOD_NAME = "Aurora Visuals";
    public static final String MOD_VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static KeyBinding openGuiKey;
    public static KeyBinding openHudEditorKey;
    public static KeyBinding zoomKey;

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Aurora Visuals] Initializing v{}...", MOD_VERSION);

        LiteModeManager.detectEnvironment();
        ThemeManager.init();
        ModuleManager.init();
        ConfigManager.init();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "aurora.key.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "Aurora Visuals"
        ));

        openHudEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "aurora.key.hud_editor",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "Aurora Visuals"
        ));

        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "aurora.key.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "Aurora Visuals"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            SlowTickUpdater.onClientTick(client);

            while (openGuiKey.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }
            while (openHudEditorKey.wasPressed()) {
                client.setScreen(new HudEditorScreen());
            }
        });

        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) return;
            if (client.currentScreen instanceof HudEditorScreen) return;

            NinePatchAtlas.ensureInitialized();

            float tickDelta = renderTickCounter.getTickDelta(true);
            ModuleManager.renderHudModules(drawContext, tickDelta);
        });
    }
}
