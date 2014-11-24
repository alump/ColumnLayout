package org.vaadin.alump.columnlayout.notooltip;

import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;

import java.util.Collection;

/**
 * Combobox that does not use tooltip to show description or error
 */
@SuppressWarnings("serial")
public class NoTooltipComboBox extends ComboBox {
    public NoTooltipComboBox() {
        super();
    }

    public NoTooltipComboBox(String caption, Collection<?> options) {
        super(caption, options);
    }

    public NoTooltipComboBox(String caption, Container dataSource) {
        super(caption, dataSource);
    }

    public NoTooltipComboBox(String caption) {
        super(caption);
    }
}
