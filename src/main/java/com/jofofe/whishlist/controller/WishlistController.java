package com.jofofe.whishlist.controller;

import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.WishlistDTO;
import com.jofofe.whishlist.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@Api(value = "Wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    @ApiOperation(value = "Incluir wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Wishlist criada com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<WishlistDTO> addWishlist(@RequestBody WishlistDTO wishlist) {
        WishlistDTO wishlistDTO = wishlistService.addWishlist(wishlist);
        return ResponseEntity.ok(wishlistDTO);
    }

    @PostMapping("/{idWishlist}/products")
    @ApiOperation(value = "Incluir produto na wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto na wishlist salvo com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<WishlistDTO> addWishlistProduct(@PathVariable String idWishlist, @RequestBody ProductDTO product) {
        WishlistDTO wishlistDTO = wishlistService.addProductInWishlist(idWishlist, product);
        return ResponseEntity.ok(wishlistDTO);
    }

    @GetMapping("/{idWishlist}/products")
    @ApiOperation(value = "Retornar produtos da wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos na wishlist retornados com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<List<ProductDTO>> findWishlistProducts(@PathVariable String idWishlist) {
        List<ProductDTO> productsDTO = wishlistService.findWishlistProducts(idWishlist);
        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping("/{idWishlist}/products/{idProduct}")
    @ApiOperation(value = "Retornar um produto da wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto na wishlist retornado com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<ProductDTO> findWishlistProduct(@PathVariable String idWishlist, @PathVariable String idProduct) {
        ProductDTO productDTO = wishlistService.findWishlistProduct(idWishlist, idProduct);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{idWishlist}/products/{idProduct}")
    @ApiOperation(value = "Deletar produto sa wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto na wishlist deletado com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity deleteWishlistProduct(@PathVariable String idWishlist, @PathVariable String idProduct) {
        wishlistService.deleteWishlistProduct(idWishlist, idProduct);
        return ResponseEntity.ok().build();
    }




}
