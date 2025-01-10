package com.gapi.spring_rest_api;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class GuitarsService {

    private final List<Guitar> guitars = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String filePath = "guitars.json";

    public Guitar createGuitar(@Valid Guitar guitar, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("Invalid guitar data: " + bindingResult.getAllErrors());
        }
        guitar.setId((long) (guitars.size() + 1));
        guitars.add(guitar);
        saveToFile();
        return guitar;
    }

    private void saveToFile(){
        try{
            objectMapper.writeValue(new File(filePath), guitars);
        } catch (IOException e){
            throw new RuntimeException("Failed to save to guitar file", e);
        }

    }


}
