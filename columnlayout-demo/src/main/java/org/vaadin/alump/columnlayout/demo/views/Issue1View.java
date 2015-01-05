package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.ColumnLayout;

/**
 * Created by alump on 23/11/14.
 */
@ViewIdentifier("Issue1")
@ViewDescription("Test case for issue #1")
public class Issue1View extends CLView {

    private ColumnLayout columnLayout;

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        menuBar.addComponent(new Label("Resizing window small enough should move second button under the first"));
    }

    @Override
    protected void constructViewPanel() {
        Panel panel = new Panel();
        panel.setSizeFull();
        addComponent(panel);
        setExpandRatio(panel, 1.0f);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setWidth("100%");
        panel.setContent(vLayout);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setWidth("100%");
        vLayout.addComponent(hLayout);

        columnLayout = createLayout();
        vLayout.addComponent(columnLayout);

        VerticalLayout vLayout2 = new VerticalLayout();
        vLayout2.setWidth("100%");
        vLayout.addComponent(vLayout2);

    }

    @Override
    protected ColumnLayout createLayout() {
        ColumnLayout layout = new ColumnLayout();
        layout.setWidth("100%");
        layout.setColumnWidth(400);

        Button button1 = new Button("Hello");
        button1.setWidth("100%");
        layout.addComponent(button1, 0);

        Button button2 = new Button("World");
        button2.setWidth("100%");
        layout.addComponent(button2, 1);

        return layout;
    }

    @Override
    protected ColumnLayout getLayout() {
        return columnLayout;
    }
}
