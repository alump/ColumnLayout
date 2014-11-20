package org.vaadin.alump;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import junit.framework.Assert;
import org.junit.Test;
import org.vaadin.alump.columnlayout.ColumnLayout;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// JUnit tests here
public class ColumnLayoutTest {

	@Test
	public void thisAlwaysPasses() {
		Assert.assertEquals(true, true);
	}

    @Test
    public void childMapping() {
        ColumnLayout layout = new ColumnLayout();
        Set<Component> added = new HashSet<Component>();

        for(int i = 0; i < 10; ++i) {
            Label label = new Label("" + i);
            layout.addComponent(label);
            added.add(label);
        }

        Iterator<Component> iter = layout.iterator();
        while(iter.hasNext()) {
            Component child = iter.next();
            Assert.assertTrue(added.remove(child));
        }

        Assert.assertTrue(added.isEmpty());
    }

    @Test
    public void removeAllComponents() {
        ColumnLayout layout = new ColumnLayout();

        for(int i = 0; i < 10; ++i) {
            layout.addComponent(new Label("" + i));
        }

        layout.removeAllComponents();

        Assert.assertFalse(layout.iterator().hasNext());
    }

    @Test
    public void replace() {
        ColumnLayout layout = new ColumnLayout();
        Label label1 = new Label("1");
        layout.addComponent(label1);
        Label label2 = new Label("2");
        layout.replaceComponent(label1, label2);
        Assert.assertNotSame(layout, label1.getParent());
        Assert.assertSame(layout, label2.getParent());
        Iterator<Component> iter = layout.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertSame(iter.next(), label2);
        Assert.assertFalse(iter.hasNext());
    }
}
