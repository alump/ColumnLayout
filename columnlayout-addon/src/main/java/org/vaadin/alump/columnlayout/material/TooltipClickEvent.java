package org.vaadin.alump.columnlayout.material;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;

/**
 * Event given when tooltip of MaterialColumnLayout is clicked
 */
public class TooltipClickEvent {
    private final Component component;
    private final MouseEventDetails details;

    public static final int UNKNOWN_COORDINATE = -1;

    public TooltipClickEvent(Component component, MouseEventDetails details) {
        this.component = component;
        this.details = details;
    }

    /**
     * Get owner child component of tooltip that was clicked
     * @return Child component which tooltip was clicked
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Cursor X in client's coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getClientX() {
        if (null != details) {
            return details.getClientX();
        } else {
            return UNKNOWN_COORDINATE;
        }
    }

    /**
     * Cursor Y in client's coordinate
     * @return Y coordinate, or -1 if undefined
     */
    public int getClientY() {
        if (null != details) {
            return details.getClientY();
        } else {
            return UNKNOWN_COORDINATE;
        }
    }

    /**
     * Cursor X in relative coordinate (to layout)
     * @return X coordinate, or -1 if undefined
     */
    public int getRelativeX() {
        if (null != details) {
            return details.getRelativeX();
        } else {
            return UNKNOWN_COORDINATE;
        }
    }

    /**
     * Cursor Y in relative coordinate (to layout)
     * @return Y coordinate, or -1 if undefined
     */
    public int getRelativeY() {
        if (null != details) {
            return details.getRelativeY();
        } else {
            return UNKNOWN_COORDINATE;
        }
    }

    /**
     * Check if Alt key was pressed down when clicked
     * @return true if Alt key was pressed down. false if not or undefined
     */
    public boolean isAltKey() {
        if (null != details) {
            return details.isAltKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Ctrl key was pressed down when clicked
     * @return true if Ctrl key was pressed down. false if not or undefined
     */
    public boolean isCtrlKey() {
        if (null != details) {
            return details.isCtrlKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Meta key was pressed down when clicked
     * @return true if Meta key was pressed down. false if not or undefined
     */
    public boolean isMetaKey() {
        if (null != details) {
            return details.isMetaKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Shift key was pressed down when clicked
     * @return true if Shift key was pressed down. false if not or undefined
     */
    public boolean isShiftKey() {
        if (null != details) {
            return details.isShiftKey();
        } else {
            return false;
        }
    }
}
