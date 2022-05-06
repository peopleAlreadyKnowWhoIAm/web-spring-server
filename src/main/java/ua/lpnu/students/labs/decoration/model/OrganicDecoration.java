package ua.lpnu.students.labs.decoration.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;
import ua.lpnu.students.labs.decoration.textmenu.TextParser;

/**
 * Organic decoration type class.
 */
public class OrganicDecoration extends PieceDecoration {
  public static final Type CLASS_TYPE = Type.ORGANIC_DECORATION;
  // Variables

  // String literals
  private static final String DATE_STR = "Date of production";
  private static final String EXPIRATION_TERM_STR = "Number of days to expire";

  @Setter
  @Getter
  protected int numberOfDaysToExpiration;

  protected Date dateOfProduction = new Date();

  /**
   * Constructor of all parameters.
   */
  public OrganicDecoration(
      String name,
      Usage usage,
      int avalaibleAmount,
      String color,
      String style,
      String material,
      Size size,
      float pricePerPiece,
      int numberOfDaysToExpiration,
      Date dateOfProduction) {
    super(name, CLASS_TYPE, usage, avalaibleAmount, color, style, material, size, pricePerPiece);
    this.numberOfDaysToExpiration = numberOfDaysToExpiration;
    this.dateOfProduction = new Date(dateOfProduction.getTime());
  }

  public OrganicDecoration() {
    super(CLASS_TYPE);
  }

  // Text menu functions
  @Override
  public void setFields(final List<FieldDescription> fields) {

    // Number of days to expiration
    this.numberOfDaysToExpiration = (Integer) fields.get(8).getValue();

    // Date of production
    this.dateOfProduction = (Date) fields.get(9).getValue();

    super.setFields(fields);
  }

  @Override
  public List<FieldDescription> getFields() {
    List<FieldDescription> out = super.getFields();

    // Number of days to expiration
    var tmpHowFarExp = TEMPLATE_FIELDS[0];
    tmpHowFarExp.setValue(this.numberOfDaysToExpiration);
    out.add(tmpHowFarExp);

    // Date of production
    var tmpDate = TEMPLATE_FIELDS[1];
    tmpDate.setValue(this.dateOfProduction);
    out.add(tmpDate);

    return out;
  }

  @Override
  public String toString() {
    return super.toString()
        + String.format(DSCRIPTION_STR,
            new SimpleDateFormat(TextParser.DATE_PATTERN_STR).format(this.dateOfProduction),
            String.valueOf(this.numberOfDaysToExpiration));
  }

  private static final String DSCRIPTION_STR = DATE_STR + DIVIDER + EXPIRATION_TERM_STR + DIVIDER;

  private static final FieldDescription[] TEMPLATE_FIELDS = {
      new FieldDescription(null, EXPIRATION_TERM_STR),
      new FieldDescription(null, DATE_STR)
  };

  public Date getDateOfProduction() {
    return new Date(dateOfProduction.getTime());
  }

  public void setDateOfProduction(Date dateOfProduction) {
    this.dateOfProduction = new Date(dateOfProduction.getTime());
  }

}
