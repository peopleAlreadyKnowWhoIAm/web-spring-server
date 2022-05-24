package ua.lpnu.students.labs.restapp.dataaccess.database;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.lpnu.students.labs.restapp.model.shared.Decoration;


@Repository
public interface DecorationRepository extends CrudRepository<Decoration, Integer> {

    
}
