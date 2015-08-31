package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Removes tooltip from Label
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipLabel.class)
public class NoTooltipLabelConnector extends LabelConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }

}
