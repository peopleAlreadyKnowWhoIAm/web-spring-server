package ua.lpnu.students.labs.decoration.model.shared;

/**
 * Usage enum for model fields.
 */
public enum Usage {
    UNKNOWN, OUTSIDE_DECORATION, FOR_CHRISTMASS, UNIVERSAL, FOR_WINDOWS;

    /**
     * Convert all possible value to string with their values.
     *
     * @return the string
     */
    public static String allToString() {
        StringBuilder out = new StringBuilder();
        for (Usage usage : Usage.values()) {
            out.append(String.format("%d - %s%n", usage.ordinal(), usage.toString()));
        }
        return out.toString();
    }
}
