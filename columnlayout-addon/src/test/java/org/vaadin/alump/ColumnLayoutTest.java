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

    /**
     * Trying to really verify that children management works as it should by removing, adding and setting component
     */
    @Test
    public void reallyTestOutChildren() {
        ColumnLayout layout = new ColumnLayout();
        Label label1 = new Label("1");
        layout.addComponent(label1, 0, 0);
        Label label2 = new Label("2");
        layout.addComponent(label2, 0, -1);
        Label label3 = new Label("3");
        layout.addComponent(label3, 0, 2);

        Iterator<Component> iter = layout.iterator();
        Assert.assertSame(label1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());

        Label label0 = new Label("0");
        layout.addComponent(label0, 0, 0);
        iter = layout.iterator();
        Assert.assertSame(label0, iter.next());
        Assert.assertSame(label1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());

        Label label4 = new Label("4");
        layout.setComponent(label4, 0, 4);
        iter = layout.iterator();
        Assert.assertSame(label0, iter.next());
        Assert.assertSame(label1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());
        Assert.assertSame(label4, iter.next());

        layout.removeComponent(label1);
        iter = layout.iterator();
        Assert.assertSame(label0, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());
        Assert.assertSame(label4, iter.next());

        Label newLabel1 = new Label("new 1");
        layout.setComponent(newLabel1, 0, 0);
        iter = layout.iterator();
        Assert.assertSame(newLabel1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());
        Assert.assertSame(label4, iter.next());

        Label label5 = new Label("5");
        layout.setComponent(label5, 0, 4);
        iter = layout.iterator();
        Assert.assertSame(newLabel1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(label3, iter.next());
        Assert.assertSame(label4, iter.next());
        Assert.assertSame(label5, iter.next());

        Label newLabel3 = new Label("new 3");
        layout.setComponent(newLabel3, 0, 2);
        iter = layout.iterator();
        Assert.assertSame(newLabel1, iter.next());
        Assert.assertSame(label2, iter.next());
        Assert.assertSame(newLabel3, iter.next());
        Assert.assertSame(label4, iter.next());
        Assert.assertSame(label5, iter.next());
    }
}
