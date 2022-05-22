package ua.lpnu.students.labs.decoration.model.shared;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.data.DataStorage;

public class DecorationTest {
  private List<Decoration> samples;

  @BeforeEach
  void init() {
    samples = new DataStorage().decorations;
  }

  @Test
  void testGetAndSetFields() {
    samples.forEach((a) -> {
      var fields = a.getFields();
      Assertions.assertEquals(fields.get(0).getValue(), a.getName());
      Assertions.assertEquals(fields.get(1).getValue(), a.getUsage());
      Assertions.assertEquals(fields.get(2).getValue(), a.getMaterial());
      Assertions.assertEquals(fields.get(3).getValue(), a.getAvalaibleAmount());
      Assertions.assertEquals(fields.get(4).getValue(), a.getPrice());

      fields.get(0).setValue("NAME");
      fields.get(1).setValue(Usage.UNKNOWN);
      fields.get(2).setValue("MATERIAL");
      fields.get(3).setValue(103);
      fields.get(4).setValue(104.0f);

      a.setFields(fields);

      fields = a.getFields();

      Assertions.assertEquals(fields.get(0).getValue(), "NAME");
      Assertions.assertEquals(fields.get(1).getValue(), Usage.UNKNOWN);
      Assertions.assertEquals(fields.get(2).getValue(), "MATERIAL");
      Assertions.assertEquals(fields.get(3).getValue(), 103);
      Assertions.assertEquals(fields.get(4).getValue(), 104.0f);

    });
  }

  @Test
  void testToString() {
    samples.forEach((a) -> {
      String tmp = a.toString();
      Assertions.assertTrue(
          tmp.contains(a.getName()), String.format("Error in name %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getUsage().toString()),
          String.format("Error in usage %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getMaterial()),
          String.format("Error in material %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getAvalaibleAmount())),
          String.format("Error in amount %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getPrice())),
          String.format("Error in price %d dec", samples.indexOf(a)));

    });
  }
}
