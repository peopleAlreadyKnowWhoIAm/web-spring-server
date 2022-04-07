package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public class PieceDecoration extends Template {
    protected String color;
    protected String style;
    protected int[] size = {0, 0, 0}; //3 value of width ,heihgt and depth
    protected float pricePerPiece;
    public PieceDecoration(String name, Usage usage, int avalaibleAmount, String color, String style,
            String material, int[] size, float pricePerPiece) {
        super(name, classType, usage, avalaibleAmount);
        this.color = color;
        this.style = style;
        this.material = material;
        this.size = size;
        this.pricePerPiece = pricePerPiece;
    }

    public PieceDecoration() {
        super(classType);
    }

    protected PieceDecoration(String type){
        super(type);
    }

    @Override
    public void editAll(){
        super.editAll();

        this.color = TextMenu.editString(this.color, colorStr);

        this.style = TextMenu.editString(this.style, styleStr);

        System.out.print(sizeStr);
        int[] size_new = {0,0,0};
        for(byte i = 0; i < 3; i++){
            size_new[i] = TextMenu.editInt(this.size[i], String.format("%d. ", i));
        }
        this.size = size_new;

        this.pricePerPiece = TextMenu.editInt(this.pricePerPiece, priceStr)/100.0f;
    }

    @Override
    public void setAll(){
        super.setAll();

        this.color = TextMenu.setString(colorStr);

        this.style = TextMenu.setString(styleStr);

        System.out.print(sizeStr);
        int[] size_new = {0,0,0};
        for(byte i = 0; i < 3; i++){
            size_new[i] = TextMenu.setInt(String.format("%d. ", i));
        }
        this.size = size_new;

        this.pricePerPiece = TextMenu.setInt(priceStr)/100.0f;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr,
            this.color,
            this.style,
            this.size[0], this.size[1], this.size[2],
            String.valueOf(this.pricePerPiece)
        );
    }

    private static final String classType = "Piece decoration";

    private static final String colorStr = "Color: ";
    private static final String styleStr = "Style: ";
    private static final String sizeStr = "Demensions: width, height, depth: \n";
    private static final String priceStr = "Price multiplied by 100: ";

    private static final String descriptionStr = colorStr + "%s\t" + styleStr + "%s\tDemesions: %d x %d x %d\t Price: %s";
        

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
