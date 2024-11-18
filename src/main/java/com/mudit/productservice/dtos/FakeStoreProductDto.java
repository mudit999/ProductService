package com.mudit.productservice.dtos;

import com.mudit.productservice.models.Category;
import com.mudit.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);

        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);
        product.setImageUrl(image);

        return product;
    }
}


/*
Create DTOS as per the usecase separately
 */