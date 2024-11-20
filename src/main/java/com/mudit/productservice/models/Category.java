package com.mudit.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel {
    private String title;

    // cascade - if category is removed, then associated data is also removed
  @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    List<Product> productList;
}

/*
    Cardinality
    Product : Category
    M : 1
 */