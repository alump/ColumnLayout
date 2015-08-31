package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * Abstract base class that forces demo theme (to be used with material views)
 */
public abstract class CLMaterialView extends CLView {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);

        // Make sure Valo based theme is used
        if(!UI.getCurrent().getTheme().equals("demo")) {
            UI.getCurrent().setTheme("demo");
            Notification.show("Theme changed to support Material theme", Notification.Type.TRAY_NOTIFICATION);
        }
    }
}
