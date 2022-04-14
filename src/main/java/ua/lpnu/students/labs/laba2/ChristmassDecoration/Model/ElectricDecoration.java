package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

@Getter
@Setter
public class ElectricDecoration extends Template {
    public static final Type CLASS_TYPE = Type.ELECTRIC_DECORATION;
    // Variables
    private List<String> colorsOfLights = new LinkedList<>();

    private int length;
    private int amountLampsPerMeter;
    private int powerInWatts;

    // Constructors
    public ElectricDecoration(String name, Usage usage, String material, int avalaibleAmount,
            List<String> colorsOfLights,
            int length, int amountLampsPerMeter, int powerInWatts, float pricePerPiece) {
        super(name, CLASS_TYPE, usage, material, avalaibleAmount, pricePerPiece);
        this.colorsOfLights = colorsOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }

    public ElectricDecoration() {
        super(CLASS_TYPE);
    }

    protected ElectricDecoration(Type type) {
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
    @SuppressWarnings("unchecked")
    public void setFields(final List<FieldDescription> fields){
        this.colorsOfLights = (List<String>) fields.get(5).getValue();

        this.length = (Integer) fields.get(6).getValue();

        this.amountLampsPerMeter = (Integer) fields.get(7).getValue();

        this.powerInWatts = (Integer) fields.get(8).getValue();

        super.setFields(fields);

    }

    @Override
    public String toString() {
        return super.toString() + String.format(DESCRIPTION_STR,
                this.colorsOfLights.toString(),
                String.valueOf(this.length),
                String.valueOf(this.amountLampsPerMeter),
                String.valueOf(this.powerInWatts),
                String.valueOf(this.price));
    }

    // String literals

    private static final String COLORS_STR = "Colors of the lights\n";
    private static final String LENGTH_STR = "Length: ";
    private static final String AMOUNT_LAMPS_STR = "Amount of lamps per meter: ";
    private static final String POWER_STR = "Power in watts: ";

    private static final String DESCRIPTION_STR = "Color of lights: %s\t" + LENGTH_STR + "%s\t" + AMOUNT_LAMPS_STR
            + "%s\t"
            + POWER_STR + "%s\tPrice: %s\t";
    // ---------------

    private final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, COLORS_STR),
            new FieldDescription(null, LENGTH_STR),
            new FieldDescription(null, AMOUNT_LAMPS_STR),
            new FieldDescription(null, POWER_STR)
    };
}
