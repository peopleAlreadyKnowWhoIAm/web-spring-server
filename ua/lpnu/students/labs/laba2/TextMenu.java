package ua.lpnu.students.labs.laba2;


import ua.lpnu.students.labs.laba2.ChristmassDecoration.*;

public class TextMenu {
    private Manager manager;

    TextMenu(Manager manager) {
        this.manager = manager;
    }

    public void mainMenu() {
        boolean always = true;
        while (always) {
            System.out.print(mainMenuStr);
            String scanned = System.console().readLine();
            switch (scanned) {
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
                case "x":
                    always = false;
                    break;
                default:
                    System.out.print(invalidNumberStr);
            }
        }
    }

    public void addDecoration() {
        System.out.print(chooseTypeOfTheDecoration);
        listDecorationVariants();
        int variant = scanInt(possibleClasses.length);
        try {
            Template tmp = (Template) possibleClasses[variant].getDeclaredConstructor().newInstance();
            tmp.setAll();
            manager.getDecorations().add(tmp);
        } catch (Exception e) {

            e.printStackTrace();
            System.exit(11);
        }
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
        for (Class<?> class1 : possibleClasses) {
            System.out.println(count + lineNumberDividerStr + class1.getSimpleName());
            count++;
        }
    }

    public static int scanInt(int max) {

        while (true) {
            try{
                int out = Integer.parseInt(System.console().readLine());
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

    // output text section
    private static final String listStr = "There are %d decoration(s):\n";
    private static final String lineNumberDividerStr = ". ";
    private static final String chooseNumberStr = "Enter number of the decoration: ";
    private static final String invalidNumberStr = "Incorrect number\n try again\n";
    private static final String editingOptionStr = "Edit decoration";
    private static final String chooseTypeOfTheDecoration = "Adding new decoration\nChoose the type of the decoration:\n";
    private static final String mainMenuStr = """
            Choose option:
            a - add decoration
            l - list decoration
            d - delete decoration
            e - edit decoration
            x - exit
            >>>""";

    static {

        possibleClasses = new Class<?>[] {
                ElectricDecoration.class,
                LongDecoration.class,
                OrganicDecoration.class,
                PieceDecoration.class
        };

    }

    private static final Class<?>[] possibleClasses;

}
