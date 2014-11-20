package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.checkbox.CheckBoxConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Just disables tooltips of CheckBox
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipCheckBox.class)
public class NoTooltipCheckBoxConnector extends CheckBoxConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
