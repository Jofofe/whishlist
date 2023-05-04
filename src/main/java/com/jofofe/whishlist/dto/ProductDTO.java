package com.jofofe.whishlist.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    @NotBlank(message = "{id.not.blank}")
    private String id;

    @NotBlank(message = "{name.not.blank}")
    private String nome;

    @NotBlank(message = "{description.not.blank}")
    private String description;

    @NotNull(message = "{price.not.null}")
    private BigDecimal price;

}