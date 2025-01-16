package com.gapi.spring_rest_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "guitars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Make is required")
    private String make;

    @NotEmpty(message = "Model is required")
    private String model;

    @NotEmpty(message = "Wood type is required")
    private String woodType;

    @Positive(message = "Price must be a positive value")
    private double price;

}
