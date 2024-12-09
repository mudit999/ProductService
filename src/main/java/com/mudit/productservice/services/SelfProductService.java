package com.mudit.productservice.services;

import com.mudit.productservice.exceptions.ProductNotFoundException;
import com.mudit.productservice.models.Category;
import com.mudit.productservice.models.Product;
import com.mudit.productservice.repositories.CategoryRepository;
import com.mudit.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found in database");
        }

        return product.get();
    }

    public Page<Product> getAllProductsPaginated(int pageNo, int pageSize){
            return productRepository.findAll(PageRequest.of(pageNo, pageSize));
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
