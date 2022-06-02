package ua.lpnu.students.labs.decoration.model.shared;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsageTest {
    @Test
    void testAllToString() {
        String tmp = Usage.allToString();
        String[] lines = tmp.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Assertions.assertTrue(lines[i].contains(Usage.values()[i].toString()));
            Assertions.assertTrue(lines[i].contains(String.valueOf(i)));
        }
    }
}
