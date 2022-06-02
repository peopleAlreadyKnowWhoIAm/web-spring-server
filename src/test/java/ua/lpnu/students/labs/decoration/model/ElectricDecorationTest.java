package ua.lpnu.students.labs.decoration.model;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedArrayList;

public class ElectricDecorationTest {

    private List<ElectricDecoration> samples;

    @BeforeEach
    void init() {
        samples = Arrays.asList(
                new ElectricDecoration("Cheap christmass lights", Usage.FOR_CHRISTMASS, "plastic", 120,
                        new TypedArrayList<>("", Arrays.asList("blue", "green", "red")), 150, 10, 25, 120.50f),
                new ElectricDecoration("Interested house ilumination", Usage.OUTSIDE_DECORATION, "plastic",
                        40, new TypedArrayList<>("", Arrays.asList("yellow", "green", "red", "blue", "white")),
                        2500, 15, 150, 1200.0f),
                new ElectricDecoration());
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

            Assertions.assertEquals(fields.get(5).getValue().toString(),
                    a.getColorsOfLights().toString());
            Assertions.assertEquals(fields.get(6).getValue(), a.getLength());
            Assertions.assertEquals(fields.get(7).getValue(), a.getAmountLampsPerMeter());
            Assertions.assertEquals(fields.get(8).getValue(), a.getPowerInWatts());

            fields.get(0).setValue("NAME");
            fields.get(1).setValue(Usage.UNKNOWN);
            fields.get(2).setValue("MATERIAL");
            fields.get(3).setValue(103);
            fields.get(4).setValue(104.0f);

            fields.get(5).setValue(new TypedArrayList<>(new String(), Arrays.asList("COLOR", "COLOR")));
            fields.get(6).setValue(106);
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
                    new TypedArrayList<>(new String(), Arrays.asList("COLOR", "COLOR")));
            Assertions.assertEquals(fields.get(6).getValue(), 106);
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
            Assertions.assertTrue(tmp.contains(a.getColorsOfLights().toString()),
                    String.format("Error in colors of lights %d dec", samples.indexOf(a)));
            Assertions.assertTrue(tmp.contains(String.valueOf(a.getLength())),
                    String.format("Error in length %d dec", samples.indexOf(a)));
            Assertions.assertTrue(tmp.contains(String.valueOf(a.getPowerInWatts())),
                    String.format("Error in power %d dec", samples.indexOf(a)));
            Assertions.assertTrue(tmp.contains(String.valueOf(a.getAmountLampsPerMeter())),
                    String.format("Error in amount per meter %d dec", samples.indexOf(a)));
            Assertions.assertTrue(tmp.contains(String.valueOf(a.getPrice())),
                    String.format("Error in price %d dec", samples.indexOf(a)));

        });
    }
}
