package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.exception.ProductNotFoundException;
import com.jofofe.whishlist.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService  extends AbstractService<Product, String, ProductRepository> {

    private final ObjectMapper mapper;
    
    ProductService(ProductRepository repository, ObjectMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public List<ProductDTO> findProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(product -> mapper.convertValue(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public ProductDTO findProduct(String idProduct) {
        Product product = findProductById(idProduct).orElseThrow(ProductNotFoundException::new);
        return mapper.convertValue(product, ProductDTO.class);
    }

    public Optional<Product> findProductById(String id) {
        return repository.findById(id);
    }

    public List<Product> findProductsById(List<String> idProducts) {
        return repository.findByIdIn(idProducts);
    }
}
