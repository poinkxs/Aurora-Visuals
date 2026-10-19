package com.aurora.visuals.module;

import com.aurora.visuals.ZoomModule;
import com.aurora.visuals.hud.impl.*;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();
    private static final List<HudModule> HUD_MODULES = new ArrayList<>();

    public static void init() {
        registerHud(new WatermarkHud());
        registerHud(new ArrayListHud());
        registerHud(new InfoBlockHud());
        registerHud(new TargetHud());
        registerHud(new PotionsHud());
        registerHud(new KeybindsHud());
        registerHud(new CooldownsHud());
        registerHud(new ArmorAndCountersHud());
        registerHud(new InventoryPreviewHud());
        registerHud(new NotificationsHud());
        registerHud(new CustomHotbarHud());
        registerHud(new CustomCrosshairHud());

        MODULES.add(new ZoomModule());
        MODULES.add(new FullbrightModule());
        MODULES.add(new ViewModelModule());
        MODULES.add(new HitColorModule());
        MODULES.add(new WorldParticlesModule());
    }

    private static void registerHud(HudModule module) {
        MODULES.add(module);
        HUD_MODULES.add(module);
        module.setEnabled(true);
    }

    public static List<Module> getModules() { return MODULES; }
    public static List<HudModule> getHudModules() { return HUD_MODULES; }

    public static List<Module> getModulesByCategory(Category category) {
        List<Module> list = new ArrayList<>();
        for (Module m : MODULES) {
            if (m.getCategory() == category) list.add(m);
        }
        return list;
    }

    public static void renderHudModules(DrawContext context, float delta) {
        for (HudModule hm : HUD_MODULES) {
            if (hm.isEnabled()) {
                hm.render(context, delta);
            }
        }
    }
}
