package ua.lpnu.students.labs.laba2.decoration.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.laba2.decoration.data.DataStorage;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Size;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Template;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Type;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Usage;
import ua.lpnu.students.labs.laba2.decoration.storages.TypedList;
import ua.lpnu.students.labs.laba2.decoration.storages.impl.TypedLinkedList;

public class DataFileOperatorTest {
  List<Template> decorations;

  @BeforeEach
  void init() {
    decorations = new DataStorage().decorations;
  }

  @Test
  void converterCsv() {
    DataFileOperator operator = new DataFileOperator();
    decorations.forEach((a) -> {
      try {
        Assertions.assertEquals(a.toString(), (operator.fromCsv(operator.toCsv(a))).toString());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Test
  void operationWithFile() throws Exception {
    DataFileOperator file = new DataFileOperator();

    file.writeToFile("file.csv", new DataStorage().decorations);

    var result = file.readFromFile("file.csv");

    for (var i = 0; i < decorations.size(); i++) {
      Assertions.assertEquals(decorations.get(i).toString(), result.get(i).toString());
    }
  }

  @Test
  void testConverts() throws Exception {
    Object[] buf = {
        new String("Help"),
        Usage.FOR_CHRISTMASS,
        Type.ELECTRIC_DECORATION,
        new Size(12, 11, 10),
        new ArrayList<>(Arrays.asList("one", "two", "zero")),
        new TypedLinkedList<String>(new String()),
        new GregorianCalendar(2009, 9, 29).getTime()
    };

    DataFileOperator converter = new DataFileOperator();

    for (Object object : buf) {
      Assertions.assertEquals(object.toString(),
          (converter.fromCsvPrimitive(converter.toCsvPrimitive(object))).toString());
      if (object instanceof TypedList) {
        Assertions.assertEquals(((TypedList<?>) object).getType(),
            ((TypedList<?>) (
              converter.fromCsvPrimitive(converter.toCsvPrimitive(object)))).getType());
      }
    }
  }
}
