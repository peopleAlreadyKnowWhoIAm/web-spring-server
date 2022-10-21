package ua.lpnu.students.labs.restapp.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ua.lpnu.students.labs.restapp.logic.ImageService;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    final ImageService images;

    @PostMapping()
    public ResponseEntity<String> postImage(@RequestBody byte[] image) {
        images.add(image);
        return ResponseEntity.status(201).body("OK");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return ResponseEntity.ok().body(images.getById(id).get());
    }
}
