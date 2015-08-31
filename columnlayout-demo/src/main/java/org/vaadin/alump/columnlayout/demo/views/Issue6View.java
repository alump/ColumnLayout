package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.material.MaterialColumnLayout;
import org.vaadin.alump.columnlayout.material.TooltipClickEvent;
import org.vaadin.alump.columnlayout.material.TooltipClickListener;

/**
 * Tests tooltip clicking and html in errors and descriptions
 */
@ViewIdentifier("Issue6")
@ViewDescription("Test case for issue #6")
public class Issue6View extends CLMaterialView implements TooltipClickListener {
    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        CheckBox doNotListenClicks = new CheckBox("Do not listen tooltip clicks");
        doNotListenClicks.setImmediate(true);
        doNotListenClicks.addValueChangeListener(e -> {
            MaterialColumnLayout layout = (MaterialColumnLayout) getLayout();
            if((Boolean)e.getProperty().getValue()) {
                layout.removeTooltipClickListener(this);
            } else {
                layout.addTooltipClickListener(this);
            }
        });
        menuBar.addComponent(doNotListenClicks);
    }

    @Override
    protected ColumnLayout createLayout() {
        MaterialColumnLayout layout = new MaterialColumnLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addTooltipClickListener(this);

        TextField field = new TextField("Text Field");
        field.setInputPrompt("Something");
        field.setWidth("100%");
        field.setDescription(FontAwesome.QUESTION_CIRCLE.getHtml() + " This should be <b>BOLD</b>");
        layout.addComponent(field);
        layout.addComponent(field, 0);

        DateField date = new DateField("Date Field");
        date.setWidth("100%");
        date.setDescription("Should not be shown");
        date.setComponentError(new ErrorMessage() {
            @Override
            public ErrorLevel getErrorLevel() {
                return ErrorLevel.ERROR;
            }

            @Override
            public String getFormattedHtmlMessage() {
                return FontAwesome.WARNING.getHtml()
                        + " Lorem ipsum "
                        + "<span style=\"text-decoration: underline; cursor: pointer; color: blue;\">Click me!</span>";
            }
        });
        layout.addComponent(date, 1);

        Button button = new Button("Button", e -> Notification.show("Button clicked"));
        button.setDescription("✯ Normal text");
        layout.addComponent(button, 0);

        return layout;
    }

    @Override
    public void onTooltipClicked(TooltipClickEvent event) {
        Notification.show("Tooltip of " + event.getComponent().getCaption() + " clicked");
    }
}
