package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;


@Setter
@Getter
public class LongDecoration extends Template {
    public static final Type classType = Type.LONG_DECORATION;
//Variables
    protected List<String> color = new LinkedList<String>();

    protected String style = new String();

    protected int minimalLength;
    protected int widthInCentimeters;




//Constructors
    public LongDecoration(String name,Usage usage, int avalaibleAmount, List<String> color, String style,
            String material, int minimalLength, int widthInCentimeters, float pricePerMeter) {
        super(name, classType, usage, material, avalaibleAmount, pricePerMeter);
        this.color = color;
        this.style = style;
        this.minimalLength = minimalLength;
        this.widthInCentimeters = widthInCentimeters;
    }

    public LongDecoration() {
        super(classType);
    }

    protected LongDecoration(Type type){
        super(type);
    }


    // Text menu functions
    @Override
    @SuppressWarnings("unchecked")
    public void setFields(final List<FieldDescription> fields) {

        // Colors
        this.color = (List<String>) fields.get(5).getValue();

        // Style
        this.style = (String) fields.get(6).getValue();

        // Minimal length
        this.minimalLength = (Integer) fields.get(7).getValue();

        //Width
        this.widthInCentimeters = (Integer) fields.get(8).getValue();

        super.setFields(fields);
    }

    @Override
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = super.getFields();

        // Colors
        var tmpColors = TEMPLATE_FIELDS[0];
        tmpColors.setValue(this.color);
        out.add(tmpColors);

        // Style
        var tmpStyle = TEMPLATE_FIELDS[1];
        tmpStyle.setValue(this.style);
        out.add(tmpStyle);

        // Minimal length
        var tmpMinLen = TEMPLATE_FIELDS[2];
        tmpMinLen.setValue(this.minimalLength);
        out.add(tmpMinLen);

        // Width
        var tmpWidth = TEMPLATE_FIELDS[3];
        tmpWidth.setValue(this.widthInCentimeters);
        out.add(tmpWidth);

        return out;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr,
            this.color.toString(),
            this.style,
            String.valueOf(this.minimalLength),
            String.valueOf(this.widthInCentimeters),
            String.valueOf(this.price)
        );
    }

//String literals 
    
    private static final String colorsStr = "Colors of the decoration\nSend empty to confirm\n";
    private static final String styleStr = "Style: ";
    private static final String minimalLengthStr = "Minimal length selling: ";
    private static final String widthStr = "Width: ";

    private static final String descriptionStr = "Colors: %s\t"+ styleStr + "%s\t" + minimalLengthStr +"%s\t" + widthStr + "%s\tPrice per meter: %s\t";

    private final FieldDescription[] TEMPLATE_FIELDS = {
        new FieldDescription(null, colorsStr),
        new FieldDescription(null, styleStr),
        new FieldDescription(null, minimalLengthStr),
        new FieldDescription(null, widthStr)
    };
}
