package org.vaadin.alump.columnlayout.client.share;

import com.vaadin.shared.Connector;
import org.vaadin.alump.columnlayout.client.share.ColumnLayoutState;

import java.util.HashMap;
import java.util.Map;

/**
 * State of MaterialColumnLayout
 */
public class MaterialColumnLayoutState extends ColumnLayoutState {

    /**
     * Unit string map
     */
    public Map<Connector, String> unitMap = new HashMap<Connector, String>();

    /**
     * If client side should listen tooltip clicks and notify server
     */
    public boolean listenTooltipClicks = false;
}
