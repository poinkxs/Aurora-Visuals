package com.aurora.visuals.module;

public class HitColorModule extends Module {
    private static boolean active = false;
    private static boolean useCustomColor = false;
    private static int customColor = 0xFFFF0000;

    public HitColorModule() {
        super("aurora.module.hitcolor", "aurora.module.hitcolor.desc", Category.VISUALS);
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

    public static boolean isUseCustomColor() {
        return useCustomColor;
    }

    public static void setUseCustomColor(boolean val) {
        useCustomColor = val;
    }

    public static int getCustomColor() {
        return customColor;
    }

    public static void setCustomColor(int color) {
        customColor = color;
    }
}
