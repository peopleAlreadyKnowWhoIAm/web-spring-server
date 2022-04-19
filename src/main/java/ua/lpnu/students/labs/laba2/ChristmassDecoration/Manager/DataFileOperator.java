package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.TypedList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl.TypedArrayList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl.TypedLinkedList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.LongDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.PieceDecoration;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

//Generatest CSV files
//Struct:
//"TYPE:VALUE";"TYPE:VALUE",
//List: "LISTTYPE?TYPE:VALUE,VALUE,VALUE"
//P.S. string must be between quotes
@NoArgsConstructor
public class DataFileOperator {


	public String writeToFile(String fileName, List<Template> list) throws Exception {
		String out = null;
		File file = new File(fileName);
		if (file.exists()) {
			out = "File exist. Will be overwritten";
		} else {
			if(file.createNewFile())out = "File will be created";
		}

		StringBuffer toWrite = new StringBuffer();
		for (Template template : list) {
			toWrite.append(String.format("%s%n", toCsv(template)));
		}
		FileWriter writer = null;

		try {
			writer = new FileWriter(file, Charset.defaultCharset());
			writer.write(toWrite.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (writer != null)
				try {
					writer.close();
				} catch (IOException a) {
					a.printStackTrace();
				}
		}
		
		

		return out;
	}

	public List<Template> readFromFile(String fileName) throws IOException {
		var file = new File(fileName);
		if (!file.exists()) {
			throw new IOException("File don't exist");
		}
		var scanner = new Scanner(file, Charset.defaultCharset()).useDelimiter("\n");
		var fileData = new LinkedList<String>();
		while (scanner.hasNext()) {
			fileData.add(scanner.next());
		}
		scanner.close();
		var list = new ArrayList<Template>(fileData.size());
		for (int i = 0; i < fileData.size(); i++) {
			// if((fileData.get.isEmpty())continue;
			list.add(fromCsv(fileData.get(i)));
		}

		return list;
	}

	String toCsv(Template object) throws Exception {
		var fields = object.getFields();

		String type = object.getClass().getSimpleName();

		StringBuffer fieldsString = new StringBuffer();
		for (var field : fields) {
			
			fieldsString.append(toCsvPrimitive(field.getValue()));
			fieldsString.append(";");
		}

		return String.format(OBJECT_TEMPLATE, type, fieldsString.substring(0, fieldsString.length() - 1));
	}

	Template fromCsv(String csv) {
		var trimmedCsv = csv.substring(1, csv.length() - 1);
		var typeSplitter = trimmedCsv.indexOf(":");
		if (typeSplitter == -1) {
			System.out.println(csv);
		}
		var typeStr = trimmedCsv.substring(0, typeSplitter);
		var values = trimmedCsv.substring(typeSplitter + 1).split(";");

		var decoration = DECORATION_TYPES.get(typeStr).createDecoration();

		var fields = decoration.getFields();
		for (int i = 0; i < fields.size(); i++) {
			fields.get(i).setValue(fromCsvPrimitive(values[i]));
		}

		decoration.setFields(fields);

		return decoration;
	}

	String toCsvPrimitive(Object object) throws Exception {
		String out = "";
		if (object instanceof TypedList) {
			var list = (TypedList<?>) object;
			StringBuffer tmpList = new StringBuffer();

			for (int i = 0; i < list.size(); i++) {
				if (list.getType().getClass() == String.class) {
					tmpList.append(String.format(STRING_TEMPLATE, list.get(i)));
				} else {
					tmpList.append(list.toString());
				}
				if (i != list.size() - 1) {
					tmpList.append(COMMA);
				}
			}

			out = String.format(LIST_TEMPLATE,
					list.getClass().getSimpleName(),
					list.getType().getClass().getSimpleName(),
					tmpList);

		} else if (object instanceof List) {
			var list = (List<?>) object;
			StringBuffer tmpList = new StringBuffer();
			if (list.isEmpty())
				throw new Exception("List is empty or not typed");
			var type = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (type.getClass() == String.class) {
					tmpList.append(String.format(STRING_TEMPLATE, list.get(i)));
				} else {
					tmpList.append(list.toString());
				}
				if (i != list.size() - 1) {
					tmpList.append(COMMA);
				}
			}

			out = String.format(LIST_TEMPLATE,
					list.getClass().getSimpleName(),
					type.getClass().getSimpleName(),
					tmpList.toString());

		} else if (object instanceof String) {
			out = String.format(OBJECT_TEMPLATE, String.class.getSimpleName(),
					String.format(STRING_TEMPLATE, object.toString()));
		} else if (object instanceof Date) {
			out = String.format(OBJECT_TEMPLATE, Date.class.getSimpleName(), String.valueOf(((Date) object).getTime()));
		} else {
			out = String.format(OBJECT_TEMPLATE, object.getClass().getSimpleName(), object.toString());
		}

		return out;
	}

	// Input format - "TYPE:VALUE" OR "LISTTYPE?TYPE:VALUE,VALUE"
	Object fromCsvPrimitive(String csvPrimitive) {
		var csvPrimitiveCorrect = csvPrimitive.substring(1, csvPrimitive.length() - 1);
		var valuesOfStrign = csvPrimitiveCorrect.split(SEMICOLON);
		if (valuesOfStrign[0].contains("?")) {

			var typeDescription = valuesOfStrign[0].split("\\?");
			List<Object> list = LIST_TYPES.get(typeDescription[0]).createList(OBJECT_TYPES.get(typeDescription[1]));
			if (valuesOfStrign.length == 2) {
				var valuesStrings = valuesOfStrign[1].split(COMMA);
				for (String string : valuesStrings) {
					list.add(OBJECT_PARSER.get(typeDescription[1]).parseType(string));
				}
			}
			return list;
		} else {
			return OBJECT_PARSER.get(valuesOfStrign[0]).parseType(valuesOfStrign[1]);
		}
	}

	final static String COMMA = ",";
	final static String QUOTE = "\"";
	final static String SEMICOLON = ":";
	final static String LIST_TEMPLATE = "\"%s?%s:%s\"";
	final static String OBJECT_TEMPLATE = "\"%s:%s\"";
	final static String STRING_TEMPLATE = "\"%s\"";

	final static Map<String, CreateList> LIST_TYPES;
	final static Map<String, Object> OBJECT_TYPES;
	final static Map<String, ParseObject> OBJECT_PARSER;
	final static Map<String, CreateDecoration> DECORATION_TYPES;

	static {
		LIST_TYPES = new HashMap<>();
		LIST_TYPES.put(TypedArrayList.class.getSimpleName(),
				(object) -> new TypedArrayList<Object>(object.getClass().cast(object)));
		LIST_TYPES.put(TypedLinkedList.class.getSimpleName(),
				(object) -> new TypedLinkedList<Object>(object.getClass().cast(object)));
		LIST_TYPES.put(ArrayList.class.getSimpleName(), (object) -> new ArrayList<Object>());
		LIST_TYPES.put(LinkedList.class.getSimpleName(), (object) -> new LinkedList<Object>());

		OBJECT_TYPES = new HashMap<>() {
			{
				put(Integer.class.getSimpleName(), 0);
				put(Float.class.getSimpleName(), 0f);
				put(String.class.getSimpleName(), "");
				put(Usage.class.getSimpleName(), Usage.UNKNOWN);
				put(Type.class.getSimpleName(), Type.ELECTRIC_DECORATION);
				put(Date.class.getSimpleName(), new Date());
				put(Size.class.getSimpleName(), new Size());
			}
		};

		OBJECT_PARSER = new HashMap<>() {
			{
				put(Integer.class.getSimpleName(), (csv) -> Integer.parseInt(csv));
				put(Float.class.getSimpleName(), (csv) -> Float.parseFloat(csv));
				put(String.class.getSimpleName(), (csv) -> csv.substring(1, csv.length() - 1));
				put(Usage.class.getSimpleName(), (csv) -> Usage.valueOf(csv));
				put(Type.class.getSimpleName(), (csv) -> Type.valueOf(csv));
				put(Date.class.getSimpleName(), (csv) -> new Date(Long.parseLong(csv)));
				put(Size.class.getSimpleName(), (csv) -> Size.parseSize(csv));

			}
		};
		DECORATION_TYPES = new HashMap<>() {
			{
				put(ElectricDecoration.class.getSimpleName(), () -> new ElectricDecoration());
				put(LongDecoration.class.getSimpleName(), () -> new LongDecoration());
				put(OrganicDecoration.class.getSimpleName(), () -> new OrganicDecoration());
				put(PieceDecoration.class.getSimpleName(), () -> new PieceDecoration());
			}
		};
	}

	@FunctionalInterface
	interface CreateList {
		List<Object> createList(Object initType);
	}

	@FunctionalInterface
	interface ParseObject {
		Object parseType(String csv);
	}

	@FunctionalInterface
	interface CreateDecoration {
		Template createDecoration();
	}
}