package com.aurora.visuals.ui.render;

/**
 * Утилиты для работы с ARGB цветами и интерполяцией без аллокаций.
 */
public final class ColorUtils {

    private ColorUtils() {}

    public static int setAlpha(int color, float alpha) {
        int a = (int) (Math.clamp(alpha, 0.0f, 1.0f) * 255);
        return (color & 0x00FFFFFF) | (a << 24);
    }

    public static int interpolateColor(int color1, int color2, float factor) {
        factor = Math.clamp(factor, 0.0f, 1.0f);
        int a1 = (color1 >> 24) & 0xFF;
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int a2 = (color2 >> 24) & 0xFF;
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int a = (int) (a1 + (a2 - a1) * factor);
        int r = (int) (r1 + (r2 - r1) * factor);
        int g = (int) (g1 + (g2 - g1) * factor);
        int b = (int) (b1 + (b2 - b1) * factor);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
