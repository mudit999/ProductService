package com.mudit.productservice.services;

import com.mudit.productservice.dtos.CreateProductRequestDto;
import com.mudit.productservice.exceptions.ProductNotFoundException;
import com.mudit.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(long id) throws ProductNotFoundException;

    Page<Product> getAllProductsPaginated(int pageNo, int pageSize);

    Product createProduct(String title,
                        String description,
                        String image,
                        String category,
                        double price);

}