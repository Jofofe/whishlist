package com.jofofe.whishlist.repository;

import com.jofofe.whishlist.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}