package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.datefield.PopupDateFieldConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Removes tooltips from popup date field
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipPopupDateField.class)
public class NoTooltipPopupDateFieldConnector extends PopupDateFieldConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
