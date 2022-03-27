package ua.lpnu.students.labs.laba2.ChristmassDecoration;

abstract class Template {
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
