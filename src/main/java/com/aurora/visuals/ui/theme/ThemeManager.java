package com.aurora.visuals.ui.theme;

public final class ThemeManager {
    // Передаем 7 аргументов под конструктор Theme (или используем конструктор по умолчанию)
    private static Theme currentTheme = new Theme("Aurora Dark", 0xFF1A1A2E, 0xFF00F2FE, 0xFF4FACFE, 0xFFFFFFFF, 0xFF000000, 0xFFAAAAAA);

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
