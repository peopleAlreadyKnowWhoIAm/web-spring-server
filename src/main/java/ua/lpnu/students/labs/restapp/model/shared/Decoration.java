package ua.lpnu.students.labs.restapp.model.shared;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Abstract decoration template to inherit.
 */
@Data
@AllArgsConstructor
@Table(value = "DECORATIONS")
public class Decoration{
  // Variables

  // String literals
  private static final String NAME_STR = "Name";
  private static final String USAGE_STR = "Usage";
  private static final String TYPE_STR = "Type";
  private static final String AVAILABLE_AMOUNT_STR = "Available";
  private static final String MATERIAL_STR = "Material";

  protected static final String PRICE_STR = "Price";
  protected static final String DIVIDER = ": %s\t";

  protected String name = "";
  protected String material = "";
  protected Type type = null;
  protected Usage usage = Usage.FOR_CHRISTMASS;
  protected int avalaibleAmount;
  protected float price;

  @Id
  private long id;


  // Overriding
  @Override
  public String toString() {
    return String.format(DESCRIPTION_STR, this.name, this.type, this.usage.toString(),
        String.valueOf(this.avalaibleAmount), this.material);
  }


  public Decoration() {
}


private static final String DESCRIPTION_STR = 
      NAME_STR + DIVIDER 
      + TYPE_STR + DIVIDER 
      + USAGE_STR + DIVIDER
      + AVAILABLE_AMOUNT_STR + DIVIDER
      + MATERIAL_STR + DIVIDER;

}
