package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

public enum Usage {
    OUTSIDE_DECORATION, FOR_CHRISTMASS, UNIVERSAL, FOR_WINDOWS;

    public static String allToString(){
        int count = 0;
        String out = new String();
        for (Usage i : Usage.values()) {
            out+= count + ". " + i.toString() + "\n";
            count++;
        }
        return out;
    }
}
