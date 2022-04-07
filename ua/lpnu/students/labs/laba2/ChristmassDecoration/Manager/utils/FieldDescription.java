package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils;

public class FieldDescription{
    public FieldDescription(String message, Object value){
        this.message = message;
        this.value = value;
    }

    private Object value;
    private final String message;


    public Object getValue() {
        return value;
    }

    public void setValue(Object a) {
        this.value = a;
    }

    public String getMessage() {
        return message;
    }
}
