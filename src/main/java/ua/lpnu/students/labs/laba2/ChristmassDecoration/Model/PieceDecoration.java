package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

@Getter
@Setter
public class PieceDecoration extends Template {
    public static final Type CLASS_TYPE = Type.PIECE_DECORATION;
    // Variables
    protected String color = new String();
    protected String style = new String();

    protected Size size = new Size(); // 3 value of width ,heihgt and depth

    // Constructors
    public PieceDecoration(String name, Usage usage, int avalaibleAmount, String color, String style,
            String material, Size size, float pricePerPiece) {
        super(name, CLASS_TYPE, usage, material, avalaibleAmount, pricePerPiece);
        this.color = color;
        this.style = style;
        this.size = size;
    }

    public PieceDecoration() {
        super(CLASS_TYPE);
    }

    protected PieceDecoration(Type type) {
        super(type);
    }

    // Text menu functions
    @Override
    public void setFields(final List<FieldDescription> fields) {

        // Color
        this.color = (String) fields.get(5).getValue();

        // Style
        this.style = (String) fields.get(6).getValue();

        // Size
        this.size = (Size) fields.get(7).getValue();

        super.setFields(fields);
    }

    @Override
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = super.getFields();

        // Color
        var tmpColor = TEMPLATE_FIELDS[0];
        tmpColor.setValue(this.color);
        out.add(tmpColor);

        // Style
        var tmpStyle = TEMPLATE_FIELDS[1];
        tmpStyle.setValue(this.style);
        out.add(tmpStyle);

        // Size
        var tmpSize = TEMPLATE_FIELDS[2];
        tmpSize.setValue(this.size);
        out.add(tmpSize);

        return out;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(DESCRIPTION_STR,
                this.color,
                this.style,
                this.size.toString(),
                String.valueOf(this.price));
    }

    // String literals
    private static final String COLOR_STR = "Color: ";
    private static final String STYLE_STR = "Style: ";
    private static final String DESCRIPTION_STR = COLOR_STR + "%s\t" + STYLE_STR
            + "%s\tDemesions: %s\t Price: %s";
    private final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, COLOR_STR),
            new FieldDescription(null, STYLE_STR),
            new FieldDescription(null, "Demensions: width, height, depth: \n")
    };
}
