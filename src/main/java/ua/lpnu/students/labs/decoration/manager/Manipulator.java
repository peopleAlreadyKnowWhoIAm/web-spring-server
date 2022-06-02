package ua.lpnu.students.labs.decoration.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import lombok.NoArgsConstructor;
import ua.lpnu.students.labs.decoration.model.shared.Decoration;
import ua.lpnu.students.labs.decoration.model.shared.Type;
import ua.lpnu.students.labs.decoration.model.shared.Usage;
import ua.lpnu.students.labs.decoration.model.shared.utils.FieldDescription;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.TypedList;
import ua.lpnu.students.labs.decoration.model.shared.utils.storages.impl.TypedLinkedList;

/**
 * Class which filter and sort given list of the decoration.
 */
@NoArgsConstructor
public class Manipulator {

    private static final String BY_TYPE = "By type";
    private static final String BY_NAME = "By name";
    private static final String BY_MATERIALS = "By material";
    private static final String BY_USAGE = "By usage";
    private static final String AMOUNT_MORE = "More than";
    private static final String PRICE_FROM = "Price from";
    private static final String PRICE_TO = "Price to";
    private static final String BY_PRICE = "By price";
    private static final String BY_AMOUNT = "By amount";
    private static final String DIVIDER = ": ";

    List<Decoration> allDecorations;
    List<Decoration> filteredDecorations;

    TypedList<Type> types = new TypedLinkedList<>(Type.ELECTRIC_DECORATION);

    TypedList<String> names = new TypedLinkedList<>("");
    TypedList<String> materials = new TypedLinkedList<>("");

    TypedList<Usage> usages = new TypedLinkedList<>(Usage.FOR_CHRISTMASS);

    int minAmount = 0;

    float priceFrom = 0.0f;
    float priceTo = 0.0f;

    private static final FieldDescription[] FILTER_TYPES = {
            new FieldDescription(null, BY_TYPE + DIVIDER),
            new FieldDescription(null, BY_NAME + DIVIDER),
            new FieldDescription(null, BY_MATERIALS + DIVIDER),
            new FieldDescription(null, BY_USAGE + DIVIDER),
            new FieldDescription(null, AMOUNT_MORE + DIVIDER),
            new FieldDescription(null, PRICE_FROM + DIVIDER),
            new FieldDescription(null, PRICE_TO + DIVIDER)
    };

    // Constructor
    public Manipulator(List<Decoration> allDecorations) {
        this.filteredDecorations = new ArrayList<>(allDecorations);
        this.allDecorations = new ArrayList<>(allDecorations);
    }

    public static final List<String> AVAILABLE_SORTING = Collections.unmodifiableList(
            List.of(BY_NAME, BY_PRICE, BY_AMOUNT));

    /**
     * Sort filtered decoration by name.
     *
     * @param descending whether descending or ascending
     */
    public void sortByName(boolean descending) {
        filteredDecorations.sort(Comparator.comparing(Decoration::getName));
        if (descending) {
            Collections.reverse(filteredDecorations);
        }
    }

    /**
     * Sort filtered decoration by price.
     *
     * @param descending whether descending or ascending
     */
    public void sortByPrice(boolean descending) {
        filteredDecorations.sort(Comparator.comparing(Decoration::getPrice));
        if (descending) {
            Collections.reverse(filteredDecorations);
        }
    }

    /**
     * Sort filtered decoration by amount.
     *
     * @param descending whether descending or ascending
     */
    public void sortByAmount(boolean descending) {
        filteredDecorations.sort(Comparator.comparing(Decoration::getAvalaibleAmount));
        if (descending) {
            Collections.reverse(filteredDecorations);
        }
    }

    /**
     * Sort filtered decoration.
     *
     * @param sortingType from list of `POSSIBLE_SORTING`
     * @param descending  whether descending or ascending
     */
    public void sort(final String sortingType, final boolean descending) {
        switch (sortingType) {
            case BY_NAME:
                sortByName(descending);
                break;
            case BY_PRICE:
                sortByPrice(descending);
                break;
            case BY_AMOUNT:
                sortByAmount(descending);
                break;
            default:

        }
    }

    /**
     * Filter decoration according to set filters.
     */
    public void filter() {
        // Filter types
        if (!types.isEmpty()) {
            filterPart(decoration -> !types.contains(decoration.getType()));
        }

        // Filter names
        if (!names.isEmpty()) {
            filterPart(decoration -> !names.contains(decoration.getName()));
        }

        // Filter materials
        if (!materials.isEmpty()) {
            filterPart(decoration -> !materials.contains(decoration.getMaterial()));
        }

        // Filter usages
        if (!usages.isEmpty()) {
            filterPart(decoration -> !usages.contains(decoration.getUsage()));
        }

        // Filter minimal amount
        if (minAmount != 0) {
            filterPart(decoration -> decoration.getAvalaibleAmount() <= this.minAmount);
        }

        // Filter price
        if (priceTo != 0) {
            filterPart(decoration -> this.priceTo <= decoration.getPrice());
        }
        if (priceFrom != 0) {
            filterPart(decoration -> this.priceFrom >= decoration.getPrice());
        }
    }

    private void filterPart(Predicate<Decoration> filter) {
        if (!this.filteredDecorations.isEmpty()) {
            filteredDecorations.removeIf(filter);

        }
    }

    /**
     * Return filtered decoration(only if `filter()` called earlier).
     *
     * @return List of filtered decoration
     */
    // Filter and get
    public List<Decoration> getFilteredDecorations() {
        return new ArrayList<>(this.filteredDecorations);
    }

    /**
     * Return all possible filters and their values.
     *
     * @return List of filters
     */
    // If empty send trash in it
    // Needs creating new value of describtion
    public List<FieldDescription> getFilters() {
        List<FieldDescription> filters = new ArrayList<>(7);

        // Types
        var tmpTypes = FILTER_TYPES[0];
        tmpTypes.setValue(this.types);
        filters.add(tmpTypes);

        // Names
        var tmpNames = FILTER_TYPES[1];
        tmpNames.setValue(this.names);
        filters.add(tmpNames);

        // Materials
        var tmpMaterials = FILTER_TYPES[2];
        tmpMaterials.setValue(this.materials);
        filters.add(tmpMaterials);

        // Usages
        var tmpUsages = FILTER_TYPES[3];
        tmpUsages.setValue(this.usages);
        filters.add(tmpUsages);

        // Minimal amount
        var tmpMinAmount = FILTER_TYPES[4];
        tmpMinAmount.setValue(this.minAmount);
        filters.add(tmpMinAmount);

        // Price from
        var tmpPriceFrom = FILTER_TYPES[5];
        tmpPriceFrom.setValue(this.priceFrom);
        filters.add(tmpPriceFrom);

        // Price to
        var tmpPriceTo = FILTER_TYPES[6];
        tmpPriceTo.setValue(this.priceTo);
        filters.add(tmpPriceTo);

        return filters;
    }

    /**
     * Method which set filters to fiter later.
     *
     * @param filters to set
     */
    @SuppressWarnings("unchecked")
    public void setFilters(final List<FieldDescription> filters) {
        if (filters.get(0).getValue() instanceof TypedList) {
            if (((TypedList<?>) filters.get(0).getValue()).getType() instanceof Type) {
                this.types = (TypedList<Type>) filters.get(0).getValue();
            }
        }

        if (filters.get(1).getValue() instanceof TypedList) {
            if (((TypedList<?>) filters.get(1).getValue()).getType() instanceof String) {
                this.names = (TypedList<String>) filters.get(1).getValue();
            }
        }

        if (filters.get(2).getValue() instanceof TypedList) {
            if (((TypedList<?>) filters.get(2).getValue()).getType() instanceof String) {
                this.materials = (TypedList<String>) filters.get(2).getValue();
            }
        }

        if (filters.get(3).getValue() instanceof TypedList) {
            if (((TypedList<?>) filters.get(3).getValue()).getType() instanceof Usage) {
                this.usages = (TypedList<Usage>) filters.get(3).getValue();
            }
        }

        this.minAmount = (Integer) filters.get(4).getValue();

        this.priceFrom = (Float) filters.get(5).getValue();

        this.priceTo = (Float) filters.get(6).getValue();
    }

    public void setAllDecorations(final List<Decoration> decorations) {
        this.allDecorations = new ArrayList<>(decorations);
        this.filteredDecorations = new ArrayList<>(decorations);
    }

    public List<Decoration> getAllDecorations() {
        return new ArrayList<>(this.allDecorations);
    }

}
