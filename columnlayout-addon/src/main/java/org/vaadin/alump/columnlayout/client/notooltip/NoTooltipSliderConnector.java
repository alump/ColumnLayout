package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.slider.SliderConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Removes tooltips from slider
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipSlider.class)
public class NoTooltipSliderConnector extends SliderConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
