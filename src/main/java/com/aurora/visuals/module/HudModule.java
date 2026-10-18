package com.aurora.visuals.module;

import net.minecraft.client.gui.DrawContext;

public class HudModule extends Module {
    private int x;
    private int y;
    private int width;
    private int height;

    public HudModule(String nameKey, String descKey, int defaultX, int defaultY, int width, int height) {
        super(nameKey, descKey, Category.HUD);
        this.x = defaultX;
        this.y = defaultY;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext context, float delta) {}

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
