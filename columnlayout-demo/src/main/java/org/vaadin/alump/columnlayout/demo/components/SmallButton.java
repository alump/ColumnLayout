package org.vaadin.alump.columnlayout.demo.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Just adds small button stylename to button
 */
public class SmallButton extends Button {

    public SmallButton(String caption, Button.ClickListener clickListener) {
        super(caption, clickListener);
        addStyleName(ValoTheme.BUTTON_SMALL);
    }
}
