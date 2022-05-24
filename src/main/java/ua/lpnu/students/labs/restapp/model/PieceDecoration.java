package ua.lpnu.students.labs.restapp.model;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.restapp.model.shared.Decoration;
import ua.lpnu.students.labs.restapp.model.shared.Size;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Piece decoration type class.
 */
@Getter
@Setter
public class PieceDecoration extends Decoration {
  public static final Type CLASS_TYPE = Type.PIECE_DECORATION;
  // Variables

  protected String color = "";
  protected String style = "";
  protected Size size = new Size(); 

  @Override
  public String toString() {
    return super.toString() + String.format(DESCRIPTION_STR,
        this.color,
        this.style,
        this.size.toString(),
        String.valueOf(this.price));
  }

  // String literals
  private static final String COLOR_STR = "Color";
  private static final String STYLE_STR = "Style";
  private static final String DEMESNSIONS_STR = "Demensions";
  private static final String DESCRIPTION_STR = COLOR_STR + DIVIDER + STYLE_STR
      + DIVIDER + DEMESNSIONS_STR + DIVIDER + PRICE_STR + DIVIDER;

public PieceDecoration(String name, String material, Type type, Usage usage, int avalaibleAmount, float price, long id,
        String color, String style, Size size) {
    super(name, material, type, usage, avalaibleAmount, price, id);
    this.color = color;
    this.style = style;
    this.size = size;
}
}
