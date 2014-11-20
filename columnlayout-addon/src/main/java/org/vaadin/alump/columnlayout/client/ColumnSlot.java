package org.vaadin.alump.columnlayout.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Initial implementation of slots used to wrap child widgets in ColumnPanel
 */
public class ColumnSlot extends SimplePanel {
    private Widget child;
    private Element captionElement;

    public final static String SLOT_CLASSNAME = "column-slot";
    public final static String CAPTION_CLASSNAME = "v-caption";
    public final static String CAPTION_REQUIRED_CLASSNAME = "v-required";

    public ColumnSlot() {
        setElement(Document.get().createDivElement());
        setStyleName(SLOT_CLASSNAME);
    }

    /**
     * Called to initialize slot. All constructing (except root) should be done in this to allow overriding
     * @param child Widget inside slot
     * @return This instance (just to allow nice one liners)
     */
    public ColumnSlot init(Widget child) {
        this.child = child;

        captionElement = Document.get().createDivElement();
        captionElement.addClassName(CAPTION_CLASSNAME);
        getElement().appendChild(captionElement);

        setWidget(child);

        return this;
    }

    /**
     * Define caption shown
     * @param caption Caption text (if null, caption is hidden)
     * @param required Used with fields to show that this component is required
     * @return This instance (just to allow nice one liners)
     */
    public ColumnSlot setCaption(String caption, boolean required) {
        captionElement.setInnerText(caption);
        if(caption == null) {
            captionElement.setInnerText("");
            captionElement.getStyle().setDisplay(Style.Display.NONE);
        } else {
            captionElement.setInnerText(caption);
            captionElement.getStyle().clearDisplay();
        }
        if(required) {
            captionElement.addClassName(CAPTION_REQUIRED_CLASSNAME);
        } else {
            captionElement.removeClassName(CAPTION_REQUIRED_CLASSNAME);
        }

        return this;
    }

    /**
     * Get caption element of this slot
     * @return Caption element
     */
    protected Element getCaptionElement() {
        return captionElement;
    }
}
