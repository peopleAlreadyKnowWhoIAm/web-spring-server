package ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl;

import java.util.Collection;
import java.util.LinkedList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.TypedList;

/**
 * Linked list with explicit type.
 *
 * @version 1.0
 *
 * @param <T> type of the datastructure
 */
public class TypedLinkedList<T extends Object>
        extends LinkedList<T> implements TypedList<T> {
    /**
     * Type of the datastructure.
     */
    private final T type;

    /**
     * Copy constructor.
     *
     * @param type       type of the list
     * @param collection collection to copy
     */
    public TypedLinkedList(
            final T type,
            final Collection<? extends T> collection) {
        super(collection);
        this.type = type;
    }

    /**
     * Init constructor.
     *
     * @param type type of the list
     */
    public TypedLinkedList(final T type) {
        super();
        this.type = type;
    }

    @Override
    public T getType() {
        return type;
    }

}
