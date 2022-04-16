package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.io.ByteArrayInputStream;
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
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class TextMenuTest {
    private List<Template> decorations;

    @BeforeEach
    void init() {
        decorations = new ArrayList<>(Arrays.asList(
                new ElectricDecoration("Cheap christmass lights", Usage.FOR_CHRISTMASS, "plastic", 120,
                        Arrays.asList("blue", "green", "red"), 150, 10, 25, 120.50f),
                new ElectricDecoration("Interested house ilumination", Usage.OUTSIDE_DECORATION,
                        "plastic", 40,
                        new ArrayList<>(Arrays.asList("yellow", "green", "red", "blue", "white")), 2500, 15, 150,
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
                        new ArrayList<>(Arrays.asList("yellow", "orange", "white")), "long wide rope", "plactic",
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
    void testAddDecoration() {
        String[] input = {
            "2\nNAME\n1\nwood\n123\n12.50\nblue\nred\n\nclossic\n12\n10\n",
            "3\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\n"
        };
        Template[] result = {
            new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123, Arrays.asList("blue", "red"), "clossic", "wood", 12, 10, 12.5f),
            new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321, Arrays.asList("red","ble"), 200, 22, 50, 221.5f)    
        };
        for (int i = 0; i < input.length; i++) {
            System.setIn(new ByteArrayInputStream(input[i].getBytes()));
            var menu = new TextMenu(decorations);
            menu.addDecoration();
            Assertions.assertEquals(menu.manager.getDecorations().get(menu.manager.getDecorations().size()-1).toString(), result[i].toString());
        }
    }

    @Test
    void testDeleteDecoration() {
		Template[] toDelete = {
			decorations.get(0), decorations.get(decorations.size()-1)
		};
		String input = "0\n"+ String.valueOf(decorations.size()-2) +"\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		var menu = new TextMenu(decorations);
		menu.deleteDecoration();
		menu.deleteDecoration();
		Assertions.assertFalse(menu.listDecorationIndexed().contains(toDelete[0].toString()));
		Assertions.assertFalse(menu.listDecorationIndexed().contains(toDelete[1].toString()));
    }

    @Test
    void testEditDecoration() {
		String[] input = {
            "5\nNAME\n1\nwood\n123\n12.50\nblue\nred\n\nclossic\n12\n10\n",
            "1\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\n"
        };
		Template[] absent = {
			decorations.get(5).copy(), decorations.get(1).copy()
		};
        Template[] result = {
            new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123, Arrays.asList("blue", "red"), "clossic", "wood", 12, 10, 12.5f),
            new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321, Arrays.asList("red","ble"), 200, 22, 50, 221.5f)    
        };

		for (int i = 0; i < input.length; i++) {
			System.setIn(new ByteArrayInputStream(input[i].getBytes()));
			var menu = new TextMenu(decorations);
			menu.editDecoration();
			Assertions.assertTrue(menu.listDecorationIndexed().contains(result[i].toString()));
			Assertions.assertFalse(menu.listDecorationIndexed().contains(absent[i].toString()));
		}
    }

    @Test
    void testMainMenu() {
		//TODO end this add filter
        String[] input = {
            "\n"
        };
    }
}
