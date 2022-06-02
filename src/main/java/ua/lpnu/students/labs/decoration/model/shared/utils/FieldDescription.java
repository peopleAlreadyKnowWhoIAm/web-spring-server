package ua.lpnu.students.labs.decoration.model.shared.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class which save value with message to it.
 */
@Getter
@Setter
@AllArgsConstructor
public final class FieldDescription {
    private Object value;
    private final String message;
}
