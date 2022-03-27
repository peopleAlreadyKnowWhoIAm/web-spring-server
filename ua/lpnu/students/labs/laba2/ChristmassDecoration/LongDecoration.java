package ua.lpnu.students.labs.laba2.ChristmassDecoration;

public class LongDecoration extends Template {
    protected String color;
    protected String style;
    protected String material;
    protected int minimalLength;
    protected int widthInCentimeters;
    protected float pricePerMeter;

    public LongDecoration(String name, Type type, String usage, int avalaibleAmount, String color, String style,
            String material, int minimalLength, int widthInCentimeters, float pricePerMeter) {
        super(name, type, usage, avalaibleAmount);
        this.color = color;
        this.style = style;
        this.material = material;
        this.minimalLength = minimalLength;
        this.widthInCentimeters = widthInCentimeters;
        this.pricePerMeter = pricePerMeter;
    }

    public LongDecoration() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getMinimalLength() {
        return minimalLength;
    }

    public void setMinimalLength(int minimalLength) {
        this.minimalLength = minimalLength;
    }

    public int getWidthInCentimeters() {
        return widthInCentimeters;
    }

    public void setWidthInCentimeters(int widthInCentimeters) {
        this.widthInCentimeters = widthInCentimeters;
    }

    public float getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(float pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }
    
    
    
}
