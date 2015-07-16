package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.InlineDateField;

import java.util.Date;

/**
 * InlineDateField that does not use tooltip to present description or errors
 */
public class NoTooltipInlineDateField extends InlineDateField {
    public NoTooltipInlineDateField() {
    }

    public NoTooltipInlineDateField(Property dataSource) throws IllegalArgumentException {
        super(dataSource);
    }

    public NoTooltipInlineDateField(String caption, Date value) {
        super(caption, value);
    }

    public NoTooltipInlineDateField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipInlineDateField(String caption) {
        super(caption);
    }
}
