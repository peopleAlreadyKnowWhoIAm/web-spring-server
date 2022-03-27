package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import java.util.List;

public class Manager {
    public Manager(List<Template> decorations) {
        this.decorations = decorations;
    }

    public Manager() {
    }

    protected List<Template> decorations;

    public List<Template> getDecorations() {
        return decorations;
    }

    public void addDecoration(Template decorations) {
        this.decorations.add(decorations);
    }

    
}