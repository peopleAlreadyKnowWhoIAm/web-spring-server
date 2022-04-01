package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public abstract class Template{
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
        this.name = TextMenu.editString(this.name, nameStr);
        
        this.type = Type.values()[TextMenu.editInt(
            this.type.ordinal(),
            Type.allToString()+typeStr, 
            Type.values().length - 1
            )];

        this.usage = TextMenu.editString(this.usage, usageStr);
        
        this.avalaibleAmount = TextMenu.editInt(this.avalaibleAmount, availableStr);

        this.material = TextMenu.editString(this.material, materialStr);
    }

    public void setAll() {
        this.name = TextMenu.setString(nameStr);

        this.type = Type.values()[TextMenu.setInt(
            Type.allToString()+typeStr, 
            Type.values().length - 1
            )];

        this.usage = TextMenu.setString(usageStr);

        this.avalaibleAmount = TextMenu.setInt(availableStr);

        this.material = TextMenu.setString(materialStr);
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
    protected static final String showLastStr = "(%s) "; 

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
