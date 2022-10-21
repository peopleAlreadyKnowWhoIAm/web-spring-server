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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.lpnu.students.labs.restapp.logic.DecorationService;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

@RestController
@RequestMapping("/decorations")
public class DecorationController {

    @Autowired
    DecorationService service;

    @GetMapping
    public ResponseEntity<Iterable<ElectricDecoration>> getAllDecorations() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ElectricDecoration> getDecorationById(@PathVariable String id) {
        long id_n = Long.valueOf(id);
        var res = service.getById(id_n);
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/filter")
    public ResponseEntity<Iterable<ElectricDecoration>> getFilteredDecoration(
            @RequestParam(required = false, name = "sort") String sortMethod,
            @RequestParam(required = false, name = "search") String search,
            @RequestParam(required = false, name = "price-from") String priceFromStr,
            @RequestParam(required = false, name = "price-to") String priceToStr,
            @RequestParam(required = false, name = "length-from") String lengthFromStr,
            @RequestParam(required = false, name = "length-to") String lengthToStr) {
        float priceFrom = -1;
        if (priceFromStr != null) {
            try {
                priceFrom = Float.parseFloat(priceFromStr);
            } catch (NumberFormatException e) {
                priceFrom = -1;
            }
        }

        float priceTo = -1;
        if (priceToStr != null) {
            try {
                priceTo = Float.parseFloat(priceToStr);
            } catch (NumberFormatException e) {
                priceTo = -1;
            }
        }

        float lengthFrom = -1;
        if (lengthFromStr != null) {
            try {
                lengthFrom = Float.parseFloat(lengthFromStr);
            } catch (NumberFormatException e) {
                lengthFrom = -1;
            }
        }

        float lengthTo = -1;
        if (lengthToStr != null) {
            try {
                lengthTo = Float.parseFloat(lengthToStr);
            } catch (NumberFormatException e) {
                lengthTo = -1;
            }
        }

        return new ResponseEntity<>(service.filter(search, sortMethod, priceFrom, priceTo, lengthFrom, lengthTo),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addDecoration(@RequestBody ElectricDecoration decoration) {
        System.out.println(decoration.toString());
        service.addDecoration(decoration);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<String> updateDecoration(@PathVariable String id,
            @RequestBody ElectricDecoration decoration) {
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
