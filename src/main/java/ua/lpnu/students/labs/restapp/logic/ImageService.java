package ua.lpnu.students.labs.restapp.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ua.lpnu.students.labs.restapp.dataaccess.database.ImageRepository;
import ua.lpnu.students.labs.restapp.model.Image;

@Service
@RequiredArgsConstructor
public class ImageService {
    final ImageRepository images;

    public Optional<byte[]> getById(Long id){
        return images.findById(id).map((arg)->arg.getImage());
    }

    public void add(byte[] image) {
        images.save(new Image(image));
    }

    public void update(Long id, byte[] image) {
        val img = new Image(id, image);
        images.save(img);
    }

    public void delete(Long id) {
        images.deleteById(id);
    }

}
