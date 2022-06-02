package ua.lpnu.students.labs.decoration.model.shared;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;

/**
 * Abstract decoration template to inherit.
 */
public abstract class Decoration implements Cloneable {
    // Variables

    // String literals
    private static final String NAME_STR = "Name";
    private static final String USAGE_STR = "Usage";
    private static final String TYPE_STR = "Type";
    private static final String AVAILABLE_AMOUNT_STR = "Available";
    private static final String MATERIAL_STR = "Material";

    protected static final String PRICE_STR = "Price";
    protected static final String DIVIDER = ": %s\t";

    @Getter
    @Setter
    protected String name = "";

    @Getter
    @Setter
    protected String material = "";

    @Getter
    @Setter
    protected Type type;

    @Getter
    @Setter
    protected Usage usage = Usage.UNKNOWN;

    @Getter
    @Setter
    protected int avalaibleAmount;

    @Getter
    @Setter
    protected float price;

    protected Decoration(
            String name,
            Type type,
            Usage usage,
            String material,
            int avalaibleAmount,
            float price) {
        this.name = name;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.type = type;
        this.material = material;
        this.price = price;
    }

    protected Decoration(Type type) {
        this.type = type;
    }

    /**
     * Set fields to the Template object.
     *
     * @param fields list contains FieldDescription with all fields
     */
    public void setFields(final List<FieldDescription> fields) {

        // Name
        this.name = (String) fields.get(0).getValue();

        // Usage
        this.usage = (Usage) fields.get(1).getValue();

        // Material
        this.material = (String) fields.get(2).getValue();

        // Amount
        this.avalaibleAmount = (Integer) fields.get(3).getValue();

        // Price
        this.price = (Float) fields.get(4).getValue();
    }

    /**
     * Get fields with Template values or grabage when not set.
     *
     * @return the list of the field
     */
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = new LinkedList<>();

        // Name
        var tmpName = TEMPLATE_FIELDS[0];
        tmpName.setValue(this.name);
        out.add(tmpName);

        // Usage
        var tmpUsage = TEMPLATE_FIELDS[1];
        tmpUsage.setValue(this.usage);
        out.add(tmpUsage);

        // Material
        var tmpMaterial = TEMPLATE_FIELDS[2];
        tmpMaterial.setValue(this.material);
        out.add(tmpMaterial);

        // Amount
        var tmpAmount = TEMPLATE_FIELDS[3];
        tmpAmount.setValue(this.avalaibleAmount);
        out.add(tmpAmount);

        // Price
        var tmpPrice = TEMPLATE_FIELDS[4];
        tmpPrice.setValue(this.price);
        out.add(tmpPrice);

        return out;
    }

    // Overriding
    @Override
    public String toString() {
        return String.format(DESCRIPTION_STR, this.name, this.type, this.usage.toString(),
                String.valueOf(this.avalaibleAmount), this.material);
    }

    /**
     * Make a full copy for the object.
     *
     * @return the copy
     */
    public Decoration copy() {
        try {
            return (Decoration) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String DESCRIPTION_STR = NAME_STR + DIVIDER
            + TYPE_STR + DIVIDER
            + USAGE_STR + DIVIDER
            + AVAILABLE_AMOUNT_STR + DIVIDER
            + MATERIAL_STR + DIVIDER;

    private static final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, NAME_STR),
            new FieldDescription(null, USAGE_STR),
            new FieldDescription(null, MATERIAL_STR),
            new FieldDescription(null, AVAILABLE_AMOUNT_STR),
            new FieldDescription(null, PRICE_STR)
    };
}
