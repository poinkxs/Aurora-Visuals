package com.aurora.visuals.core;

import net.minecraft.client.MinecraftClient;

public final class SlowTickUpdater {
    private static int tickCounter = 0;
    private static int cachedFps = 0;
    private static int cachedPing = 0;

    private SlowTickUpdater() {}

    public static void onClientTick(MinecraftClient client) {
        tickCounter++;
        if (tickCounter >= 10) { // Обновление раз в 0.5 секунды
            tickCounter = 0;
            cachedFps = client.getCurrentFps();
            if (client.getNetworkHandler() != null && client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()) != null) {
                cachedPing = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
            }
        }
    }

    public static int getCachedFps() { return cachedFps; }
    public static int getCachedPing() { return cachedPing; }
}
