package com.mudit.productservice.services;

import com.mudit.productservice.dtos.CreateProductRequestDto;
import com.mudit.productservice.dtos.FakeStoreProductDto;
import com.mudit.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate; // using this, we can call 3rd party API/ External API

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

//    @Override
//    public Product getSingleProduct(long id) {
//        /*
//        call the external fakestore product api
//        https://fakestoreapi.com/products/1
//         */
//
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
//        return fakeStoreProductDto.toProduct();
//    }

    @Override
    public Product getSingleProduct(long id) {
        /*
        call the external fakestore product api
        https://fakestoreapi.com/products/1
         */

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

//        if(fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatus.valueOf(200)){
//            // throw an exception
//        }
//
//        fakeStoreProductDtoResponseEntity.getHeaders()

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto == null){
            return null;
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String image,
                                 String category,
                                 double price) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);

        return fakeStoreProductDto1.toProduct();
        /*
        POST : /products actually doesn't create product fakestore
        It is a dummy api
         */
    }
}