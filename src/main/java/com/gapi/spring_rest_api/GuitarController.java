package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
        return ResponseEntity.ok(guitarService.getGuitarById(id));
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<Guitar>> getGuitarsByMake(@PathVariable String make){
        List<Guitar> guitars = guitarService.getGuitarsByMake(make);
        return ResponseEntity.ok(guitars);
    }

    @PostMapping
    public ResponseEntity<Guitar> createGuitar(@Valid @RequestBody Guitar guitar){
        return ResponseEntity.status(HttpStatus.CREATED).body(guitarService.createGuitar(guitar));
    }

    @PostMapping("/bulk-guitars")
    public ResponseEntity<String> bulkCreateGuitar(@RequestBody @Valid List<Guitar> guitars){
        guitarService.bulkCreateGuitar(guitars);
        return ResponseEntity.status(HttpStatus.CREATED).body("Guitars created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guitar> updateGuitar(@PathVariable Long id,@Valid @RequestBody Guitar updatedGuitar){
        return ResponseEntity.ok(guitarService.updateGuitar(id, updatedGuitar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuitar(@PathVariable Long id){
        guitarService.deleteGuitar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
