package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;


@Getter
@Setter
@NoArgsConstructor
public class Manipulator {
    List<Template> allDecorations;
    List<Template> filteredDecorations;

    List<Type> types = new LinkedList<>();

    List<String> names = new LinkedList<>();
    List<String> materials = new LinkedList<>();
    List<String> styles = new LinkedList<>();
    List<String> colors = new LinkedList<>();

    List<Usage> usages = new LinkedList<>();

    int minAmount = 0;

    float priceFrom = 0.0f, priceTo = 0.0f;

    private static final FieldDescription[] FILTER_TYPES = {
        new FieldDescription(null, "By type: "),
        new FieldDescription(null, "By name: "),
        new FieldDescription(null, "By material: "),
        new FieldDescription(null, "By style: "),
        new FieldDescription(null, "By color: "),
        new FieldDescription(null, "By usage: "),
        new FieldDescription(null, "More than: "),
        new FieldDescription(null, "Price from: "),
        new FieldDescription(null, "Price to: ")
    };

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

    public List<FieldDescription> getFilters(){
        List<FieldDescription> filters = new ArrayList<>();

        //Types
        var tmpTypes = FILTER_TYPES[0];
        tmpTypes.setValue(this.types);
        filters.add(tmpTypes);

        //Names
        var tmpNames = FILTER_TYPES[1];
        tmpNames.setValue(this.names);
        filters.add(tmpNames);

        //Materials
        var tmpMaterials = FILTER_TYPES[2];
        tmpMaterials.setValue(this.materials);
        filters.add(tmpMaterials);

        //Styles
        var tmpStyles = FILTER_TYPES[3];
        tmpStyles.setValue(this.styles);
        filters.add(tmpStyles);

        //Colors
        var tmpColors = FILTER_TYPES[4];
        tmpColors.setValue(this.colors);
        filters.add(tmpColors);

        //Usages
        var tmpUsages = FILTER_TYPES[5];
        tmpUsages.setValue(this.usages);
        filters.add(tmpUsages);

        //Minimal amount
        var tmpMinAmount = FILTER_TYPES[6];
        tmpMinAmount.setValue(this.minAmount);
        filters.add(tmpMinAmount);

        //Price from
        var tmpPriceFrom = FILTER_TYPES[7];
        tmpPriceFrom.setValue(this.priceFrom);
        filters.add(tmpPriceFrom);

        //Price to
        var tmpPriceTo = FILTER_TYPES[8];
        tmpPriceTo.setValue(this.priceTo);
        filters.add(tmpPriceTo);

        return filters;
    }


    @SuppressWarnings("unchecked")
    public void setFilters(final List<FieldDescription> filters){
        this.types = (List<Type>) filters.get(0).getValue();

        this.names = (List<String>) filters.get(1).getValue();

        this.materials = (List<String>) filters.get(2).getValue();

        this.styles = (List<String>) filters.get(3).getValue();

        this.colors = (List<String>) filters.get(4).getValue();

        this.usages = (List<Usage>) filters.get(5).getValue();

        this.minAmount = (Integer) filters.get(6).getValue();

        this.priceFrom = (Float) filters.get(7).getValue();

        this.priceTo = (Float) filters.get(8).getValue();
    }

//Overriding toString()
    @Override
    public String toString(){
        return null;
        //TODO end
    }
}
