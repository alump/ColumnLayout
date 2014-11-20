package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.combobox.ComboBoxConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Just disables tooltips of combobox
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipComboBox.class)
public class NoTooltipComboBoxConnector extends ComboBoxConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
