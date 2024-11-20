package com.mudit.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;

    // cascade persist -> if category is inserted and it is not present in existing categories
    // ,then add it to Category table / persist the category
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String imageUrl;
}