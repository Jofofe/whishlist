package com.jofofe.whishlist.controller;

import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "Product")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ApiOperation(value = "Retornar todos os products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Products retornados com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<List<ProductDTO>> findProducts() {
        List<ProductDTO> productsDTO = productService.findProducts();
        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping("/{idProduct}")
    @ApiOperation(value = "Retornar product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product retornado com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<ProductDTO> findProduct(@PathVariable String idProduct) {
        ProductDTO products = productService.findProduct(idProduct);
        return ResponseEntity.ok(products);
    }
    
}
