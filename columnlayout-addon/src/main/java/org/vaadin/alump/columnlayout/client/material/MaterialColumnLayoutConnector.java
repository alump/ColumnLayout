package org.vaadin.alump.columnlayout.client.material;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.columnlayout.client.ColumnLayoutConnector;
import org.vaadin.alump.columnlayout.client.ColumnPanel;
import org.vaadin.alump.columnlayout.client.ColumnSlot;

/**
 * Material design extensions to ColumnLayout on client side
 */
@Connect(org.vaadin.alump.columnlayout.material.MaterialColumnLayout.class)
public class MaterialColumnLayoutConnector extends ColumnLayoutConnector {

    @Override
    public MaterialColumnPanel getWidget() {
        return (MaterialColumnPanel)super.getWidget();
    }

    @Override
    public MaterialColumnLayoutState getState() {
        return (MaterialColumnLayoutState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        updateUnits();
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event) {
        super.onConnectorHierarchyChange(event);

        updateUnits();
    }

    protected void updateUnits() {
        for(ComponentConnector child : getChildComponents()) {
            if(getState().unitMap.containsKey(child)) {
                getWidget().getSlot(child.getWidget()).setUnit(getState().unitMap.get(child));
            } else {
                getWidget().getSlot(child.getWidget()).setUnit(null);
            }
        }
    }

    @Override
    protected void updateChildState(StateChangeEvent event, ComponentConnector child) {
        super.updateChildState(event, child);

        if(event == null || event.hasPropertyChanged("description") || event.hasPropertyChanged("errorMessage")){
            getWidget().updateTooltip(child.getWidget(), child.getState().description, child.getState().errorMessage);
        }
    }
}
