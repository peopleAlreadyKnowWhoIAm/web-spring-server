package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.LongDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.PieceDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;

public class Manager {

    public Manager(List<Template> decorations) {
        this.decorations = new ArrayList<>();
        this.decorations.addAll(decorations);
    }

    public Manager() {
        decorations = new ArrayList<Template>();
    }

    // All decorations
    protected List<Template> decorations;

    protected Manipulator manipulator = new Manipulator();
    // Initing Map of classes
    static final Map<Type, Class<?>> POSSIBLE_CLASSES = new HashMap<>(){{
			put(Type.ELECTRIC_DECORATION, ElectricDecoration.class);
			put(Type.LONG_DECORATION, LongDecoration.class);
			put(Type.ORGANIC_DECORATION, OrganicDecoration.class);
			put(Type.PIECE_DECORATION, PieceDecoration.class);
	}};

    public List<FieldDescription> getFieldsOf(Type type) {
        try {
            Template tmp = (Template) POSSIBLE_CLASSES.get(type).getConstructor().newInstance();
            return tmp.getFields();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FieldDescription> getFieldsOf(int pos) {
        return decorations.get(pos).getFields();
    }

    public void addDecoration(Type type, List<FieldDescription> fields) {
        try {
            Template tmp = (Template) POSSIBLE_CLASSES.get(type).getConstructor().newInstance();
            tmp.setFields(fields);
            decorations.add(tmp);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public void setDecoration(int position, List<FieldDescription> fields) {
        Template tmp = decorations.get(position);
        tmp.setFields(fields);
        decorations.set(position,tmp);
    }

    public List<Template> getDecorations() {
        return new ArrayList<>(decorations);
    }

    public void addDecoration(Template decorations) {
        this.decorations.add(decorations);
    }

    public void deleteDecoration(int pos) {
        this.decorations.remove(pos);
    }

    public void deleteDecoration(Template delete) {
        this.decorations.remove(delete);
    }

    public List<Type> getPossibleTypes() {
        return Arrays.asList(Type.values());
    }

    public List<FieldDescription> getFilters() {
        return manipulator.getFilters();
    }

    public void setFilters(List<FieldDescription> filters) {
        manipulator.setFilters(filters);
    }

    public String[] getSortings() {
        return manipulator.AVAILABLE_SORTING;
    }

    public void setSorting(final String sorting, final boolean descending) {
        manipulator.sort(sorting, descending);
    }

    public List<Template> filter() {
        manipulator.setAllDecorations(decorations);
        manipulator.filter();
        return manipulator.getFilteredDecorations();
    }

}
