package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.demo.components.CustomPlaceholder;

/**
 * View testing placeholders and setting components
 */
@ViewIdentifier("Issue2")
@ViewDescription("Test case for issue #2")
public class Issue2View extends CLView {

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        CheckBox useCustomPlaceholder = new CheckBox("Use custom placeholder");
        useCustomPlaceholder.setDescription("Use custom placeholder class or default, will rebuild layout");
        useCustomPlaceholder.addValueChangeListener(e -> {
            if (e.getValue()) {
                getLayout().setPlaceholderClass(CustomPlaceholder.class);
            } else {
                getLayout().setPlaceholderClass(null);
            }
            rebuildLayout(getLayout());
        });
        menuBar.addComponent(useCustomPlaceholder);

        CheckBox highlight = new CheckBox("Highlight");
        highlight.setDescription("Highlight all placeholders");
        highlight.addValueChangeListener(e -> {
            if ((Boolean) e.getValue()) {
                getLayout().addStyleName("highlight-placeholders");
            } else {
                getLayout().removeStyleName("highlight-placeholders");
            }
        });
        menuBar.addComponent(highlight);

    }

    @Override
    protected ColumnLayout createLayout() {
        ColumnLayout layout = new ColumnLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setExpandingColumns(true);
        rebuildLayout(layout);
        return layout;
    }


    private void rebuildLayout(ColumnLayout layout) {
        layout.removeAllComponents();

        for(int i = 0; i < 10; ++i) {
            int column = i % 2;
            int slot = i;
            TextField field = new TextField("Item: " + column + " " + slot);
            field.setWidth("100%");
            layout.setComponent(field, column, slot);
        }
    }
}
