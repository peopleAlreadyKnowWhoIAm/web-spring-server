package ua.lpnu.students.labs.decoration.model;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedArrayList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedLinkedList;

public class LongDecorationTest {

  private List<LongDecoration> samples;

  @BeforeEach
  void init() {
    samples = Arrays.asList(
        new LongDecoration("Colorful rain", Usage.FOR_CHRISTMASS, 400,
            new TypedLinkedList<>("", Arrays.asList("green", "silver", "gold", "blue")),
            "colorful narrow strips", "plastic", 50, 3, 20.0f),
        new LongDecoration("Beautiful dawn", Usage.FOR_CHRISTMASS, 350,
            new TypedLinkedList<>("", Arrays.asList("yellow", "orange", "white")), "long wide rope",
            "plactic", 100, 8, 35.0f),
        new LongDecoration("Strange world", Usage.UNIVERSAL, 500,
            new TypedLinkedList<>("", Arrays.asList("blue", "green", "red")),
            "long very wide strip with ornament", "paper", 200, 40, 50.0f),
        new LongDecoration());
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

      Assertions.assertEquals(fields.get(5).getValue().toString(), a.getColor().toString());
      Assertions.assertEquals(fields.get(6).getValue(), a.getStyle());
      Assertions.assertEquals(fields.get(7).getValue(), a.getMinimalLength());
      Assertions.assertEquals(fields.get(8).getValue(), a.getWidthInCentimeters());

      fields.get(0).setValue("NAME");
      fields.get(1).setValue(Usage.UNKNOWN);
      fields.get(2).setValue("MATERIAL");
      fields.get(3).setValue(103);
      fields.get(4).setValue(104.0f);

      fields.get(5).setValue(new TypedArrayList<>("", Arrays.asList("COLOR", "COLOR")));
      fields.get(6).setValue("STYLE");
      fields.get(7).setValue(107);
      fields.get(8).setValue(108);

      a.setFields(fields);

      fields = a.getFields();

      Assertions.assertEquals(fields.get(0).getValue(), "NAME");
      Assertions.assertEquals(fields.get(1).getValue(), Usage.UNKNOWN);
      Assertions.assertEquals(fields.get(2).getValue(), "MATERIAL");
      Assertions.assertEquals(fields.get(3).getValue(), 103);
      Assertions.assertEquals(fields.get(4).getValue(), 104.0f);

      Assertions.assertEquals(fields.get(5).getValue(),
          new TypedArrayList<>("", Arrays.asList("COLOR", "COLOR")));
      Assertions.assertEquals(fields.get(6).getValue(), "STYLE");
      Assertions.assertEquals(fields.get(7).getValue(), 107);
      Assertions.assertEquals(fields.get(8).getValue(), 108);
    });
  }

  @Test
  void testToString() {
    samples.forEach((a) -> {
      String tmp = a.toString();
      Assertions.assertTrue(tmp.contains(a.getName()), 
            String.format("Error in name %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getUsage().toString()),
          String.format("Error in usage %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getMaterial()),
          String.format("Error in material %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getAvalaibleAmount())),
          String.format("Error in amount %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getColor().toString()),
          String.format("Error in colors of lights %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getStyle()), 
            String.format("Error in style %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getMinimalLength())),
          String.format("Error in minimal length %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getWidthInCentimeters())),
          String.format("Error in width %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getPrice())),
          String.format("Error in price %d dec", samples.indexOf(a)));

    });
  }
}
