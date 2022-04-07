package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class ConsoleTextFormatter{
    private static final String invalidDateStr = "Invalid entered date\nTry again!\n";
    private static final String lastValueStr = "(%s) ";
    private static final String invalidNumberStr = "Incorrect number\n try again\n";

    public static final String datePatternStr = "dd/mm/yyyy";

    public static final DateFormat dateFormat = new SimpleDateFormat(datePatternStr);

    public static void print(String str){
        System.out.print(str);
    }

    // Scanner functions (for input)
    public static int scanInt(int max) {
        while (true) {
            try {
                String tmp = scanString();
                if (tmp.isEmpty()) {
                    return -1;
                }
                int out = Integer.parseInt(tmp);
                if (out <= max || max == -1)
                    return out;
            } catch (NumberFormatException e) {
                print(invalidNumberStr);
            }
        }
    }

    public static String scanString() {
        return System.console().readLine();
    }

    // Section of asking for input
    public static int editInt(final int previous, final String message) {
        print(String.format(message + lastValueStr, String.valueOf(previous)));
        int tmp = scanInt(-1);
        if (tmp == -1) {
            return previous;
        }
        return tmp;
    }

    public static int setInt( final String message) {
        print(message);
        return scanInt(-1);
    }

    public static float editFloat(final float previous, final String message){
        print(String.format(message + lastValueStr, String.valueOf(previous)));

        while (true) {
            try {
                String tmp = scanString();
                if (tmp.isEmpty()) {
                    return previous;
                }
                float out = Float.parseFloat(tmp);
                return out;
            } catch (NumberFormatException e) {
                print(invalidNumberStr);
            }
        }
    }

    public static float setFloat(final String message){
        print(message);
        while (true) {
            try {
                String tmp = scanString();
                float out = Float.parseFloat(tmp);
                return out;
            } catch (NumberFormatException e) {
                print(invalidNumberStr);
            }
        }
    }

    public static String editString(final String previous, final String message) {
        print(String.format(message + lastValueStr, previous));
        String tmp = scanString();
        if (tmp.isEmpty()) {
            return previous;
        }
        return tmp;
    }

    public static String setString(final String message) {
        System.out.print(message);
        return scanString();
    }

    public static List<String> editStringList(final List<String> previous, final String message) {
        print(String.format(message + lastValueStr, previous.toString()));
        List<String> out = new LinkedList<>();
        String tmp = new String();
        do {
            tmp = scanString();
            if (!tmp.isEmpty()) {
                out.add(tmp);
            }
        } while (!tmp.isEmpty());
        if (out.isEmpty()) {
            return previous;
        }
        return out;
    }

    public static List<String> setStringList(final String message) {
        print(message);
        List<String> out = new LinkedList<>();
        String tmp = new String();
        do {
            tmp = scanString();
            if (!tmp.isEmpty()) {
                out.add(tmp);
            }
        } while (!tmp.isEmpty());
        return out;
    }

    public static Date editDate(final Date previous, final String message) {
        print(String.format(message + lastValueStr, dateFormat.format(previous)));
        Date out;
        while (true) {
            String buf = scanString();
            if (buf.isEmpty()) {
                return previous;
            }
            try {
                out = dateFormat.parse(buf);
                break;
            } catch (Exception e) {
                System.out.println(invalidDateStr);
                continue;
            }
        }
        return out;
    }

    public static Date setDate(final String message) {
        print(message);
        Date out;
        while (true) {
            String buf = scanString();
            try {
                out = dateFormat.parse(buf);
                break;
            } catch (Exception e) {
                System.out.println(invalidDateStr);
                continue;
            }
        }
        return out;
    }

    public static Usage editUsage(final Usage previous, final String message){
        print(Usage.allToString());
        print(String.format(message + lastValueStr, previous.toString()));
        return Usage.values()[scanInt(Usage.values().length)];
    }

    public static Usage setUsage(final String message){
        print(Usage.allToString());
        print(message);
        return Usage.values()[scanInt(Usage.values().length)];
    }
    
    public static Size editSize(final Size previous, final String message){
        print(message);

        Size tmp = new Size();
        print(String.format("width: " + lastValueStr, String.valueOf(previous.width)));
        tmp.width = scanInt(-1);
        print(String.format("height: " + lastValueStr, String.valueOf(previous.height)));
        tmp.height = scanInt(-1);
        print(String.format("depth: " + lastValueStr, String.valueOf(previous.depth)));
        tmp.depth = scanInt(-1);

        return tmp;
    }

    public static Size setSize(final String message){
        print(message);
        
        Size tmp = new Size();
        print("width: ");
        tmp.width = scanInt(-1);
        print("height: ");
        tmp.height = scanInt(-1);
        print("depth: ");
        tmp.depth = scanInt(-1);

        return tmp;
    }

    public static Type setType(final String message){
        print(message);
        print(Type.allToString());
        return Type.values()[scanInt(Type.values().length - 1)];
    }

    public static Object edit(final Object a, final String message){
        return null;
    }

    public static Object set(final Object a, final String message){
        String clazz = a.getClass().getName();

        if(clazz == String.class.getName()){
            return setString(message);
        }
        if(clazz == Integer.class.getName()){
            return setInt(message);
        }
        if(clazz == List.class.getName()){
            return setStringList(message);
        }
        if(clazz == Date.class.getName()){
            return setDate(message);
        }
        if(clazz == Usage.class.getName()){
            return setUsage(message);
        }
        if(clazz == Size.class.getName()){
            return setSize(message);
        }
        if(clazz == Type.class.getName()){
            return setType(message);
        }
        if(clazz == Float.class.getName()){
            return setFloat(message);
        }

    
        return null;
    }

}
