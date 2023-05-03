package com.jofofe.whishlist.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = {"id"})
@Document(collection = "produtos")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private Double price;

}
