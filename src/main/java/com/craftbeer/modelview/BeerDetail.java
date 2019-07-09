package com.craftbeer.modelview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDetail {
    private int id;
    private String manufacturer;
    private String name;
    private int categoryId;
    private String country;
    private double price;
    private String description;
    private String status;
}
