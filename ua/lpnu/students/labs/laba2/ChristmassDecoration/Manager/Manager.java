package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.LongDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.PieceDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;

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

    //Initing list of classes
    public static final Class<?>[] POSSIBLE_CLASSES =  new Class<?>[]{
        ElectricDecoration.class,
        LongDecoration.class,
        OrganicDecoration.class,
        PieceDecoration.class

    
};
    public static final Map<Class<?>, Map<Method, String>> setMap;
    public static final Map<Class<?>, Map<Method, String>> getMap;

    static{
        setMap = new HashMap<>();

        Map<Method, String> templateSets = new HashMap<>();
        var a () -> = PieceDecoration::setAvalaibleAmount;
        templateSets.put(Template::setAvalaibleAmount, null);
        Map<Method, String> pieceDecorationSets = new HashMap<>();
        pieceDecorationSets.put()

    }
}