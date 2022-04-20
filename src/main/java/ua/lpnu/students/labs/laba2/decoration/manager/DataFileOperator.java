package ua.lpnu.students.labs.laba2.decoration.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.laba2.decoration.model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.LongDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.PieceDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Size;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Template;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Type;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Usage;
import ua.lpnu.students.labs.laba2.decoration.storages.TypedList;
import ua.lpnu.students.labs.laba2.decoration.storages.impl.TypedArrayList;
import ua.lpnu.students.labs.laba2.decoration.storages.impl.TypedLinkedList;

/**
 * Class provide read-write functionality.
 *
 * @version 1.0
 */
@NoArgsConstructor
public final class DataFileOperator {

  /**
   * Writes given list to file.
   *
   * @param fileName name of the file
   * @param list list with templates
   * @return result comment
   * @throws Exception occurs when error in reading file
   */
  public String writeToFile(
      final String fileName,
      final List<Template> list) throws Exception {
    String out = null;
    final File file = new File(fileName);
    if (file.exists()) {
      out = "File exist. Will be overwritten";
    } else {
      if (file.createNewFile()) {
        out = "File will be created";
      }
    }

    final StringBuffer toWrite = new StringBuffer();
    for (final Template template : list) {
      toWrite.append(String.format("%s%n", toCsv(template)));
    }
    FileWriter writer = null;

    try {
      writer = new FileWriter(file, Charset.defaultCharset());
      writer.write(toWrite.toString());
      writer.close();
    } catch (final IOException e) {
      e.printStackTrace();
      if (writer != null) {
        try {
          writer.close();
        } catch (final IOException a) {
          a.printStackTrace();
        }
      }
    }

    return out;
  }

  /**
   * Read list from file.
   *
   * @param fileName name of the file
   * @return list from the file
   * @throws IOException occurs when problems in reading file
   */
  public List<Template> readFromFile(
      final String fileName) throws IOException {
    final var file = new File(fileName);
    if (!file.exists()) {
      throw new IOException("File don't exist");
    }

    Scanner scanner = null;
    final var fileData = new LinkedList<String>();
    try {
      scanner = new Scanner(
          file,
          Charset.defaultCharset()).useDelimiter("\n");
      while (scanner.hasNext()) {
        fileData.add(scanner.next());
      }
      
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      scanner.close();
    }
    
    final var list = new ArrayList<Template>(fileData.size());
    for (int i = 0; i < fileData.size(); i++) {
      // if((fileData.get.isEmpty())continue;
      list.add(fromCsv(fileData.get(i)));
    }

    return list;
  }

  /**
   * Convert template extends object to csv.
   *
   * @param object template to convert
   * @return csv-string
   * @throws Exception when list in structure is empty or not typed
   */
  String toCsv(final Template object) throws Exception {
    final var fields = object.getFields();

    final String type = object.getClass().getSimpleName();

    final StringBuffer fieldsString = new StringBuffer();
    for (final var field : fields) {

      fieldsString.append(toCsvPrimitive(field.getValue()));
      fieldsString.append(";");
    }

    return String.format(
        OBJECT_TEMPLATE,
        type, fieldsString.substring(0, fieldsString.length() - 1));
  }

  /**
   * Convert csv-string to the template object.
   *
   * @param csv converted string
   * @return from csv-string
   */
  Template fromCsv(final String csv) {
    final var trimmedCsv = csv.substring(1, csv.length() - 1);
    final var typeSplitter = trimmedCsv.indexOf(":");
    if (typeSplitter == -1) {
      System.out.println(csv);
    }
    final var typeStr = trimmedCsv.substring(0, typeSplitter);
    final var values = trimmedCsv.substring(typeSplitter + 1).split(";");

    final var decoration = DECORATION_TYPES.get(typeStr).createDecoration();

    final var fields = decoration.getFields();
    for (int i = 0; i < fields.size(); i++) {
      fields.get(i).setValue(fromCsvPrimitive(values[i]));
    }

    decoration.setFields(fields);

    return decoration;
  }

  /**
   * Generate csv string from the object(primitive or list).
   *
   * @param object primitive object(or list) to convert
   * @return String csv
   * @throws Exception when list in structure is empty or not typed
   */
  String toCsvPrimitive(final Object object) throws Exception {
    String out = "";
    if (object instanceof TypedList) {
      final var list = (TypedList<?>) object;
      final StringBuffer tmpList = new StringBuffer();

      for (int i = 0; i < list.size(); i++) {
        if (list.getType().getClass() == String.class) {
          tmpList.append(String.format(STRING_TEMPLATE, list.get(i)));
        } else {
          tmpList.append(list.toString());
        }
        if (i != list.size() - 1) {
          tmpList.append(COMMA);
        }
      }

      out = String.format(LIST_TEMPLATE,
          list.getClass().getSimpleName(),
          list.getType().getClass().getSimpleName(),
          tmpList);

    } else if (object instanceof List) {
      final var list = (List<?>) object;
      final StringBuffer tmpList = new StringBuffer();
      if (list.isEmpty()) {
        throw new Exception("List is empty or not typed");
      }
      final var type = list.get(0);
      for (int i = 0; i < list.size(); i++) {
        if (type.getClass() == String.class) {
          tmpList.append(String.format(STRING_TEMPLATE, list.get(i)));
        } else {
          tmpList.append(list.toString());
        }
        if (i != list.size() - 1) {
          tmpList.append(COMMA);
        }
      }

      out = String.format(LIST_TEMPLATE,
          list.getClass().getSimpleName(),
          type.getClass().getSimpleName(),
          tmpList.toString());

    } else if (object instanceof String) {
      out = String.format(OBJECT_TEMPLATE, String.class.getSimpleName(),
          String.format(STRING_TEMPLATE, object.toString()));
    } else if (object instanceof Date) {
      out = String.format(
          OBJECT_TEMPLATE,
          Date.class.getSimpleName(),
          String.valueOf(((Date) object).getTime()));
    } else {
      out = String.format(
          OBJECT_TEMPLATE,
          object.getClass().getSimpleName(), object.toString());
    }

    return out;
  }

  /**
   * Read object(primitive and lists) from csv string.
   *
   * @param csvPrimitive String with csv primitive(or List)
   * @return Object
   */
  Object fromCsvPrimitive(final String csvPrimitive) {
    final var csvPrimitiveCorrect = csvPrimitive.substring(
        1, csvPrimitive.length() - 1);
    final var valuesOfStrign = csvPrimitiveCorrect.split(SEMICOLON);
    if (valuesOfStrign[0].contains("?")) {

      final var typeDescription = valuesOfStrign[0].split("\\?");
      final List<Object> list = LIST_TYPES.get(typeDescription[0])
          .createList(OBJECT_TYPES.get(typeDescription[1]));
      if (valuesOfStrign.length == 2) {
        final var valuesStrings = valuesOfStrign[1].split(COMMA);
        for (final String string : valuesStrings) {
          list.add(OBJECT_PARSER
              .get(typeDescription[1]).parseType(string));
        }
      }
      return list;
    } else {
      return OBJECT_PARSER.get(
          valuesOfStrign[0]).parseType(valuesOfStrign[1]);
    }
  }

  public static final String COMMA = ",";
  public static final String QUOTE = "\"";
  public static final String SEMICOLON = ":";
  public static final String LIST_TEMPLATE = "\"%s?%s:%s\"";
  public static final String OBJECT_TEMPLATE = "\"%s:%s\"";
  public static final String STRING_TEMPLATE = "\"%s\"";

  public static final Map<String, CreateList> LIST_TYPES = new HashMap<>() {
    {
      put(TypedArrayList.class.getSimpleName(),
          (object) -> new TypedArrayList<Object>(object.getClass().cast(object)));
      put(TypedLinkedList.class.getSimpleName(),
          (object) -> new TypedLinkedList<Object>(object.getClass().cast(object)));
      put(ArrayList.class.getSimpleName(), (object) -> new ArrayList<Object>());
      put(LinkedList.class.getSimpleName(), (object) -> new LinkedList<Object>());
    }
  };
  public static final Map<String, Object> OBJECT_TYPES = new HashMap<>() {
    {
      put(Integer.class.getSimpleName(), 0);
      put(Float.class.getSimpleName(), 0f);
      put(String.class.getSimpleName(), "");
      put(Usage.class.getSimpleName(), Usage.UNKNOWN);
      put(Type.class.getSimpleName(), Type.ELECTRIC_DECORATION);
      put(Date.class.getSimpleName(), new Date());
      put(Size.class.getSimpleName(), new Size());
    }
  };
  public static final Map<String, ParseObject> OBJECT_PARSER = new HashMap<>() {
    {
      put(Integer.class.getSimpleName(), (csv) -> Integer.parseInt(csv));
      put(Float.class.getSimpleName(), (csv) -> Float.parseFloat(csv));
      put(String.class.getSimpleName(), (csv) -> csv.substring(1, csv.length() - 1));
      put(Usage.class.getSimpleName(), (csv) -> Usage.valueOf(csv));
      put(Type.class.getSimpleName(), (csv) -> Type.valueOf(csv));
      put(Date.class.getSimpleName(), (csv) -> new Date(Long.parseLong(csv)));
      put(Size.class.getSimpleName(), (csv) -> Size.parseSize(csv));

    }
  };
  public static final Map<String, CreateDecoration> DECORATION_TYPES = new HashMap<>() {
    {
      put(ElectricDecoration.class.getSimpleName(), () -> new ElectricDecoration());
      put(LongDecoration.class.getSimpleName(), () -> new LongDecoration());
      put(OrganicDecoration.class.getSimpleName(), () -> new OrganicDecoration());
      put(PieceDecoration.class.getSimpleName(), () -> new PieceDecoration());
    }
  };

  @FunctionalInterface
  interface CreateList {
    List<Object> createList(Object initType);
  }

  @FunctionalInterface
  interface ParseObject {
    Object parseType(String csv);
  }

  @FunctionalInterface
  interface CreateDecoration {
    Template createDecoration();
  }
}
