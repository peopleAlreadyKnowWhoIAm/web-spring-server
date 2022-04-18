package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.TypedList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl.TypedArrayList;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl.TypedLinkedList;
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

	String toCsvPrimitive(Object object) throws Exception {
		String out = new String();
		if (object instanceof TypedList) {
			var list = (TypedList<?>) object;
			String tmpList = new String();

			for (int i = 0; i < list.size(); i++) {
				if(list.getType().getClass() == String.class){
					tmpList += String.format(STRING_TEMPLATE, list.get(i));
				}else{
					tmpList += list.toString();
				}
				if (i != list.size() - 1) {
					tmpList += COMMA;
				}
			}

			out = String.format(LIST_TEMPLATE, 
					list.getClass().getSimpleName(),
					list.getType().getClass().getSimpleName(), 
					tmpList
			);

		}else if (object instanceof List) {
			var list = (List<?>) object;
			String tmpList = new String();
			if(list.isEmpty()) throw new Exception("List is empty or not typed");
			var type = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (type.getClass() == String.class) {
					tmpList += String.format(STRING_TEMPLATE, list.get(i));
				} else {
					tmpList += list.toString();
				}
				if (i != list.size() - 1) {
					tmpList += COMMA;
				}
			}

			out = String.format(LIST_TEMPLATE,
					list.getClass().getSimpleName(),
					type.getClass().getSimpleName(),
					tmpList);

		} else if(object instanceof String){
			out = String.format(OBJECT_TEMPLATE, String.class.getSimpleName(),String.format(STRING_TEMPLATE, object.toString()));
		}else if(object instanceof Date){
			out = String.format(OBJECT_TEMPLATE, Date.class.getSimpleName(),String.valueOf(((Date)object).getTime()));
		}else{
			out = String.format(OBJECT_TEMPLATE, object.getClass().getSimpleName(), object.toString());
		}
		System.out.println(out);
		return out;
	}

	//Input format - "TYPE:VALUE" OR "LISTTYPE?TYPE:VALUE,VALUE"
	Object fromCsvPrimitive(String csvPrimitive) {
		var csvPrimitiveCorrect = csvPrimitive.substring(1, csvPrimitive.length()-1);
		var valuesOfStrign = csvPrimitiveCorrect.split(SEMICOLON);
		if(valuesOfStrign[0].contains("?")){
			System.out.println(valuesOfStrign[0]);
			var typeDescription = valuesOfStrign[0].split("\\?");
			List<Object> list = LIST_TYPES.get(typeDescription[0]).createList(OBJECT_TYPES.get(typeDescription[1]));
			var valuesStrings = valuesOfStrign[1].split(COMMA);
			for (String string : valuesStrings) {
				list.add(OBJECT_PARSER.get(typeDescription[1]).parseType(string));
			}
			return list;	
		}else{
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

	static{
		LIST_TYPES = new HashMap<>();
		LIST_TYPES.put(TypedArrayList.class.getSimpleName(), (object) -> new TypedArrayList<Object>(object.getClass().cast(object)));
		LIST_TYPES.put(TypedLinkedList.class.getSimpleName(), (object) -> new TypedLinkedList<Object>(object.getClass().cast(object)));
		LIST_TYPES.put(ArrayList.class.getSimpleName(), (object) -> new ArrayList<Object>());
		LIST_TYPES.put(LinkedList.class.getSimpleName(), (object) -> new LinkedList<Object>());

		OBJECT_TYPES = new HashMap<>(){{
			put(Integer.class.getSimpleName(), 0);
			put(Float.class.getSimpleName(), 0f);
			put(String.class.getSimpleName(), new String());
			put(Usage.class.getSimpleName(), Usage.UNKNOWN);
			put(Type.class.getSimpleName(), Type.ELECTRIC_DECORATION);
			put(Date.class.getSimpleName(), new Date());
			put(Size.class.getSimpleName(), new Size());
		}};
	
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
	}

	@FunctionalInterface
	interface CreateList{
		List<Object> createList(Object initType);
	}

	@FunctionalInterface
	interface ParseObject{
		Object parseType(String csv);
	}
}