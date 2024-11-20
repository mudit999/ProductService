package com.mudit.productservice.services;

import com.mudit.productservice.dtos.CreateProductRequestDto;
import com.mudit.productservice.exceptions.ProductNotFoundException;
import com.mudit.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(long id) throws ProductNotFoundException;

    Product createProduct(String title,
                        String description,
                        String image,
                        String category,
                        double price);
    }