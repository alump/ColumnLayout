package org.vaadin.alump.columnlayout.client.material;

import com.google.gwt.user.client.ui.Widget;
import org.vaadin.alump.columnlayout.client.ColumnPanel;
import org.vaadin.alump.columnlayout.client.ColumnSlot;

/**
 * Material extensions to ColumnPanel
 */
public class MaterialColumnPanel extends ColumnPanel {

    public MaterialColumnPanel() {
        super();
        setWidgetSlotProvider(new WidgetSlotProvider() {

            @Override
            public ColumnSlot createWidgetSlot(Widget widget) {
                ColumnSlot slot = new MaterialColumnSlot();
                slot.init(widget);
                return slot;
            }
        });
    }

    /**
     * Update information usually presented by tooltip
     * @param widget Widget with data
     * @param description Description of widget
     * @param errorMessage Error message of widget
     */
    public void updateTooltip(Widget widget, String description, String errorMessage) {
        getSlot(widget).setTooltip(description, errorMessage);
    }

    @Override
    public MaterialColumnSlot getSlot(Widget widget) {
        return (MaterialColumnSlot)super.getSlot(widget);
    }
}
