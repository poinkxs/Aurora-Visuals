package com.aurora.visuals.ui.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import com.aurora.visuals.AuroraVisualsClient;

public final class NinePatchAtlas {
    private static boolean initialized = false;
    private static Identifier atlasId;

    public static void ensureInitialized() {
        if (initialized) return;
        try {
            NativeImage image = new NativeImage(64, 64, true);
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    image.setColorArgb(x, y, 0xFFFFFFFF);
                }
            }
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            atlasId = Identifier.of("aurora", "atlas");
            MinecraftClient.getInstance().getTextureManager().registerTexture(atlasId, texture);
            initialized = true;
        } catch (Exception e) {
            AuroraVisualsClient.LOGGER.error("[Aurora Visuals] Ошибка инициализации NinePatchAtlas", e);
        }
    }

    public static Identifier getAtlasId() {
        ensureInitialized();
        return atlasId;
    }
}
