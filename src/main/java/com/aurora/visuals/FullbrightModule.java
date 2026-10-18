package com.aurora.visuals;

import com.aurora.visuals.module.Category;
import com.aurora.visuals.module.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FullbrightModule extends Module {
    private boolean applied = false;

    public FullbrightModule() {
        super("aurora.module.fullbright", "aurora.module.fullbright.desc", Category.VISUALS);
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        if (client.player == null) return;
        if (isEnabled()) {
            client.player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION, 400, 0, false, false, false));
            applied = true;
        } else if (applied) {
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            applied = false;
        }
    }
}
