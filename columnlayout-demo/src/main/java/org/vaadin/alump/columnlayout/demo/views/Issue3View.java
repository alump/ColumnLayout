package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.alump.columnlayout.ColumnLayout;

/**
 * Created by alump on 04/01/15.
 */
@ViewIdentifier("Issue3")
@ViewDescription("Test case for issue #3")
public class Issue3View extends CLView {

    private Component toggleVisibilityComponent;

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        Button toggle = new Button("Toggle visibility of label a2", e -> {
            toggleVisibilityComponent.setVisible(!toggleVisibilityComponent.isVisible());
        });
        menuBar.addComponent(toggle);
    }

    @Override
    protected ColumnLayout createLayout() {
        ColumnLayout layout = new ColumnLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setExpandingColumns(true);
        buildLayout(layout);
        return layout;
    }

    protected void buildLayout(ColumnLayout layout) {
        Label label = new Label("Label a1");
        layout.addComponent(label, 0);

        label = new Label("Label a2");
        toggleVisibilityComponent = label;
        label.setVisible(false);
        layout.addComponent(label, 0);

        label = new Label("Label a3");
        layout.addComponent(label, 0);

        label = new Label("Label b1");
        layout.addComponent(label, 1);

        label = new Label("Label b2");
        layout.addComponent(label, 1);

        label = new Label("Label b3");
        layout.addComponent(label, 1);
    }
}
