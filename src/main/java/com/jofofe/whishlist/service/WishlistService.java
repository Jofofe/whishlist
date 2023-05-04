package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.WishlistDTO;
import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.entity.Wishlist;
import com.jofofe.whishlist.exception.ClientNotFoundException;
import com.jofofe.whishlist.exception.MaxProductsInWishlistException;
import com.jofofe.whishlist.exception.ProductNotFoundException;
import com.jofofe.whishlist.exception.WishlistNotFoundException;
import com.jofofe.whishlist.repository.WishlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WishlistService extends AbstractService<Wishlist, String, WishlistRepository>{

    private static final Integer MAX_PRODUCTS = 20;

    private final ProductService productService;

    private final ClientService clientService;

    private final ObjectMapper mapper;

    WishlistService(WishlistRepository repository, ProductService productService, ClientService clientService, ObjectMapper mapper) {
        super(repository);
        this.productService = productService;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    public WishlistDTO addProductInWishlist(String idWishlist, ProductDTO productDTO) {
        Wishlist wishlist = repository.findById(idWishlist).orElseThrow(WishlistNotFoundException::new);
        if (wishlist.getIdProducts() != null && wishlist.getIdProducts().size() > MAX_PRODUCTS) {
            throw new MaxProductsInWishlistException();
        }
        Product product = productService.findProductById(productDTO.getId()).orElseThrow(ProductNotFoundException::new);
        wishlist = updateWishlist(product, wishlist);
        return convertWishlistToDTO(wishlist);
    }

    private Wishlist updateWishlist(Product product, Wishlist wishlist) {
        wishlist.addProduct(product);
        return repository.save(wishlist);
    }

    private WishlistDTO convertWishlistToDTO(Wishlist wishlist) {
        Client wishlistClient = clientService.findClientById(wishlist.getIdClient()).orElseThrow(ClientNotFoundException::new);
        List<Product> wishlistProducts = productService.findProductsById(wishlist.getIdProducts());
        WishlistDTO wishlistDTO = mapper.convertValue(wishlist, WishlistDTO.class);
        wishlistDTO.setClient(mapper.convertValue(wishlistClient, ClientDTO.class));
        wishlistDTO.setProducts(wishlistProducts.stream()
                .map(wishlistProduct -> mapper.convertValue(wishlistProduct, ProductDTO.class))
                .collect(Collectors.toList())
        );
        return wishlistDTO;
    }
}
