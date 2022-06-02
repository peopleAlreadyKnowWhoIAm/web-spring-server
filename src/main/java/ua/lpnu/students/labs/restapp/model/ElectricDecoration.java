package ua.lpnu.students.labs.restapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
import ua.lpnu.students.labs.restapp.model.shared.Decoration;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Electric decoration class.
 */
@NoArgsConstructor
@Getter
@Setter
@Table("electric-decoration")
public class ElectricDecoration extends Decoration {
    public Type type = Type.ELECTRIC_DECORATION;
    // Variables

    // String literals
    private static final String COLORS_STR = "Colors of the lights";
    private static final String LENGTH_STR = "Length";
    private static final String AMOUNT_LAMPS_STR = "Amount of lamps per meter";
    private static final String POWER_STR = "Power in watts";

    String colourOfLights = "";
    int length;
    int amountLampsPerMeter;
    int powerInWatts;


    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(DESCRIPTION_STR,
                this.colourOfLights,
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

    /**
     * Constructor without id and type.
     */
    public ElectricDecoration(String name, String material, Usage usage, int avalaibleAmount, float price,
            String colourOfLights, int length, int amountLampsPerMeter, int powerInWatts) {
        super(name, material, usage, avalaibleAmount, price);
        this.colourOfLights = colourOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }

    ElectricDecoration(String name, String material, Usage usage, int avalaibleAmount, float price, Type type,
            long id, String colourOfLights, int length, int amountLampsPerMeter, int powerInWatts) {
        super(name, material, usage, avalaibleAmount, price, id);
        this.colourOfLights = colourOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }
}
