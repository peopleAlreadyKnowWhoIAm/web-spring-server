package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.List;

import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;

@NoArgsConstructor
public class DataFileOperator {
	String toCsv(Template object) {
		return null;
	}

	Template fromCsv(String csv) {
		return null;
	}

	public void writeToFile(String string, List<Template> list) {
	}

	public void writeToFile(List<Template> list) {
	}

	public List<Template> readFromFile(String string) {
		return null;
	}

	String toCsvPrimitive(Object object) {
		if(object instanceof List){
			
		}
		return null;
	}

	Object fromCsvPrimitive(String csvPrimitive) {
		return null;
	}

}