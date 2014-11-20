package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Just disables tooltips from text field
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipTextField.class)
public class NoTooltipTextFieldConnector extends TextFieldConnector {

    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
