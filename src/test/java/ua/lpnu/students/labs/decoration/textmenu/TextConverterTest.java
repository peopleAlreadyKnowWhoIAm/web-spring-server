package ua.lpnu.students.labs.decoration.textmenu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedArrayList;

public class TextConverterTest {
    DateFormat dateFormat = new SimpleDateFormat(TextParser.DATE_PATTERN_STR);

    @Test
    void testEdit() throws ParseException {

        // For string
        String[] inputString = {
                "\n", "Test\n", "Alpha\n" };
        String[] resultString = {
                "Test", "Test", "Alpha"
        };
        String[] previousString = {
                "Test", "jkdfj", "shdj"
        };
        for (int i = 0; i < inputString.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputString[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousString[i], ""), resultString[i]);
        }

        // For integer
        String[] inputInt = {
                "\n", "54\n", "bla\n11\n" };
        Integer[] resultInt = {
                120, 54, 11
        };
        Integer[] previousInt = {
                120, 12, 12
        };
        for (int i = 0; i < inputInt.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputInt[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousInt[i], ""), resultInt[i]);
        }

        // For string list
        String[] inputStringList = {
                "bla\nbla\nbal\n\n", "\n", "test\n\n" };
        List<List<String>> previousStringList = Arrays.asList(
                new TypedArrayList<>(new String(), Arrays.asList(new String("bla"))),
                new TypedArrayList<>(new String(), Arrays.asList("super", "puper", "test")),
                new TypedArrayList<>(new String(), Arrays.asList("saj", "test")));
        List<List<String>> resultStringList = Arrays.asList(
                Arrays.asList("bla", "bla", "bal"), Arrays.asList("super", "puper", "test"),
                Arrays.asList("test"));
        for (int i = 0; i < inputStringList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputStringList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousStringList.get(i), "")
                    .toString(),
                    resultStringList.get(i).toString());
        }

        // For type list
        String[] inputTypeList = {
                "0\n5\n4\n\n", "\n", "1\n\n", "0\n1\n2\n\n" };
        List<List<Type>> previousTypeList = Arrays.asList(
                new TypedArrayList<>(Type.ELECTRIC_DECORATION, Arrays.asList(Type.ELECTRIC_DECORATION)),
                new TypedArrayList<>(Type.ELECTRIC_DECORATION,
                        Arrays.asList(Type.PIECE_DECORATION, Type.ORGANIC_DECORATION, Type.LONG_DECORATION)),
                new TypedArrayList<>(Type.ELECTRIC_DECORATION, Arrays.asList(Type.LONG_DECORATION)),
                new TypedArrayList<>(Type.ELECTRIC_DECORATION, Arrays.asList(Type.ORGANIC_DECORATION)));
        List<List<Type>> resultTypeList = Arrays.asList(
                Arrays.asList(Type.PIECE_DECORATION),
                Arrays.asList(Type.PIECE_DECORATION, Type.ORGANIC_DECORATION, Type.LONG_DECORATION),
                Arrays.asList(Type.ORGANIC_DECORATION),
                Arrays.asList(Type.PIECE_DECORATION, Type.ORGANIC_DECORATION, Type.LONG_DECORATION));
        for (int i = 0; i < inputTypeList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputTypeList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousTypeList.get(i), "")
                    .toString(),
                    resultTypeList.get(i).toString());
        }

        // For usage list
        String[] inputUsageList = {
                "0\n5\n4\n\n", "\n", "1\n\n", "0\n1\n2\n\n" };
        List<List<Usage>> previousUsageList = Arrays.asList(
                new TypedArrayList<>(Usage.FOR_CHRISTMASS,
                        Arrays.asList(Usage.FOR_WINDOWS)),
                new TypedArrayList<>(Usage.FOR_CHRISTMASS,
                        Arrays.asList(Usage.OUTSIDE_DECORATION, Usage.FOR_WINDOWS)),
                new TypedArrayList<>(Usage.FOR_CHRISTMASS,
                        Arrays.asList(Usage.OUTSIDE_DECORATION, Usage.UNKNOWN)),
                new TypedArrayList<>(Usage.FOR_CHRISTMASS,
                        Arrays.asList(Usage.OUTSIDE_DECORATION, Usage.FOR_CHRISTMASS)));
        List<List<Usage>> resultUsageList = Arrays.asList(
                Arrays.asList(Usage.UNKNOWN, Usage.FOR_WINDOWS),
                Arrays.asList(Usage.OUTSIDE_DECORATION, Usage.FOR_WINDOWS),
                Arrays.asList(Usage.OUTSIDE_DECORATION),
                Arrays.asList(Usage.UNKNOWN, Usage.OUTSIDE_DECORATION, Usage.FOR_CHRISTMASS));
        for (int i = 0; i < inputUsageList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputUsageList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousUsageList.get(i), "")
                    .toString(),
                    resultUsageList.get(i).toString());
        }

        // For date
        String[] inputDate = {
                "bla\n22/01/2012\n", "\n", "06/02/2004\n" };

        Date[] previousDate = {
                dateFormat.parse("11/2/2002"),
                dateFormat.parse("22/01/2012"),
                dateFormat.parse("11/07/2001") };
        Date[] resultDate = {
                dateFormat.parse("22/01/2012"),
                dateFormat.parse("22/01/2012"),
                dateFormat.parse("06/02/2004") };
        for (int i = 0; i < inputDate.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputDate[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousDate[i], ""), resultDate[i]);
        }

        // For usage
        String[] inputUsage = {
                "5\n8\n1\n", "\n", "1\n" };
        Usage[] previousUsage = {
                Usage.UNKNOWN, Usage.FOR_WINDOWS, Usage.UNIVERSAL
        };
        Usage[] resultUsage = {
                Usage.OUTSIDE_DECORATION, Usage.FOR_WINDOWS, Usage.OUTSIDE_DECORATION
        };
        for (int i = 0; i < inputUsage.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputUsage[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousUsage[i], ""),
                    resultUsage[i]);
        }

        // For size
        String[] inputSize = {
                "\n", "144\n144\n144\n" };
        Size[] previousSize = {
                new Size(12, 24, 2), new Size(44, 44, 44)
        };
        Size[] resultSize = {
                new Size(12, 24, 2), new Size(144, 144, 144)
        };
        for (int i = 0; i < inputSize.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputSize[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousSize[i], ""),
                    resultSize[i]);
        }

        // For type
        String[] inputType = {
                "5\n4\n1\n", "\n", "1\n" };

        Type[] previousType = {
                Type.PIECE_DECORATION, Type.ORGANIC_DECORATION, Type.ELECTRIC_DECORATION
        };
        Type[] resultType = {
                Type.ORGANIC_DECORATION, Type.ORGANIC_DECORATION, Type.ORGANIC_DECORATION
        };
        for (int i = 0; i < inputType.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputType[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousType[i], ""),
                    resultType[i]);
        }

        // For float
        String[] inputFloat = {
                "vla\n12.45\n", "\n", "12.37727392\n" };
        float[] previousFloat = {
                12.37727392f, 12.44f, 121f
        };
        float[] resultFloat = {
                12.45f, 12.44f, 12.37727392f
        };

        for (int i = 0; i < inputFloat.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputFloat[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousFloat[i], ""),
                    resultFloat[i]);
        }

        // For boolean
        String[] inputBoolean = {
                "1\n", "\n", "-8\n", "\n" };
        boolean[] previousBoolean = {
                false, true, false, false
        };
        boolean[] resultBoolean = {
                true, false, false, false
        };
        for (int i = 0; i < inputBoolean.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputBoolean[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.edit(previousBoolean[i], ""),
                    resultBoolean[i]);
        }
    }

    @Test
    void testPrint() {
        var systemOut = System.out;

        String[] input = { "\n", "Test\n", "Alpha\n" };
        for (String string : input) {
            var out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            var console = new TextParser();
            console.print(string);

            Assertions.assertEquals(string, out.toString());
        }

        System.setOut(systemOut);
    }

    @Test
    void testScanInt() {
        String[] input = {
                "\n",
                "kjkdsjksfld;sa;dkfjkkaskdjkasjkfjsaqasd;l;elkas\n1\n",
                "50\n4\n",
                "5\n"
        };
        Integer[] arguments = { null, null, 4, null };
        Integer[] result = { null, 1, 4, 5 };

        for (int i = 0; i < input.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(input[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.scanInt(arguments[i]), result[i]);
        }
    }

    @Test
    void testScanStrictlyInt() {
        String[] input = {
                "\nss\n5",
                "kjkdsjksfld;sa;dkfjkkaskdjkasjkfjsaqasd;l;elkas\n1\n",
                "50\n4\n",
                "5\n"
        };
        Integer[] arguments = { null, null, 4, null };
        Integer[] result = { 5, 1, 4, 5 };

        for (int i = 0; i < input.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(input[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.scanStrictlyInt(arguments[i]), result[i]);
        }
    }

    @Test
    void testSet() throws ParseException {
        // For string
        String[] inputString = {
                "\n", "Test\n", "Alpha\n" };
        String[] resultString = {
                "", "Test", "Alpha"
        };
        for (int i = 0; i < inputString.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputString[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(new String(), ""), resultString[i]);
        }

        // For integer
        String[] inputInt = {
                "\n", "54\n", "bla\n11\n" };
        Integer[] resultInt = {
                0, 54, 11
        };
        for (int i = 0; i < inputInt.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputInt[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(0, ""), resultInt[i]);
        }

        // For string list
        String[] inputStringList = {
                "bla\nbla\nbal\n\n", "\n", "test\n\n" };
        List<List<String>> resultStringList = Arrays.asList(
                Arrays.asList("bla", "bla", "bal"), new LinkedList<>(), Arrays.asList("test"));
        for (int i = 0; i < inputStringList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputStringList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(new TypedArrayList<String>(new String()), ""),
                    resultStringList.get(i));
        }

        // For type list
        String[] inputTypeList = {
                "0\n5\n4\n\n", "\n", "1\n\n", "0\n1\n2\n\n" };
        List<List<Type>> resultTypeList = Arrays.asList(
                Arrays.asList(Type.PIECE_DECORATION), new LinkedList<>(),
                Arrays.asList(Type.ORGANIC_DECORATION),
                Arrays.asList(Type.PIECE_DECORATION, Type.ORGANIC_DECORATION, Type.LONG_DECORATION));
        for (int i = 0; i < inputTypeList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputTypeList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(
                    console.set(new TypedArrayList<>(Type.ELECTRIC_DECORATION), ""),
                    resultTypeList.get(i));
        }

        // For usage list
        String[] inputUsageList = {
                "0\n5\n4\n\n", "\n", "1\n\n", "0\n1\n2\n\n" };
        List<List<Usage>> resultUsageList = Arrays.asList(
                Arrays.asList(Usage.UNKNOWN, Usage.FOR_WINDOWS), new LinkedList<>(),
                Arrays.asList(Usage.OUTSIDE_DECORATION),
                Arrays.asList(Usage.UNKNOWN, Usage.OUTSIDE_DECORATION, Usage.FOR_CHRISTMASS));
        for (int i = 0; i < inputUsageList.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputUsageList[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(new TypedArrayList<>(Usage.UNKNOWN), ""),
                    resultUsageList.get(i));
        }

        // For date
        String[] inputDate = {
                "bla\n22/01/2012\n", "22/01/2012\n", "06/02/2004\n" };

        Date[] resultDate = {
                dateFormat.parse("22/01/2012"),
                dateFormat.parse("22/01/2012"),
                dateFormat.parse("06/02/2004") };
        for (int i = 0; i < inputDate.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputDate[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(new Date(), ""), resultDate[i]);
        }

        // For usage
        String[] inputUsage = {
                "5\n8\n1\n", "\n", "1\n" };
        Usage[] resultUsage = {
                Usage.OUTSIDE_DECORATION, null, Usage.OUTSIDE_DECORATION
        };
        for (int i = 0; i < inputUsage.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputUsage[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(Usage.FOR_CHRISTMASS, ""),
                    resultUsage[i]);
        }

        // For size
        String[] inputSize = {
                "22\n34\n12\n", "144\n144\n144\n" };
        Size[] resultSize = {
                new Size(22, 34, 12), new Size(144, 144, 144)
        };
        for (int i = 0; i < inputSize.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputSize[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(new Size(), ""),
                    resultSize[i]);
        }

        // For type
        String[] inputType = {
                "5\n4\n1\n", "\n", "1\n" };
        Type[] resultType = {
                Type.ORGANIC_DECORATION, null, Type.ORGANIC_DECORATION
        };
        for (int i = 0; i < inputType.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputType[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(Type.ELECTRIC_DECORATION, ""),
                    resultType[i]);
        }

        // For float
        String[] inputFloat = {
                "vla\n12.45\n", "121\n", "12.37727392\n" };
        float[] resultFloat = {
                12.45f, 121f, 12.37727392f
        };

        for (int i = 0; i < inputFloat.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputFloat[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(0.0f, ""),
                    resultFloat[i]);
        }

        // For boolean
        String[] inputBoolean = {
                "1\n", "0\n", "-8\n", "\n" };
        boolean[] resultBoolean = {
                true, false, false, false
        };
        for (int i = 0; i < inputBoolean.length; i++) {
            ByteArrayInputStream buf = new ByteArrayInputStream(inputBoolean[i].getBytes());
            System.setIn(buf);

            var console = new TextParser();
            Assertions.assertEquals(console.set(false, ""),
                    resultBoolean[i]);
        }

    }
}
