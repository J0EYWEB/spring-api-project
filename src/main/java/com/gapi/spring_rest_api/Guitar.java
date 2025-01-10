package com.gapi.spring_rest_api;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guitar {


    private Long id;

    @NotEmpty(message = "Make is required")
    private String make;

    @NotEmpty(message = "Model is required")
    private String model;

    @NotEmpty(message = "Wood type is required")
    private String woodType;

    @NotEmpty(message = "Price is required")
    private double price;

}
