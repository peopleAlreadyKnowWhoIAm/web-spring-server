package ua.lpnu.students.labs.restapp.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.restapp.model.shared.Decoration;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Long decoration class.
 */
@Getter
@Setter
public class LongDecoration extends Decoration {
  {
  type = Type.LONG_DECORATION;
  }
  // Variables

  // String literals
  private static final String COLORS_STR = "Colors of the decoration";
  private static final String STYLE_STR = "Style";
  private static final String MINIMAL_LENGTH_STR = "Minimal length selling";
  private static final String WIDTH_STR = "Width";
  protected static final String PRICE_PER_METER = "Price per meter";

  protected List<String> color = new LinkedList<String>();
  protected String style = "";
  protected int minimalLength;
  protected int widthInCentimeters;

  @Override
  public String toString() {
    return super.toString() + String.format(DESCRIPTION_STR,
        this.color.toString(),
        this.style,
        String.valueOf(this.minimalLength),
        String.valueOf(this.widthInCentimeters),
        String.valueOf(this.price));
  }

  private static final String DESCRIPTION_STR = 
      COLORS_STR + DIVIDER 
      + STYLE_STR + DIVIDER 
      + MINIMAL_LENGTH_STR + DIVIDER
      + WIDTH_STR + DIVIDER 
      + PRICE_PER_METER + DIVIDER;

public LongDecoration(String name, String material, Type type, Usage usage, int avalaibleAmount, float price, long id,
        List<String> color, String style, int minimalLength, int widthInCentimeters) {
    super(name, material, type, usage, avalaibleAmount, price, id);
    this.color = color;
    this.style = style;
    this.minimalLength = minimalLength;
    this.widthInCentimeters = widthInCentimeters;
}

}
