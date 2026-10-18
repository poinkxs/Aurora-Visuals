package com.aurora.visuals.config;

import com.aurora.visuals.AuroraVisualsClient;

public final class ConfigManager {
    private ConfigManager() {}

    public static void init() {
        AuroraVisualsClient.LOGGER.info("[Aurora Visuals] Конфигурация загружена.");
    }
}
