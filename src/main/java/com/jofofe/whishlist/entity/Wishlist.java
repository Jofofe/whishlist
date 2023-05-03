package com.jofofe.whishlist.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"id"})
@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;

    private String idCliente;

    private List<String> idProdutos;

}
