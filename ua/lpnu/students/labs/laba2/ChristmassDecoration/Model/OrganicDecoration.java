package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.Date;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Size;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.ConsoleTextFormatter;

public class OrganicDecoration extends PieceDecoration {
    public static final Type classType = Type.ORGANIC_DECORATION;
    // Variables
    protected int numberOfDaysToExpiration;

    protected Date dateOfProduction = new Date();

    // Constructors
    public OrganicDecoration(String name, Usage usage, int avalaibleAmount, String color, String style,
            String material, Size size, float pricePerPiece, int numberOfDaysToExpiration, Date dateOfProduction) {
        super(name, usage, avalaibleAmount, color, style, material, size, pricePerPiece);
        this.numberOfDaysToExpiration = numberOfDaysToExpiration;
        this.dateOfProduction = dateOfProduction;
    }

    public OrganicDecoration() {
        super(classType);
    }

    protected OrganicDecoration(Type type) {
        super(type);
    }

    // Text menu functions
    @Override
    public void setFields(final List<FieldDescription> fields) {

        // Number of days to expiration
        this.numberOfDaysToExpiration = (Integer) fields.get(8).getValue();

        // Date of production
        this.dateOfProduction = (Date) fields.get(9).getValue();

        super.setFields(fields);
    }

    @Override
    public List<FieldDescription> getFields() {
        List<FieldDescription> out = super.getFields();

        // Number of days to expiration
        var tmpHowFarExp = TEMPLATE_FIELDS[0];
        tmpHowFarExp.setValue(this.numberOfDaysToExpiration);
        out.add(tmpHowFarExp);

        // Date of production
        var tmpDate = TEMPLATE_FIELDS[1];
        tmpDate.setValue(this.dateOfProduction);
        out.add(tmpDate);

        return out;
    }


    @Override
    public String toString() {
        return super.toString() + String.format(descriptionStr, ConsoleTextFormatter.dateFormat.format(this.dateOfProduction),
                String.valueOf(this.numberOfDaysToExpiration));
    }

    // String literals

    private static final String dateStr = "Date of production using patern " + ConsoleTextFormatter.datePatternStr + " :";
    private static final String expirationTermStr = "Number of days to expire: ";

    private static final String descriptionStr = "Date of production: %s\t" + expirationTermStr + "%s\t";


    private final FieldDescription[] TEMPLATE_FIELDS = {
        new FieldDescription(expirationTermStr, null),
        new FieldDescription(dateStr, null)
    };

    // Getters/Setters
    public int getNumberOfDaysToExpiration() {
        return numberOfDaysToExpiration;
    }

    public void setNumberOfDaysToExpiration(int numberOfDaysToExpiration) {
        this.numberOfDaysToExpiration = numberOfDaysToExpiration;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

}
