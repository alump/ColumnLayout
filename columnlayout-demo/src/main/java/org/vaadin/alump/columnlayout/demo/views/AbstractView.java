package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.alump.columnlayout.demo.DemoUI;

/**
 * Created by alump on 20/11/14.
 */
public abstract class AbstractView extends VerticalLayout implements View {

    private Navigator navigator;
    private boolean uiConstructed = false;

    protected AbstractView() {
        setSizeFull();
        setSpacing(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        navigator = event.getNavigator();

        if(!uiConstructed) {
            construct();
            uiConstructed = true;
        }
    }

    protected abstract void construct();

    protected Navigator getNavigator() {
        return navigator;
    }

    protected void navigateTo(Class<? extends View> viewClass) {
        getNavigator().navigateTo(DemoUI.getViewIdentifier(viewClass));
    }
}
