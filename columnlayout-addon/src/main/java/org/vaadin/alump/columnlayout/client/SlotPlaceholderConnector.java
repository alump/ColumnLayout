package org.vaadin.alump.columnlayout.client;

import com.google.gwt.user.client.ui.HTML;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.columnlayout.SlotPlaceholder;
import org.vaadin.alump.columnlayout.client.share.SlotPlaceholderState;

/**
 * Client side implementation of dummy placeholder component
 */
@Connect(SlotPlaceholder.class)
public class SlotPlaceholderConnector extends AbstractComponentConnector {

    @Override
    protected void init() {
        super.init();
        //getWidget().setHTML("");
    }

    @Override
    public HTML getWidget() {
        return (HTML)super.getWidget();
    }

    @Override
    public SlotPlaceholderState getState() {
        return (SlotPlaceholderState)super.getState();
    }

}
