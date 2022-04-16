package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

import java.util.LinkedList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Template implements Cloneable {
    // Variables
    protected String name = new String();
    protected String material = new String();

    protected Type type;

    protected Usage usage = Usage.UNKNOWN;

    protected int avalaibleAmount;

    protected float price;

    // Constructors
    protected Template(String name, Type type, Usage usage, String material, int avalaibleAmount, float price) {
        this.name = name;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.type = type;
        this.material = material;
        this.price = price;
    }

    protected Template(Type type) {
        this.type = type;
    }

    // Text menu functions
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

    public Template copy(){
        try {
            return (Template)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, NAME_STR),
            new FieldDescription(null, USAGE_STR),
            new FieldDescription(null, MATERIAL_STR),
            new FieldDescription(null, AVAILABLE_AMOUNT_STR),
            new FieldDescription(null, PRICE_STR)
    };

    // String literals
    private static final String NAME_STR = "Name: ";
    private static final String USAGE_STR = "Usage: ";
    private static final String TYPE_STR = "Type: ";
    private static final String AVAILABLE_AMOUNT_STR = "Available: ";
    private static final String MATERIAL_STR = "Material: ";
    private static final String PRICE_STR = "Price: ";

    private static final String DESCRIPTION_STR = NAME_STR + "%s\t" + TYPE_STR + "%s\t" + USAGE_STR + "%s\t" + AVAILABLE_AMOUNT_STR
            + "%s\t"
            + MATERIAL_STR + "%s\t";

}
