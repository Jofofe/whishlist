package com.jofofe.whishlist.repository;

import com.jofofe.whishlist.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    List<Wishlist> findByIdCliente(String idCliente);

    void deleteByIdCliente(String idCliente);

}
