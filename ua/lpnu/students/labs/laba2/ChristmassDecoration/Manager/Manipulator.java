package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.LinkedList;
import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class Manipulator {
    List<Template> allDecorations;
    List<Template> filteredDecorations;

    Type[] types;

    String[] names;
    String[] materials;
    String[] styles;
    String[] colors;

    Usage[] usages;

    int minAmount;

    float priceFrom, priceTo;

    List<FilterLambda> filterLambdas = new LinkedList<>();

//Settings filter
    public void setFilterByTypes(Type[] type){
        
    }

    public void setSearchWithNames(String[] name){

    }

    public void setFilterByUsages(Usage[] usage){
        
    }

    public void setFilterByMaterials(String[] material){

    }

    public void setFilterByAmountMore(int amount){

    }

    public void setFilterByPrice(float from, float to){

    }

    public void setFilterByStyles(String[] style){

    }

    public void setFilterByColors(String[] colors){

    }


//Set sorting
    public void sortByName(boolean descending){

    }

    public void sortByPrice(boolean descending){

    }


//Filtering utils
    private void buildFilterList(){

    }

    private void filter(FilterLambda filter){

    }

//Lambda interface for filter function
    @FunctionalInterface
    interface FilterLambda{
        boolean filter(Template a);
    }

//Constructor
    public Manipulator(List<Template> allDecorations) {
        this.allDecorations= allDecorations;
    }

//Filter and get
    public List<Template> getFilteredDecorations() {
        return filteredDecorations;
    }


//Overriding toString()
    @Override
    public String toString(){
        return null;
        //TODO end
    }


//Getters of the filter parameters
    public Type[] getTypes() {
        return types;
    }

    public String[] getNames() {
        return names;
    }

    public String[] getMaterials() {
        return materials;
    }

    public String[] getStyles() {
        return styles;
    }

    public String[] getColors() {
        return colors;
    }

    public Usage[] getUsages() {
        return usages;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public float getPriceFrom() {
        return priceFrom;
    }


    public float getPriceTo() {
        return priceTo;
    }
}
