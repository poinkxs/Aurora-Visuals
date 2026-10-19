package com.aurora.visuals.module;

public class ViewModelModule extends Module {
    private static boolean active = false;
    public static float scaleX = 1.0f;
    public static float scaleY = 1.0f;
    public static float scaleZ = 1.0f;

    public ViewModelModule() {
        super("aurora.module.viewmodel", "aurora.module.viewmodel.desc", Category.VISUALS);
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
