package ua.lpnu.students.labs.decoration.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.data.DataStorage;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;

public class DataFileOperatorTest {
  List<Decoration> decorations;

  Path folder;

  DataFileOperator operator;

  @BeforeEach
  void init() {
    decorations = new DataStorage().decorations;
    folder = Paths.get("src", "test", "resources", "csv");
    operator = new DataFileOperator();
  }

  @Test
  void testCsvToStandartInput() {
    String in = "kjfjska,dijskjsa,asdjkasjd,dksaoodosa,oaskjdoa,\"jhhsdjhd,sjdijdsajid,siajdjsa"
        + ",dsijsdi,\",dsjiajsia,sdajijdias,ijsadisadjka,iasjdiasjdioasd,jsiodjkksd";
    String out = "kjfjska\ndijskjsa\nasdjkasjd\n"
        + "dksaoodosa\noaskjdoa\njhhsdjhd\nsjdijdsajid\nsiajdjsa"
        + "\ndsijsdi\n\ndsjiajsia\nsdajijdias\nijsadisadjka\niasjdiasjdioasd\njsiodjkksd\n";
    assertEquals(out, operator.csvToStandartIn(in));
  }

  @Test
  void readWriteTest() throws IOException {
    var readed = operator.readFromFile(Paths.get(folder.toAbsolutePath().toString(), "empty.csv"));

    Path tmp = Files.createTempFile(null, null);

    assertTrue(readed.isEmpty());
    operator.writeToFile(tmp, decorations);

    final String allString = decorations.toString();
    val result = operator.readFromFile(tmp);
    result.forEach((a) -> assertTrue(allString.contains(a.toString())));
    Files.delete(tmp);
  }

  @Test
  void testWriteEmptyList() throws IOException {
    List<Decoration> list = Collections.emptyList();

    Path tmp = Files.createTempFile(null, null);
    operator.writeToFile(tmp, list);
    assertTrue(Files.mismatch(tmp,
        Path.of(folder.toAbsolutePath().toString(), "fromEmptyList.csv")) == -1);
  }

  @Test
  void testReadEmptyFile() throws IOException {
    List<Decoration> list = operator.readFromFile(Path.of(folder.toString(), "fromEmptyList.csv"));

    assertTrue(list.isEmpty());
  }
}
