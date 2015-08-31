package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.material.MaterialColumnLayout;
import org.vaadin.alump.columnlayout.notooltip.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * View testing MaterialColumnLayout
 * @see org.vaadin.alump.columnlayout.material.MaterialColumnLayout
 */
@ViewIdentifier("Material")
@ViewDescription("Material design extension of ColumnLayout")
public class MaterialView extends CLMaterialView {

    private Component swapComponent;
    private TextField unit;
    private ComboBox loading;

    private final Set<Component> fields = new HashSet<>();

    @Override
    protected ColumnLayout createLayout() {
        MaterialColumnLayout  columnLayout = new MaterialColumnLayout();
        columnLayout.setWidth(100, Unit.PERCENTAGE);
        columnLayout.setSpacing(true);
        columnLayout.setMargin(true);

        TextField textField = new NoTooltipTextField();
        fields.add(textField);
        textField.setWidth("100%");
        textField.setImmediate(true);
        textField.setCaption("Email");
        textField.setInputPrompt("email here");
        textField.setDescription("Input must be valid email");
        textField.addValidator(new EmailValidator("Not valid email address"));
        columnLayout.addComponent(textField);

        final ComboBox cbox = new NoTooltipComboBox();
        fields.add(cbox);
        cbox.setWidth("100%");
        cbox.setImmediate(true);
        cbox.setCaption("Select any");
        cbox.addItem("foo");
        cbox.addItem("bar");
        cbox.setRequired(true);
        columnLayout.addComponent(cbox, 1);

        final ComboBox cbox2 = new NoTooltipComboBox();
        fields.add(cbox2);
        cbox2.setWidth("100%");
        cbox2.setImmediate(true);
        cbox2.setCaption("Select foo");
        cbox2.setDescription("Bar is no good!");
        cbox2.addItem("foo");
        cbox2.addItem("bar");
        cbox2.setValue("bar");
        cbox2.setTextInputAllowed(false);
        cbox2.setNewItemsAllowed(false);
        cbox2.setNullSelectionAllowed(false);
        cbox2.addValidator(new Validator() {
            @Override
            public void validate(Object o) throws InvalidValueException {
                if(o == null || !o.equals("foo")) {
                    throw new InvalidValueException("This is no foo!");
                }
            }
        });
        columnLayout.addComponent(cbox2, 1);

        DateField dateField = new NoTooltipDateField();
        fields.add(dateField);
        dateField.setWidth("100%");
        dateField.setImmediate(true);
        dateField.setValue(new Date());
        dateField.setCaption("Date picker");
        dateField.setDescription("Past is bad");
        dateField.addValidator(new Validator() {
            @Override
            public void validate(Object o) throws InvalidValueException {
                if(((Date)o).getTime() < new Date().getTime()) {
                    throw new InvalidValueException("This value is in the past!");
                }
            }
        });
        columnLayout.addComponent(dateField, 0);

        TextArea textArea = new NoTooltipTextArea();
        fields.add(textArea);
        textArea.setWidth("100%");
        textArea.setRows(6);
        textArea.setCaption("Your life story");
        textArea.setInputPrompt("Write your life story here");
        columnLayout.addComponent(textArea, 2);

        CheckBox checkBox = new NoTooltipCheckBox();
        fields.add(checkBox);
        checkBox.setCaption("I'm checkbox, hello!");
        checkBox.setDescription("Also checkbox can have description");
        columnLayout.addComponent(checkBox, 0);

        Label label = new NoTooltipLabel("I'm just a label");
        label.setCaption("Label can be also in material layout");
        label.setComponentError(new ErrorMessage() {
            @Override
            public ErrorLevel getErrorLevel() {
                return ErrorLevel.ERROR;
            }

            @Override
            public String getFormattedHtmlMessage() {
                return "Also label can have an error!";
            }
        });
        columnLayout.addComponent(label, 0);

        unit = new NoTooltipTextField();
        fields.add(unit);
        unit.setWidth("100%");
        columnLayout.addComponent(unit, 1);
        unit.setCaption("Unit test");
        columnLayout.setComponentUnit(unit, "units");

        Button submit = new Button("Submit", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                cbox.setRequiredError("You must provide!");
                unit.setDescription("This should have units");
                if(swapComponent != null) {
                    getLayout().replaceComponent(swapComponent, createSwapCombobox());
                    fields.add(swapComponent);
                    swapComponent = null;
                }
                loading.removeStyleName(MaterialColumnLayout.LOADING_FIELD_STYLE_NAME);
                loading.setEnabled(true);
                loading.setValue("Yes");
                loading.setDescription("");
            }
        });
        fields.add(submit);
        columnLayout.addComponent(submit, 2);

        ProgressBar spinner = new ProgressBar();
        fields.add(spinner);
        swapComponent = spinner;
        spinner.setDescription("Loading meaning of life");
        spinner.setIndeterminate(true);
        columnLayout.addComponent(spinner, 2);

        loading = new NoTooltipComboBox();
        fields.add(loading);
        loading.setCaption("Loading state with CSS");
        loading.addStyleName(MaterialColumnLayout.LOADING_FIELD_STYLE_NAME);
        loading.setDescription("Loading data...");
        loading.setWidth("100%");
        loading.addItem("Yes");
        loading.addItem("No");
        loading.setEnabled(false);
        loading.setNewItemsAllowed(false);
        loading.setTextInputAllowed(false);
        columnLayout.addComponent(loading, 0);

        /*
        PopupDateField popupDate = new NoTooltipPopupDateField("Popup date");
        popupDate.setValue(new Date());
        popupDate.setWidth(100, Unit.PERCENTAGE);
        columnLayout.addComponent(popupDate, 2);
        */

        InlineDateField inlineDate = new NoTooltipInlineDateField("Inline date");
        inlineDate.setValue(new Date());
        inlineDate.setWidth(100, Unit.PERCENTAGE);
        inlineDate.setDescription("I'm inline datefield");
        columnLayout.addComponent(inlineDate, 2);

        Slider slider = new NoTooltipSlider("Slider");
        slider.setMin(0.0);
        slider.setMax(100.0);
        slider.setValue(50.0);
        slider.setWidth(100, Unit.PERCENTAGE);
        slider.setDescription("I'm slider");
        columnLayout.addComponent(slider, 1);

        return columnLayout;
    }

    private ComboBox createSwapCombobox() {
        final ComboBox cbox = new NoTooltipComboBox();
        fields.add(cbox);
        cbox.setWidth("100%");
        cbox.setImmediate(true);
        cbox.setCaption("Swapped combobox");
        cbox.setDescription("I was swapped");
        cbox.addItem("lorem");
        cbox.addItem("ipsum");
        cbox.setValue("lorem");
        cbox.setTextInputAllowed(false);
        cbox.setNewItemsAllowed(false);
        cbox.setNullSelectionAllowed(false);
        return cbox;
    }

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        CheckBox readOnly = new CheckBox("ro");
        readOnly.setDescription("Make form readonly");
        readOnly.addValueChangeListener(e -> fields.forEach(f -> f.setReadOnly((Boolean)e.getProperty().getValue())));
        menuBar.addComponent(readOnly);

        menuBar.addComponent(
                new Label("Material design look'n feel example modification of ColumnLayout (provided by add-on)"));

    }
}
