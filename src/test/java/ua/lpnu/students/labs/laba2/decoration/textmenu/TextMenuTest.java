package ua.lpnu.students.labs.laba2.decoration.textmenu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.laba2.decoration.data.DataStorage;
import ua.lpnu.students.labs.laba2.decoration.model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.LongDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Template;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Type;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Usage;
import ua.lpnu.students.labs.laba2.decoration.storages.impl.TypedArrayList;
import ua.lpnu.students.labs.laba2.decoration.storages.impl.TypedLinkedList;

public class TextMenuTest {
  private List<Template> decorations;

  @BeforeEach
  void init() {
    decorations = new DataStorage().decorations;
  }

  @Test
  void testAddDecoration() {
    String[] input = {
        "2\nNAME\n1\nwood\n123\n12.50\nblue\nred\n\nclossic\n12\n10\n",
        "3\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\n"
    };
    Template[] result = {
        new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123,
            new TypedLinkedList<>(new String(), Arrays.asList("blue", "red")),
            "clossic", "wood", 12, 10, 12.5f),
        new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
            new TypedLinkedList<>(new String(), Arrays.asList("red", "ble")), 200, 22, 50, 221.5f)
    };
    for (int i = 0; i < input.length; i++) {
      System.setIn(new ByteArrayInputStream(input[i].getBytes()));
      var menu = new TextMenu(decorations);
      menu.addDecoration();
      Assertions.assertEquals(menu.manager.getDecorations()
          .get(menu.manager.getDecorations().size() - 1).toString(),
          result[i].toString());
    }
  }

  @Test
  void testDeleteDecoration() {
    final Template[] toDelete = {
        decorations.get(0), decorations.get(decorations.size() - 1)
    };
    String input = "0\n" + String.valueOf(decorations.size() - 2) + "\n";
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
        new LongDecoration("NAME", Usage.OUTSIDE_DECORATION, 123,
            new TypedArrayList<>(new String(), Arrays.asList("blue", "red")),
            "clossic", "wood", 12, 10, 12.5f),
        new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
            new TypedArrayList<>(new String(), Arrays.asList("red", "ble")), 200, 22, 50, 221.5f)
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
    String[] inputAdd = {
        "s\na\n3\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\nx\n"
    };
    Template[] resultAdd = {
        new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
            new TypedArrayList<>(new String(), Arrays.asList("red", "ble")), 200, 22, 50, 221.5f)
    };
    for (int i = 0; i < inputAdd.length; i++) {
      System.setIn(new ByteArrayInputStream(inputAdd[i].getBytes()));
      var menu = new TextMenu(decorations);
      menu.mainMenu();
      Assertions.assertEquals(menu.manager.getDecorations()
          .get(menu.manager.getDecorations().size() - 1).toString(),
          resultAdd[i].toString());
    }

    String[] inputEdit = {
        "e\n1\nNNAME\n2\nplastic\n321\n221.5\nred\nble\n\n200\n22\n50\nx\n"
    };
    Template[] absentEdit = {
        decorations.get(1).copy()
    };
    Template[] resultEdit = {
        new ElectricDecoration("NNAME", Usage.FOR_CHRISTMASS, "plastic", 321,
            new TypedArrayList<>(new String(), Arrays.asList("red", "ble")), 200, 22, 50, 221.5f)
    };

    for (int i = 0; i < inputEdit.length; i++) {
      System.setIn(new ByteArrayInputStream(inputEdit[i].getBytes()));
      var menu = new TextMenu(decorations);
      menu.mainMenu();
      Assertions.assertTrue(menu.listDecorationIndexed().contains(resultEdit[i].toString()));
      Assertions.assertFalse(menu.listDecorationIndexed().contains(absentEdit[i].toString()));
    }

    String[] inputList = {
        "l\nx\n"
    };
    var systemOut = System.out;
    for (int i = 0; i < inputList.length; i++) {
      System.setIn(new ByteArrayInputStream(inputList[i].getBytes()));
      var outStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outStream));
      var menu = new TextMenu(decorations);
      menu.mainMenu();
      var out = outStream.toString();
      decorations.forEach((a) -> {
        Assertions.assertTrue(out.contains(a.toString()));
      });
    }
    System.setOut(systemOut);

    String[] inputDelete = {
        "d\n3\nx\n"
    };
    Template[] resultDelete = {
        decorations.get(3)
    };
    for (int i = 0; i < inputDelete.length; i++) {
      System.setIn(new ByteArrayInputStream(inputDelete[i].getBytes()));
      var menu = new TextMenu(decorations);
      menu.mainMenu();
      Assertions.assertFalse(menu.listDecorationIndexed().contains(resultDelete[i].toString()));
      ;
    }

    String inputFilter = "f\n3\n\n\n\n\n20\n0\n0\n1\n1\nx\n";
    System.setIn(new ByteArrayInputStream(inputFilter.getBytes()));
    var menu = new TextMenu(decorations);
    menu.mainMenu();
    var filtered = menu.manager.filter();
    for (Template template : filtered) {
      Assertions.assertTrue(template.getType() == Type.ELECTRIC_DECORATION);

    }
    ;
  }
}
