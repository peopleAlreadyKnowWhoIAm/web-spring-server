package ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.Manager;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.LongDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.PieceDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;

public class TextMenu {
//variables
    private Manager manager;

//Constructor
    public TextMenu(Manager manager) {
        this.manager = manager;
    }

//Functions
    //Provide menu
    public void mainMenu() {
        boolean always = true;
        while (always) {
            System.out.print(mainMenuStr);
            switch (scanString()) {
                case "a":
                    addDecoration();
                    break;
                case "l":
                    listDecoration();
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
                    System.out.print(invalidNumberStr);
            }
        }
    }

    //Menu options
    public void addDecoration() {
        System.out.print(chooseTypeOfTheDecoration);
        listDecorationVariants();
        int variant = setInt(chooseNumberStr + Type.allToString(), Type.values().length);
        //TODO end this
    }

    public void listDecoration() {
        System.out.print(String.format(listStr, manager.getDecorations().size()));
        int count = 0;
        for (Template i : manager.getDecorations()) {
            System.out.println(count + lineNumberDividerStr + i.toString());
            count++;
        }
        System.out.println();
    }

    public void editDecoration() {
        listDecoration();
        System.out.println(editingOptionStr);
        System.out.print(chooseNumberStr);
        int position = scanInt(manager.getDecorations().size());
        manager.getDecorations().get(position).editAll();
    }

    public void deleteDecoration() {
        listDecoration();
        System.out.print(editingOptionStr);
        System.out.print(chooseNumberStr);
        int position = scanInt(manager.getDecorations().size());
        manager.getDecorations().remove(position);
    }

    private void listDecorationVariants() {
        int count = 0;
        for (Type type : Type.values()) {
            System.out.println(count + lineNumberDividerStr + type.toString());
            count++;
        }
    }
    //Menu options


//String literals
    private static final String listStr = "There are %d decoration(s):\n";
    private static final String lineNumberDividerStr = ". ";
    private static final String chooseNumberStr = "Enter number of the decoration: ";
    private static final String invalidNumberStr = "Incorrect number\n try again\n";
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
        private static final String invalidDateStr = "Invalid entered date\nTry again!\n";
    private static final String lastValueStr = "(%s) ";
    public static final String datePatternStr = "dd/mm/yyyy";

    public static final DateFormat dateFormat = new SimpleDateFormat(datePatternStr);
//String literals

//Scanner functions (for input)
public static int scanInt(int max) {
    while (true) {
        try{
            String tmp = System.console().readLine();
            if(tmp.isEmpty()){
                return -1;
            }
            int out = Integer.parseInt(tmp);
        if (out <= max || max == -1)
            return out;
        }catch(NumberFormatException e){
            System.out.print(invalidNumberStr);
        }
    }
}

public static String scanString() {
    return System.console().readLine();
}



//Section of asking for input
    public static int editInt(final int previous, final String message, final int max){
        System.out.printf(message + lastValueStr, String.valueOf(previous));
        int tmp = scanInt(-1);
        if(tmp == -1){
            return (int)(previous*100.0f);
        }
        return tmp;
    }

    public static int editInt(final float previous, final String message){
        System.out.printf(message + lastValueStr, String.valueOf(previous));
        int tmp = scanInt(-1);
        if(tmp == -1){
            return (int)(previous*100.0f);
        }
        return tmp;
    }

    public static int editInt(final int previous, final String message){
        return editInt(previous, message, -1);
    }

    public static int setInt(final String message, final int max){
        System.out.print(message);
        return scanInt(max);
    }

    public static int setInt(final String message){
        return setInt(message, -1);
    }

    public static String editString(final String previous, final String message){
        System.out.printf(message + lastValueStr, previous);
        String tmp = scanString();
        if(tmp.isEmpty()){
            return previous;
        }
        return tmp;
    }

    public static String setString(final String message){
        System.out.print(message);
        return scanString();
    }

    public static List<String> editStringList(final List<String> previous, final String message){
        System.out.printf(message, previous.toString());
        List<String> out = new LinkedList<>();
        String tmp = new String();
        do{
            tmp = TextMenu.scanString();
            if(!tmp.isEmpty()){
                out.add(tmp);
            }
        }while(!tmp.isEmpty());
        if(out.isEmpty()){
            return previous;
        }
        return out;
    }

    public static List<String> setStringList(final String message){
        System.out.print(message);
        List<String> out = new LinkedList<>();
        String tmp = new String();
        do{
            tmp = TextMenu.scanString();
            if(!tmp.isEmpty()){
                out.add(tmp);
            }
        }while(!tmp.isEmpty());
        return out;
    }

    public static Date editDate(final Date previous, final String message){
        System.out.printf(message + lastValueStr, dateFormat.format(previous));
        Date out;
        while(true){
            String buf = TextMenu.scanString();
            if(buf.isEmpty()){
                return previous;
            }
            try {
                out = dateFormat.parse(buf);
                break;
            } catch (Exception e) {
                System.out.println(invalidDateStr);
                continue;
            }
        }
        return out;
    }

    public static Date setDate(final String message){
        System.out.print(message);
        Date out;
        while(true){
            String buf = TextMenu.scanString();
            try {
                out = dateFormat.parse(buf);
                break;
            } catch (Exception e) {
                System.out.println(invalidDateStr);
                continue;
            }
        }
        return out;
    }
}
