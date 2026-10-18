package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class FullbrightModule extends Module {
    private static boolean active = false;

    public FullbrightModule() {
        super("aurora.module.fullbright", "aurora.module.fullbright.desc", Module.Category.RENDER);
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
