package ua.lpnu.students.labs.laba2.decoration.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.lpnu.students.labs.laba2.decoration.model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.LongDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.PieceDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Template;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Type;
import ua.lpnu.students.labs.laba2.decoration.model.shared.utils.FieldDescription;

/**
 * Class which managing decoration database.
 */
public class Manager {

  public Manager(List<Template> decorations) {
    this.decorations = new ArrayList<>();
    this.decorations.addAll(decorations);
  }

  public Manager() {
    decorations = new ArrayList<Template>();
  }

  protected List<Template> decorations;

  protected Manipulator manipulator = new Manipulator();

  static final Map<Type, Class<?>> POSSIBLE_CLASSES = new HashMap<>() {
    {
      put(Type.ELECTRIC_DECORATION, ElectricDecoration.class);
      put(Type.LONG_DECORATION, LongDecoration.class);
      put(Type.ORGANIC_DECORATION, OrganicDecoration.class);
      put(Type.PIECE_DECORATION, PieceDecoration.class);
    }
  };

  /**
   * Return fields of the certain type decoration.
   *
   * @param type type of the decoration
   * @return List of fields from type of the decoration
   */
  public List<FieldDescription> getFieldsOf(Type type) {
    try {
      Template tmp = (Template) POSSIBLE_CLASSES.get(type).getConstructor().newInstance();
      return tmp.getFields();
    } catch (InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Get fields of the certain decoration.
   *
   * @param pos position of the decoration
   * @return List of fields of certain decoration
   */
  public List<FieldDescription> getFieldsOf(int pos) {
    return decorations.get(pos).getFields();
  }

  /**
   * Add new decoration.
   *
   * @param type   type of a decoration to create
   * @param fields fields of the decoration
   */
  public void addDecoration(Type type, List<FieldDescription> fields) {
    try {
      Template tmp = (Template) POSSIBLE_CLASSES.get(type).getConstructor().newInstance();
      tmp.setFields(fields);
      decorations.add(tmp);
    } catch (InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add new decoration.
   *
   * @param decorations add complete decoration
   */
  public void addDecoration(Template decorations) {
    this.decorations.add(decorations);
  }

  /**
   * Update data of the decoration.
   *
   * @param position position
   * @param fields   fields of the decoration
   */
  public void setDecoration(int position, List<FieldDescription> fields) {
    Template tmp = decorations.get(position);
    tmp.setFields(fields);
    decorations.set(position, tmp);
  }

  /**
   * Give list of all decorations.
   *
   * @return List
   */
  public List<Template> getDecorations() {
    return new ArrayList<>(decorations);
  }

  /**
   * Delete certain decoration.
   *
   * @param pos position in the list
   */
  public void deleteDecoration(int pos) {
    this.decorations.remove(pos);
  }

  /**
   * Delete certain decoration(once).
   *
   * @param delete instance of the decoration to delete
   */
  public void deleteDecoration(Template delete) {
    this.decorations.remove(delete);
  }

  /**
   * Gives possible types of the decoration.
   *
   * @return List of types
   */
  public List<Type> getPossibleTypes() {
    return Arrays.asList(Type.values());
  }

  /**
   * Gives possible filters.
   *
   * @return List of filters
   */
  public List<FieldDescription> getFilters() {
    return manipulator.getFilters();
  }

  /**
   * Set filters.
   *
   * @param filters to set
   */
  public void setFilters(List<FieldDescription> filters) {
    manipulator.setFilters(filters);
  }

  public void setSorting(final String sorting, final boolean descending) {
    manipulator.sort(sorting, descending);
  }

  /**
   * Filter the decoraton with previously set filters.
   *
   * @return List of filtered
   */
  public List<Template> filter() {
    manipulator.setAllDecorations(decorations);
    manipulator.filter();
    return manipulator.getFilteredDecorations();
  }
}
