package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
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

        Label info = new Label("ColumnLayout is an UI component add-on for Vaadin 7.3+ and Valo based themes. It " +
                "provides alternative for Horizontal+VerticalLayout and GridLayout usage when building columned " +
                "layouts.");
        panelLayout.addComponent(info);

        Link github = new Link("Project in GitHub", new ExternalResource("https://github.com/alump/ColumnLayout"));
        panelLayout.addComponent(github);

        panelLayout.addComponent(new Button(DemoUI.getViewDescription(BasicView.class), e -> navigateTo(BasicView.class)));
        panelLayout.addComponent(new Button(DemoUI.getViewDescription(MaterialView.class), e ->  navigateTo(MaterialView.class)));
    }


}
