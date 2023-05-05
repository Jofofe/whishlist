package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.WhishlistApplication;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WhishlistApplication.class})
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository repository;

    @Mock
    ObjectMapper mapper;

    @Test
    public void findProductsTest() {
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(createProduct()));
        Mockito.when(mapper.convertValue(any(), eq(ProductDTO.class))).thenReturn(createProductDTO());
        productService.findProducts();
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    public void findProductTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createProduct()));
        Mockito.when(mapper.convertValue(any(), eq(ProductDTO.class))).thenReturn(createProductDTO());
        productService.findProduct("sdfljk3wkj43r5");
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    public void findProductByIdInTest() {
        Mockito.when(repository.findByIdIn(any())).thenReturn(Collections.singletonList(createProduct()));
        productService.findProductsById(Collections.singletonList("sdfljk3wkj43r5"));
        verify(repository, atLeastOnce()).findByIdIn(any());
    }

    private Product createProduct() {
        return Product.builder()
                .id("aS6fT8nK2hY1jD4bM7pQ9z")
                .price(BigDecimal.TEN)
                .name("TV")
                .description("55 polegadas")
                .build();
    }

    private ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Teste");
        productDTO.setDescription("Teste description");
        productDTO.setPrice(BigDecimal.TEN);
        return productDTO;
    }


}
