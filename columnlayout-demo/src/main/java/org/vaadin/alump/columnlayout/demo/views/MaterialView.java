package org.vaadin.alump.columnlayout.demo.views;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.*;
import org.vaadin.alump.columnlayout.ColumnLayout;
import org.vaadin.alump.columnlayout.material.MaterialColumnLayout;
import org.vaadin.alump.columnlayout.notooltip.*;

import java.time.LocalDate;
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
        textField.setCaption("Email");
        textField.setPlaceholder("email here");
        textField.setDescription("Input must be valid email");
        //textField.(new EmailValidator("Not valid email address"));
        columnLayout.addComponent(textField);

        final ComboBox cbox = new NoTooltipComboBox();
        fields.add(cbox);
        cbox.setWidth("100%");
        cbox.setCaption("Select any");
        cbox.setItems("foo", "bar");
        cbox.addValueChangeListener(event -> {
            if(event.getValue() == null) {
                cbox.setComponentError(new ErrorMessage() {
                    @Override
                    public ErrorLevel getErrorLevel() {
                        return ErrorLevel.ERROR;
                    }

                    @Override
                    public String getFormattedHtmlMessage() {
                        return "Empty selection not allowed";
                    }
                });
            } else {
                cbox.setComponentError(null);
            }
        });
        cbox.setValue("foo");
        cbox.clear();
        columnLayout.addComponent(cbox, 1);

        final ComboBox cbox2 = new NoTooltipComboBox();
        fields.add(cbox2);
        cbox2.setWidth("100%");
        cbox2.setCaption("Select foo");
        cbox2.setDescription("Bar is no good!");
        cbox2.setItems("foo", "bar");
        cbox2.setTextInputAllowed(false);
        cbox2.setEmptySelectionAllowed(false);
        cbox2.addValueChangeListener(event -> {
            if(event.getValue() == null || !event.getValue().equals("foo")) {
                cbox2.setComponentError(new ErrorMessage() {
                    @Override
                    public ErrorLevel getErrorLevel() {
                        return ErrorLevel.ERROR;
                    }

                    @Override
                    public String getFormattedHtmlMessage() {
                        return "This is no foo!";
                    }
                });
            } else {
                cbox2.setComponentError(null);
            }
        });
        cbox2.setValue("foo");
        columnLayout.addComponent(cbox2, 1);

        DateField dateField = new NoTooltipDateField();
        fields.add(dateField);
        dateField.setWidth("100%");
        dateField.setValue(LocalDate.now());
        dateField.setCaption("Date picker");
        dateField.setDescription("Past is bad");
        dateField.addValueChangeListener(event -> {
            if(event.getValue().isBefore(LocalDate.now())) {
                dateField.setParseErrorMessage(null);
            } else {
                dateField.setParseErrorMessage("This value is in the past");
            }
        });
        columnLayout.addComponent(dateField, 0);

        TextArea textArea = new NoTooltipTextArea();
        fields.add(textArea);
        textArea.setWidth("100%");
        textArea.setRows(6);
        textArea.setCaption("Your life story");
        textArea.setPlaceholder("Write your life story here");
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
                //cbox.setRequiredError("You must provide!");
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
        loading.setItems("Yes","No");
        loading.setEnabled(false);
        loading.setTextInputAllowed(false);
        columnLayout.addComponent(loading, 0);

        InlineDateField inlineDate = new NoTooltipInlineDateField("Inline date");
        inlineDate.setValue(LocalDate.now());
        inlineDate.setWidth(100, Unit.PERCENTAGE);
        inlineDate.setDescription("I'm inline datefield");
        columnLayout.addComponent(inlineDate, 2);
        fields.add(inlineDate);

        Slider slider = new NoTooltipSlider("Slider");
        slider.setMin(0.0);
        slider.setMax(100.0);
        slider.setValue(50.0);
        slider.setWidth(100, Unit.PERCENTAGE);
        slider.setDescription("I'm slider");
        columnLayout.addComponent(slider, 1);
        fields.add(slider);

        return columnLayout;
    }

    private ComboBox createSwapCombobox() {
        final ComboBox cbox = new NoTooltipComboBox();
        fields.add(cbox);
        cbox.setWidth("100%");
        cbox.setCaption("Swapped combobox");
        cbox.setDescription("I was swapped");
        cbox.setItems("lorem", "ipsum", "lorem");
        cbox.setTextInputAllowed(false);
        cbox.setEmptySelectionAllowed(false);
        return cbox;
    }

    @Override
    protected void createMenuBar(HorizontalLayout menuBar) {
        CheckBox readOnly = new CheckBox("ro");
        readOnly.setDescription("Make form readonly");
        readOnly.addValueChangeListener(e -> fields.forEach(f -> f.setEnabled(!e.getValue())));
        menuBar.addComponent(readOnly);

        menuBar.addComponent(
                new Label("Material design look'n feel example modification of ColumnLayout (provided by add-on)"));

    }
}
