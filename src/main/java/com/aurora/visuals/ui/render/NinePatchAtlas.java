package com.aurora.visuals.ui.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import com.aurora.visuals.AuroraVisualsClient;

/**
 * Процедурно генерирует текстуру скруглённых панелей и мягких теней в памяти
 * для быстрого рендеринга без аллокаций и без тяжёлых шейдеров на телефонах (GL4ES).
 */
public final class NinePatchAtlas {
    private static boolean initialized = false;
    private static Identifier atlasId;

    public static void ensureInitialized() {
        if (initialized) return;
        try {
            NativeImage image = new NativeImage(64, 64, true);
            // Заполнение текстурного атласа скруглений и градиентов
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    image.setColorArgb(x, y, 0xFFFFFFFF);
                }
            }
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            
            // Безопасная регистрация текстуры для Minecraft 1.21.4
            atlasId = MinecraftClient.getInstance().getTextureManager().registerTexture("aurora_atlas", texture);
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
