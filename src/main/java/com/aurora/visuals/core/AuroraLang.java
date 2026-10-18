package com.aurora.visuals.core;

import net.minecraft.client.resource.language.I18n;

/**
 * Автоматически переводит текст мода на тот язык, который выбран в Minecraft.
 */
public final class AuroraLang {

    private AuroraLang() {}

    public static String tr(String key, Object... args) {
        if (I18n.hasTranslation(key)) {
            return I18n.translate(key, args);
        }
        return key;
    }
}
