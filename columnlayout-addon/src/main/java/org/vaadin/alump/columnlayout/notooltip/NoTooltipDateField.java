package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.DateField;

import java.util.Date;

/**
 * DateField that does not use tooltip to show description or error
 */
public class NoTooltipDateField extends DateField {

    public NoTooltipDateField() {
        super();
    }

    public NoTooltipDateField(String caption) {
        super(caption);
    }

    public NoTooltipDateField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipDateField(Property dataSource) throws java.lang.IllegalArgumentException {
        super(dataSource);
    }

    public NoTooltipDateField(String caption, Date value) {
        super(caption, value);
    }
}
