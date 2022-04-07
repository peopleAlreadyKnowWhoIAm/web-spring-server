package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;

import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.Manager;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;

public class TextMenu {
    private Manager manager;

//Constructor
    public TextMenu() {
        manager = new Manager();
    }

    public TextMenu(List<Template> decorations){
        manager = new Manager(decorations);
    }

//Functions
    //Provide menu
    public void mainMenu() {
        boolean always = true;
        while (always) {
            ConsoleTextFormatter.print(mainMenuStr);
            switch (ConsoleTextFormatter.scanString()) {
                case "a":
                    addDecoration();
                    break;
                case "l":
                    ConsoleTextFormatter.print(listDecorationIndexed());
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
                    ConsoleTextFormatter.print("Invalid input\nTry again!!\n");
            }
        }
    }


    private void filterDecorations() {
    }

    //Menu options
    public void addDecoration() {
        ConsoleTextFormatter.print(chooseTypeOfTheDecoration);

        Type type = (Type) ConsoleTextFormatter.set(Type.ELECTRIC_DECORATION, "");
        List<FieldDescription> fields = manager.getFieldsOf(type);
        fields.forEach((field)->{
            Object a = (Object) ConsoleTextFormatter.set(field.getValue().getClass().cast(field.getValue()), field.getMessage());
            field.setValue(a);
        });
        manager.addDecoration(type, fields);
    }

    private String listDecorationIndexed() {
        String out = listStr;
        out.formatted(manager.getDecorations().size());
        int count = 0;
        for (Template i : manager.getDecorations()) {
            out += count + lineNumberDividerStr + i.toString() + "\n";
            count++;
        }
        return out;
    }

    public void editDecoration() {
        ConsoleTextFormatter.print(listDecorationIndexed());
        ConsoleTextFormatter.print(editingOptionStr);
        int position;
        do{
            position = (Integer)ConsoleTextFormatter.set(0, chooseNumberStr);
        }while(position <= manager.getDecorations().size());
        List<FieldDescription> fields = manager.getFieldsOf(position);
        fields.forEach((field)->{
            Object a = ConsoleTextFormatter.edit(field.getValue(), field.getMessage());
            field.setValue(a);
        });
        manager.setDecoration(position, fields);
    }

    public void deleteDecoration() {
        ConsoleTextFormatter.print(listDecorationIndexed());
        ConsoleTextFormatter.print(deletingOptionStr);
        int position;
        do{
            position = (Integer)ConsoleTextFormatter.set(0, chooseNumberStr);
        }while(position >= manager.getDecorations().size());
        manager.deleteDecoration(position);
    }
    //Menu options


//String literals
    private static final String deletingOptionStr = "Delete decoration:";
    private static final String listStr = "There are %d decoration(s):\n";
    private static final String lineNumberDividerStr = ". ";
    private static final String chooseNumberStr = "Enter number of the decoration: ";
    private static final String editingOptionStr = "Edit decoration";
    private static final String chooseTypeOfTheDecoration = "Adding new decoration\nChoose the type of the decoration:\n";
    private static final String mainMenuStr = """
            Choose option:
            a - add decoration
            l - list decorations
            d - delete decoration
            e - edit decoration
            f - filter decorations
            x - exit
            >>>""";
//String literals
}
