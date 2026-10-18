package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class FullbrightModule extends Module {
    private static boolean active = false;

    public FullbrightModule() {
        super("aurora.module.fullbright", "aurora.module.fullbright.desc", Category.RENDER);
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
}
