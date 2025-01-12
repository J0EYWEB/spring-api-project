package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
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
        if (guitarService.deleteGuitar(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
