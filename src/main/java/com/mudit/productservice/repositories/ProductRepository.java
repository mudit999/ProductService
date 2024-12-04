package com.mudit.productservice.repositories;

import com.mudit.productservice.models.Category;
import com.mudit.productservice.models.Product;
import com.mudit.productservice.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Declared Queries
    Product save(Product p);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Title(String categoryTitle);

    List<Product> findAllByCategory_Id(Long categoryId);

    // HQL Queries - here use model
    @Query("Select p.title as title, p.id as id from Product p where p.category.title = :categoryName")
    List<ProductProjection> getTitlesAndIdOfAllProductsWithGivenCategoryName(@Param("categoryName") String categoryName);

    //Native SQL Queries - here use tables from database
    @Query(value = "Select * from products p where p.id = 1 and p.title = :productTitle", nativeQuery = true)
    List<ProductProjection> getTitlesAndIdOfAllProductsWithGivenProductTitleEquals(@Param("productTitle") String productTitle);

}
