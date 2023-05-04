package com.jofofe.whishlist.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class WishlistDTO {

    private String id;

    @NotBlank(message = "{name.not.null}")
    private String name;

    @NotNull(message = "{client.not.null}")
    private ClientDTO client;

    private List<ProductDTO> products;

}
