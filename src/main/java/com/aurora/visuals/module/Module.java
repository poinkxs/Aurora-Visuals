package com.aurora.visuals.module;

public class Module {
    private final String nameKey;
    private final String descKey;
    private final Category category;
    private boolean enabled;
    private int keybind;

    public Module(String nameKey, String descKey, Category category) {
        this.nameKey = nameKey;
        this.descKey = descKey;
        this.category = category;
        this.enabled = false;
        this.keybind = 0;
    }

    public String getNameKey() { return nameKey; }
    public String getDescKey() { return descKey; }
    public Category getCategory() { return category; }
    
    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    public void toggle() {
        setEnabled(!this.enabled);
    }

    public void onEnable() {}
    public void onDisable() {}

    public int getKeybind() { return keybind; }
    public void setKeybind(int keybind) { this.keybind = keybind; }
}
