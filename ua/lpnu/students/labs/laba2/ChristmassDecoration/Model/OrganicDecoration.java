package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.Date;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public class OrganicDecoration extends PieceDecoration {
    public static final Type classType = Type.ORGANIC_DECORATION;
//Variables
    protected int numberOfDaysToExpiration;

    protected Date dateOfProduction;
    


//Constructors
    public OrganicDecoration(String name, Usage usage, int avalaibleAmount, String color, String style,
            String material, int[] size, float pricePerPiece, int numberOfDaysToExpiration, Date dateOfProduction) {
        super(name, usage, avalaibleAmount, color, style, material, size, pricePerPiece);
        this.numberOfDaysToExpiration = numberOfDaysToExpiration;
        this.dateOfProduction = dateOfProduction;
    }
    public OrganicDecoration() {
        super(classType);
    }

    protected OrganicDecoration(Type type){
        super(type);
    }



//Overriding
    @Override
    public void editAll(){
        super.editAll();

        this.dateOfProduction = TextMenu.editDate(this.dateOfProduction, dateStr);

        this.numberOfDaysToExpiration = TextMenu.editInt(this.numberOfDaysToExpiration, expirationTermStr);
    }

    @Override
    public void setAll(){
        super.setAll();

        this.dateOfProduction = TextMenu.setDate(dateStr);
        
        this.numberOfDaysToExpiration = TextMenu.setInt(expirationTermStr);
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr, TextMenu.dateFormat.format(this.dateOfProduction), String.valueOf(this.numberOfDaysToExpiration));
    }



//String literals

    private static final String dateStr = "Date of production using patern " + TextMenu.datePatternStr + " :";
    private static final String expirationTermStr = "Number of days to expire: ";

    private static final String descriptionStr = "Date of production: %s\t" + expirationTermStr + "%s\t";



//Getters/Setters
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
