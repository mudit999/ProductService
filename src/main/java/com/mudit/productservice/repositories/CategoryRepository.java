package com.mudit.productservice.repositories;

import com.mudit.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//  JpaRepository <a,b>
// a - Specify the model - Category
// b - Data type of primary key of model

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title); // JPA method

    /*
    Find * from Category
    where title like 'title'

    convert the row to category object and return it
     */

    // Optional - just in case if category does not exist
    Optional<Category> findById(Long id);

}
