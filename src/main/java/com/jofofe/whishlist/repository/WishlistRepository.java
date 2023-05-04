package com.jofofe.whishlist.repository;

import com.jofofe.whishlist.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

}
