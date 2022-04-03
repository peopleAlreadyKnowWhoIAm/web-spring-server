package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;


import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public abstract class Template{
    protected Template(String name, String type, Usage usage, int avalaibleAmount) {
        this.name = name;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.type = type;
    }

    protected Template(String type) {
        this.type = type;
    }

    protected String name;

    protected String type;
    
    protected Usage usage;
    protected int avalaibleAmount;
    protected String material;

    public void editAll() {
        this.name = TextMenu.editString(this.name, nameStr);
        
        this.usage = Usage.values()[TextMenu.editInt(
            this.usage.ordinal(),
            Usage.allToString() + usageStr, 
            Usage.values().length - 1
            )];
        
        this.avalaibleAmount = TextMenu.editInt(this.avalaibleAmount, availableStr);

        this.material = TextMenu.editString(this.material, materialStr);
    }

    public void setAll() {
        this.name = TextMenu.setString(nameStr);

        this.usage = Usage.values()[TextMenu.setInt(
            Usage.allToString() + usageStr, 
            Usage.values().length - 1
            )];

        this.avalaibleAmount = TextMenu.setInt(availableStr);

        this.material = TextMenu.setString(materialStr);
    }

    @Override
    public String toString(){
        return String.format(descriptionStr, this.name, this.type, this.usage.toString(), String.valueOf(this.avalaibleAmount), this.material);
    }

    private static final String nameStr = "Name: ";
    private static final String usageStr = "Usage: ";
    private static final String typeStr = "Type: ";
    private static final String availableStr = "Available: ";
    private static final String materialStr = "Material: ";
    private static final String descriptionStr =
    nameStr + "%s\t" + typeStr + "%s\t"+ usageStr + "%s\t" + availableStr + "%s\t"
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

    public String getType(){
        return this.type;
    }

    public Usage getUsage() {
        return this.usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public int getAvalaibleAmount() {
        return avalaibleAmount;
    }

    public void setAvalaibleAmount(int avalaibleAmount) {
        this.avalaibleAmount = avalaibleAmount;
    }
}
