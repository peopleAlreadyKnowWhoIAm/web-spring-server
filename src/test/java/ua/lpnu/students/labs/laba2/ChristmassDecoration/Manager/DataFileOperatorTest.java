package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.dataStorage.DataStorage;

public class DataFileOperatorTest {
	List<Template> decorations;

	@BeforeEach
	void init(){
		decorations = new DataStorage().decorations;
	}

	@Test
	void converterCsv(){
		DataFileOperator operator = new DataFileOperator();
		decorations.forEach((a)->{
			Assertions.assertEquals(a.toString(), (operator.fromCsv(operator.toCsv(a))).toString());
		});
	}

	@Test
	void operationWithFile(){
		DataFileOperator file = new DataFileOperator();
		var part0 = decorations.subList(0, 8);
		var part1 = decorations.subList(8, decorations.size() -1);

		file.writeToFile("file.csv", part0);
		file.writeToFile(part1);

		var result = file.readFromFile("file.csv");

		for(var i = 0; i < decorations.size(); i++){
			Assertions.assertEquals(decorations.get(i).toString(), result.get(i).toString());
		}
	}

	@Test
	void testConverts() throws Exception{
		Object[] buf = {
			new String("Help"),
			Usage.FOR_CHRISTMASS,
			Type.ELECTRIC_DECORATION,
			new Size(12,11,10),
			new ArrayList<>(Arrays.asList("one","two","zero")),
			new GregorianCalendar(2009, 9, 29).getTime()
		};

		DataFileOperator converter = new DataFileOperator();

		for (Object object : buf) {
			Assertions.assertEquals(object.toString(), (converter.fromCsvPrimitive(converter.toCsvPrimitive(object))).toString() );
		}
	}
}
