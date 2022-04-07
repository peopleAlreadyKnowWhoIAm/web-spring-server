package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;

public class Manipulator {
    List<Template> allDecorations;
    List<Template> filteredDecorations;

    List<Type> types;

    List<String> names;
    List<String> materials;
    List<String> styles;

    List<String> colors;

    List<Usage> usages;

    int minAmount;

    float priceFrom, priceTo;

//Settings filter
    public void setFilterByTypes(List<Type> type){
        this.types = type;
    }

    public void setSearchWithNames(List<String> name){
        this.names = name;
    }

    public void setFilterByUsages(List<Usage> usage){
        this.usages = usage;
    }

    public void setFilterByMaterials(List<String> material){
        this.materials = material;
    }

    public void setFilterByAmountMore(int amount){
        this.minAmount = amount;
    }

    public void setFilterByPrice(float from, float to){
        this.priceFrom = from;
        this.priceTo = to;
    }

    public void setFilterByStyles(List<String> style){
        this.styles = style;
    }

    public void setFilterByColors(List<String> colors){
        this.colors = colors;
    }


//Set sorting
    public void sortByName(boolean descending){
        filteredDecorations.sort(Comparator.comparing(Template::getName));
        if(descending){
            Collections.reverse(filteredDecorations);
        }
    }

    public void sortByPrice(boolean descending){
        filteredDecorations.sort(Comparator.comparing(Template::getPrice));
        if(descending){
            Collections.reverse(filteredDecorations);
        }
    }


//Filtering utils
    public void filter(){
        //Filter types
        if(types != null)filterPart(a-> types.contains(a.getType()));

        //Filter names
        if(names != null)filterPart(a -> names.contains(a.getName()));

        //Filter materials
        if(materials != null)filterPart(a -> materials.contains(a.getMaterial()));

        //Filter styles
        if(styles != null)filterPart(a -> false ); //TODO end this, make it work

        //Filter colors
        if(colors != null)filterPart(a -> false); //TODO and this

        //Filter usages
        if(usages != null)filterPart(a -> usages.contains(a.getUsage()));

        //Filter minimal amount
        filterPart(a -> a.getAvalaibleAmount() >= this.minAmount );

        //Filter price
        filterPart(a -> this.priceFrom >= a.getPrice() && this.priceTo <= a.getPrice());
    }

    private void filterPart(Predicate<Template> filter){
        filteredDecorations.removeIf(filter);
    }

//Constructor
    public Manipulator(List<Template> allDecorations) {
        this.filteredDecorations = allDecorations;
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
    public List<Type> getTypes() {
        return types;
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getStyles() {
        return styles;
    }

    public List<String> getColors() {
        return colors;
    }

    public List<Usage> getUsages() {
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
