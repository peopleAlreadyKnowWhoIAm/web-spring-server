package ua.lpnu.students.labs.decoration.textmenu;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.data.DataStorage;
import ua.lpnu.students.labs.decoration.model.ElectricDecoration;
import ua.lpnu.students.labs.decoration.model.LongDecoration;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedArrayList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedLinkedList;

public class TextMenuTest {
    private DataStorage data;

    @BeforeEach
    void init() {
        data = new DataStorage();
    }

    @Test
    void testAddDecoration() {
        String input = "a\n2\nNAME\n1\nwood\n123\n12.50\nblue\nred\n\nclossic\n12\n10\n"
                + "a\n3\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\n"
                + "x\n";

        Decoration[] result = {
                new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123,
                        new TypedLinkedList<>(new String(), Arrays.asList("blue", "red")),
                        "clossic", "wood", 12, 10, 12.5f),
                new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
                        new TypedLinkedList<>(new String(), Arrays.asList("red", "ble")), 200, 22,
                        50, 221.5f)
        };
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        var menu = new TextMenu(data.decorations);
        menu.mainMenu();

        for (int i = result.length - 1; i >= 0; i--) {
            Assertions.assertEquals(menu.manager.getDecorations()
                    .get(menu.manager.getDecorations().size() - result.length + i).toString(),
                    result[i].toString());
        }
    }

    @Test
    void testDeleteDecoration() {
        final Decoration[] toDelete = {
                data.decorations.get(0), data.decorations.get(data.decorations.size() - 1)
        };
        String input = "d\n0\nd\n" + String.valueOf(data.decorations.size() - 2) + "\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        var menu = new TextMenu(data.decorations);
        menu.mainMenu();
        Assertions.assertFalse(menu.listDecorationIndexed().contains(toDelete[0].toString()));
        Assertions.assertFalse(menu.listDecorationIndexed().contains(toDelete[1].toString()));
    }

    @Test
    void testEditDecoration() {
        String input = "e\n5\nNAME\n1\nwood\n123\n12.50\nblue\nred\n\nclossic\n12\n10\n"
                + "e\n1\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\n"
                + "x\n";
        Decoration[] absent = {
                data.decorations.get(5).copy(), data.decorations.get(1).copy()
        };
        Decoration[] result = {
                new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123,
                        new TypedArrayList<>(new String(), Arrays.asList("blue", "red")),
                        "clossic", "wood", 12, 10, 12.5f),
                new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
                        new TypedArrayList<>(new String(), Arrays.asList("red", "ble")), 200, 22, 50, 221.5f)
        };

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        var menu = new TextMenu(data.decorations);
        menu.mainMenu();

        for (int i = 0; i < result.length; i++) {

            Assertions.assertTrue(menu.listDecorationIndexed().contains(result[i].toString()));
            Assertions.assertFalse(menu.listDecorationIndexed().contains(absent[i].toString()));
        }
    }

    @Test
    void testFilter() {
        String inputFilter = "f\n3\n\n\n\n\n20\n0\n0\n1\n1\nx\n";
        System.setIn(new ByteArrayInputStream(inputFilter.getBytes()));
        var menu = new TextMenu(data.decorations);
        menu.mainMenu();
        var filtered = menu.manager.filter();
        for (Decoration template : filtered) {
            Assertions.assertTrue(template.getType() == Type.ELECTRIC_DECORATION);

        }
    }

    @Test
    void testList() {
        String[] inputList = {
                "l\nx\n"
        };
        var systemOut = System.out;
        for (int i = 0; i < inputList.length; i++) {
            System.setIn(new ByteArrayInputStream(inputList[i].getBytes()));
            var outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            var menu = new TextMenu(data.decorations);
            menu.mainMenu();
            var out = outStream.toString();
            data.decorations.forEach((a) -> {
                Assertions.assertTrue(out.contains(a.toString()));
            });
        }
        System.setOut(systemOut);
    }

    @Test
    void saveDecorationTest() throws IOException {
        String input = "s\ntest.csv\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        var menu = new TextMenu(data.decorations);
        menu.mainMenu();

        val file = new File("test.csv");
        assertTrue(file.exists());
        assertTrue(Files.mismatch(Path.of("test.csv"), data.testCsv) == -1);

        file.delete();
    }

    @Test
    void openDecorationFile() {
        String input = "o\nsrc/test/resources/csv/testList.csv\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        var menu = new TextMenu();
        menu.mainMenu();

        val listString = menu.listDecorationIndexed();
        data.decorations.stream().forEachOrdered((a) -> assertTrue(listString.contains(a.toString())));
    }
}
