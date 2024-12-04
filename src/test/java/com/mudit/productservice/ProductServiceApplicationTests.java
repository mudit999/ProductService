package com.mudit.productservice;

import com.mudit.productservice.models.Product;
import com.mudit.productservice.projections.ProductProjection;
import com.mudit.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {
//    Autowired used for dependency injection
    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testingQueries(){
//        List<Product> products = productRepository.findAllByCategory_Title("electronic");
//        System.out.println(products);

//        List<Product> product1 = productRepository.getTitlesAndIdOfAllProductsWithGivenCategoryName("abc");
//        System.out.println(product1);

        List<ProductProjection> productProjections = productRepository.getTitlesAndIdOfAllProductsWithGivenCategoryName("electronics");

        for(ProductProjection productProjection : productProjections){
            System.out.println(productProjection.getId());
            System.out.println(productProjection.getTitle());
        }
        System.out.println();
    }
}