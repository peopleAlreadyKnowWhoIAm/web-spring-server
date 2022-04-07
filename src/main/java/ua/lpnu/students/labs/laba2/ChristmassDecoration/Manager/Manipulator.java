package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;


@Getter
@Setter
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
}
