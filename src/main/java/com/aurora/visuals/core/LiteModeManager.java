package com.aurora.visuals.core;

import com.aurora.visuals.AuroraVisualsClient;

public final class LiteModeManager {
    private static boolean liteMode = false;

    private LiteModeManager() {}

    public static void detectEnvironment() {
        String renderer = System.getProperty("os.name");
        if (renderer != null && renderer.toLowerCase().contains("android")) {
            liteMode = true;
            AuroraVisualsClient.LOGGER.info("[Aurora Visuals] Обнаружена Android среда (PojavLauncher). Включен Lite-режим для оптимизации FPS.");
        }
    }

    public static boolean isLiteMode() {
        return liteMode;
    }

    public static void setLiteMode(boolean mode) {
        liteMode = mode;
    }
}
