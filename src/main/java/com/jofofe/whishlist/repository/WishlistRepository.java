package com.jofofe.whishlist.repository;

import com.jofofe.whishlist.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findByIdAndProductsIdContaining(String idWishlist, String idProduct);

}
