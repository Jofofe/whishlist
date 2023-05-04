package com.jofofe.whishlist.dto;

import lombok.Data;
import java.util.List;

@Data
public class WishlistDTO {

    private String id;

    private String name;

    private ClientDTO client;

    private List<ProductDTO> products;

}
