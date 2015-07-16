package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.datefield.InlineDateFieldConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Removes tooltips from inline date field
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipInlineDateField.class)
public class NoTooltipInlineDateFieldConnector extends InlineDateFieldConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
