package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.Manager;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;

public class TextMenu {
    private Manager manager;

    // Constructor
    public TextMenu() {
        manager = new Manager();
    }

    public TextMenu(List<Template> decorations) {
        manager = new Manager(decorations);
    }

    // Functions
    // Provide menu
    public void mainMenu() {
        boolean always = true;
        while (always) {
            ConsoleComunicator.print(MAIN_MENU_STR);
            switch (ConsoleComunicator.scanString()) {
                case "a":
                    addDecoration();
                    break;
                case "l":
                    ConsoleComunicator.print(listDecorationIndexed());
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
                case "x":
                    always = false;
                    break;
                default:
                    ConsoleComunicator.print(INVALID_STRING);
            }
        }
    }

    // Menu options
    private void filterDecorations() {
        List<FieldDescription> filters = manager.getFilters();
        filters.forEach((field) -> {
            Object tmp = (Object) ConsoleComunicator.set(
                field.getValue(),
                field.getMessage()
            );
            field.setValue(tmp);
        });

        manager.setFilters(filters);

        List<Template> filtered = manager.filter();

        String[] sorting = manager.getSortings();

        String out = new String();
        int count = 0;
        for (String i : sorting) {
            out += String.format("%d. %s \n", count++, i);
        }
        ConsoleComunicator.print(out);
        int option = ConsoleComunicator.scanStrictlyInt(sorting.length - 1);
        boolean descending = ConsoleComunicator.setBool(DESCENDING_STRING);

        manager.setSorting(sorting[option], descending);

        filtered.forEach((decoration) -> {
            ConsoleComunicator.print(String.format("%s\n", decoration.toString()));
        });

    }

    public void addDecoration() {
        ConsoleComunicator.print(CHOOSE_TYPE_STR);

        Type type = (Type) ConsoleComunicator.set(Type.ELECTRIC_DECORATION, "");
        List<FieldDescription> fields = manager.getFieldsOf(type);
        fields.forEach((field) -> {
            Object a = (Object) ConsoleComunicator.set(field.getValue().getClass().cast(field.getValue()),
                    field.getMessage());
            field.setValue(a);
        });
        manager.addDecoration(type, fields);
    }

    private String listDecorationIndexed() {
        String out = String.format(LIST_OPTION_STR, manager.getDecorations().size());
        out.formatted(manager.getDecorations().size());
        int count = 0;
        for (Template i : manager.getDecorations()) {
            out += count + LINE_NUMBER_DIVADER_STR + i.toString() + "\n";
            count++;
        }
        return out;
    }

    public void editDecoration() {
        ConsoleComunicator.print(listDecorationIndexed());
        ConsoleComunicator.print(EDITING_OPTION_STR);
        int position;
        do {
            position = ConsoleComunicator.setInt(CHOOSE_NUMBER_STR);
        } while (position >= manager.getDecorations().size());

        List<FieldDescription> fields = manager.getFieldsOf(position);
        fields.forEach((field) -> {
            Object a = ConsoleComunicator.edit(field.getValue(), field.getMessage());
            field.setValue(a);
        });
        manager.setDecoration(position, fields);
    }

    public void deleteDecoration() {
        ConsoleComunicator.print(listDecorationIndexed());
        ConsoleComunicator.print(DELETING_OPTION_STR);
        int position;
        do {
            position = ConsoleComunicator.setInt(CHOOSE_NUMBER_STR);
        } while (position >= manager.getDecorations().size());
        manager.deleteDecoration(position);
    }
    // Menu options

    // String literals
    private static final String DELETING_OPTION_STR = "Delete decoration:";
    private static final String LIST_OPTION_STR = "There are %d decoration(s):\n";
    private static final String LINE_NUMBER_DIVADER_STR = ". ";
    private static final String CHOOSE_NUMBER_STR = "Enter number of the decoration: ";
    private static final String EDITING_OPTION_STR = "Edit decoration\n";
    private static final String CHOOSE_TYPE_STR = "Adding new decoration\nChoose the type of the decoration:\n";
    private static final String MAIN_MENU_STR = """
            Choose option:
            a - add decoration
            l - list decorations
            d - delete decoration
            e - edit decoration
            f - filter decorations
            x - exit
            >>>""";

    private static final String INVALID_STRING = "Invalid input\nTry again!!\n";
    private static final String DESCENDING_STRING = "Descending?\n";
    // String literals
}
