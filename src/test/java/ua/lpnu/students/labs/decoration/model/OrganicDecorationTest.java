package ua.lpnu.students.labs.decoration.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.textmenu.TextParser;

public class OrganicDecorationTest {
  private List<OrganicDecoration> samples;

  @BeforeEach
  void init() {
    samples = Arrays.asList(
        new OrganicDecoration("Christmass", Usage.UNIVERSAL, 73, "dark green", 
          "classic christmass", "orginic",
            new Size(100, 200, 100), 507.0f, 60, (new GregorianCalendar(2021, 12, 30)).getTime()),
        new OrganicDecoration("Christmass branches", Usage.UNIVERSAL, 38, "dark green",
             "classic christmass", "orginic",
            new Size(10, 100, 50), 50.0f, 60, (new GregorianCalendar(2021, 12, 28)).getTime()),
        new OrganicDecoration());
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

      Assertions.assertEquals(fields.get(5).getValue(), a.getColor());
      Assertions.assertEquals(fields.get(6).getValue(), a.getStyle());
      Assertions.assertEquals(fields.get(7).getValue(), a.getSize());

      Assertions.assertEquals(fields.get(8).getValue(), a.getNumberOfDaysToExpiration());
      Assertions.assertEquals(fields.get(9).getValue(), a.getDateOfProduction());

      fields.get(0).setValue("NAME");
      fields.get(1).setValue(Usage.UNKNOWN);
      fields.get(2).setValue("MATERIAL");
      fields.get(3).setValue(103);
      fields.get(4).setValue(104.0f);

      fields.get(5).setValue("COLOR");
      fields.get(6).setValue("STYLE");
      fields.get(7).setValue(new Size(107, 107, 107));

      fields.get(8).setValue(108);
      fields.get(9).setValue(new GregorianCalendar(2009, 9, 29).getTime());

      a.setFields(fields);

      fields = a.getFields();

      Assertions.assertEquals(fields.get(0).getValue(), "NAME");
      Assertions.assertEquals(fields.get(1).getValue(), Usage.UNKNOWN);
      Assertions.assertEquals(fields.get(2).getValue(), "MATERIAL");
      Assertions.assertEquals(fields.get(3).getValue(), 103);
      Assertions.assertEquals(fields.get(4).getValue(), 104.0f);

      Assertions.assertEquals(fields.get(5).getValue(), "COLOR");
      Assertions.assertEquals(fields.get(6).getValue(), "STYLE");
      Assertions.assertEquals(fields.get(7).getValue(), new Size(107, 107, 107));

      Assertions.assertEquals(fields.get(8).getValue(), 108);
      Assertions.assertEquals(fields.get(9).getValue(), 
          new GregorianCalendar(2009, 9, 29).getTime());
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
      Assertions.assertTrue(tmp.contains(String.valueOf(a.getPrice())),
          String.format("Error in price %d dec", samples.indexOf(a)));

      Assertions.assertTrue(tmp.contains(String.valueOf(a.getAvalaibleAmount())),
          String.format("Error in amount %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getColor().toString()),
          String.format("Error in colors of lights %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getStyle()), 
          String.format("Error in length %d dec", samples.indexOf(a)));
      Assertions.assertTrue(tmp.contains(a.getSize().toString()),
          String.format("Error in power %d dec", samples.indexOf(a)));

      Assertions.assertTrue(tmp.contains(String.valueOf(a.getNumberOfDaysToExpiration())),
          String.format("Error in amount per meter %d dec", samples.indexOf(a)));
      Assertions.assertTrue(
          tmp.contains(new SimpleDateFormat(TextParser.DATE_PATTERN_STR)
                                        .format(a.getDateOfProduction())),
          String.format("Error in price %d dec", samples.indexOf(a)));

    });
  }
}
