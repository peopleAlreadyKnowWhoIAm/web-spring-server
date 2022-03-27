package ua.lpnu.students.labs.laba2.ChristmassDecoration;

public class PieceDecoration extends Template {
    protected String color;
    protected String style;
    protected String material;
    protected int[] size; //3 value of width ,heihgt and depth
    protected float pricePerPiece;
    public PieceDecoration(String name, Type type, String usage, int avalaibleAmount, String color, String style,
            String material, int[] size, float pricePerPiece) {
        super(name, type, usage, avalaibleAmount);
        this.color = color;
        this.style = style;
        this.material = material;
        this.size = size;
        this.pricePerPiece = pricePerPiece;
    }

    public PieceDecoration() {
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
    public int[] getSize() {
        return size;
    }
    public void setSize(int[] size) {
        this.size = size;
    }
    public float getPricePerPiece() {
        return pricePerPiece;
    }
    public void setPricePerPiece(float pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }
    
}
