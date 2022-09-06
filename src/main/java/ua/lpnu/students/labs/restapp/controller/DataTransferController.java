package ua.lpnu.students.labs.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.lpnu.students.labs.restapp.logic.DecorationService;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

@RestController
@RequestMapping("/decorations")
public class DataTransferController {

    @Autowired
    DecorationService service;

    @GetMapping
    public ResponseEntity<Iterable<ElectricDecoration>> getAllDecorations() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ElectricDecoration> getDecoration(@PathVariable String id) {
        long id_n = Long.valueOf(id);
        var res = service.getById(id_n);
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addDecoration(@RequestBody ElectricDecoration decoration) {
        service.addDecoration(decoration);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<String> updateDecoration(@PathVariable String id, @RequestBody ElectricDecoration decoration) {
        long id_n = Long.valueOf(id);
        boolean result = service.updateDecoration(id_n, decoration);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> deleteEntity(@PathVariable String id) {
        long id_n = Long.valueOf(id);
        service.deleteDecoration(id_n);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
