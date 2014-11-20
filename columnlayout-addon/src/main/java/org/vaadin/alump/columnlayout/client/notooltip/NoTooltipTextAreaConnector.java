package org.vaadin.alump.columnlayout.client.notooltip;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.TooltipInfo;
import com.vaadin.client.ui.textarea.TextAreaConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Removed tooltips from text area
 */
@Connect(org.vaadin.alump.columnlayout.notooltip.NoTooltipTextArea.class)
public class NoTooltipTextAreaConnector extends TextAreaConnector {
    @Override
    public boolean hasTooltip() {
        return false;
    }

    @Override
    public TooltipInfo getTooltipInfo(Element element) {
        return new TooltipInfo();
    }
}
