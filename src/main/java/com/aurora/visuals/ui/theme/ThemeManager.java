package com.aurora.visuals.ui.theme;

public final class ThemeManager {
    private static Theme currentTheme = new Theme(
        "Aurora Dark",
        0xFF0B0B14, // Фон
        0xFF11111A, // Панель
        0xFF00F2FE, // Акцент 1
        0xFF4FACFE, // Акцент 2
        0xFFFFFFFF, // Основной текст (Белый)
        0xFFAAAAAA  // Доп. текст (Серый)
    );

    private ThemeManager() {}

    public static Theme getCurrentTheme() {
        return currentTheme;
    }

    public static void init() {
        // Инициализация тем
    }

    public static void setTheme(Theme theme) {
        if (theme != null) {
            currentTheme = theme;
        }
    }
}
