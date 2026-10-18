package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class CrosshairModule extends Module {
    private static boolean active = false;

    public CrosshairModule() {
        super("aurora.module.crosshair", "aurora.module.crosshair.desc", Module.Category.RENDER);
    }

    public void onEnable() {
        active = true;
    }

    public void onDisable() {
        active = false;
    }

    public static boolean isActive() {
        return active;
    }
}
