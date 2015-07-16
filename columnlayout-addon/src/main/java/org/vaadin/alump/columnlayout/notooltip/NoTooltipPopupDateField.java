package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.PopupDateField;

import java.util.Date;

/**
 * PopupDateField that does not use tooltip to present description or errors
 */
@SuppressWarnings("serial")
public class NoTooltipPopupDateField extends PopupDateField {

    public NoTooltipPopupDateField() {
    }

    public NoTooltipPopupDateField(Property dataSource) throws IllegalArgumentException {
        super(dataSource);
    }

    public NoTooltipPopupDateField(String caption, Date value) {
        super(caption, value);
    }

    public NoTooltipPopupDateField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipPopupDateField(String caption) {
        super(caption);
    }

}
