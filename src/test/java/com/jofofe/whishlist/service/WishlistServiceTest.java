package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.WhishlistApplication;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.WishlistDTO;
import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.entity.Wishlist;
import com.jofofe.whishlist.exception.ProductNotFoundException;
import com.jofofe.whishlist.repository.WishlistRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WhishlistApplication.class})
public class WishlistServiceTest {

    @InjectMocks
    WishlistService wishlistService;

    @Mock
    WishlistRepository repository;

    @Mock
    ProductService productService;

    @Mock
    ClientService clientService;

    @Mock
    ObjectMapper mapper;

    @Test
    public void addWishlistTest() {
        Mockito.when(repository.save(any())).thenReturn(createWishlistProduct());
        Mockito.when(clientService.findClientById(anyString())).thenReturn(Optional.of(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(WishlistDTO.class))).thenReturn(createWishlistDTOProduct());
        Mockito.when(mapper.convertValue(any(), eq(Wishlist.class))).thenReturn(createWishlistProduct());
        Mockito.when(productService.findProductsById(any())).thenReturn(Collections.singletonList(createProduct()));
        WishlistDTO wishlistDTO = wishlistService.addWishlist(createWishlistDTO());
        Assert.assertNotNull(wishlistDTO);
        verify(repository, atLeastOnce()).save(any(Wishlist.class));
    }

    @Test
    public void addProductInWishlistTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createWishlist()));
        Mockito.when(repository.save(any())).thenReturn(createWishlist());
        Mockito.when(clientService.findClientById(anyString())).thenReturn(Optional.of(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(WishlistDTO.class))).thenReturn(createWishlistDTO());
        Mockito.when(mapper.convertValue(any(), eq(Wishlist.class))).thenReturn(createWishlist());
        Mockito.when(productService.findProductById(any())).thenReturn(Optional.of(createProduct()));
        WishlistDTO wishlistDTO = wishlistService.addProductInWishlist("shjkfrejk5hn43kj", createProductDTO());
        Assert.assertNotNull(wishlistDTO);
        verify(repository, atLeastOnce()).save(any(Wishlist.class));
    }

    @Test
    public void findWishlistsTest() {
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(createWishlistProduct()));
        Mockito.when(clientService.findClientById(anyString())).thenReturn(Optional.of(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(WishlistDTO.class))).thenReturn(createWishlistDTO());
        Mockito.when(mapper.convertValue(any(), eq(Wishlist.class))).thenReturn(createWishlistProduct());
        Mockito.when(productService.findProductById(any())).thenReturn(Optional.of(createProduct()));
        List<WishlistDTO> wishlistsDTO = wishlistService.findWishlists();
        Assert.assertNotNull(wishlistsDTO);
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    public void findWishlistTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createWishlistProduct()));
        Mockito.when(clientService.findClientById(anyString())).thenReturn(Optional.of(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(WishlistDTO.class))).thenReturn(createWishlistDTO());
        Mockito.when(mapper.convertValue(any(), eq(Wishlist.class))).thenReturn(createWishlistProduct());
        Mockito.when(productService.findProductById(any())).thenReturn(Optional.of(createProduct()));
        WishlistDTO wishlistDTO = wishlistService.findWishlist("shjkfrejk5hn43kj");
        Assert.assertNotNull(wishlistDTO);
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    public void findWishlistProductsTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createWishlistProduct()));
        Mockito.when(productService.findProductsById(any())).thenReturn(Collections.singletonList(createProduct()));
        List<ProductDTO> products = wishlistService.findWishlistProducts("shjkfrejk5hn43kj");
        Assert.assertNotNull(products);
        verify(productService, atLeastOnce()).findProductsById(any());
    }

    @Test(expected = ProductNotFoundException.class)
    public void findWishlistProductsNotFoundTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createWishlist()));
        Mockito.when(productService.findProductsById(any())).thenReturn(Collections.singletonList(createProduct()));
        wishlistService.findWishlistProducts("shjkfrejk5hn43kj");
    }

    @Test
    public void findWishlistProductTest() {
        Mockito.when(repository.findByIdAndIdProductsContaining(any(), any())).thenReturn(Optional.of(createWishlistProduct()));
        Mockito.when(productService.findProductById(any())).thenReturn(Optional.of(createProduct()));
        Mockito.when(mapper.convertValue(any(), eq(ProductDTO.class))).thenReturn(createProductDTO());
        ProductDTO product = wishlistService.findWishlistProduct("shjkfrejk5hn43kj", "wesdnh234hu23");
        Assert.assertNotNull(product);
        verify(repository, atLeastOnce()).findByIdAndIdProductsContaining(any(), any());
    }

    @Test
    public void deleteWishlistProductTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createWishlistProduct()));
        wishlistService.deleteWishlistProduct("shjkfrejk5hn43kj", "wesdnh234hu23");
        verify(repository, atLeastOnce()).findById(any());
    }

    private Wishlist createWishlist() {
        return Wishlist.builder()
                .id("435erdgre645tg")
                .name("Casamento")
                .idClient("324532rfedsefgvd")
                .build();
    }

    private Wishlist createWishlistProduct() {
        return Wishlist.builder()
                .id("435erdgre645tg")
                .name("Casamento")
                .idClient("324532rfedsefgvd")
                .idProducts(Collections.singletonList("324234jsdfsf"))
                .build();
    }

    private WishlistDTO createWishlistDTO() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setName("Teste");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("lktj34k5n3tjfke");
        wishlistDTO.setClient(clientDTO);
        return wishlistDTO;
    }

    private WishlistDTO createWishlistDTOProduct() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setName("Teste");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("lktj34k5n3tjfke");
        wishlistDTO.setClient(clientDTO);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId("kejr234k5jwekr");
        wishlistDTO.setProducts(Collections.singletonList(productDTO));
        return wishlistDTO;
    }

    private Client createClient() {
        return Client.builder()
                .id("q4jM6hL8iRtU9sN2eF7kP3a")
                .email("jojo@gmail.com")
                .name("João")
                .build();
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
