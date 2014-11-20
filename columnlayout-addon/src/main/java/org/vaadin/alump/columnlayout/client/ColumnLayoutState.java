package org.vaadin.alump.columnlayout.client;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AbstractLayoutState;
import com.vaadin.shared.ui.MarginInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Client side state of ColumnLayout component
 */
public class ColumnLayoutState extends AbstractLayoutState {

    {
        primaryStyleName = "column-layout";
    }

    /**
     * Width of columns
     */
    public int columnWidth = 300;

    /**
     * If columns and widgets should have spacing
     */
    public boolean spacing = false;

    /**
     * State of column
     */
    public static class ColumnState implements Serializable {
        public List<Connector> children = new ArrayList<Connector>();
    }

    /**
     * Current columns
     */
    public List<ColumnState> columns = new ArrayList<ColumnState>();

    /**
     * Margin setup
     */
    public Boolean margins[] = new Boolean[]{ false, false, false, false };

    /**
     * If columns should expand to fill the empty space
     */
    public boolean expandColumns = true;
}
