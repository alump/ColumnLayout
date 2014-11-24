package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;

/**
 * CheckBox that does not use tooltip to show description or error
 */
@SuppressWarnings("serial")
public class NoTooltipCheckBox extends CheckBox {
    public NoTooltipCheckBox() {
        super();
    }

    public NoTooltipCheckBox(String caption) {
        super(caption);
    }

    public NoTooltipCheckBox(String caption, boolean initialState) {
        super(caption, initialState);
    }

    public NoTooltipCheckBox(String caption, Property<?> dataSource) {
        super(caption, dataSource);
    }
}
