package com.aurora.visuals.visuals;

import com.aurora.visuals.module.Module;
import com.aurora.visuals.module.Category;

public class ZoomModule extends Module {
    private boolean zooming = false;

    public ZoomModule() {
        super("aurora.module.zoom", "aurora.module.zoom.desc", Category.VISUALS);
    }

    public boolean isZooming() { return zooming; }
    public void setZooming(boolean zooming) { this.zooming = zooming; }
}
