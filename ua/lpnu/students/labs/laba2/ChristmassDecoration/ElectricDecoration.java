package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import java.util.List;

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

}
