package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * Label that does not use tooltip to show description or error
 */
public class NoTooltipLabel extends Label {

    /**
     * Creates an empty Label.
     */
    public NoTooltipLabel() {
        super();
    }

    /**
     * Creates a new instance of Label with text-contents.
     *
     * @param content
     */
    public NoTooltipLabel(String content) {
        super(content);
    }

    /**
     * Creates a new instance of Label with text-contents read from given
     * datasource.
     *
     * @param contentSource
     */
    public NoTooltipLabel(Property contentSource) {
        super(contentSource);
    }

    /**
     * Creates a new instance of Label with text-contents.
     *
     * @param content
     * @param contentMode
     */
    public NoTooltipLabel(String content, ContentMode contentMode) {
        super(content, contentMode);
    }

    /**
     * Creates a new instance of Label with text-contents read from given
     * datasource.
     *
     * @param contentSource
     * @param contentMode
     */
    public NoTooltipLabel(Property contentSource, ContentMode contentMode) {
        super(contentSource, contentMode);
    }
}
