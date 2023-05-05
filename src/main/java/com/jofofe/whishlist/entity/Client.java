package com.jofofe.whishlist.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@EqualsAndHashCode(callSuper=false, of = {"id"})
@Document(collection = "client")
public class Client extends BaseEntity{

    @Id
    private String id;

    private String name;

    private String email;

}