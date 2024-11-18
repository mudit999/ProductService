package com.mudit.productservice;

import com.mudit.productservice.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        Product p1 = new Product();
//        p1.setId(3);
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
