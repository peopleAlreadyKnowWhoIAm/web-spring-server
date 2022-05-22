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
 * Long decoration class.
 */
public class LongDecoration extends Decoration {
  public static final Type CLASS_TYPE = Type.LONG_DECORATION;
  // Variables

  // String literals
  private static final String COLORS_STR = "Colors of the decoration";
  private static final String STYLE_STR = "Style";
  private static final String MINIMAL_LENGTH_STR = "Minimal length selling";
  private static final String WIDTH_STR = "Width";

  protected static final String PRICE_PER_METER = "Price per meter";

  @Getter
  @Setter
  protected TypedList<String> color = new TypedLinkedList<String>("");

  @Getter
  @Setter
  protected String style = "";

  @Getter
  @Setter
  protected int minimalLength;

  @Getter
  @Setter
  protected int widthInCentimeters;

  /**
   * Constructor with all parameters.
   */
  public LongDecoration(
      String name, 
      Usage usage, 
      int avalaibleAmount, 
      TypedList<String> color, 
      String style,
      String material, 
      int minimalLength, 
      int widthInCentimeters, 
      float pricePerMeter
  ) {
    super(name, CLASS_TYPE, usage, material, avalaibleAmount, pricePerMeter);
    this.color = color;
    this.style = style;
    this.minimalLength = minimalLength;
    this.widthInCentimeters = widthInCentimeters;
  }

  public LongDecoration() {
    super(CLASS_TYPE);
  }

  // Text menu functions
  @Override
  @SuppressWarnings("unchecked")
  public void setFields(final List<FieldDescription> fields) {

    // Colors
    this.color = (TypedList<String>) fields.get(5).getValue();

    // Style
    this.style = (String) fields.get(6).getValue();

    // Minimal length
    this.minimalLength = (Integer) fields.get(7).getValue();

    // Width
    this.widthInCentimeters = (Integer) fields.get(8).getValue();

    super.setFields(fields);
  }

  @Override
  public List<FieldDescription> getFields() {
    List<FieldDescription> out = super.getFields();

    // Colors
    var tmpColors = TEMPLATE_FIELDS[0];
    tmpColors.setValue(this.color);
    out.add(tmpColors);

    // Style
    var tmpStyle = TEMPLATE_FIELDS[1];
    tmpStyle.setValue(this.style);
    out.add(tmpStyle);

    // Minimal length
    var tmpMinLen = TEMPLATE_FIELDS[2];
    tmpMinLen.setValue(this.minimalLength);
    out.add(tmpMinLen);

    // Width
    var tmpWidth = TEMPLATE_FIELDS[3];
    tmpWidth.setValue(this.widthInCentimeters);
    out.add(tmpWidth);

    return out;
  }

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

  private static final FieldDescription[] TEMPLATE_FIELDS = {
      new FieldDescription(null, COLORS_STR),
      new FieldDescription(null, STYLE_STR),
      new FieldDescription(null, MINIMAL_LENGTH_STR),
      new FieldDescription(null, WIDTH_STR)
  };
}
