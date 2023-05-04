package com.jofofe.whishlist.service;

import com.jofofe.whishlist.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public abstract class AbstractService<E extends BaseEntity, I extends Serializable, R extends MongoRepository<E, I>> {

    protected final R repository;

    AbstractService(R repository) {
        super();
        this.repository = repository;
    }

}
