package ua.lpnu.students.labs.decoration.manager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;
import ua.lpnu.students.labs.decoration.textmenu.TextParser;

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
   * @param filePath path to the file
   * @param list     list with templates
   */
  public void writeToFile(
      final Path filePath,
      final List<Decoration> list) throws IOException {
    ArrayList<StringBuilder> buffers = new ArrayList<>(Type.values().length);
    for (int i = 0; i < Type.values().length; i++) {
      var buf = new StringBuilder();
      buf.append(Type.values()[i].toString() + "\n");
      buf.append(
          Type.values()[i]
              .createDecoration()
              .getFields()
              .stream()
              .map((a) -> a.getMessage())
              .collect(Collectors.joining(","))
          + '\n'
      );
      buffers.add(buf);
    }
    for (Decoration decoration : list) {
      int index = decoration.getType().ordinal();
      buffers.get(index).append(toCsv(decoration));
    }
    final var out = String.join("\n", buffers);
    var file = filePath.toFile();
    if (!file.exists()) {
      if (!file.createNewFile()) {
        throw new IOException("File not created");
      }
    }
    if (!(file.canRead() && file.canWrite())) {
      throw new IOException("Don't have permission to the file");
    }
    try (var writer = new FileWriter(file, Charset.defaultCharset(), false)) {
      writer.flush();
      writer.write(out);
    } catch (IOException e) {
      System.err.println(e.toString());
    }

  }

  /**
   * Read list from file.
   *
   * @param filePath path to the file
   * @return list from the file
   * @throws IOException occurs when problems in reading file
   */
  public List<Decoration> readFromFile(
      final Path filePath) throws IOException {
    final var file = filePath.toFile();
    if (!file.exists()) {
      throw new IOException("File don't exist");
    }

    final var fileData = new LinkedList<String>();
    try (Scanner scanner = new Scanner(
        file,
        Charset.defaultCharset()).useDelimiter("\n\n")) {
      while (scanner.hasNext()) {
        fileData.add(scanner.next());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    LinkedList<Decoration> out = new LinkedList<>();
    for (String string : fileData) {
      var iterator = string.lines().iterator();
      final Type type = Type.valueOf(iterator.next());
      iterator.next();
      if (!iterator.hasNext()) {
        continue;
      }
      iterator.forEachRemaining((str) -> out.add(fromCsv(str, type)));
    }

    return new ArrayList<>(out);
  }

  /**
   * Convert template extends object to csv.
   *
   * @param object template to convert
   * @return csv-string
   * @throws Exception when list in structure is empty or not typed
   */
  String toCsv(final Decoration object) {
    final var fields = object.getFields();
    var out = fields.parallelStream().map((field) -> {
      if (field.getValue() instanceof List) {
        var buf = new StringBuilder();
        buf.append('"');
        var list = (List<?>) field.getValue();
        buf.append(list.stream().map((a) -> a.toString()).collect(Collectors.joining(",")));
        buf.append(",\"");
        return buf.toString();
      } else if (field.getValue() instanceof Date) {
        return String.valueOf(((Date) field.getValue()).getTime());
      } else {
        return field.getValue().toString();
      }
    }).collect(Collectors.joining(","));

    out += '\n';
    return out;
  }

  /**
   * Convert csv-string to the template object.
   *
   * @param csv converted string
   * @return from csv-string
   */
  Decoration fromCsv(final String csvLine, final Type type) {
    var decoration = type.createDecoration();
    var fields = decoration.getFields();
    var parser = new TextParser(csvToStandartIn(csvLine));
    for (FieldDescription field : fields) {
      field.setValue(parser.set(field.getValue(), ""));
    }
    decoration.setFields(fields);
    return decoration;
  }

  String csvToStandartIn(String str) {
    str = str.replace("\"", "");
    str = str.replace(',', '\n');
    str += '\n';
    return str;
  }
}
