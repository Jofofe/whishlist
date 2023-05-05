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

    private static final Integer NO_PRODUCTS = 0;

    private final ProductService productService;

    private final ClientService clientService;

    private final ObjectMapper mapper;

    WishlistService(WishlistRepository repository, ProductService productService, ClientService clientService, ObjectMapper mapper) {
        super(repository);
        this.productService = productService;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    public WishlistDTO addWishlist(WishlistDTO wishlistDTO) {
        if (wishlistDTO.getProducts() != null && wishlistDTO.getProducts().size() > MAX_PRODUCTS) {
            throw new MaxProductsInWishlistException();
        }
        Wishlist wishlist = repository.save(buildWishlist(wishlistDTO));
        return convertWishlistToDTO(wishlist);
    }

    public WishlistDTO addProductInWishlist(String idWishlist, ProductDTO productDTO) {
        Wishlist wishlist = repository.findById(idWishlist).orElseThrow(WishlistNotFoundException::new);
        if (wishlist.getIdProducts() != null && wishlist.getIdProducts().size() > MAX_PRODUCTS) {
            throw new MaxProductsInWishlistException();
        }
        Product product = productService.findProductById(productDTO.getId()).orElseThrow(ProductNotFoundException::new);
        wishlist = updateWishlistProduct(product, wishlist);
        return convertWishlistToDTO(wishlist);
    }

    public List<WishlistDTO> findWishlists() {
        List<Wishlist> wishlists = repository.findAll();
        return wishlists.stream().map(this::convertWishlistToDTO).collect(Collectors.toList());
    }

    public WishlistDTO findWishlist(String idWishlist) {
        Wishlist wishlist = repository.findById(idWishlist).orElseThrow(WishlistNotFoundException::new);
        return convertWishlistToDTO(wishlist);
    }

    public List<ProductDTO> findWishlistProducts(String idWishlist) {
        Wishlist wishlist = repository.findById(idWishlist).orElseThrow(WishlistNotFoundException::new);
        if (wishlist.getIdProducts() == null || wishlist.getIdProducts().size() == NO_PRODUCTS) {
            throw new ProductNotFoundException();
        }
        List<Product> wishlistProducts = productService.findProductsById(wishlist.getIdProducts());
        return convertProductsToDTO(wishlistProducts);
    }

    public ProductDTO findWishlistProduct(String idWishlist, String idProduct) {
        repository.findByIdAndIdProductsContaining(idWishlist, idProduct)
                .orElseThrow(ProductNotFoundException::new);
        Product product = productService.findProductById(idProduct).orElseThrow(ProductNotFoundException::new);
        return mapper.convertValue(product, ProductDTO.class);

    }

    public void deleteWishlistProduct(String idWishlist, String idProduct) {
        Wishlist wishlist = repository.findById(idWishlist).orElseThrow(WishlistNotFoundException::new);
        wishlist.setIdProducts(wishlist.getIdProducts().stream()
                .filter(wishlistIdProduct -> !wishlistIdProduct.equals(idProduct))
                .collect(Collectors.toList()));
        repository.save(wishlist);
    }

    private Wishlist buildWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = mapper.convertValue(wishlistDTO, Wishlist.class);
        Client client = clientService.findClientById(wishlistDTO.getClient().getId())
                .orElseThrow(ClientNotFoundException::new);
        wishlist.setIdClient(client.getId());
        if (wishlistDTO.getProducts() != null) {
            List<Product> products = wishlistDTO.getProducts().stream()
                    .map(product -> productService.findProductById(product.getId()).orElseThrow(ProductNotFoundException::new))
                    .collect(Collectors.toList());
            wishlist.setIdProducts(products.stream().map(Product::getId).collect(Collectors.toList()));
        }
        return wishlist;
    }

    private Wishlist updateWishlistProduct(Product product, Wishlist wishlist) {
        wishlist.addProduct(product);
        return repository.save(wishlist);
    }

    private WishlistDTO convertWishlistToDTO(Wishlist wishlist) {
        Client wishlistClient = clientService.findClientById(wishlist.getIdClient()).orElseThrow(ClientNotFoundException::new);
        List<Product> wishlistProducts = productService.findProductsById(wishlist.getIdProducts());
        WishlistDTO wishlistDTO = mapper.convertValue(wishlist, WishlistDTO.class);
        wishlistDTO.setClient(mapper.convertValue(wishlistClient, ClientDTO.class));
        wishlistDTO.setProducts(convertProductsToDTO(wishlistProducts));
        return wishlistDTO;
    }

    private List<ProductDTO> convertProductsToDTO(List<Product> wishlistProducts) {
        return wishlistProducts.stream()
                .map(wishlistProduct -> mapper.convertValue(wishlistProduct, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
