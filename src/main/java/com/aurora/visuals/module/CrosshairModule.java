package com.aurora.visuals.module;

public class CrosshairModule extends Module {
    private static boolean active = false;

    public CrosshairModule() {
        super("aurora.module.crosshair", "aurora.module.crosshair.desc", Category.VISUALS);
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
