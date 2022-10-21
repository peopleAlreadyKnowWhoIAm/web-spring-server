package ua.lpnu.students.labs.restapp.dataaccess.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.lpnu.students.labs.restapp.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>{
    
}
