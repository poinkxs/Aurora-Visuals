package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class HitColorModule extends Module {
    private static boolean active = false;
    private static boolean useCustomColor = false;
    private static int customColor = 0xFFFF0000; // По умолчанию базовый красный

    public HitColorModule() {
        super("aurora.module.hitcolor", "aurora.module.hitcolor.desc", Category.RENDER);
    }

    @Override
    public void onEnable() {
        active = true;
    }

    @Override
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
