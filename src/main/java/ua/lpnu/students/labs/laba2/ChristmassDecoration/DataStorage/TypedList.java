/***
 * Interface to give list type
 */
package ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage;

import java.util.List;

public interface TypedList<T> extends List<T>{
	T getType();
}
