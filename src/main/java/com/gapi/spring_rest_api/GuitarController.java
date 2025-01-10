package com.gapi.spring_rest_api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GuitarController {

    @GetMapping("/test-call")
    public String testCall() {
        return "All good";
    }


}
