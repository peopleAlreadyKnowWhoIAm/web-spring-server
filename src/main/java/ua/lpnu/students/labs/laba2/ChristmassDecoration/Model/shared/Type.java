package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

public enum Type {
    PIECE_DECORATION, ORGANIC_DECORATION, LONG_DECORATION, ELECTRIC_DECORATION;

    public static String allToString(){
        String out = new String();
        Type[] types = Type.values();
        for(Type type: types){
            out += String.format("%d - %s\n", type.ordinal(), type.toString());
        }
        return out;
    }
}
