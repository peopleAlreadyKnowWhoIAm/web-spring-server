package ua.lpnu.students.labs.decoration.model;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Usage;

public class PieceDecorationTest {
    private List<PieceDecoration> samples;

    @BeforeEach
    void init() {
        samples = Arrays.asList(
                new PieceDecoration("Colorful ball", Usage.FOR_CHRISTMASS, 340, "green and blue",
                        "classic", "glass",
                        new Size(10, 10, 10), 65.0f),

                new PieceDecoration("Glass cone", Usage.FOR_CHRISTMASS, 180, "gold-yellow",
                        "classic", "plastic",
                        new Size(8, 15, 8), 25.0f),

                new PieceDecoration("Black cube", Usage.FOR_CHRISTMASS, 20, "black",
                        "modern", "glass", new Size(11, 18, 9),
                        659.0f),

                new PieceDecoration());
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

            fields.get(0).setValue("NAME");
            fields.get(1).setValue(Usage.UNKNOWN);
            fields.get(2).setValue("MATERIAL");
            fields.get(3).setValue(103);
            fields.get(4).setValue(104.0f);

            fields.get(5).setValue("COLOR");
            fields.get(6).setValue("STYLE");
            fields.get(7).setValue(new Size(107, 107, 107));

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
        });
    }
}
