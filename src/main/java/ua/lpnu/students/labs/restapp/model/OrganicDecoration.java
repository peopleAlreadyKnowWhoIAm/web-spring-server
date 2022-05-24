package ua.lpnu.students.labs.restapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.restapp.model.shared.Size;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Organic decoration type class.
 */
public class OrganicDecoration extends PieceDecoration {
  {
    type = Type.ORGANIC_DECORATION;
  }
    // Variables

  // String literals
  private static final String DATE_STR = "Date of production";
  private static final String EXPIRATION_TERM_STR = "Number of days to expire";

  @Setter
  @Getter
  protected int numberOfDaysToExpiration;
  protected Date dateOfProduction = new Date();

  @Override
  public String toString() {
    return super.toString()
        + String.format(DSCRIPTION_STR,
            new SimpleDateFormat("dd/mm/yyyy").format(this.dateOfProduction),
            String.valueOf(this.numberOfDaysToExpiration));
  }

  private static final String DSCRIPTION_STR = DATE_STR + DIVIDER + EXPIRATION_TERM_STR + DIVIDER;

  public OrganicDecoration(String name, String material, Type type, Usage usage, int avalaibleAmount, float price,
        long id, String color, String style, Size size, int numberOfDaysToExpiration, Date dateOfProduction) {
    super(name, material, type, usage, avalaibleAmount, price, id, color, style, size);
    this.numberOfDaysToExpiration = numberOfDaysToExpiration;
    this.dateOfProduction = dateOfProduction;
}

public Date getDateOfProduction() {
    return new Date(dateOfProduction.getTime());
  }

  public void setDateOfProduction(Date dateOfProduction) {
    this.dateOfProduction = new Date(dateOfProduction.getTime());
  }
}
