package com.aurora.visuals.ui.theme;

/**
 * Описание цветовой схемы темы оформления (Aurora, Plintus и др.).
 */
public class Theme {
    private final String name;
    private final int backgroundColor;
    private final int panelColor;
    private final int accentStart;
    private final int accentEnd;
    private final int textColor;
    private final int textSecondary;

    public Theme(String name, int bg, int panel, int accentStart, int accentEnd, int text, int textSec) {
        this.name = name;
        this.backgroundColor = bg;
        this.panelColor = panel;
        this.accentStart = accentStart;
        this.accentEnd = accentEnd;
        this.textColor = text;
        this.textSecondary = textSec;
    }

    public String getName() { return name; }
    public int getBackgroundColor() { return backgroundColor; }
    public int getPanelColor() { return panelColor; }
    public int getAccentStart() { return accentStart; }
    public int getAccentEnd() { return accentEnd; }
    public int getTextColor() { return textColor; }
    public int getTextSecondary() { return textSecondary; }
}
