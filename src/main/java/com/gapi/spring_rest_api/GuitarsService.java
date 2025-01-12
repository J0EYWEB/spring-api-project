package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
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

    private final List<Guitar> guitars = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    private String filePath = "guitars.json";

    public GuitarsService(){
        loadFromFile();
    }

    public List<Guitar> getAllGuitars(){
        return guitars;
    }

    public Guitar getGuitarById(long id){
        return guitars.stream()
                .filter(guitar -> guitar.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Guitar createGuitar(@Valid Guitar guitar, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("Invalid guitar data: " + bindingResult.getAllErrors());
        }

        guitar.setId((long) (guitars.size() + 1));
        guitars.add(guitar);
        saveToFile();
        return guitar;
    }

    public Guitar updateGuitar(long id, @Valid Guitar updatedGuitar, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("Invalid guitar data: " + bindingResult.getAllErrors());
        }
        Guitar guitar = getGuitarById(id);
        if(guitar != null) {
            guitar.setMake(updatedGuitar.getMake());
            guitar.setModel(updatedGuitar.getModel());
            guitar.setWoodType(updatedGuitar.getWoodType());
            guitar.setPrice(updatedGuitar.getPrice());
            saveToFile();
        }
        return guitar;
    }

    private boolean deleteGuitar(long id){
        boolean deleted = guitars.removeIf(guitar -> guitar.getId().equals(id));
        if (deleted){
            saveToFile();
        }
        return deleted;
    }

    private void saveToFile(){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), guitars);
        } catch (IOException e){
            throw new RuntimeException("Failed to save to guitar file", e);
        }
    }

    private void loadFromFile(){
        try{
            File file = new File(filePath);
            if(file.exists()){
                Guitar[] loadedGuitars = objectMapper.readValue(file, Guitar[].class);
                guitars.addAll(Arrays.asList(loadedGuitars));
            }
        } catch (IOException e){
            throw new RuntimeException("Failed to load data from " + filePath);
        }
    }


}
