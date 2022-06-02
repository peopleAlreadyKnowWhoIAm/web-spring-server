package ua.lpnu.students.labs.decoration.model.shared;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    void testAllToString() {
        String tmp = Type.allToString();
        String[] lines = tmp.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Assertions.assertTrue(lines[i].contains(Type.values()[i].toString()));
            Assertions.assertTrue(lines[i].contains(String.valueOf(i)));
        }
    }
}
