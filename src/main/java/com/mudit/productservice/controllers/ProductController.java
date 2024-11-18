package com.mudit.productservice.controllers;

import com.mudit.productservice.dtos.CreateProductRequestDto;
import com.mudit.productservice.models.Product;
import com.mudit.productservice.services.FakeStoreProductService;
import com.mudit.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ProductController - This class will receive request from client

@RestController
public class ProductController {

    public ProductService productService;

    // Here class should not initialize the service, it should be injected (IOC)
    // public ProductService productService = new FakeStoreProductService();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
    Api = method in my controller
     */

    /*
    GET /products
     */

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    /*
    GET products/{id}
     */

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id){
        Product p =  productService.getSingleProduct(id);
        ResponseEntity<Product> responseEntity;

        if(p == null){
            responseEntity = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        }else{
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        return responseEntity;
    }

    /*
    Create a Product

    {
        title:
        description:
        price:
        category:
    }

    POST /products
    */

    // @RequestBody of this datatype -> CreateProductRequestDto createProductRequestDto
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto ){
        return productService.createProduct(createProductRequestDto.getTitle(),
                                            createProductRequestDto.getDescription(),
                                            createProductRequestDto.getImage(),
                                            createProductRequestDto.getCategory(),
                                            createProductRequestDto.getPrice());
    }
}
