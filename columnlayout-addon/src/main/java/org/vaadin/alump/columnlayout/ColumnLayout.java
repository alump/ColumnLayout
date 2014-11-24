package org.vaadin.alump.columnlayout;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.client.share.ColumnLayoutState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Column based layout. Client side will try to show content in columns defined. If it runs out of screen space it will
 * wrap columns to next "row".
 */
@SuppressWarnings("serial")
public class ColumnLayout extends AbstractLayout implements Layout.SpacingHandler, Layout.MarginHandler {

    private final Class<? extends AbstractComponent> DEFAULT_PLACEHOLDER_CLASS = SlotPlaceholder.class;
    private Class<? extends AbstractComponent> placeholderClass = DEFAULT_PLACEHOLDER_CLASS;

    @Override
    protected ColumnLayoutState getState() {
        return (ColumnLayoutState) super.getState();
    }

    /**
     * Adds component to first column
     * @param component Component added
     */
    @Override
    public void addComponent(Component component) {
        addComponent(component, 0);
    }

    /**
     * Adds component to given column
     * @param component Component added
     * @param columnIndex Column index, columns needed will be created automatically
     */
    public void addComponent(Component component, int columnIndex) {
        addComponent(component, getColumn(columnIndex), -1);
    }

    /**
     * Adds component to given column with given slot index
     * @param component Component added
     * @param columnIndex Column index, columns needed will be created automatically
     * @param slotIndex Slot index. If less than zero will be added to end of column.
     */
    public void addComponent(Component component, int columnIndex, int slotIndex) {
        addComponent(component, getColumn(columnIndex), slotIndex);
    }

    /**
     * Set component to given column with given slot index.
     * @param component Component set to given index
     * @param columnIndex Column index. Needed columns will be created automatically
     * @param slotIndex Slot index. If column does not have enough components, placeholder will be used to fill earlier
     *                  slots. If slot already has a component, it will be replaced.
     */
    public void setComponent(Component component, int columnIndex, int slotIndex) {
        if(slotIndex < 0) {
            throw new IllegalArgumentException("Invalid slot index: " + slotIndex);
        }
        setComponent(component, getColumn(columnIndex), slotIndex);
    }

    /**
     * Add component
     * @param component Component added
     * @param column Column where component is added
     * @param slotIndex Index inside column
     */
    protected void addComponent(Component component, ColumnLayoutState.ColumnState column, int slotIndex) {
        super.addComponent(component);

        if(slotIndex < 0 || column.children.size() <= slotIndex) {
            column.children.add(component);
        } else {
            column.children.add(slotIndex, component);
        }
    }

    /**
     * Set component
     * @param component Component set to given index
     * @param column Column index
     * @param slotIndex Slot index
     */
    protected void setComponent(Component component, ColumnLayoutState.ColumnState column, int slotIndex) {

        // Replace component if already defined
        if(slotIndex < column.children.size()) {
            replaceComponent((Component) column.children.get(slotIndex), component);

        // Add component if not defined yet
        } else {
            // Add placeholders if needed
            while (slotIndex > column.children.size()) {
                addComponent(createPlaceholder(
                        getState().columns.indexOf(column), column.children.size()), column, -1);
            }

            addComponent(component, column, slotIndex);
        }
    }

    /**
     * Method used to create cell placeholder. To be overridden if simple reflections with default constructors are not
     * enough.
     * @param column Column index
     * @param cell Cell index
     * @return New instance of placeholder
     */
    protected Component createPlaceholder(int column, int cell) {
        Component placeHolder;
        try {
            placeHolder = placeholderClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize placeholder class instance", e);
        }
        placeHolder.addStyleName("placeholder");
        placeHolder.addStyleName("placeholder-" + column + "-" + cell);
        return placeHolder;
    }

    /**
     * Define placeholder component class used to fill empty cells.
     * @param klass Component class used to fill empty cells. Most have default constructor. If null given default
     *              placeholder class will be used.
     */
    public void setPlaceholderClass(Class<? extends AbstractComponent> klass) {
        if(klass != null) {
            placeholderClass = klass;
        } else {
            placeholderClass = DEFAULT_PLACEHOLDER_CLASS;
        }
    }

    /**
     * Get placeholder Component class used to fill empty cells
     * @return Placeholder Component class
     */
    public Class<? extends AbstractComponent> getPlaceholderClass() {
        return placeholderClass;
    }

    @Override
    public void removeComponent(Component component) {
        ColumnLayoutState.ColumnState column = findColumn(component);
        if(column == null) {
            return;
        }

        column.children.remove(component);
        super.removeComponent(component);
    }

    @Override
    public void removeAllComponents() {
        getState().columns.clear();
        super.removeAllComponents();
    }

    /**
     * Find column with given child component
     * @param child Child component
     * @return Column with child, or null if not found
     */
    protected ColumnLayoutState.ColumnState findColumn(Component child) {
        for(ColumnLayoutState.ColumnState column : getState().columns) {
            for(Connector columnChild : column.children) {
                if(columnChild == child) {
                    return column;
                }
            }
        }
        return null;
    }

    /**
     * Get column with given index. New columns are created to match if column with given index is not found
     * @param index Index of column wanted
     * @return Column at given index
     */
    protected ColumnLayoutState.ColumnState getColumn(int index) {
        if(index < 0) {
            throw new IllegalArgumentException("Invalid column index: " + index);
        }

        while(getState().columns.size() <= index) {
            getState().columns.add(new ColumnLayoutState.ColumnState());
        }

        return getState().columns.get(index);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {

        // Ignore if component is replaced with itself
        if(oldComponent == newComponent) {
            return;
        }

        // Remove new component from children to allow position calculations
        if(newComponent.getParent() == this) {
            removeComponent(newComponent);
        }

        // Resolve column
        ColumnLayoutState.ColumnState column = findColumn(oldComponent);
        if(column == null) {
            throw new IllegalArgumentException("Old component not found");
        }

        // Resolve position inside column
        int index = column.children.indexOf(oldComponent);

        // Remove old and add new to same position in column
        removeComponent(oldComponent);
        addComponent(newComponent, column, index);
    }

    @Override
    public int getComponentCount() {
        return getChildren().size();
    }

    @Override
    public Iterator<Component> iterator() {
        return getChildren().iterator();
    }

    protected List<Component> getChildren() {
        List<Component> components = new ArrayList<Component>();
        for(ColumnLayoutState.ColumnState column : getState().columns) {
            for(Connector columnChild : column.children) {
                components.add((Component)columnChild);
            }
        }
        return components;
    }

    @Override
    public void setSpacing(boolean spacing) {
        getState().spacing = spacing;
    }

    @Override
    public boolean isSpacing() {
        return getState().spacing;
    }

    @Override
    public void setMargin(boolean enabled) {
        getState().margins[0] = getState().margins[1] = getState().margins[2] = getState().margins[3] = enabled;
    }

    @Override
    public void setMargin(MarginInfo marginInfo) {
        getState().margins[0] = marginInfo.hasTop();
        getState().margins[1] = marginInfo.hasRight();
        getState().margins[2] = marginInfo.hasBottom();
        getState().margins[3] = marginInfo.hasLeft();
    }

    @Override
    public MarginInfo getMargin() {
        return new MarginInfo(getState().margins[0], getState().margins[1], getState().margins[2],
                getState().margins[3]);
    }

    /**
     * Define if columns should expand to fill empty space
     * @param expand true to expand, false to not
     */
    public void setExpandingColumns(boolean expand) {
        getState().expandColumns = expand;
    }

    /**
     * If ColumnLayout will expand columns to fill all available space
     * @return true if expanding, false if not
     */
    public boolean isExpandingColumns() {
        return getState().expandColumns;
    }

    /**
     * Define column width in pixels. If columns are expanding this value is used as minimum width of column.
     * @param pixels Width of column in pixels
     */
    public void setColumnWidth(int pixels) {
        getState().columnWidth = pixels;
    }

    /**
     * Get column width
     * @return Width of column in pixels
     */
    public int getColumnWidth() {
        return getState().columnWidth;
    }

}
