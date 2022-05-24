package ua.lpnu.students.labs.restapp.model.shared;

/**
 * The type enum of the models.
 */
public enum Type {
  PIECE_DECORATION, ORGANIC_DECORATION, LONG_DECORATION, ELECTRIC_DECORATION;

  /**
   * Convert all possible value to string with their values.
   *
   * @return the string
   */
  public static String allToString() {
    StringBuilder out = new StringBuilder();
    Type[] types = Type.values();
    for (Type type : types) {
      out.append(String.format("%d - %s%n", type.ordinal(), type.toString()));
    }
    return out.toString();
  }
}
