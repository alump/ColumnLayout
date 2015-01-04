package org.vaadin.alump.columnlayout.client;

import com.google.web.bindery.event.shared.HandlerRegistration;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.DirectionalManagedLayout;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.client.ui.AbstractLayoutConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.client.share.ColumnLayoutState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Client side connector for ColumnLayout
 */
@Connect(ColumnLayout.class)
public class ColumnLayoutConnector extends AbstractLayoutConnector implements DirectionalManagedLayout {

    private final static Logger LOGGER = Logger.getLogger(ColumnLayoutConnector.class.getName());

    private final List<HandlerRegistration> stateChangeHandlers = new ArrayList<HandlerRegistration>();

    @Override
    public ColumnLayoutState getState() {
        return (ColumnLayoutState)super.getState();
    }

    public ColumnPanel getWidget() {
        return (ColumnPanel)super.getWidget();
    }

    @Override
    protected void init() {
        super.init();
        getLayoutManager().registerDependency(this, getWidget().getContentElement());
    }

    @Override
    public void onUnregister() {
        // Remove all state change handlers
        for(HandlerRegistration handler : stateChangeHandlers) {
            handler.removeHandler();
        }
        stateChangeHandlers.clear();

        getLayoutManager().unregisterDependency(this, getWidget().getContentElement());
        super.onUnregister();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        getWidget().setMargins(getState().margins[0], getState().margins[1],
                getState().margins[2], getState().margins[3]);
        getWidget().setSpacing(getState().spacing);
        getWidget().updateColumnWidth(getState().columnWidth, getState().expandColumns);
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event) {
        // Remove old state change handlers
        for(HandlerRegistration handler : stateChangeHandlers) {
            handler.removeHandler();
        }
        stateChangeHandlers.clear();

        // Remove all widgets
        getWidget().clear();

        // Reconstruct widgets and handlers
        for(int i = 0; i < getState().columns.size(); ++i) {
            ColumnLayoutState.ColumnState column = getState().columns.get(i);
            for(Connector child : column.children) {
                ComponentConnector cc = (ComponentConnector) child;
                // Hidden children are listed in state as null, ignore those
                if(cc != null) {
                    stateChangeHandlers.add(cc.addStateChangeHandler(childStateListener));
                    getWidget().add(cc.getWidget(), i);
                    updateChildState(null, cc);
                }
            }
        }

        getWidget().updateColumnWidth(getState().columnWidth, getState().expandColumns);
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        getWidget().updateCaption(connector.getWidget(), connector.getState().caption);
    }

    private StateChangeEvent.StateChangeHandler childStateListener = new StateChangeEvent.StateChangeHandler() {

        @Override
        public void onStateChanged(StateChangeEvent event) {
            ComponentConnector cc = (ComponentConnector)event.getConnector();
            if(cc.getParent() != ColumnLayoutConnector.this) {
                LOGGER.severe("StateChangeHandler connection left behind!");
                cc.removeStateChangeHandler(this);
                return;
            }

            updateChildState(event, cc);
        }
    };

    /**
     *
     * @param event Can be null!
     * @param child
     */
    protected void updateChildState(StateChangeEvent event, ComponentConnector child) {
        //TODO: optimize, only if changed
        if(child.delegateCaptionHandling()) {
            boolean required = false;
            if(child instanceof AbstractFieldConnector) {
                required = ((AbstractFieldConnector)child).getState().required;
            }
            getWidget().updateCaption(child.getWidget(), child.getState().caption, required);
        } else {
            getWidget().updateCaption(child.getWidget(), null);
        }
    }

    @Override
    public void layoutVertically() {
        // ignore
    }

    @Override
    public void layoutHorizontally() {
        getWidget().updateColumnWidth(getState().columnWidth, getState().expandColumns);
    }
}
