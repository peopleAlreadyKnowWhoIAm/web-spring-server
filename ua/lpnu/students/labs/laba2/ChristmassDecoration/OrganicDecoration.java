package ua.lpnu.students.labs.laba2.ChristmassDecoration;

import java.util.Date;

public class OrganicDecoration extends PieceDecoration {
    protected int numberOfDaysToExpiration;
    protected Date dateOfProduction;
    
    public OrganicDecoration(String name, Type type, String usage, int avalaibleAmount, String color, String style,
            String material, int[] size, float pricePerPiece, int numberOfDaysToExpiration, Date dateOfProduction) {
        super(name, type, usage, avalaibleAmount, color, style, material, size, pricePerPiece);
        this.numberOfDaysToExpiration = numberOfDaysToExpiration;
        this.dateOfProduction = dateOfProduction;
    }
    public OrganicDecoration() {
    }
    
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
