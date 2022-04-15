package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
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

public class ManagerTest {
	private List<Template> decorations;

	@BeforeEach
	void init() {
		decorations = new ArrayList<>(Arrays.asList(
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
	}

	@Test
	void testAddGetDeleteDecoration() {
		Manager manager = new Manager(decorations);

		var tmp = new OrganicDecoration("TEST", Usage.UNKNOWN, 103, "COLOR", "STYLE", "MATERIAL",
				new Size(101, 102, 103), 19.0f, 22, (new GregorianCalendar(2000, 1, 1)).getTime());
		manager.addDecoration(tmp);
		var tmp1 = new PieceDecoration();
		manager.addDecoration(tmp1);

		Assertions.assertTrue(manager.getDecorations().contains(tmp));
		Assertions.assertTrue(manager.getDecorations().contains(tmp1));

		manager.deleteDecoration(manager.getDecorations().size() - 1);
		manager.deleteDecoration(tmp);

		Assertions.assertFalse(manager.getDecorations().contains(tmp));
		Assertions.assertFalse(manager.getDecorations().contains(tmp1));

		Assertions.assertEquals(manager.getDecorations(), decorations);
	}

	@Test
	void testSetDecoration() {
		Manager manager = new Manager(decorations);

		var types = manager.getPossibleTypes();

		Assertions.assertEquals(types, Arrays.asList(Type.values()));

		var fields = manager.getFieldsOf(types.get(0));
		manager.addDecoration(Type.PIECE_DECORATION, fields);

		fields = manager.getFieldsOf(manager.getDecorations().size() - 2);

		fields.get(0).setValue("NAME");
		fields.get(1).setValue(Usage.UNKNOWN);
		fields.get(2).setValue("MATERIAL");
		fields.get(3).setValue(103);
		fields.get(4).setValue(104.0f);

		fields.get(5).setValue("COLOR");
		fields.get(6).setValue("STYLE");
		fields.get(7).setValue(new Size(107, 107, 107));

		manager.setDecoration(manager.getDecorations().size() - 2, fields);

		manager.addDecoration(Type.PIECE_DECORATION, fields);

		Assertions.assertEquals(manager.getDecorations().get(manager.getDecorations().size() - 1).toString(),
				manager.getDecorations().get(manager.getDecorations().size() - 3).toString());
	}
}
