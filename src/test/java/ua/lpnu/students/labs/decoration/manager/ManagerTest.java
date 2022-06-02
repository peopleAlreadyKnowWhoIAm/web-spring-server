package ua.lpnu.students.labs.decoration.manager;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lpnu.students.labs.decoration.data.DataStorage;
import ua.lpnu.students.labs.decoration.model.OrganicDecoration;
import ua.lpnu.students.labs.decoration.model.PieceDecoration;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Size;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;

public class ManagerTest {
    private List<Decoration> decorations;

    @BeforeEach
    void init() {
        decorations = new DataStorage().decorations;
    }

    @Test
    void testAddGetDeleteDecoration() {
        Manager manager = new Manager(decorations);

        var tmp = new OrganicDecoration("TEST", Usage.UNKNOWN, 103, "COLOR", "STYLE", "MATERIAL",
                new Size(101, 102, 103), 19.0f, 22, (new GregorianCalendar(2000, 1, 1)).getTime());
        manager.addDecoration(tmp);
        var tmp1 = new PieceDecoration();
        manager.addDecoration(tmp1);

        Assertions.assertTrue(manager.getDecorations().contains(tmp));
        Assertions.assertTrue(manager.getDecorations().contains(tmp1));

        manager.deleteDecoration(manager.getDecorations().size() - 1);
        manager.deleteDecoration(tmp);

        Assertions.assertFalse(manager.getDecorations().contains(tmp));
        Assertions.assertFalse(manager.getDecorations().contains(tmp1));

        Assertions.assertEquals(manager.getDecorations(), decorations);
    }

    @Test
    void testSetDecoration() {
        Manager manager = new Manager(decorations);

        var types = manager.getPossibleTypes();

        Assertions.assertEquals(types, Arrays.asList(Type.values()));

        var fields = manager.getFieldsOf(types.get(0));
        manager.addDecoration(Type.PIECE_DECORATION, fields);

        fields = manager.getFieldsOf(manager.getDecorations().size() - 2);

        fields.get(0).setValue("NAME");
        fields.get(1).setValue(Usage.UNKNOWN);
        fields.get(2).setValue("MATERIAL");
        fields.get(3).setValue(103);
        fields.get(4).setValue(104.0f);

        fields.get(5).setValue("COLOR");
        fields.get(6).setValue("STYLE");
        fields.get(7).setValue(new Size(107, 107, 107));

        manager.setDecoration(manager.getDecorations().size() - 2, fields);

        manager.addDecoration(Type.PIECE_DECORATION, fields);

        Assertions.assertEquals(
                manager.getDecorations().get(manager.getDecorations().size() - 1).toString(),
                manager.getDecorations().get(manager.getDecorations().size() - 3).toString());
    }
}
