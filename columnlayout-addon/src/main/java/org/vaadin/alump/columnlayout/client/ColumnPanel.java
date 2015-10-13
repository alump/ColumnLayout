package org.vaadin.alump.columnlayout.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Panel that can be used to construct layouts with columns
 */
public class ColumnPanel extends ComplexPanel {

    public final static String CONTENT_CLASSNAME = "column-panel-content";
    public final static String COLUMN_CLASSNAME = "column";
    public final static String SPACING_CLASSNAME = "spacing";
    public final static String MARGIN_TOP_CLASSNAME = "v-margin-top";
    public final static String MARGIN_RIGHT_CLASSNAME = "v-margin-right";
    public final static String MARGIN_BOTTOM_CLASSNAME = "v-margin-bottom";
    public final static String MARGIN_LEFT_CLASSNAME = "v-margin-left";
    public final static String WRAPPED_COLUMN_CLASSNAME = "wrapped";

    private int columnWidth = 300;
    private boolean columnExpand = false;
    private final List<Element> columnElements = new ArrayList<Element>();
    private Element contentElement;

    private final static Logger LOGGER = Logger.getLogger(ColumnPanel.class.getName());

    private WidgetSlotProvider widgetSlotProvider = new WidgetSlotProvider() {

        @Override
        public ColumnSlot createWidgetSlot(Widget widget) {
            return new ColumnSlot().init(widget);
        }
    };

    private final List<Element> spacers = new ArrayList<Element>();

    /**
     * Interface to be implemented by class providing slots used to wrap child widgets
     */
    public interface WidgetSlotProvider {
        /**
         * Just to allow overriding and custom slots
         * @param widget Widget that needs slot
         * @return Slot created for widget
         */
        ColumnSlot createWidgetSlot(Widget widget);
    }

    public ColumnPanel() {
        setElement(Document.get().createDivElement());

        contentElement = Document.get().createDivElement();
        contentElement.addClassName(CONTENT_CLASSNAME);
        getElement().appendChild(contentElement);
    }

    /**
     * Replace WidgetSlotProvider with custom implementation
     * @param provider Slot provider to be used
     */
    public void setWidgetSlotProvider(WidgetSlotProvider provider) {
        widgetSlotProvider = provider;
    }

    /**
     * Get current WidgetSlotProvider used by this panel
     * @return Slot provider used
     */
    public WidgetSlotProvider getWidgetSlotProvider() {
        return widgetSlotProvider;
    }

    /**
     * Get content element that has all slot elements and children
     * @return Content element
     */
    public Element getContentElement() {
        return contentElement;
    }

    /**
     * Removes all content of panel (children, columns...)
     */
    public void clear() {
        WidgetCollection children = getChildren();
        for(int i = children.size() - 1; i >= 0; --i) {
            children.get(i).removeFromParent();
        }
        getContentElement().removeAllChildren();
        spacers.clear();
        columnElements.clear();
    }

    /**
     * Add widget to given column
     * @param widget Widget added to panel
     * @param column Index of column where widget is added. Columns will be created automatically.
     */
    public void add(Widget widget, int column) {
        while(columnElements.size() <= column) {
            Element element = createColumnElement();
            if(columnElements.size() > 0) {
                Element spacing = Document.get().createDivElement();
                spacing.addClassName("v-spacing");
                getContentElement().appendChild(spacing);
                spacers.add(spacing);
            }
            getContentElement().appendChild(element);
            if(columnWidth > 0) {
                element.getStyle().setWidth(columnWidth, Style.Unit.PX);
            }
            columnElements.add(element);
        }

        Element columnElement = columnElements.get(column);
        ColumnSlot slot = widgetSlotProvider.createWidgetSlot(widget);
        super.add(slot, columnElement);
    }

    /**
     * Update caption of widget
     * @param widget Widget with caption
     * @param caption Caption of widget
     */
    public void updateCaption(Widget widget, String caption) {
        updateCaption(widget, caption, false);
    }

    /**
     * Update caption of widget
     * @param widget Widget with caption
     * @param caption Caption of widget
     * @param required If required indicator should be added
     */
    public void updateCaption(Widget widget, String caption, boolean required) {
        getSlot(widget).setCaption(caption, required);
    }

    /**
     * Define margins enabled
     * @param top If top margin is enabled
     * @param left If left margin is enabled
     * @param right If right margin is enabled
     * @param bottom If bottom margin is enabled
     */
    public void setMargins(boolean top, boolean right, boolean bottom, boolean left) {
        setStyleName(MARGIN_TOP_CLASSNAME, top);
        setStyleName(MARGIN_RIGHT_CLASSNAME, left);
        setStyleName(MARGIN_BOTTOM_CLASSNAME, right);
        setStyleName(MARGIN_LEFT_CLASSNAME, bottom);
    }

    /**
     * Define if spacing should be enabled
     * @param spacing
     */
    public void setSpacing(boolean spacing) {
        if(spacing) {
            addStyleName(SPACING_CLASSNAME);
        } else {
            removeStyleName(SPACING_CLASSNAME);
        }
    }

    @Override
    public void setWidth(String width) {
        super.setWidth(width);

        updateColumnWidth(columnWidth);
    }

    /**
     * Update width of columns
     * @param width Width of columns in pixels
     * @param columnExpand If columns should expand to extra space
     */
    public void updateColumnWidth(int width, boolean columnExpand) {
        setExpandingColumns(columnExpand);
        updateColumnWidth(width);
    }

    protected void clearSpacersDisplay() {
        for(Element element : spacers) {
            element.getStyle().clearDisplay();
        }
    }

    /**
     * Update width of columns
     * @param width Width of columns in pixels
     */
    protected void updateColumnWidth(int width) {

        int newWidth = width;

        if(columnExpand) {
            newWidth = calculateColumnWidth(width);
        }

        if(newWidth == columnWidth) {
            return;
        }

        columnWidth = newWidth;

        int lastTop = getContentElement().getAbsoluteTop();
        for(int i = 0; i < columnElements.size(); ++i) {
            Element element = columnElements.get(i);
            element.getStyle().setWidth(columnWidth, Style.Unit.PX);

            // Add style name if it looks like column has been wrapped
            element.removeClassName(WRAPPED_COLUMN_CLASSNAME);
            if(element.getAbsoluteTop() > getContentElement().getAbsoluteTop()) {
                element.addClassName(WRAPPED_COLUMN_CLASSNAME);
            }

            // Hide spacers at start of row
            if(lastTop != element.getAbsoluteTop() && i > 0) {
                spacers.get(i - 1).getStyle().setDisplay(Style.Display.NONE);
                lastTop = element.getAbsoluteTop();
            } else if(i > 0) {
                spacers.get(i - 1).getStyle().clearDisplay();
            }
        }
    }

    /**
     * Calculates width of column with helps of guide width
     * @param guideWidth Guide width for columns
     * @return Actual width of columns
     */
    protected int calculateColumnWidth(double guideWidth) {
        if(!isAttached()) {
            return (int)guideWidth;
        }

        double numberOfColumns = columnElements.size();
        if(numberOfColumns == 0) {
            return (int)guideWidth;
        }

        double spacingWidth = 0.0;

        // Calculate width of spacer
        if(!spacers.isEmpty()) {
            Element spacer = spacers.get(0);
            String display = spacer.getStyle().getDisplay();
            spacer.getStyle().clearDisplay();
            spacingWidth = spacer.getClientWidth();
            if(display != null) {
                spacer.getStyle().setProperty("display", display);
            }
        }

        // expanding when can not even fit columns
        while(numberOfColumns > 1.0
                && (guideWidth * numberOfColumns + spacingWidth * (numberOfColumns - 1.0)) >
                (double)(getContentElement().getClientWidth())) {
            numberOfColumns = numberOfColumns - 1.0;
        }

        double wastedSpace = spacingWidth * (numberOfColumns - 1.0);
        if(wastedSpace < 0.0) {
            wastedSpace = 0.0;
        }

        double availableSpace = (double)(getContentElement().getClientWidth()) - wastedSpace;
        double availableColumn = Math.floor(availableSpace / numberOfColumns);
        if(availableColumn > guideWidth) {
            return (int)availableColumn;
        } else {
            return (int)guideWidth;
        }
    }

    /**
     * Access slot with given widget
     * @param widget Child widget
     * @return Slot wrapping child
     */
    public ColumnSlot getSlot(Widget widget) {
        WidgetCollection slots = getChildren();
        for(int i = 0; i < slots.size(); ++i) {
            ColumnSlot slot = (ColumnSlot)slots.get(i);
            if(slot.getWidget() == widget) {
                return slot;
            }
        }
        return null;
    }

    /**
     * Creates new column element
     * @return Column element created
     */
    protected Element createColumnElement() {
        Element element = Document.get().createDivElement();
        element.addClassName(COLUMN_CLASSNAME);
        return element;
    }

    /**
     * Enable/Disable column expanding
     * @param expand
     */
    public void setExpandingColumns(boolean expand) {
        columnExpand = expand;
    }

    /**
     * If column expanding is enabled
     * @return
     */
    public boolean isExpandingColumns() {
        return columnExpand;
    }

}
