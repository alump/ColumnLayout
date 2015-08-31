package org.vaadin.alump.columnlayout.client.material;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.VButton;
import org.vaadin.alump.columnlayout.client.ColumnSlot;
import com.google.gwt.user.client.Event;

import java.util.logging.Logger;

/**
 * Material design column slot
 */
public class MaterialColumnSlot extends ColumnSlot implements FocusHandler, BlurHandler {
    public final static String TOOLTIP_CLASSNAME = "column-tooltip";
    public final static String TOOLTIP_EMPTY_CLASSNAME = "mode-empty";
    public final static String TOOLTIP_ERROR_CLASSNAME = "mode-error";
    public final static String TOOLTIP_DESCRIPTION_CLASSNAME = "mode-desc";
    public final static String UNIT_CLASSNAME = "column-units";
    public final static String FOCUS_CLASSNAME = "column-focus";

    private Element tooltipElement;
    private Element unitElement = null;

    private HandlerRegistration focusHandler;
    private HandlerRegistration blurHandler;

    private final static Logger LOGGER = Logger.getLogger(MaterialColumnSlot.class.getName());

    public interface MaterialColumnSlotEventHandler {
        void onTooltipClick(MaterialColumnSlot slot, NativeEvent clickEvent);
    }

    private MaterialColumnSlotEventHandler eventHandler = null;

    @Override
    public MaterialColumnSlot init(Widget child) {
        super.init(child);

        /* Attach to focus and blur signals if child provides those. */
        if(checkFocusListening(child)) {
            FocusWidget focusWidget = (FocusWidget)child;
            focusHandler = focusWidget.addFocusHandler(this);
            blurHandler = focusWidget.addBlurHandler(this);
        }

        tooltipElement = Document.get().createDivElement();
        tooltipElement.addClassName(TOOLTIP_CLASSNAME);
        getElement().appendChild(tooltipElement);

        return this;
    }

    public void setMaterialColumnSlotEventHandler(MaterialColumnSlotEventHandler handler) {
        eventHandler = handler;
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        DOM.sinkEvents(tooltipElement, Event.ONCLICK);
        DOM.setEventListener(tooltipElement, tooltipEventListener);

    }

    protected EventListener tooltipEventListener = new EventListener() {

        @Override
        public void onBrowserEvent(Event event) {
            if(event.getTypeInt() == Event.ONCLICK) {
                onTooltipClick(event);
            }
        }
    };

    protected void onTooltipClick(Event event) {
        if(eventHandler != null) {
            eventHandler.onTooltipClick(this, event);
        }
    }

    /**
     * Runs checks if focus and blur handlers should be attached to given child
     * @param widget Child widget
     * @return true if focus and blur should be connected
     */
    protected boolean checkFocusListening(Widget widget) {

        // If not focuswidget, there's no way to connect
        if(!(widget instanceof FocusWidget)) {
            return false;
        }

        // Ignore checkboxes and buttons
        if(widget instanceof ButtonBase || widget instanceof VButton) {
            return false;
        }

        return true;
    }

    @Override
    public void onDetach() {
        if(focusHandler != null) {
            focusHandler.removeHandler();
            focusHandler = null;
        }
        if(blurHandler != null) {
            blurHandler.removeHandler();
            blurHandler = null;
        }
        super.onDetach();
    }

    /**
     * Get unit element
     * @param create If true then unit element is created if not present
     * @return Unit element (can be null with false parameter)
     */
    protected Element getUnitElement(boolean create) {
        if(unitElement == null && create) {
            unitElement = Document.get().createDivElement();
            unitElement.addClassName(UNIT_CLASSNAME);
            unitElement.getStyle().setDisplay(Style.Display.NONE);
            getElement().appendChild(unitElement);
        }

        return unitElement;
    }

    /**
     * Define unit value shown next to child widget
     * @param unit Unit text
     * @return This instance (just to allow nice one liners)
     */
    public MaterialColumnSlot setUnit(String unit) {
        if(unit != null) {
            getUnitElement(true).setInnerText(unit);
            unitElement.getStyle().clearDisplay();
            updateUnitPosition();
        } else if(unitElement != null) {
            unitElement.removeFromParent();
            unitElement = null;
        }

        return this;
    }

    /**
     * Define "tooltip" data presented inside layout
     * @param description
     * @param errorMessage
     * @return This instance (just to allow nice one liners)
     */
    public MaterialColumnSlot setTooltip(String description, String errorMessage) {
        boolean hasError = errorMessage != null;
        String message = hasError ? errorMessage : description;

        if(message == null || message.isEmpty()) {
            tooltipElement.removeClassName(TOOLTIP_ERROR_CLASSNAME);
            tooltipElement.removeClassName(TOOLTIP_DESCRIPTION_CLASSNAME);
            tooltipElement.setInnerHTML("&nbsp;");
            tooltipElement.addClassName(TOOLTIP_EMPTY_CLASSNAME);
        } else {
            tooltipElement.setInnerHTML(message);
            tooltipElement.removeClassName(TOOLTIP_EMPTY_CLASSNAME);
            if(hasError) {
                tooltipElement.removeClassName(TOOLTIP_DESCRIPTION_CLASSNAME);
                tooltipElement.addClassName(TOOLTIP_ERROR_CLASSNAME);
            } else {
                tooltipElement.removeClassName(TOOLTIP_ERROR_CLASSNAME);
                tooltipElement.addClassName(TOOLTIP_DESCRIPTION_CLASSNAME);
            }
        }

        updateUnitPosition();
        return this;
    }

    /**
     * Update position of absolute unit element based on state of other components
     */
    protected void updateUnitPosition() {
        if(unitElement == null) {
            return;
        }
        int tooltipHeight = tooltipElement.getClientHeight();
        unitElement.getStyle().setBottom(tooltipHeight, Style.Unit.PX);
    }

    /**
     * Get tooltip element added by MaterialColumnSlot
     * @return Tooltip element
     */
    protected Element getTooltipElement() {
        return tooltipElement;
    }

    @Override
    public void onFocus(FocusEvent event) {
        getElement().addClassName(FOCUS_CLASSNAME);
    }

    @Override
    public void onBlur(BlurEvent event) {
        getElement().removeClassName(FOCUS_CLASSNAME);
    }
}
