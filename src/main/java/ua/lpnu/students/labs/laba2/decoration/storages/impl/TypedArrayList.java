package ua.lpnu.students.labs.laba2.decoration.storages.impl;

import java.util.ArrayList;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import ua.lpnu.students.labs.laba2.decoration.storages.TypedList;


@EqualsAndHashCode(callSuper = false)
public class TypedArrayList<T> extends ArrayList<T> implements TypedList<T> {

  /**
   * Instance of the type.
   */
  private final T type;

  /**
   * Copy constructor.
   * @param type       of the Array(an instance)
   * @param collection to export
   */
  public TypedArrayList(
      final T type,
      final Collection<? extends T> collection) {
    super(collection);
    this.type = type;
  }

  /**
   * Init empty list.
   * @param type of the Array(an instance)
   */
  public TypedArrayList(final T type) {
    super();
    this.type = type;
  }

  /**
   * Get type of the list.
   * @return type of the class
   */
  @Override
  public T getType() {
    return type;
  }

}
