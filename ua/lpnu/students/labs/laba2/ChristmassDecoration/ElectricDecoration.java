package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.TextMenu;

public class ElectricDecoration extends Template {
    private List<String> colorsOfLights;
    private int length;
    private int amountLampsPerMeter;
    private int powerInWatts;
    protected float pricePerPiece;

    public ElectricDecoration(String name, Type type, String usage, int avalaibleAmount, List<String> colorsOfLights,
            int length, int amountLampsPerMeter, int powerInWatts, float pricePerPiece) {
        super(name, type, usage, avalaibleAmount);
        this.colorsOfLights = colorsOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
        this.pricePerPiece = pricePerPiece;
    }

    public ElectricDecoration() {
        
    }

    @Override
    public void setAll(){
        super.setAll();
        System.out.println(colorsStr);
        String tmp= "ddsfld";
        LinkedList<String> array = new LinkedList<String>();
        do{
            if(tmp != "ddsfld"){
                array.add(tmp);
            }
            tmp = TextMenu.scanString();
        }while(!tmp.isEmpty());
        colorsOfLights = array;

        System.out.print(lengthStr);
        length = TextMenu.scanInt(-1);

        System.out.print(amountLampsStr);
        amountLampsPerMeter = TextMenu.scanInt(-1);

        System.out.print(powerStr);
        powerInWatts = TextMenu.scanInt(-1);

        System.out.print(priceStr);
        pricePerPiece = TextMenu.scanInt(-1)/100.0f;
    }

    @Override
    public void editAll(){
        super.editAll();
        
    }

    /**
     * @return List<String> return the colorsOfLights
     */
    public List<String> getColorsOfLights() {
        return colorsOfLights;
    }

    /**
     * @param colorsOfLights the colorsOfLights to set
     */
    public void setColorsOfLights(List<String> colorsOfLights) {
        this.colorsOfLights = colorsOfLights;
    }

    /**
     * @return int return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return int return the amountLampsPerMeter
     */
    public int getAmountLampsPerMeter() {
        return amountLampsPerMeter;
    }

    /**
     * @param amountLampsPerMeter the amountLampsPerMeter to set
     */
    public void setAmountLampsPerMeter(int amountLampsPerMeter) {
        this.amountLampsPerMeter = amountLampsPerMeter;
    }

    /**
     * @return int return the powerInWatts
     */
    public int getPowerInWatts() {
        return powerInWatts;
    }

    /**
     * @param powerInWatts the powerInWatts to set
     */
    public void setPowerInWatts(int powerInWatts) {
        this.powerInWatts = powerInWatts;
    }

    private static final String colorsStr = "Enter colors of the lights\nSend empty to confirm\n";
    private static final String lengthStr = "Enter length: ";
    private static final String amountLampsStr = "Enter amount of lamps per meter: ";
    private static final String powerStr = "Enter power in watts: ";
    private static final String priceStr = "Enter price multiplied by 100: ";
}
