package com.mudit.productservice.services;

import com.mudit.productservice.exceptions.ProductNotFoundException;
import com.mudit.productservice.models.Category;
import com.mudit.productservice.models.Product;
import com.mudit.productservice.repositories.CategoryRepository;
import com.mudit.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository
    ){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String image,
                                 String category,
                                 double price) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setImageUrl(image);
        p.setPrice(price);

        Category categoryFromDb = categoryRepository.findByTitle(category);

        if(categoryFromDb == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDb = newCategory;
        }

        p.setCategory(categoryFromDb);
        Product createdProduct = productRepository.save(p);

        return createdProduct;
    }
}
