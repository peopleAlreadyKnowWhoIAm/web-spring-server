package ua.lpnu.students.labs.decoration.textmenu;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.TypedList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedLinkedList;

/**
 * Class which convert text(from standart input or an String) to an object.
 */
public class TextParser {
  private static final String INVALID_DATE_STR = "Invalid entered date\nTry again!";
  private static final String LAST_VALUE_STR = "%s%nPrevious: %s";
  private static final String INVALID_NUMBER_STR = "Incorrect number\nTry again!";

  private static final String BOOLEAN_OPTIONS = "0. - false\n1. - true\n";

  public static final String DATE_PATTERN_STR = "dd/mm/yyyy";
  public static final String INPUT_PREFIX_STR = "\n>>> ";

  public final DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN_STR);

  private Scanner scanner;

  private boolean fromText = false;

  public TextParser() {
    scanner = new Scanner(System.in, Charset.defaultCharset());
  }

  public TextParser(String text) {
    scanner = new Scanner(text);
    fromText = true;
  }

  /**
   * Print string when scanning from standart input (not text).
   *
   * @param str to print
   */
  public void print(String str) {
    if (!fromText) {
      System.out.print(str);
    }
  }

  public String scanString() {
    print(INPUT_PREFIX_STR);
    return scanner.nextLine();
  }

  /**
   * Scan int from the standart input.
   *
   * @param max maximum scanned integer
   * @return the integer or `null` when empty
   */
  public Integer scanInt(Integer max) {
    while (true) {
      try {
        String tmp = scanString();
        if (tmp.isEmpty()) {
          return null;
        }
        int out = Integer.parseInt(tmp);
        if (max == null || out <= max) {
          return out;
        }
      } catch (NumberFormatException e) {
        print(INVALID_NUMBER_STR);
      }
    }
  }

  /**
   * Scan long from the standart input.
   *
   * @return the long
   */
  public long scanLong() {
    while (true) {
      try {
        String tmp = scanString();
        long out = Long.parseLong(tmp);
        return out;
      } catch (NumberFormatException e) {
        print(INVALID_NUMBER_STR);
      }
    }
  }

  /**
   * Scan only int.
   *
   * @param max max possible integer
   * @return the integer
   */
  public int scanStrictlyInt(Integer max) {
    Integer out;
    while (true) {
      out = scanInt(max);
      if (out != null) {
        break;
      }
      print(INVALID_NUMBER_STR);
    }
    return out;
  }

  private Integer integerScanner(final String message) {
    print(message);
    Integer tmp = scanInt(null);
    return tmp;
  }

  private Float floatScanner(final String message) {
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

  /**
   * Scan string from the standart input.
   *
   * @param message to show
   * @return entered string or `null` when empty
   */
  public String stringScanner(final String message) {
    print(message);
    String tmp = scanString();
    if (tmp.isEmpty()) {
      return null;
    }
    return tmp;
  }

  /**
   * Scan string list from the standart input.
   *
   * @param message to show
   * @return entered string list or null when empty
   */
  public List<String> stringListScanner(final String message) {
    print(message);
    List<String> out = new TypedLinkedList<>("");
    String tmp;
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
    if (fromText) {
      return new Date(scanLong());
    } else {
      print(message);
      Date out;
      while (true) {
        String buf = scanString();
        if (buf.isEmpty()) {
          return null;
        }
        try {
          out = dateFormat.parse(buf);
          break;
        } catch (Exception e) {
          System.out.println(INVALID_DATE_STR);
          continue;
        }
      }
      return out;
    }
  }

  Usage usageScanner(final String message) {
    if (fromText) {
      return Usage.valueOf(scanString());
    } else {
      print(message);

      print(Usage.allToString());

      var tmp = scanInt(Usage.values().length - 1);
      if (tmp == null) {
        return null;
      }
      return Usage.values()[tmp];
    }
  }

  Size sizeScanner(final String message) {
    if (fromText) {
      return Size.parseSize(scanString());
    } else {
      print(message);

      Size tmp = new Size();
      print("width: ");
      var tmpInt = scanInt(null);
      if (tmpInt == null) {
        return null;
      }
      tmp.width = tmpInt;

      print("height: ");
      tmpInt = scanInt(null);
      if (tmpInt == null) {
        return null;
      }
      tmp.height = tmpInt;

      print("depth: ");
      tmpInt = scanInt(null);
      if (tmpInt == null) {
        return null;
      }
      tmp.depth = tmpInt;

      return tmp;
    }
  }

  Type typeScanner(final String message) {
    if (fromText) {
      return Type.valueOf(scanString());
    } else {
      print(message);
      print(Type.allToString());
      var tmp = scanInt(Type.values().length - 1);

      if (tmp == null) {
        return null;
      }
      return Type.values()[tmp];
    }

  }

  List<Type> typeListScanner(String message) {
    print(message);
    List<Type> out = new TypedLinkedList<>(Type.ELECTRIC_DECORATION);
    Type tmp = null;
    do {
      tmp = typeScanner("");
      if (tmp != null) {
        out.add(tmp);
      }
    } while (tmp != null);

    if (out.isEmpty()) {
      return null;
    }
    return out;
  }

  List<Usage> usageListScanner(final String message) {
    print(message);
    List<Usage> out = new TypedLinkedList<>(Usage.UNIVERSAL);
    Usage tmp = null;
    do {
      tmp = usageScanner("");
      if (tmp != null) {
        out.add(tmp);
      }
    } while (tmp != null);

    if (out.isEmpty()) {
      return null;
    }
    return out;
  }

  private Boolean booleanScanner(final String message) {
    print(message);
    print(BOOLEAN_OPTIONS);
    Integer option = scanInt(1);
    if (option == null) {
      return false;
    }
    return option == 1;
  }

  /**
   * Edit the object or return previous when empty.
   *
   * @param previous object to change
   * @param message  to show
   * @return edited object
   */
  public Object edit(final Object previous, final String message) {
    // System.out.println(className);
    if (previous instanceof String) {
      String tmp = stringScanner(String.format(LAST_VALUE_STR, message, previous));
      return tmp == null ? previous : tmp;

    }
    if (previous instanceof Integer) {
      Integer tmp = integerScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof TypedList) {
      var list = (TypedList<?>) previous;
      var listItemClass = list.getType();

      if (listItemClass instanceof String) {
        List<String> tmp = stringListScanner(
            String.format(LAST_VALUE_STR, message, previous.toString()));
        return tmp == null ? previous : tmp;
      }

      if (listItemClass instanceof Usage) {
        List<Usage> tmp = usageListScanner(
            String.format(LAST_VALUE_STR, message, previous.toString()));
        return tmp == null ? previous : tmp;
      }

      if (listItemClass instanceof Type) {
        List<Type> tmp = typeListScanner(
            String.format(LAST_VALUE_STR, message, previous.toString()));
        return tmp == null ? previous : tmp;
      }
      return null;
    }
    if (previous instanceof Date) {
      Date tmp = dateScanner(String.format(LAST_VALUE_STR, message, dateFormat.format(previous)));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof Usage) {
      Usage tmp = usageScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof Type) {
      Type tmp = typeScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof Size) {
      Size tmp = sizeScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof Float) {
      Float tmp = floatScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }
    if (previous instanceof Boolean) {
      Boolean tmp = booleanScanner(String.format(LAST_VALUE_STR, message, previous.toString()));
      return tmp == null ? previous : tmp;
    }

    return null;
  }

  /**
   * Scan given object from the standart input.
   *
   * @param pointOnType type to scan
   * @param message     to show
   * @return the object
   */
  public Object set(final Object pointOnType, final String message) {
    if (pointOnType instanceof String) {
      String tmp = stringScanner(message);
      return tmp == null ? "" : tmp;
    }
    if (pointOnType instanceof Integer) {
      Integer tmp = integerScanner(message);
      if (tmp == null) {
        return 0;
      } else {
        return tmp;
      }
    }
    if (pointOnType instanceof TypedList) {
      var list = (TypedList<?>) pointOnType;
      Object listItemClass = list.getType();

      if (listItemClass instanceof String) {
        var tmp = stringListScanner(message);
        return tmp == null ? new LinkedList<>() : tmp;
      }

      if (listItemClass instanceof Type) {
        var tmp = typeListScanner(message);
        return tmp == null ? new LinkedList<>() : tmp;
      }

      if (listItemClass instanceof Usage) {
        var tmp = usageListScanner(message);
        return tmp == null ? new LinkedList<>() : tmp;
      }

      return null;
    }
    if (pointOnType instanceof Date) {
      var tmp = dateScanner(message);
      return tmp;
    }
    if (pointOnType instanceof Usage) {
      var tmp = usageScanner(message);
      return tmp;
    }
    if (pointOnType instanceof Size) {
      var tmp = sizeScanner(message);
      if (tmp == null) {
        return new Size();
      } else {
        return tmp;
      }
    }
    if (pointOnType instanceof Type) {
      var tmp = typeScanner(message);
      return tmp;
    }
    if (pointOnType instanceof Float) {
      var tmp = floatScanner(message);
      if (tmp == null) {
        return 0.0f;
      } else {
        return tmp;
      }
    }

    if (pointOnType instanceof Boolean) {
      Boolean tmp = booleanScanner(message);
      if (tmp == null) {
        return false;
      } else {
        return tmp;
      }
    }

    return null;
  }
}
