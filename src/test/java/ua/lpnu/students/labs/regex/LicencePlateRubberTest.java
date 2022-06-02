package ua.lpnu.students.labs.regex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LicencePlateRubberTest {
    static final int HAPPENS = 6;

    String text;

    Path toResult;

    LicencePlateRubber rubber;

    @BeforeEach
    void init() throws IOException {
        toResult = Path.of("", "src", "test", "resources", "txts");
        text = Files.readString(
                toResult.resolve("regex-sample.txt"),
                Charset.defaultCharset());
        rubber = new LicencePlateRubber(text);
    }

    @Test
    void testProcess() throws IOException {
        assertFalse(text.contains(rubber.getReplacement()));

        rubber.process();
        val out = rubber.getText();
        System.out.print("alpha: ");
        System.out.println(out);
        assertEquals(HAPPENS, countAppereance(out, rubber.getReplacement()));

        val temp = Files.createTempFile(null, null);
        Files.writeString(temp, out, Charset.defaultCharset());

        Files.delete(temp);
    }

    @Test
    void testReplacementProcess() throws IOException {
        rubber.setReplacement("REPLASEMENT");
        testProcess();
    }

    int countAppereance(String there, String those) {
        long count = 0;
        int previous = 0;
        while (true) {
            previous = there.indexOf(those, previous + 1);
            if (previous != -1L) {
                count++;
            } else {
                break;
            }
        }

        return (int) count;
    }
}
