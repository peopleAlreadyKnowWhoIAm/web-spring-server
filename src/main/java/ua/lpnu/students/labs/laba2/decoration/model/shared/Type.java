package ua.lpnu.students.labs.laba2.decoration.model.shared;

public enum Type {
  PIECE_DECORATION, ORGANIC_DECORATION, LONG_DECORATION, ELECTRIC_DECORATION;

  public static String allToString() {
    StringBuffer out = new StringBuffer();
    Type[] types = Type.values();
    for (Type type : types) {
      out.append(String.format("%d - %s%n", type.ordinal(), type.toString()));
    }
    return out.toString();
  }

}
