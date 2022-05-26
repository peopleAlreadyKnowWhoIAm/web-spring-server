package ua.lpnu.students.labs.restapp.dataaccess.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

/**
 * Repository for databse.
 */
@Repository
public interface ElectricDecorationRepository extends CrudRepository<ElectricDecoration, Long> {

}
