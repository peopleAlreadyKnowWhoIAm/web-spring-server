package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class ConsoleTextFormatter {
    private static final String INVALID_DATE_STR = "Invalid entered date\nTry again!\n";
    private static final String LAST_VALUE_STR = "(%s) ";
    private static final String INVALID_NUMBER_STR = "Incorrect number\n try again\n";

    private static final String BOOLEAN_OPTIONS = "0. - false\n1. - true\n";

    public static final String DATE_PATTERN_STR = "dd/mm/yyyy";

    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN_STR);

    public static void print(String str) {
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
                print(INVALID_NUMBER_STR);
            }
        }
    }

    public static String scanString() {
        return System.console().readLine();
    }

    // Section of asking for input
    public static int editInt(final int previous, final String message) {
        print(String.format(message + LAST_VALUE_STR, String.valueOf(previous)));
        int tmp = scanInt(-1);
        if (tmp == -1) {
            return previous;
        }
        return tmp;
    }

    public static int setInt(final String message) {
        print(message);
        return scanInt(-1);
    }

    public static float editFloat(final float previous, final String message) {
        print(String.format(message + LAST_VALUE_STR, String.valueOf(previous)));

        while (true) {
            try {
                String tmp = scanString();
                if (tmp.isEmpty()) {
                    return previous;
                }
                float out = Float.parseFloat(tmp);
                return out;
            } catch (NumberFormatException e) {
                print(INVALID_NUMBER_STR);
            }
        }
    }

    public static float setFloat(final String message) {
        print(message);
        while (true) {
            try {
                String tmp = scanString();
                float out = Float.parseFloat(tmp);
                return out;
            } catch (NumberFormatException e) {
                print(INVALID_NUMBER_STR);
            }
        }
    }

    public static String editString(final String previous, final String message) {
        print(String.format(message + LAST_VALUE_STR, previous));
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
        print(String.format(message + LAST_VALUE_STR, previous.toString()));
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
        print(String.format(message + LAST_VALUE_STR, DATE_FORMATTER.format(previous)));
        Date out;
        while (true) {
            String buf = scanString();
            if (buf.isEmpty()) {
                return previous;
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

    public static Date setDate(final String message) {
        print(message);
        Date out;
        while (true) {
            String buf = scanString();
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

    public static Usage editUsage(final Usage previous, final String message) {
        print(Usage.allToString());
        print(String.format(message + LAST_VALUE_STR, previous.toString()));
        return Usage.values()[scanInt(Usage.values().length)];
    }

    public static Usage setUsage(final String message) {
        print(Usage.allToString());
        print(message);
        return Usage.values()[scanInt(Usage.values().length)];
    }

    public static Size editSize(final Size previous, final String message) {
        print(message);

        Size tmp = new Size();
        print(String.format("width: " + LAST_VALUE_STR, String.valueOf(previous.width)));
        int tmpInt = scanInt(-1);
        tmp.width = (tmpInt == -1)? previous.width: tmpInt;
        print(String.format("height: " + LAST_VALUE_STR, String.valueOf(previous.height)));
        tmpInt = scanInt(-1);
        tmp.height = (tmpInt == -1)? previous.height: tmpInt;
        print(String.format("depth: " + LAST_VALUE_STR, String.valueOf(previous.depth)));
        tmpInt = scanInt(-1);
        tmp.depth = (tmpInt == -1)? previous.depth: tmpInt;

        return tmp;
    }

    public static Size setSize(final String message) {
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

    public static Type setType(final String message) {
        print(message);
        print(Type.allToString());
        return Type.values()[scanInt(Type.values().length - 1)];
    }

    private static List<Type> setTypeList(String message) {
        print(message);
        print(Type.allToString());
        List<Type> out = new LinkedList<>();
        int tmp = -1;
        do {
            tmp = scanInt(Type.values().length);
            if (tmp != -1) {
                out.add(Type.values()[tmp]);
            }
        } while (tmp != -1);
        return out;
    }

    public static List<Usage> editUsageList(final List<Usage> previous, final String message) {
        print(String.format(message + LAST_VALUE_STR, previous.toString()));
        List<Usage> out = new LinkedList<>();
        print(Usage.allToString());
        int tmp = -1;
        do {
            tmp = scanInt(Usage.values().length - 1);
            if (tmp != -1) {
                out.add(Usage.values()[tmp]);
            }
        } while (tmp != -1);
        if (out.isEmpty()) {
            return previous;
        }
        return out;
    }

    public static List<Usage> setUsageList(final String message) {
        print(message);
        print(Usage.allToString());
        List<Usage> out = new LinkedList<>();
        int tmp = -1;
        do {
            tmp = scanInt(Usage.values().length);
            if (tmp != -1) {
                out.add(Usage.values()[tmp]);
            }
        } while (tmp != -1);
        return out;
    }

    public static boolean editBool(final boolean previous, final String message) {
        print(String.format(message + LAST_VALUE_STR, String.valueOf(previous)));
        print(BOOLEAN_OPTIONS);
        int option = scanInt(1);
        return (option == -1) ? previous : (option == 1);
    }

    public static boolean setBool(final String message) {
        print(message);
        print(BOOLEAN_OPTIONS);
        int option = scanInt(1);
        return option == 1;
    }

    @SuppressWarnings("unchecked")
    public static Object edit(final Object a, final String message) {
        String clazz = a.getClass().getName();

        if (clazz == String.class.getName()) {
            return editString((String) a, message);
        }
        if (clazz == Integer.class.getName()) {
            return editInt((Integer) a, message);
        }
        if (clazz == List.class.getName()) {
            var list = (List<?>) a;
            var listItemClass = list.get(0).getClass();

            if (listItemClass == String.class) {
                return editStringList((List<String>) a, message);
            }

            if (listItemClass == Usage.class) {
                return editUsageList((List<Usage>) a, message);
            }

            return null;
        }
        if (clazz == Date.class.getName()) {
            return editDate((Date) a, message);
        }
        if (clazz == Usage.class.getName()) {
            return editUsage((Usage) a, message);
        }
        if (clazz == Size.class.getName()) {
            return editSize((Size) a, message);
        }
        if (clazz == Float.class.getName()) {
            return editFloat((Float) a, message);
        }
        if (clazz == Boolean.class.getName()) {
            return editBool((Boolean) a, message);
        }

        return null;
    }


    public static Object set(final Object a, final String message) {
        String clazz = a.getClass().getName();
        if (clazz == String.class.getName()) {
            return setString(message);
        }
        if (clazz == Integer.class.getName()) {
            return setInt(message);
        }
        if (clazz == LinkedList.class.getName() || clazz == ArrayList.class.getName()) {
            var list = (List<Object>) a;
            String listItemClass = list.get(0).getClass().getName();


            if (listItemClass == String.class.getName()) {
                return setStringList(message);
            }

            if (listItemClass == Type.class.getName()) {
                return setTypeList(message);
            }

            if (listItemClass == Usage.class.getName()) {
                return setUsageList(message);
            }

            return null;
        }
        if (clazz == Date.class.getName()) {
            return setDate(message);
        }
        if (clazz == Usage.class.getName()) {
            return setUsage(message);
        }
        if (clazz == Size.class.getName()) {
            return setSize(message);
        }
        if (clazz == Type.class.getName()) {
            return setType(message);
        }
        if (clazz == Float.class.getName()) {
            return setFloat(message);
        }

        if (clazz == Boolean.class.getName()) {
            return setBool(message);
        }

        return null;
    }
}
