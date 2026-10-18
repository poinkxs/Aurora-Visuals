package com.aurora.visuals.ui.animation;

/**
 * Функции плавности (easing) для интерфейса и анимаций модулей.
 */
public final class Easing {

    private Easing() {}

    public static float easeOutExpo(float x) {
        return x == 1.0f ? 1.0f : 1.0f - (float) Math.pow(2, -10 * x);
    }

    public static float easeOutBack(float x) {
        const float c1 = 1.70158f;
        const float c3 = c1 + 1f;
        return 1f + c3 * (float) Math.pow(x - 1f, 3) + c1 * (float) Math.pow(x - 1f, 2);
    }

    public static float smoothStep(float edge0, float edge1, float x) {
        float t = Math.clamp((x - edge0) / (edge1 - edge0), 0.0f, 1.0f);
        return t * t * (3.0f - 2.0f * t);
    }
}
