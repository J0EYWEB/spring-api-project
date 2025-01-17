package com.gapi.spring_rest_api;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class GuitarsService {

    @Autowired
    private GuitarRepository guitarRepository;


    public List<Guitar> getAllGuitars(){
        return guitarRepository.findAll();
    }

    public Guitar getGuitarById(long id){
        return guitarRepository.findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException("Guitar with ID " + id + " not found."));
    }

    public List<Guitar> getGuitarsByMake(String make){
        return guitarRepository.findByMake(make);
    }

    public Guitar createGuitar(@Valid Guitar guitar, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("Invalid guitar data: " + bindingResult.getAllErrors());
        }

        return guitarRepository.save(guitar);
    }

    public void bulkCreateGuitar(@Valid List<Guitar> guitars) throws IOException {
        guitarRepository.saveAll(guitars);
    }

    public Guitar updateGuitar(long id, @Valid Guitar updatedGuitar, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("Invalid guitar data: " + bindingResult.getAllErrors());
        }
        Guitar guitar = getGuitarById(id);
        guitar.setMake(updatedGuitar.getMake());
        guitar.setModel(updatedGuitar.getModel());
        guitar.setWoodType(updatedGuitar.getWoodType());
        guitar.setPrice(updatedGuitar.getPrice());
        return guitarRepository.save(guitar);
    }

    public void deleteGuitar(long id){
        Guitar guitar = getGuitarById(id);
        guitarRepository.delete(guitar);
    }
}
