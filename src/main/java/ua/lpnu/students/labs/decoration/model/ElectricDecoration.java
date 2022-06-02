package ua.lpnu.students.labs.decoration.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.TypedList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedLinkedList;

/**
 * Electric decoration class.
 */
public class ElectricDecoration extends Decoration {
    public static final Type CLASS_TYPE = Type.ELECTRIC_DECORATION;
    // Variables

    // String literals
    private static final String COLORS_STR = "Colors of the lights";
    private static final String LENGTH_STR = "Length";
    private static final String AMOUNT_LAMPS_STR = "Amount of lamps per meter";
    private static final String POWER_STR = "Power in watts";

    @Getter
    @Setter
    TypedList<String> colorsOfLights = new TypedLinkedList<>("");

    @Getter
    @Setter
    int length;
    @Getter
    @Setter
    int amountLampsPerMeter;
    @Getter
    @Setter
    int powerInWatts;

    /**
     * Constructor with all parameters.
     */
    public ElectricDecoration(String name, Usage usage, String material, int avalaibleAmount,
            TypedList<String> colorsOfLights,
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
    public void setFields(final List<FieldDescription> fields) {
        this.colorsOfLights = (TypedList<String>) fields.get(5).getValue();

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

    private static final String DESCRIPTION_STR = COLORS_STR + DIVIDER
            + LENGTH_STR + DIVIDER
            + AMOUNT_LAMPS_STR + DIVIDER
            + POWER_STR + DIVIDER
            + PRICE_STR + DIVIDER;
    // ---------------

    private static final FieldDescription[] TEMPLATE_FIELDS = {
            new FieldDescription(null, COLORS_STR),
            new FieldDescription(null, LENGTH_STR),
            new FieldDescription(null, AMOUNT_LAMPS_STR),
            new FieldDescription(null, POWER_STR)
    };
}
