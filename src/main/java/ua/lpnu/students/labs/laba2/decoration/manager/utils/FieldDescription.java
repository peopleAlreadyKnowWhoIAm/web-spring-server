package ua.lpnu.students.labs.laba2.decoration.manager.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class FieldDescription {
  private Object value;
  private final String message;
}
