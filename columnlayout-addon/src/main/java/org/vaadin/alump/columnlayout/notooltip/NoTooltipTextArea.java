package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * TextArea that does not use tooltip to show description or error
 */
@SuppressWarnings("serial")
public class NoTooltipTextArea extends TextArea {
    public NoTooltipTextArea() {
        super();
    }

    public NoTooltipTextArea(String caption) {
        super(caption);
    }

    public NoTooltipTextArea(Property dataSource) {
        super(dataSource);
    }

    public NoTooltipTextArea(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipTextArea(String caption, String value) {
        super(caption, value);
    }
}
