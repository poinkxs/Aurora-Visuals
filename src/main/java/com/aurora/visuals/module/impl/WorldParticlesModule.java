package com.aurora.visuals.module.impl;

import com.aurora.visuals.module.Module;

public class WorldParticlesModule extends Module {
    private static boolean active = false;

    public WorldParticlesModule() {
        super("aurora.module.particles", "aurora.module.particles.desc", Module.Category.RENDER);
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
