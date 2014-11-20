package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.alump.columnlayout.demo.DemoUI;

/**
 * Created by alump on 20/11/14.
 */
@ViewIdentifier("")
public class MenuView extends AbstractView {

    @Override
    protected void construct() {
        Panel panel = new Panel();
        panel.setSizeFull();
        addComponent(panel);

        VerticalLayout panelLayout = new VerticalLayout();
        panelLayout.setMargin(true);
        panelLayout.setSpacing(true);
        panelLayout.setWidth("100%");
        panel.setContent(panelLayout);

        panelLayout.addComponent(new Button(DemoUI.getViewDescription(BasicView.class), e -> navigateTo(BasicView.class)));
        panelLayout.addComponent(new Button(DemoUI.getViewDescription(MaterialView.class), e ->  navigateTo(MaterialView.class)));
    }


}
