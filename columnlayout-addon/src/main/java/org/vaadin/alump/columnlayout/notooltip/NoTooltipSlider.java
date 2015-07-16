package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.ui.Slider;

/**
 * Slider that does not use tooltip to present description or errors
 */
public class NoTooltipSlider extends Slider {

    public NoTooltipSlider() {
        super();
    }

    public NoTooltipSlider(String caption) {
        super(caption);
    }

    public NoTooltipSlider(double min, double max, int resolution) {
        super(min, max, resolution);
    }

    public NoTooltipSlider(String caption, int min, int max) {
        super(caption, min, max);
    }

}
