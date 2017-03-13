package org.vaadin.alump.columnlayout.notooltip;

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

    public NoTooltipTextArea(String caption, String value) {
        super(caption, value);
    }
}
