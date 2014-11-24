package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

/**
 * TextField that does not use tooltip to show description of error
 */
@SuppressWarnings("serial")
public class NoTooltipTextField extends TextField {
    public NoTooltipTextField() {
        super();
    }

    public NoTooltipTextField(String caption) {
        super(caption);
    }

    public NoTooltipTextField(Property dataSource) {
        super(dataSource);
    }

    public NoTooltipTextField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipTextField(String caption, String value) {
        super(caption, value);
    }
}
