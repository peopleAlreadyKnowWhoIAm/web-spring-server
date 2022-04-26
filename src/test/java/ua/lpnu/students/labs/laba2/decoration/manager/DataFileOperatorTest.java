package ua.lpnu.students.labs.laba2.decoration.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.laba2.decoration.data.DataStorage;
import ua.lpnu.students.labs.laba2.decoration.model.shared.Template;

public class DataFileOperatorTest {
  List<Template> decorations;

  @BeforeEach
  void init() {
    decorations = new DataStorage().decorations;
  }


  @Test
  void testCsvToStandartInput() {
    String in = "kjfjska,dijskjsa,asdjkasjd,dksaoodosa,oaskjdoa,\"jhhsdjhd,sjdijdsajid,siajdjsa"
        + ",dsijsdi,\",dsjiajsia,sdajijdias,ijsadisadjka,iasjdiasjdioasd,jsiodjkksd";
    String out = "kjfjska\ndijskjsa\nasdjkasjd\n"
        + "dksaoodosa\noaskjdoa\njhhsdjhd\nsjdijdsajid\nsiajdjsa"
        + "\ndsijsdi\n\ndsjiajsia\nsdajijdias\nijsadisadjka\niasjdiasjdioasd\njsiodjkksd\n";
    var oper = new DataFileOperator();
    assertEquals(out, oper.csvToStandartIn(in));
  }

  @Test
  void readWriteTest() throws IOException {
    val operator = new DataFileOperator();
    operator.writeToFile("test.csv", decorations);

    final String allString = decorations.toString();
    val result = operator.readFromFile("test.csv");
    result.forEach((a) -> assertTrue(allString.contains(a.toString())));
  }
}
