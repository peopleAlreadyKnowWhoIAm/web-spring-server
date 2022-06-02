package ua.lpnu.students.labs.decoration.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.data.DataStorage;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedArrayList;

public class ManipulatorTest {
    private Manipulator manipulator;

    @BeforeEach
    void init() {
        manipulator = new Manipulator(new DataStorage().decorations);
    }

    @Test
    void testFilter() {
        var filters = manipulator.getFilters();
        var previousList = new ArrayList<>(manipulator.getAllDecorations());

        manipulator.filter();
        Assertions.assertEquals(
                manipulator.getFilteredDecorations().toString(), previousList.toString());

        filters.get(0).setValue(new LinkedList<>());
        filters.get(1).setValue(new LinkedList<>());
        filters.get(2).setValue(new LinkedList<>());
        filters.get(3).setValue(new LinkedList<>());
        filters.get(4).setValue(0);
        filters.get(5).setValue(0.0f);
        filters.get(6).setValue(0.0f);

        manipulator.setFilters(filters);
        manipulator.filter();

        Assertions.assertEquals(manipulator.getFilteredDecorations().toString(),
                manipulator.getAllDecorations().toString());

        var types = new TypedArrayList<>(Type.ELECTRIC_DECORATION,
                Arrays.asList(Type.ELECTRIC_DECORATION, Type.PIECE_DECORATION));
        var names = new TypedArrayList<>(new String());
        var materials = new TypedArrayList<>(new String(), Arrays.asList("plastic", "glass"));
        var usages = new TypedArrayList<>(
                Usage.FOR_CHRISTMASS, Arrays.asList(Usage.FOR_CHRISTMASS, Usage.FOR_WINDOWS));
        var minAmount = 25;
        var minPrice = 10.0f;
        var maxPrice = 1000.0f;

        filters.get(0).setValue(types);
        filters.get(1).setValue(names);
        filters.get(2).setValue(materials);
        filters.get(3).setValue(usages);
        filters.get(4).setValue(minAmount);
        filters.get(5).setValue(minPrice);
        filters.get(6).setValue(maxPrice);

        manipulator.setFilters(filters);
        manipulator.filter();
        var filtered = manipulator.getFilteredDecorations();

        Assertions.assertFalse(filtered.isEmpty());

        Assertions.assertFalse(filtered.size() == manipulator.getAllDecorations().size());
        filtered.forEach((a) -> {
            Assertions.assertTrue(types.contains(a.getType()));
            Assertions.assertTrue(materials.contains(a.getMaterial()));
            Assertions.assertTrue(usages.contains(a.getUsage()));
            Assertions.assertTrue(a.getAvalaibleAmount() >= minAmount);
            Assertions.assertTrue(a.getPrice() >= minPrice && a.getPrice() <= maxPrice);
        });

        var left = manipulator.getAllDecorations();
        left.removeAll(filtered);

        left.forEach((a) -> {
            Assertions.assertFalse(types.contains(a.getType()) && materials.contains(a.getMaterial())
                    && usages.contains(
                            a.getUsage())
                    && a.getAvalaibleAmount() >= minAmount && a.getPrice() >= minPrice
                    && a.getPrice() <= maxPrice);
        });

        manipulator.setAllDecorations(manipulator.getAllDecorations());

        var newNames = new TypedArrayList<>(new String(),
                Arrays.asList("Patriotical ilumiation", "On window net", "Interested house ilumination",
                        "Cheap christmass lights", "Colorful ball", "Glass cone", "Black cube"));
        filters = manipulator.getFilters();

        filters.get(0).setValue(new TypedArrayList<>(Type.ELECTRIC_DECORATION));
        filters.get(1).setValue(newNames);
        filters.get(2).setValue(new TypedArrayList<>(new String()));
        filters.get(3).setValue(new TypedArrayList<>(Usage.UNKNOWN));
        filters.get(4).setValue(0);
        filters.get(5).setValue(0.0f);
        filters.get(6).setValue(0.0f);

        manipulator.setFilters(filters);
        manipulator.filter();
        filtered = manipulator.getFilteredDecorations();
        filtered.forEach((a) -> {
            Assertions.assertTrue(newNames.contains(a.getName()));

        });

        left = manipulator.getAllDecorations();
        left.removeAll(filtered);

        left.forEach((a) -> {
            Assertions.assertFalse(newNames.contains(a.getName()));
        });

    }

    @Test
    void testSort() {
        var sortings = Manipulator.AVAILABLE_SORTING;

        Assertions.assertEquals(sortings.size(), 3);

        String lastName = new String();

        manipulator.sort(sortings.get(0), false);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(lastName.compareToIgnoreCase(decor.getName()) <= 0);
            lastName = decor.getName();
        }

        manipulator.sort(sortings.get(0), true);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(lastName.compareToIgnoreCase(decor.getName()) >= 0);
            lastName = decor.getName();
        }

        var lastPrice = 0.0f;

        manipulator.sort(sortings.get(1), false);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(decor.getPrice() >= lastPrice);
            lastPrice = decor.getPrice();
        }

        manipulator.sort(sortings.get(1), true);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(decor.getPrice() <= lastPrice);
            lastPrice = decor.getPrice();
        }

        var lastAmount = 0;

        manipulator.sort(sortings.get(2), false);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(decor.getAvalaibleAmount() >= lastAmount);
            lastAmount = decor.getAvalaibleAmount();
        }

        manipulator.sort(sortings.get(2), true);

        for (var decor : manipulator.getFilteredDecorations()) {
            Assertions.assertTrue(decor.getAvalaibleAmount() <= lastAmount);
            lastAmount = decor.getAvalaibleAmount();
        }
    }
}
