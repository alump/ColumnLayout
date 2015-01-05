package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.ColumnLayout;

@ViewIdentifier("Issue4")
@ViewDescription("Test case for issue #4")
public class Issue4View extends CLView {

    private final static String STYLE_ON = "mixintest2";
    private final static String STYLE_OFF = "mixintest1";

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        CheckBox toggle = new CheckBox("This column layout should have custom spacing");
        toggle.setImmediate(true);
        toggle.addValueChangeListener(e -> {
            if((Boolean)e.getProperty().getValue()) {
                getLayout().removeStyleName(STYLE_OFF);
                getLayout().addStyleName(STYLE_ON);
            } else {
                getLayout().removeStyleName(STYLE_ON);
                getLayout().addStyleName(STYLE_OFF);
            }
        });
        menuBar.addComponent(toggle);
    }

    @Override
    protected ColumnLayout createLayout() {
        ColumnLayout layout = new ColumnLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setExpandingColumns(true);
        layout.setStyleName(STYLE_OFF);

        Button button = new Button("Button A1");
        button.setWidth("100%");
        layout.addComponent(button, 0);

        button = new Button("Button A2");
        button.setWidth("100%");
        layout.addComponent(button, 0);

        TextField field = new TextField("Field A1");
        field.setWidth("100%");
        layout.addComponent(field, 0);

        button = new Button("Button B1");
        button.setWidth("100%");
        layout.addComponent(button, 1);

        field = new TextField("Field B1");
        field.setWidth("100%");
        layout.addComponent(field, 1);

        button = new Button("Button B2");
        button.setWidth("100%");
        layout.addComponent(button, 1);

        return layout;
    }
}
