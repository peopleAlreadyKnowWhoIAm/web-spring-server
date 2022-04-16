package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class ConsoleComunicator {
    private static final String INVALID_DATE_STR = "Invalid entered date\nTry again!\n";
    private static final String LAST_VALUE_STR = "%s\nPrevious: %s\n";
    private static final String INVALID_NUMBER_STR = "Incorrect number\n try again\n";

    private static final String BOOLEAN_OPTIONS = "0. - false\n1. - true\n";

    public static final String DATE_PATTERN_STR = "dd/mm/yyyy";

    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN_STR);

    private Scanner scanner = new Scanner(System.in);
    public  void print(String str) {
        System.out.print(str);
    }

    // Scanner functions (for input)
    public Integer scanInt(Integer max) {
        while (true) {
            try {
                String tmp = scanString();
                if (tmp.isEmpty()) {
                    return null;
                }
                int out = Integer.parseInt(tmp);
                if (max == null || out <= max)
                    return out;
            } catch (NumberFormatException e) {
                print(INVALID_NUMBER_STR);
            }
        }
    }

    public  int scanStrictlyInt(Integer max){
        Integer out;
        while(true){
            out = scanInt(max);
            if(out != null){
                break;
            }
            print(INVALID_NUMBER_STR);
        }
        return out;
    }

    public  String scanString() {
        return scanner.nextLine();
    }

    private Integer integerScanner(final String message){
        print(message);
        Integer tmp = scanInt(null);
        return tmp;
    }

    private Float floatScanner(final String message){
        print(message);
        while (true) {
            try {
                String tmp = scanString();
                if (tmp.isEmpty()) {
                    return null;
                }
                Float out = Float.parseFloat(tmp);
                return out;
            } catch (NumberFormatException e) {
                print(INVALID_NUMBER_STR);
            }
        }
    }

    public  String stringScanner(final String message) {
        print(message);
        String tmp = scanString();
        if (tmp.isEmpty()) {
            return null;
        }
        return tmp;
    }

    public  List<String> stringListScanner(final String message) {
        print(message);
        List<String> out = new LinkedList<>();
        String tmp = new String();
        do {
            tmp = scanString();
            if (!tmp.isEmpty()) {
                out.add(tmp);
            }
        } while (!tmp.isEmpty());
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    Date dateScanner(final String message) {
        print(message);
        Date out;
        while (true) {
            String buf = scanString();
            if (buf.isEmpty()) {
                return null;
            }
            try {
                out = DATE_FORMATTER.parse(buf);
                break;
            } catch (Exception e) {
                System.out.println(INVALID_DATE_STR);
                continue;
            }
        }
        return out;
    }

    public  Usage usageScanner(final String message) {
        print(message);
        
        print(Usage.allToString());

        var tmp = scanInt(Usage.values().length - 1);
        if(tmp == null){
            return null;
        }
        return Usage.values()[tmp];
    }

    Size sizeScanner(final String message) {
        print(message);

        Size tmp = new Size();
        print("width: ");
        var tmpInt = scanInt(null);
        if(tmpInt == null){
            return null;
        }
        tmp.width = tmpInt;

        print("height: ");
        tmpInt = scanInt(null);
        if(tmpInt == null){
            return null;
        }
        tmp.height = tmpInt;

        print("depth: ");
        tmpInt = scanInt(null);
        if(tmpInt == null){
            return null;
        }
        tmp.depth = tmpInt;

        return tmp;
    }

    Type typeScanner(final String message) {
        print(message);
        print(Type.allToString());
        var tmp = scanInt(Type.values().length - 1);

        if(tmp == null){
            return null;
        }
        return Type.values()[tmp];
    }

    List<Type> typeListScanner(String message) {
        print(message);
        List<Type> out = new LinkedList<>();
        Type tmp = null;
        do {
            tmp = typeScanner("");
            if (tmp != null) {
                out.add(tmp);
            }
        } while (tmp != null);

        if(out.isEmpty()){
            return null;
        }
        return out;
    }

    List<Usage> usageListScanner(final String message) {
        print(message);
        List<Usage> out = new LinkedList<>();
        Usage tmp = null;
        do {
            tmp = usageScanner("");
            if (tmp != null) {
                out.add(tmp);
            }
        } while (tmp != null);

        if(out.isEmpty()){
            return null;
        }
        return out;
    }

    Boolean booleanScanner(final String message) {
        print(message);
        print(BOOLEAN_OPTIONS);
        Integer option = scanInt(1);
        if(option == null){
            return null;
        }
        return option == 1;
    }


    public  Object edit(final Object previous, final String message) {
        String className = previous.getClass().getName();
        //System.out.println(className);
        if (className == String.class.getName()) {
            String tmp = stringScanner(String.format(LAST_VALUE_STR,message,previous));
            return tmp == null? previous: tmp;
            
        }
        if (className == Integer.class.getName()) {
            Integer tmp = integerScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }
        if (className == LinkedList.class.getName() || className == ArrayList.class.getName()) {
            var list = (List<?>) previous;
            var listItemClass = list.get(0).getClass();

            if (listItemClass == String.class) {
                List<String> tmp = stringListScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
                return tmp == null? previous: tmp;
            }

            if (listItemClass == Usage.class) {
                List<Usage> tmp = usageListScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
                return tmp == null? previous: tmp;
            }

            if (listItemClass == Type.class) {
                List<Type> tmp = typeListScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
                return tmp == null? previous: tmp;
            }
            return null;
        }
        if (className == Date.class.getName()) {
            Date tmp = dateScanner(String.format(LAST_VALUE_STR,message,DATE_FORMATTER.format(previous)));
            return tmp == null? previous: tmp;
        }
        if (className == Usage.class.getName()) {
            Usage tmp = usageScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }
        if (className == Type.class.getName()) {
            Type tmp = typeScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }
        if (className == Size.class.getName()) {
            Size tmp = sizeScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }
        if (className == Float.class.getName()) {
            Float tmp = floatScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }
        if (className == Boolean.class.getName()) {
            Boolean tmp = booleanScanner(String.format(LAST_VALUE_STR,message,previous.toString()));
            return tmp == null? previous: tmp;
        }

        return null;
    }


    @SuppressWarnings("unchecked")
    public  Object set(final Object pointOnType, final String message) {
        String className = pointOnType.getClass().getName();
        if (className == String.class.getName()) {
            String tmp = stringScanner(message);
            return tmp == null? new String(): tmp;
        }
        if (className == Integer.class.getName()) {
            Integer tmp = integerScanner(message);
            return tmp == null? 0: tmp;
        }
        if (className == LinkedList.class.getName() || className == ArrayList.class.getName()) {
            var list = (List<Object>) pointOnType;
            String listItemClass = list.get(0).getClass().getName();


            if (listItemClass == String.class.getName()) {
                var tmp = stringListScanner(message);
                return tmp == null? new LinkedList<>(): tmp;
            }

            if (listItemClass == Type.class.getName()) {
                var tmp = typeListScanner(message);
                return tmp == null? new LinkedList<>(): tmp;
            }

            if (listItemClass == Usage.class.getName()) {
                var tmp = usageListScanner(message);
                return tmp == null? new LinkedList<>(): tmp;
            }
            
            return null;
        }
        if (className == Date.class.getName()) {
            var tmp = dateScanner(message);
            return tmp;
        }
        if (className == Usage.class.getName()) {
            var tmp = usageScanner(message);
            return tmp;
        }
        if (className == Size.class.getName()) {
            var tmp = sizeScanner(message);
            return tmp == null? new Size(): tmp;
        }
        if (className == Type.class.getName()) {
            var tmp = typeScanner(message);
            return tmp;
        }
        if (className == Float.class.getName()) {
            var tmp = floatScanner(message);
            return tmp == null? 0.0f: tmp;
        }

        if (className == Boolean.class.getName()) {
            Boolean tmp = booleanScanner(message);
            return tmp == null? false: tmp;
        }
        
        return null;
    }
}
