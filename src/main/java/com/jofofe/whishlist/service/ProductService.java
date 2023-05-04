package com.jofofe.whishlist.service;

import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService  extends AbstractService<Product, String, ProductRepository> {
    
    ProductService(ProductRepository repository) {
        super(repository);
    }


    public Optional<Product> findProductById(String id) {
        return repository.findById(id);
    }

    public List<Product> findProductsById(List<String> idProducts) {
        return repository.findByIdIn(idProducts);
    }
}
