package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class ElectricDecoration extends Template {
    public static final Type classType = Type.ELECTRIC_DECORATION;
//Variables
    private List<String> colorsOfLights = new LinkedList<>();

    private int length;
    private int amountLampsPerMeter;
    private int powerInWatts;



//Constructors
    public ElectricDecoration(String name, Usage usage, String material, int avalaibleAmount, List<String> colorsOfLights, 
            int length, int amountLampsPerMeter, int powerInWatts, float pricePerPiece) {
        super(name, classType , usage, material, avalaibleAmount, pricePerPiece);
        this.colorsOfLights = colorsOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }

    public ElectricDecoration() {
        super(classType);
    }

    protected ElectricDecoration(Type type){
        super(type);
    }


    @Override
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = super.getFields();

        // Colors
        var tmpColors = TEMPLATE_FIELDS[0];
        tmpColors.setValue(this.colorsOfLights);
        out.add(tmpColors);

        // Length
        var tmpLen = TEMPLATE_FIELDS[1];
        tmpLen.setValue(this.length);
        out.add(tmpLen);

        // Amount lamps per meter
        var tmpAmountLampsMeter = TEMPLATE_FIELDS[2];
        tmpAmountLampsMeter.setValue(this.amountLampsPerMeter);
        out.add(tmpAmountLampsMeter);

        // power
        var tmpPower = TEMPLATE_FIELDS[3];
        tmpPower.setValue(this.powerInWatts);
        out.add(tmpPower);

        return out;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr, 
            this.colorsOfLights.toString(),
            String.valueOf(this.length),
            String.valueOf(this.amountLampsPerMeter),
            String.valueOf(this.powerInWatts),
            String.valueOf(this.price)
        );
    }


    
//String literals

    private static final String colorsStr = "Colors of the lights\n";
    private static final String lengthStr = "Length: ";
    private static final String amountLampsStr = "Amount of lamps per meter: ";
    private static final String powerStr = "Power in watts: ";

    private static final String descriptionStr = "Color of lights: %s\t"+ lengthStr + "%s\t" + amountLampsStr +"%s\t" + powerStr + "%s\tPrice: %s\t";
//---------------

private final FieldDescription[] TEMPLATE_FIELDS = {
    new FieldDescription(colorsStr, null),
    new FieldDescription(lengthStr, 0),
    new FieldDescription(amountLampsStr, 0),
    new FieldDescription(powerStr, 0)
};


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
