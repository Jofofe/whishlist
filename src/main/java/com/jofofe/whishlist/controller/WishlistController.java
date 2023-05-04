package com.jofofe.whishlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.WishlistDTO;
import com.jofofe.whishlist.entity.Wishlist;
import com.jofofe.whishlist.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@Api(value = "Wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{idWishlist}/products")
    @ApiOperation(value = "Incluir produto na wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto na wishlist salvo com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<WishlistDTO> addProductInWishlist(
            @PathVariable String idWishlist,
            @RequestBody ProductDTO product
    ) {
        WishlistDTO wishlistDTO = wishlistService.addProductInWishlist(idWishlist, product);
        return ResponseEntity.ok(wishlistDTO);
    }


}
