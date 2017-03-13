package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.ui.InlineDateField;

import java.time.LocalDate;
import java.util.Date;

/**
 * InlineDateField that does not use tooltip to present description or errors
 */
public class NoTooltipInlineDateField extends InlineDateField {
    public NoTooltipInlineDateField() {
    }

    public NoTooltipInlineDateField(String caption, LocalDate value) {
        super(caption, value);
    }

    public NoTooltipInlineDateField(String caption) {
        super(caption);
    }
}
