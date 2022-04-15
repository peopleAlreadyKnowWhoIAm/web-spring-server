package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.LongDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.PieceDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class ManipulatorTest {
	private Manipulator manipulator;

	@BeforeEach
	void init() {
		List<Template> decorations = new ArrayList<>(Arrays.asList(
				new ElectricDecoration("Cheap christmass lights", Usage.FOR_CHRISTMASS, "plastic", 120,
						Arrays.asList("blue", "green", "red"), 150, 10, 25, 120.50f),
				new ElectricDecoration("Interested house ilumination", Usage.OUTSIDE_DECORATION,
						"plastic", 40,
						Arrays.asList("yellow", "green", "red", "blue", "white"), 2500, 15, 150,
						1200.0f),
				new ElectricDecoration("On window net", Usage.FOR_WINDOWS, "plastic", 99,
						Arrays.asList("yellow"), 120,
						30, 20, 120.0f),
				new ElectricDecoration("Patriotical ilumiation", Usage.UNIVERSAL, "plastic", 200,
						Arrays.asList("yellow", "blue"), 300, 20, 5, 80.0f),

				new LongDecoration("Colorful rain", Usage.FOR_CHRISTMASS, 400,
						Arrays.asList("green", "silver", "gold", "blue"),
						"colorful narrow strips", "plastic", 50, 3,
						20.0f),
				new LongDecoration("Beautiful dawn", Usage.FOR_CHRISTMASS, 350,
						Arrays.asList("yellow", "orange", "white"), "long wide rope", "plactic",
						100, 8, 35.0f),
				new LongDecoration("Strange world", Usage.UNIVERSAL, 500,
						Arrays.asList("blue", "green", "red"),
						"long very wide strip with ornament", "paper", 200, 40, 50.0f),
				new LongDecoration("Mystery night", Usage.OUTSIDE_DECORATION, 120,
						Arrays.asList("gray", "black", "white"),
						"wide strip decorated with simple colors", "plastic",
						120, 12, 34.6f),

				new OrganicDecoration("Christmass", Usage.UNIVERSAL, 73, "dark green",
						"classic christmass", "orginic",
						new Size(100, 200, 100), 507.0f, 60,
						(new GregorianCalendar(2021, 12, 30)).getTime()),
				new OrganicDecoration("Christmass branches", Usage.UNIVERSAL, 38, "dark green",
						"classic christmass",
						"orginic", new Size(10, 100, 50), 50.0f, 60,
						(new GregorianCalendar(2021, 12, 28)).getTime()),

				new PieceDecoration("Colorful ball", Usage.FOR_CHRISTMASS, 340, "green and blue",
						"classic", "glass",
						new Size(10, 10, 10), 65.0f),
				new PieceDecoration("Glass cone", Usage.FOR_CHRISTMASS, 180, "gold-yellow", "classic",
						"plastic",
						new Size(8, 15, 8), 25.0f),
				new PieceDecoration("Black cube", Usage.FOR_CHRISTMASS, 20, "black", "modern", "glass",
						new Size(11, 18, 9), 659.0f),
				new PieceDecoration("Detailed landscape", Usage.FOR_CHRISTMASS, 90, "differnt",
						"hi-tech",
						"glass and plactic", new Size(20, 18, 12), 129.0f)));

		manipulator = new Manipulator(decorations);
	}

	@Test
	void testFilter() {
		var filters = manipulator.getFilters();
		var previousList = new ArrayList<>(manipulator.getAllDecorations());

		manipulator.filter();
		Assertions.assertEquals(manipulator.getFilteredDecorations(), previousList);

		filters.get(0).setValue(new LinkedList<>());
		filters.get(1).setValue(new LinkedList<>());
		filters.get(2).setValue(new LinkedList<>());
		filters.get(3).setValue(new LinkedList<>());
		filters.get(4).setValue(0);
		filters.get(5).setValue(0.0f);
		filters.get(6).setValue(0.0f);

		manipulator.setFilters(filters);
		manipulator.filter();

		Assertions.assertEquals(manipulator.getFilteredDecorations(), manipulator.getAllDecorations());

		var types = Arrays.asList(Type.ELECTRIC_DECORATION, Type.PIECE_DECORATION);
		var names = new ArrayList<>();
		var materials = Arrays.asList("plastic", "glass");
		var usages = Arrays.asList(Usage.FOR_CHRISTMASS, Usage.FOR_WINDOWS);
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
					&& usages.contains(a.getUsage()) && a.getAvalaibleAmount() >= minAmount && a.getPrice() >= minPrice
					&& a.getPrice() <= maxPrice);
		});

		manipulator.setAllDecorations(manipulator.getAllDecorations());

		var newNames = Arrays.asList("Patriotical ilumiation", "On window net", "Interested house ilumination",
				"Cheap christmass lights", "Colorful ball", "Glass cone", "Black cube");
		filters = manipulator.getFilters();

		filters.get(0).setValue(new LinkedList<>());
		filters.get(1).setValue(newNames);
		filters.get(2).setValue(new LinkedList<>());
		filters.get(3).setValue(new LinkedList<>());
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
		var sortings = Manipulator.getAVAILABLE_SORTING();

		Assertions.assertEquals(sortings.length, 3);

		String lastName = new String();

		manipulator.sort(sortings[0], false);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(lastName.compareToIgnoreCase( decor.getName()) <= 0);
			lastName = decor.getName();
		}

		manipulator.sort(sortings[0], true);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(lastName.compareToIgnoreCase( decor.getName()) >= 0);
			lastName = decor.getName();
		}

		var lastPrice = 0.0f;

		manipulator.sort(sortings[1], false);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(decor.getPrice() >= lastPrice);
			lastPrice = decor.getPrice();
		}

		manipulator.sort(sortings[1], true);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(decor.getPrice() <= lastPrice);
			lastPrice = decor.getPrice();
		}

		var lastAmount = 0;

		manipulator.sort(sortings[2], false);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(decor.getAvalaibleAmount() >= lastAmount);
			lastAmount = decor.getAvalaibleAmount();
		}

		manipulator.sort(sortings[2], true);

		for (var decor : manipulator.getFilteredDecorations()) {
			Assertions.assertTrue(decor.getAvalaibleAmount() <= lastAmount);
			lastAmount = decor.getAvalaibleAmount();
		}
	}
}
