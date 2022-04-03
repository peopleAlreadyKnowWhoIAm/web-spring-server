package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;


import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public abstract class Template{
//Variables
    protected String name;
    protected String material;

    protected Type type;

    protected Usage usage;
    
    protected int avalaibleAmount;



//Constructors
    protected Template(String name, Type type, Usage usage, String material, int avalaibleAmount) {
        this.name = name;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.type = type;
        this.material = material;
    }

    protected Template(Type type) {
        this.type = type;
    }



//Text menu functions
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



//Overriding
    @Override
    public String toString(){
        return String.format(descriptionStr, this.name, this.type, this.usage.toString(), String.valueOf(this.avalaibleAmount), this.material);
    }



//String literals
    private static final String nameStr = "Name: ";
    private static final String usageStr = "Usage: ";
    private static final String typeStr = "Type: ";
    private static final String availableStr = "Available: ";
    private static final String materialStr = "Material: ";

    private static final String descriptionStr =
    nameStr + "%s\t" + typeStr + "%s\t"+ usageStr + "%s\t" + availableStr + "%s\t"
     + materialStr + "%s\t";



//Getters/Setters
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

    public Type getType(){
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
