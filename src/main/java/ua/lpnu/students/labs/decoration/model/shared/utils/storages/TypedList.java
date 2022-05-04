package ua.lpnu.students.labs.decoration.model.shared.utils.storages;

import java.util.List;

/**
 * Interface which implements TypedList for list.
 *
 * @version 1.0
 * @param <T> type of the datastructure
 */
public interface TypedList<T> extends List<T> {
  /**
   * Get type of the list.
   *
   * @return type of the list
   */
  T getType();
}
