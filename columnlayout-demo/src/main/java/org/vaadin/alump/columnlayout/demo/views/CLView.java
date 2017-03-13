package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.demo.components.SmallButton;

/**
 * Created by alump on 20/11/14.
 */
public abstract class CLView extends AbstractView {

    private ColumnLayout columnLayout;
    private HorizontalLayout menuBar;

    @Override
    protected void construct() {
        constructMenuBar();
        constructViewPanel();
    }

    protected void constructMenuBar() {
        menuBar = new HorizontalLayout();
        menuBar.addStyleName("menubar");
        menuBar.setSpacing(true);
        menuBar.setMargin(true);
        addComponent(menuBar);

        SmallButton button = new SmallButton(VaadinIcons.MENU.getHtml(), e -> navigateTo(MenuView.class));
        button.setHtmlContentAllowed(true);
        menuBar.addComponent(button);
        createMenuBar(menuBar);
    }

    protected void constructViewPanel() {
        Panel viewPanel = new Panel();
        viewPanel.setSizeFull();
        addComponent(viewPanel);
        setExpandRatio(viewPanel, 1.0f);

        columnLayout = createLayout();
        columnLayout.setWidth("100%");
        viewPanel.setContent(columnLayout);
    }

    protected abstract void createMenuBar(HorizontalLayout menuBar);

    protected abstract ColumnLayout createLayout();

    protected ColumnLayout getLayout() {
        return columnLayout;
    }

    protected HorizontalLayout getMenuBar() {
        return menuBar;
    }
}
