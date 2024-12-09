package com.mudit.productservice.controllers;

import com.mudit.productservice.dtos.CreateProductRequestDto;
import com.mudit.productservice.dtos.ErrorDTO;
import com.mudit.productservice.exceptions.ProductNotFoundException;
import com.mudit.productservice.models.Product;
import com.mudit.productservice.services.FakeStoreProductService;
import com.mudit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ProductController - This class will receive request from client

@RestController
public class ProductController {

    public ProductService productService;

    // Here class should not initialize the service, it should be injected (IOC)
    // public ProductService productService = new FakeStoreProductService();

    // Qualifier - specify the service
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException{
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
    GET products/paginated?pageNo=a&pageSize=b
    a - pageNo
    b - pageSize (max no. of items on single page)
     */

    @GetMapping("/products/paginated")
    public List<Product> getProductsPaginated(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        Page<Product> productPage = productService.getAllProductsPaginated(pageNo, pageSize);
        List<Product> products = productPage.getContent();
        return products;
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

    // Manual Exception Handler
    // Controller is catching the exception here

    //    @ExceptionHandler(ProductNotFoundException.class)
    //    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
    //        ErrorDTO errorDTO = new ErrorDTO();
    //        errorDTO.setMessage(productNotFoundException.getMessage());
    //
    //        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    //        return responseEntity;
    //    }
}
