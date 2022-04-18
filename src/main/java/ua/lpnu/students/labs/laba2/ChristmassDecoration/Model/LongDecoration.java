package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.TypedList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl.TypedLinkedList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

@Setter
@Getter
public class LongDecoration extends Template {
    public static final Type CLASS_TYPE = Type.LONG_DECORATION;
    // Variables
    protected TypedList<String> color = new TypedLinkedList<String>(new String());

    protected String style = new String();

    protected int minimalLength;
    protected int widthInCentimeters;

    // Constructors
    public LongDecoration(String name, Usage usage, int avalaibleAmount, TypedList<String> color, String style,
            String material, int minimalLength, int widthInCentimeters, float pricePerMeter) {
        super(name, CLASS_TYPE, usage, material, avalaibleAmount, pricePerMeter);
        this.color = color;
        this.style = style;
        this.minimalLength = minimalLength;
        this.widthInCentimeters = widthInCentimeters;
    }

    public LongDecoration() {
        super(CLASS_TYPE);
    }

    // Text menu functions
    @Override
    @SuppressWarnings("unchecked")
    public void setFields(final List<FieldDescription> fields) {

        // Colors
        this.color = (TypedList<String>) fields.get(5).getValue();

        // Style
        this.style = (String) fields.get(6).getValue();

        // Minimal length
        this.minimalLength = (Integer) fields.get(7).getValue();

        // Width
        this.widthInCentimeters = (Integer) fields.get(8).getValue();

        super.setFields(fields);
    }

    @Override
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = super.getFields();

        // Colors
        var tmpColors = TEMPLATE_FIELDS[0];
        tmpColors.setValue(this.color);
        out.add(tmpColors);

        // Style
        var tmpStyle = TEMPLATE_FIELDS[1];
        tmpStyle.setValue(this.style);
        out.add(tmpStyle);

        // Minimal length
        var tmpMinLen = TEMPLATE_FIELDS[2];
        tmpMinLen.setValue(this.minimalLength);
        out.add(tmpMinLen);

        // Width
        var tmpWidth = TEMPLATE_FIELDS[3];
        tmpWidth.setValue(this.widthInCentimeters);
        out.add(tmpWidth);

        return out;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(DESCRIPTION_STR,
                this.color.toString(),
                this.style,
                String.valueOf(this.minimalLength),
                String.valueOf(this.widthInCentimeters),
                String.valueOf(this.price));
    }

    // String literals

    private static final String COLORS_STR = "Colors of the decoration\nSend empty to confirm\n";
    private static final String STYLE_STR = "Style: ";
    private static final String MINIMAL_LENGTH_STR = "Minimal length selling: ";
    private static final String WIDTH_STR = "Width: ";

    private static final String DESCRIPTION_STR = "Colors: %s\t" + STYLE_STR + "%s\t" + MINIMAL_LENGTH_STR + "%s\t"
            + WIDTH_STR + "%s\tPrice per meter: %s\t";

    private final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, COLORS_STR),
            new FieldDescription(null, STYLE_STR),
            new FieldDescription(null, MINIMAL_LENGTH_STR),
            new FieldDescription(null, WIDTH_STR)
    };
}
