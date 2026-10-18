package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class ViewModelModule extends Module {
    private static boolean active = false;
    public static float scaleX = 1.0f;
    public static float scaleY = 1.0f;
    public static float scaleZ = 1.0f;

    public ViewModelModule() {
        super("aurora.module.viewmodel", "aurora.module.viewmodel.desc", Category.RENDER);
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
