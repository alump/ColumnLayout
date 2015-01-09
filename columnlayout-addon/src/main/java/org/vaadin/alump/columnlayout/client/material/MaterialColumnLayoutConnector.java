package org.vaadin.alump.columnlayout.client.material;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.columnlayout.client.ColumnLayoutConnector;
import org.vaadin.alump.columnlayout.client.share.MaterialColumnLayoutServerRpc;
import org.vaadin.alump.columnlayout.client.share.MaterialColumnLayoutState;

import java.util.logging.Logger;

/**
 * Material design extensions to ColumnLayout on client side
 */
@Connect(org.vaadin.alump.columnlayout.material.MaterialColumnLayout.class)
public class MaterialColumnLayoutConnector extends ColumnLayoutConnector {

    private final static Logger LOGGER = Logger.getLogger(MaterialColumnLayoutConnector.class.getName());

    @Override
    public MaterialColumnPanel getWidget() {
        return (MaterialColumnPanel)super.getWidget();
    }

    @Override
    public MaterialColumnLayoutState getState() {
        return (MaterialColumnLayoutState)super.getState();
    }

    protected void init() {
        super.init();
        getWidget().setMaterialColumnPanelEventHandler(panelEventHandler);
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

    private final MaterialColumnPanel.EventHandler panelEventHandler = new MaterialColumnPanel.EventHandler() {
        @Override
        public void onTooltipOfChildClicked(Widget childWidget) {
            if(getState().listenTooltipClicks) {
                ComponentConnector childConnector = getChildWithWidget(childWidget);
                if(childConnector != null) {
                    getRpcProxy(MaterialColumnLayoutServerRpc.class).onTooltipClicked(childConnector);
                } else {
                    LOGGER.warning("Failed to resolve child for clicked tooltip");
                }
            }
        }
    };

    private ComponentConnector getChildWithWidget(Widget widget) {
        for(ComponentConnector connector : getChildComponents()) {
            if(connector.getWidget() == widget) {
                return connector;
            }
        }
        return null;
    }
}
