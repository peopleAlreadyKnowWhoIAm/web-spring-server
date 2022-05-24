package ua.lpnu.students.labs.restapp.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.restapp.model.shared.Decoration;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Electric decoration class.
 */
@Getter
@Setter
public class ElectricDecoration extends Decoration {
  public static final Type CLASS_TYPE = Type.ELECTRIC_DECORATION;
  // Variables

  // String literals
  private static final String COLORS_STR = "Colors of the lights";
  private static final String LENGTH_STR = "Length";
  private static final String AMOUNT_LAMPS_STR = "Amount of lamps per meter";
  private static final String POWER_STR = "Power in watts";

  List<String> colorsOfLights = new LinkedList<>();
  int length;
  int amountLampsPerMeter;
  int powerInWatts;

  @Override
  public String toString() {
    return super.toString() + String.format(DESCRIPTION_STR,
        this.colorsOfLights.toString(),
        String.valueOf(this.length),
        String.valueOf(this.amountLampsPerMeter),
        String.valueOf(this.powerInWatts),
        String.valueOf(this.price));
  }

  private static final String DESCRIPTION_STR = 
      COLORS_STR + DIVIDER
      + LENGTH_STR + DIVIDER
      + AMOUNT_LAMPS_STR + DIVIDER
      + POWER_STR + DIVIDER
      + PRICE_STR + DIVIDER;
  // ---------------

public ElectricDecoration(String name, String material, Type type, Usage usage, int avalaibleAmount, float price,
        long id, List<String> colorsOfLights, int length, int amountLampsPerMeter, int powerInWatts) {
    super(name, material, type, usage, avalaibleAmount, price, id);
    this.colorsOfLights = colorsOfLights;
    this.length = length;
    this.amountLampsPerMeter = amountLampsPerMeter;
    this.powerInWatts = powerInWatts;
}

}
