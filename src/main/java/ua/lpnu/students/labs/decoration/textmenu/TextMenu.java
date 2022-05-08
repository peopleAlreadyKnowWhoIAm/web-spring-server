package ua.lpnu.students.labs.decoration.textmenu;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.val;
import ua.lpnu.students.labs.decoration.manager.Manager;
import ua.lpnu.students.labs.decoration.manager.Manipulator;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;

/**
 * Class to comunicate via text-terminal.
 */
public class TextMenu {
  Manager manager;

  TextParser console = new TextParser();

  // Constructor
  public TextMenu() {
    manager = new Manager();
  }

  public TextMenu(List<Decoration> decorations) {
    manager = new Manager(decorations);
  }

  /**
   * Start main menu loop for comunication.
   */
  public void mainMenu() {
    boolean always = true;
    while (always) {
      console.print(MAIN_MENU_STR);
      switch (console.scanString()) {
        case "a":
          addDecoration();
          break;
        case "l":
          console.print(listDecorationIndexed());
          break;
        case "d":
          deleteDecoration();
          break;
        case "e":
          editDecoration();
          break;
        case "f":
          filterDecorations();
          break;
        case "s":
          saveToFile();
          break;
        case "o":
          openFile();
          break;
        case "x":
          always = false;
          break;
        default:
          console.print(INVALID_STRING);
      }
    }
  }

  // Menu options
  private void openFile() {
    console.print(ENTER_PATH_STR);
    val pathStr = console.scanString();
    try {
      Path path = Paths.get(pathStr);
      manager.importFromFile(path);
    } catch (IOException e) {
      e.printStackTrace();
      console.print("Something really bad just happened");
    }
  }

  private void saveToFile() {
    console.print(ENTER_PATH_STR);
    val pathStr = console.scanString();
    try {
      Path path = Paths.get(pathStr);
      manager.saveToFile(path);
    } catch (IOException e) {
      e.printStackTrace();
      console.print("Something really bad just happened");
    }
  }

  void filterDecorations() {
    List<FieldDescription> filters = manager.getFilters();
    filters.forEach((field) -> {
      Object tmp = (Object) console.set(
          field.getValue(),
          field.getMessage());
      field.setValue(tmp);
    });

    manager.setFilters(filters);

    final List<Decoration> filtered = manager.filter();

    List<String> sorting = Manipulator.AVAILABLE_SORTING;

    StringBuilder out = new StringBuilder();
    int count = 0;
    for (String i : sorting) {
      out.append(String.format("%d. %s %n", count++, i));
    }
    console.print(out.toString());
    int option = console.scanStrictlyInt(sorting.size() - 1);
    boolean descending = (Boolean) console.set(false, DESCENDING_STRING);

    manager.setSorting(sorting.get(option), descending);

    filtered.forEach((decoration) -> {
      console.print(String.format("%s%n", decoration.toString()));
    });
  }

  /**
   * Add decoration to database with text terminal.
   */
  public void addDecoration() {
    console.print(CHOOSE_TYPE_STR);

    Type type = (Type) console.set(Type.ELECTRIC_DECORATION, "");
    List<FieldDescription> fields = manager.getFieldsOf(type);
    fields.forEach((field) -> {
      Object a = (Object) console.set(field.getValue().getClass().cast(field.getValue()),
          field.getMessage());
      field.setValue(a);
    });
    manager.addDecoration(type, fields);
  }

  String listDecorationIndexed() {
    StringBuilder out = new StringBuilder();
    out.append(String.format(LIST_OPTION_STR, manager.getDecorations().size()));
    int count = 0;
    for (Decoration i : manager.getDecorations()) {
      out.append(String.format(LINE_LIST_INDEXED_STR, count, i.toString()));
      count++;
    }
    return out.toString();
  }

  /**
   * Edit an decoration from the database.
   */
  public void editDecoration() {
    console.print(listDecorationIndexed());
    console.print(EDITING_OPTION_STR);
    int position;
    do {
      position = (Integer) console.set(4, CHOOSE_NUMBER_STR);
    } while (position >= manager.getDecorations().size());

    List<FieldDescription> fields = manager.getFieldsOf(position);
    fields.forEach((field) -> {
      Object a = console.edit(field.getValue(), field.getMessage());
      field.setValue(a);
    });
    manager.setDecoration(position, fields);
  }

  /**
   * Delete an decoration from database.
   */
  public void deleteDecoration() {
    console.print(listDecorationIndexed());
    console.print(DELETING_OPTION_STR);
    int position;
    do {
      position = (Integer) console.set(4, CHOOSE_NUMBER_STR);
    } while (position >= manager.getDecorations().size());
    manager.deleteDecoration(position);
  }
  // Menu options

  // String literals
  private static final String DELETING_OPTION_STR = "Delete decoration:";
  private static final String LIST_OPTION_STR = "There are %d decoration(s):%n";
  private static final String LINE_LIST_INDEXED_STR = "%d. %s%n";
  private static final String CHOOSE_NUMBER_STR = "Enter number of the decoration: ";
  private static final String EDITING_OPTION_STR = "Edit decoration\n";
  private static final String CHOOSE_TYPE_STR = 
      "Adding new decoration\nChoose the type of the decoration:\n";
  private static final String MAIN_MENU_STR = """
      Choose option:
      a - add decoration
      l - list decorations
      d - delete decoration
      e - edit decoration
      f - filter decorations
      s - save
      o - open
      x - exit
      >>>""";

  private static final String INVALID_STRING = "Invalid input\nTry again!!\n";
  private static final String DESCENDING_STRING = "Descending?\n";
  private static final String ENTER_PATH_STR = "Enter path to the csv-file\n";
  // String literals
}
