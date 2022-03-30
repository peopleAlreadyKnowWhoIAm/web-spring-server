package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import ua.lpnu.students.labs.laba2.TextMenu;

public abstract class Template {
    public Template(String name, Type type, String usage, int avalaibleAmount) {
        this.name = name;
        this.type = type;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
    }

    public Template() {
    }

    protected String name;
    protected Type type;
    protected String usage;
    protected int avalaibleAmount;
    protected String material;

    public void editAll() {
        System.out.print(nameStr + "(" + name + ")");
        this.name = TextMenu.scanString();
        printTypeOptions();
        System.out.print(typeStr + "(" + type + ")");
        this.type = Type.values()[TextMenu.scanInt(Type.values().length - 1)];
        System.out.print(usageStr + "(" + usage + ")");
        this.usage = TextMenu.scanString();
        System.out.print(availableStr + "(" + avalaibleAmount + ")");
        this.avalaibleAmount = TextMenu.scanInt( -1);
        System.out.print(materialStr + "(" + material + ")");
        this.material = TextMenu.scanString();
    }

    public void setAll() {
        System.out.print(nameStr);
        this.name = TextMenu.scanString();
        printTypeOptions();
        System.out.print(typeStr);
        this.type = Type.values()[TextMenu.scanInt( Type.values().length - 1)];
        System.out.print(usageStr);
        this.usage = TextMenu.scanString();
        System.out.print(availableStr);
        this.avalaibleAmount = TextMenu.scanInt( -1);
        System.out.print(materialStr);
        this.material = TextMenu.scanString();
    }

    private void printTypeOptions(){
        int count = 0;
        for (Type i : Type.values()) {
            System.out.println(count + ". " + i.toString());
            count++;
        }
    }

    @Override
    public String toString(){
        return String.format(descriptionStr, name, type.toString(), usage, String.valueOf(avalaibleAmount), material);
    }

    private static final String nameStr = "Name: ";
    private static final String typeStr = "Type: ";
    private static final String usageStr = "Usage: ";
    private static final String availableStr = "Available: ";
    private static final String materialStr = "Material: ";
    private static final String descriptionStr =
    nameStr + "%s\t" + typeStr + "%s\t" + usageStr + "%s\t" + availableStr + "%s\t"
     + materialStr + "%s\t"; 

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public int getAvalaibleAmount() {
        return avalaibleAmount;
    }

    public void setAvalaibleAmount(int avalaibleAmount) {
        this.avalaibleAmount = avalaibleAmount;
    }
}
