package com.mudit.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// lombok can be used for creating getter and setter

// Spring annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
