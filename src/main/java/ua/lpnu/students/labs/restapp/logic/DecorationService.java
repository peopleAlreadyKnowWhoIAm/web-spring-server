package ua.lpnu.students.labs.restapp.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.lpnu.students.labs.restapp.dataaccess.database.ElectricDecorationRepository;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

/**
 * Service for the server.
 */
@Service
@RequiredArgsConstructor
public class DecorationService {
    final ElectricDecorationRepository decorationRepository;

    public Iterable<ElectricDecoration> getAll() {
        return decorationRepository.findAll();
    }

    public Optional<ElectricDecoration> getById(long id) {
        return decorationRepository.findById(id);
    }

    public void addDecoration(ElectricDecoration decoration) {
        // System.out.println(decoration.toString());
        decorationRepository.save(decoration);
    }

    /**
     * Update decoration values.
     *
     * @param id         of the decoration to update
     * @param decoration decoration from which update
     * @return true if updated else false
     */
    public boolean updateDecoration(long id, ElectricDecoration decoration) {
        var decorationOptional = decorationRepository.findById(id);
        if (decorationOptional.isEmpty()) {
            return false;
        }
        var decorationToUpdate = decorationOptional.get();

        BeanUtils.copyProperties(decoration, decorationToUpdate, "id");

        decorationRepository.save(decorationToUpdate);

        return true;
    }

    public void deleteDecoration(long id) {
        decorationRepository.deleteById(id);
    }

    private final static Map<String, Comparator<ElectricDecoration>> sorting = Map.of(
            "Popular", ((a, b) -> (int) (a.getId() - b.getId())),
            "More expensive", ((a, b) -> (int) Math.floor(b.getPrice() - a.getPrice())),
            "Cheeper", ((a, b) -> (int) Math.floor(a.getPrice() - b.getPrice())),
            "Length", ((a, b) -> (b.getLength() - a.getLength())));

    public Iterable<ElectricDecoration> filter(String search, String sortMethod, float priceFrom, float priceTo,
            float lengthFrom,
            float lengthTo) {

        final String searchl = (search == null) ? "" : search.toLowerCase();
        if (sortMethod == null || !sorting.containsKey(sortMethod)) {
            sortMethod = "Popular";
        }
        var list = StreamSupport.stream(getAll().spliterator(), false)
                .filter((a) -> {
                    float price = a.getPrice();
                    if (priceFrom != -1 && price < priceFrom)
                        return false;
                    if (priceTo != -1 && price > priceTo)
                        return false;
                    int length = a.getLength();

                    if (lengthFrom != -1 && length < lengthFrom)
                        return false;
                    if (lengthTo != -1 && length > lengthTo)
                        return false;
                    if (!searchl.isBlank() && !a.getName().toLowerCase().contains(searchl))
                        return false;
                    return true;
                }).sorted(sorting.get(sortMethod))
                .collect(Collectors.toList());

        return list;
    }
}
