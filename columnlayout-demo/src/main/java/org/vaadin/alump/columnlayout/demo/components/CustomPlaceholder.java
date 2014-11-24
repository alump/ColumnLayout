package org.vaadin.alump.columnlayout.demo.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

/**
 * Custom placeholder class
 */
public class CustomPlaceholder extends Button {

    public CustomPlaceholder() {
        setCaption("Button as placeholder :)");
        addClickListener(e -> Notification.show("Placeholder says: Hei!"));
    }
}
