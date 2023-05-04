package com.jofofe.whishlist.repository;

import com.jofofe.whishlist.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByIdIn(List<String> idProducts);

}
