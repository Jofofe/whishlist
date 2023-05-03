package com.jofofe.whishlist.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = {"id"})
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String name;

    private String email;

}