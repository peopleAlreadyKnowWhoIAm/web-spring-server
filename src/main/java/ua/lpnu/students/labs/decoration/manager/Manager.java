package ua.lpnu.students.labs.decoration.manager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;

/**
 * Class which managing decoration database.
 */
public class Manager {

  public Manager(List<Decoration> decorations) {
    this.decorations = new ArrayList<>();
    this.decorations.addAll(decorations);
  }

  public Manager() {
    decorations = new ArrayList<Decoration>();
  }

  protected List<Decoration> decorations;

  protected Manipulator manipulator = new Manipulator();

  /**
   * Return fields of the certain type decoration.
   *
   * @param type type of the decoration
   * @return List of fields from type of the decoration
   */
  public List<FieldDescription> getFieldsOf(Type type) {
    Decoration tmp = type.createDecoration();
    return tmp.getFields();
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
    Decoration tmp = type.createDecoration();
    tmp.setFields(fields);
    decorations.add(tmp);
  }

  /**
   * Add new decoration.
   *
   * @param decorations add complete decoration
   */
  public void addDecoration(Decoration decorations) {
    this.decorations.add(decorations);
  }

  /**
   * Update data of the decoration.
   *
   * @param position position
   * @param fields   fields of the decoration
   */
  public void setDecoration(int position, List<FieldDescription> fields) {
    Decoration tmp = decorations.get(position);
    tmp.setFields(fields);
    decorations.set(position, tmp);
  }

  /**
   * Give list of all decorations.
   *
   * @return List
   */
  public List<Decoration> getDecorations() {
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
  public void deleteDecoration(Decoration delete) {
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
  public List<Decoration> filter() {
    manipulator.setAllDecorations(decorations);
    manipulator.filter();
    return manipulator.getFilteredDecorations();
  }

  public void importFromFile(final Path path) throws IOException {
    var dataFileOperator = new DataFileOperator();
    this.decorations = dataFileOperator.readFromFile(path);
  }

  public void saveToFile(final Path path) throws IOException {
    var dataFileOperator = new DataFileOperator();
    dataFileOperator.writeToFile(path, this.decorations);
  }
}
