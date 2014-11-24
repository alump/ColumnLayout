package org.vaadin.alump.columnlayout;

import com.vaadin.ui.AbstractComponent;
import org.vaadin.alump.columnlayout.client.share.SlotPlaceholderState;

/**
 * Placeholder is as simple component as possible to act as placeholder in slots not populated. Used by ColumnLayouts
 * are default placeholder component class. On client side this placeholder reserves one pixel in height dimension and
 * is invisible.
 */
@SuppressWarnings("serial")
public class SlotPlaceholder extends AbstractComponent {

    @Override
    protected SlotPlaceholderState getState() {
        return (SlotPlaceholderState)super.getState();
    }
}
