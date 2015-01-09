package org.vaadin.alump.columnlayout.material;

import com.vaadin.ui.Component;

/**
 * Event given when tooltip of MaterialColumnLayout is clicked
 */
public class TooltipClickEvent {
    private final Component component;

    public TooltipClickEvent(Component component) {
        this.component = component;
    }

    /**
     * Get owner child component of tooltip that was clicked
     * @return Child component which tooltip was clicked
     */
    public Component getComponent() {
        return component;
    }
}
