package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;

public abstract class Template {
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
        return String.format(descriptionStr, this.name, this.type, this.usage.toString(),
                String.valueOf(this.avalaibleAmount), this.material);
    }

    private final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription("Name: ", null),
            new FieldDescription("Usage: ", null),
            new FieldDescription("Material: ", null),
            new FieldDescription("Available amount: ", null),
            new FieldDescription("Price: ", null)
    };

    // String literals
    private static final String nameStr = "Name: ";
    private static final String usageStr = "Usage: ";
    private static final String typeStr = "Type: ";
    private static final String availableStr = "Available: ";
    private static final String materialStr = "Material: ";

    private static final String descriptionStr = nameStr + "%s\t" + typeStr + "%s\t" + usageStr + "%s\t" + availableStr
            + "%s\t"
            + materialStr + "%s\t";

    // Getters/Setters
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return this.type;
    }

    public Usage getUsage() {
        return this.usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public int getAvalaibleAmount() {
        return avalaibleAmount;
    }

    public void setAvalaibleAmount(int avalaibleAmount) {
        this.avalaibleAmount = avalaibleAmount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
