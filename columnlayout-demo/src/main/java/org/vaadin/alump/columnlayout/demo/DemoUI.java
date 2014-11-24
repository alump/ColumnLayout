package org.vaadin.alump.columnlayout.demo;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import org.vaadin.alump.columnlayout.demo.views.*;

/**
 * Demo UI of ColumnLayout Vaadin add-on
 */
@Theme("demo")
@Title("ColumnLayout Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.alump.columnlayout.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this, this);
        addViewToNavigator(navigator, MenuView.class);
        addViewToNavigator(navigator, BasicView.class);
        addViewToNavigator(navigator, MaterialView.class);
        addViewToNavigator(navigator, Issue1View.class);
        addViewToNavigator(navigator, Issue2View.class);
    }

    protected static void addViewToNavigator(Navigator navigator, Class<? extends View> viewClass) {
        String identifier = getViewIdentifier(viewClass);
        navigator.addView(identifier, viewClass);
    }

    public static String getViewIdentifier(Class<? extends View> viewClass) {
        ViewIdentifier annotation = viewClass.getAnnotation(ViewIdentifier.class);
        if(annotation != null) {
            return annotation.value();
        } else {
            return viewClass.getSimpleName();
        }
    }

    public static String getViewDescription(Class<? extends View> viewClass) {
        ViewDescription annotation = viewClass.getAnnotation(ViewDescription.class);
        if(annotation != null) {
            return annotation.value();
        } else {
            return "";
        }
    }

}
