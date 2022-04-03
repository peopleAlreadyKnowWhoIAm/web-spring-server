package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;

public class Manipulator {
    List<Template> filteredDecorations;

    
    public void filterByType(Class<?> type){
        
    }

    public Manipulator(List<Template> allDecorations) {
        this.filteredDecorations = allDecorations;
    }

    public List<Template> getFilteredDecorations() {
        return filteredDecorations;
    }

}
