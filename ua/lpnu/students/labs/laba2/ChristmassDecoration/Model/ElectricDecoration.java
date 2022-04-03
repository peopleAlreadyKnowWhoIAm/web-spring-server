package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public class ElectricDecoration extends Template {
    public static final Type classType = Type.ELECTRIC_DECORATION;
//Variables
    private List<String> colorsOfLights;

    private int length;
    private int amountLampsPerMeter;
    private int powerInWatts;

    protected float pricePerPiece;


//Constructors
    public ElectricDecoration(String name, Usage usage, String material, int avalaibleAmount, List<String> colorsOfLights, 
            int length, int amountLampsPerMeter, int powerInWatts, float pricePerPiece) {
        super(name, classType , usage, material, avalaibleAmount);
        this.colorsOfLights = colorsOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
        this.pricePerPiece = pricePerPiece;
    }

    public ElectricDecoration() {
        super(classType);
    }

    protected ElectricDecoration(Type type){
        super(type);
    }


//Overriding
    @Override
    public void setAll(){
        super.setAll();

        this.colorsOfLights = TextMenu.setStringList(colorsStr);

        this.length = TextMenu.setInt(lengthStr);

        this.amountLampsPerMeter = TextMenu.setInt(amountLampsStr);

        this.powerInWatts = TextMenu.setInt(powerStr);

        this.pricePerPiece = TextMenu.setInt(priceStr)/100.0f;
    }

    @Override
    public void editAll(){
        super.editAll();
        
        this.colorsOfLights = TextMenu.editStringList(this.colorsOfLights, colorsStr);

        this.length = TextMenu.editInt(this.length, lengthStr);

        this.amountLampsPerMeter = TextMenu.editInt(this.amountLampsPerMeter, amountLampsStr);

        this.powerInWatts = TextMenu.editInt(this.powerInWatts, powerStr);

        this.pricePerPiece = TextMenu.editInt(this.pricePerPiece, priceStr)/100.0f;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr, 
            this.colorsOfLights.toString(),
            String.valueOf(this.length),
            String.valueOf(this.amountLampsPerMeter),
            String.valueOf(this.powerInWatts),
            String.valueOf(this.pricePerPiece)
        );
    }


    
//String literals

    private static final String colorsStr = "Colors of the lights\nSend empty to confirm\n";
    private static final String lengthStr = "Length: ";
    private static final String amountLampsStr = "Amount of lamps per meter: ";
    private static final String powerStr = "Power in watts: ";
    private static final String priceStr = "Price multiplied by 100: ";

    private static final String descriptionStr = "Color of lights: %s\t"+ lengthStr + "%s\t" + amountLampsStr +"%s\t" + powerStr + "%s\tPrice: %s\t";
//---------------


//Getters//Setters
    public List<String> getColorsOfLights() {
        return colorsOfLights;
    }

    public void setColorsOfLights(List<String> colorsOfLights) {
        this.colorsOfLights = colorsOfLights;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAmountLampsPerMeter() {
        return amountLampsPerMeter;
    }

    public void setAmountLampsPerMeter(int amountLampsPerMeter) {
        this.amountLampsPerMeter = amountLampsPerMeter;
    }

    public int getPowerInWatts() {
        return powerInWatts;
    }

    public void setPowerInWatts(int powerInWatts) {
        this.powerInWatts = powerInWatts;
    }

//---------------

}
