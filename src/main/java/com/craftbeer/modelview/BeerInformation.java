package com.craftbeer.modelview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerInformation {
    private String name;
    private String category;
    private double price;
    private String manufacturer;
    private String country;
    private String description;
}


