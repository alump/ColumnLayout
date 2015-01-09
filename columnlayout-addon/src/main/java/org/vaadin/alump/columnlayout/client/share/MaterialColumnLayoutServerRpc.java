package org.vaadin.alump.columnlayout.client.share;

import com.vaadin.shared.Connector;
import com.vaadin.shared.communication.ServerRpc;

/**
 * ServerRpc added by MaterialColumnLayout
 */
public interface MaterialColumnLayoutServerRpc extends ServerRpc {
    void onTooltipClicked(Connector child);
}
