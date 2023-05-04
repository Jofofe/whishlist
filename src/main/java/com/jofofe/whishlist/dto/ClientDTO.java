package com.jofofe.whishlist.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ClientDTO {

    @NotBlank(message = "{id.not.blank}")
    private String id;

    @NotBlank(message = "{name.not.blank}")
    private String name;

    @Email(message = "{email.valid}")
    private String email;

}
