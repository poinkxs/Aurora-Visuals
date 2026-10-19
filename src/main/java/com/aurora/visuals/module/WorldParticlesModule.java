package com.aurora.visuals.module;

public class WorldParticlesModule extends Module {
    private static boolean active = false;

    public WorldParticlesModule() {
        super("aurora.module.particles", "aurora.module.particles.desc", Category.VISUALS);
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
