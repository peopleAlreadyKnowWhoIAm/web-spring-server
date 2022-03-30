package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import java.util.List;
import java.util.ArrayList;

public class Manager {
    public Manager(List<Template> decorations) {
        this.decorations = decorations;
    }

    public Manager() {
        decorations = new ArrayList<Template>();
    }

    protected List<Template> decorations;
    public List<Template> getDecorations() {
        return decorations;
    }

    public void addDecoration(Template decorations) {
        this.decorations.add(decorations);
    }

    
}