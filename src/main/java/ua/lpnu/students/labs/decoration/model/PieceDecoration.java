package ua.lpnu.students.labs.decoration.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;

/**
 * Piece decoration type class.
 */
public class PieceDecoration extends Decoration {
    public static final Type CLASS_TYPE = Type.PIECE_DECORATION;
    // Variables

    @Getter
    @Setter
    protected String color = "";

    @Getter
    @Setter
    protected String style = "";

    @Getter
    @Setter
    protected Size size = new Size();

    /**
     * Constructor of all parameter.
     */
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

    protected PieceDecoration(
            String name,
            Type type,
            Usage usage,
            int avalaibleAmount,
            String color,
            String style,
            String material,
            Size size,
            float pricePerPiece) {
        super(name, type, usage, material, avalaibleAmount, pricePerPiece);
        this.color = color;
        this.style = style;
        this.size = size;
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
    private static final String COLOR_STR = "Color";
    private static final String STYLE_STR = "Style";
    private static final String DEMESNSIONS_STR = "Demensions";
    private static final String DESCRIPTION_STR = COLOR_STR + DIVIDER + STYLE_STR
            + DIVIDER + DEMESNSIONS_STR + DIVIDER + PRICE_STR + DIVIDER;
    private static final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, COLOR_STR),
            new FieldDescription(null, STYLE_STR),
            new FieldDescription(null, DEMESNSIONS_STR)
    };
}
