package org.vaadin.alump.columnlayout.client.material;

import com.google.gwt.user.client.ui.Widget;
import org.vaadin.alump.columnlayout.client.ColumnPanel;
import org.vaadin.alump.columnlayout.client.ColumnSlot;

/**
 * Material extensions to ColumnPanel
 */
public class MaterialColumnPanel extends ColumnPanel implements MaterialColumnSlot.MaterialColumnSlotEventHandler {

    private MaterialColumnPanel.EventHandler eventHandler;

    public interface EventHandler {
        void onTooltipOfChildClicked(Widget child);
    }

    public MaterialColumnPanel() {
        super();
        setWidgetSlotProvider(new WidgetSlotProvider() {

            @Override
            public ColumnSlot createWidgetSlot(Widget widget) {
                MaterialColumnSlot slot = new MaterialColumnSlot();
                slot.init(widget);
                slot.setMaterialColumnSlotEventHandler(MaterialColumnPanel.this);
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

    @Override
    public void onTooltipClick(MaterialColumnSlot slot) {
        if(eventHandler != null) {
            eventHandler.onTooltipOfChildClicked(slot.getWidget());
        }
    }

    public void setMaterialColumnPanelEventHandler(MaterialColumnPanel.EventHandler handler) {
        eventHandler = handler;
    }
}
