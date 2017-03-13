package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.ui.DateField;

import java.time.LocalDate;
import java.util.Date;

/**
 * DateField that does not use tooltip to show description or error
 */
@SuppressWarnings("serial")
public class NoTooltipDateField extends DateField {

    public NoTooltipDateField() {
        super();
    }

    public NoTooltipDateField(String caption) {
        super(caption);
    }

    public NoTooltipDateField(String caption, LocalDate value) {
        super(caption, value);
    }
}
