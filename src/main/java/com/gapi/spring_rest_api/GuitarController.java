package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gapi.spring_rest_api.GuitarsService;
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


    @GetMapping("/test-call")
    public String testCall() {
        return "All good";
    }

    @GetMapping
    public List<Guitar> getAllGuitars(){
        return guitarService.getAllGuitars();
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
}
