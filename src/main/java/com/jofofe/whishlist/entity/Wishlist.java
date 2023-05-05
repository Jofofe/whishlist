package com.jofofe.whishlist.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
@Builder
@EqualsAndHashCode(callSuper=false, of = {"id"})
@Document(collection = "wishlist")
public class Wishlist extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String idClient;

    private List<String> idProducts;

    public void addProduct(Product product) {
        if(nonNull(product)) {
            if(isNull(this.idProducts)) {
                this.idProducts = new ArrayList<>();
            }
            this.idProducts.add(product.getId());
        }
    }

}
