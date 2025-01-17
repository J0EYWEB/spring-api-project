package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/guitars")
public class GuitarController {

    @Autowired
    private final GuitarsService guitarService;

    public GuitarController(GuitarsService guitarService) {
        this.guitarService = guitarService;
    }


    @GetMapping
    public List<Guitar> getAllGuitars(){
        return guitarService.getAllGuitars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guitar> getGuitarById(@PathVariable Long id){
        Guitar guitar = guitarService.getGuitarById(id);
        if (guitar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guitar);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<Guitar>> getGuitarsByMake(@PathVariable String make){
        List<Guitar> guitars = guitarService.getGuitarsByMake(make);
        if (guitars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(guitars);
    }

    @PostMapping
    public ResponseEntity<Guitar> createGuitar(@Valid @RequestBody Guitar guitar, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Guitar createdGuitar = guitarService.createGuitar(guitar, bindingResult);
        if (createdGuitar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGuitar);
    }

    @PostMapping("/bulk-guitars")
    public ResponseEntity<String> bulkCreateGuitar(@RequestBody @Valid List<Guitar> guitars, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        guitarService.bulkCreateGuitar(guitars);
        return ResponseEntity.status(HttpStatus.CREATED).body("Guitars created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guitar> updateGuitar(@PathVariable Long id,@Valid @RequestBody Guitar updatedGuitar, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Guitar guitar = guitarService.updateGuitar(id, updatedGuitar, bindingResult);
        if (guitar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guitar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuitar(@PathVariable Long id){
        guitarService.deleteGuitar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
