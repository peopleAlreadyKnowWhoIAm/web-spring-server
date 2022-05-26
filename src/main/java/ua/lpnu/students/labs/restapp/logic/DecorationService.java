package ua.lpnu.students.labs.restapp.logic;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lpnu.students.labs.restapp.dataaccess.database.ElectricDecorationRepository;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

/**
 * Service for the server.
 */
@Service
public class DecorationService {
    @Autowired
    ElectricDecorationRepository decorationRepository;

    public Iterable<ElectricDecoration> getAll() {
        return decorationRepository.findAll();
    }

    public Optional<ElectricDecoration> getById(long id) {
        return decorationRepository.findById(id);
    }

    public void addDecoration(ElectricDecoration decoration) {
        //System.out.println(decoration.toString());
        decorationRepository.save(decoration);
    }

    /**
     * Update decoration values.
     *
     * @param id of the decoration to update
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
}
