package ua.lpnu.students.labs.laba2.decoration.model.shared;

public enum Usage {
  UNKNOWN, OUTSIDE_DECORATION, FOR_CHRISTMASS, UNIVERSAL, FOR_WINDOWS;

  public static String allToString() {
    StringBuffer out = new StringBuffer();
    for (Usage usage : Usage.values()) {
      out.append(String.format("%d - %s%n", usage.ordinal(), usage.toString()));
    }
    return out.toString();
  }
}
