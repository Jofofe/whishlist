package com.jofofe.whishlist.controller;

import com.google.gson.Gson;
import com.jofofe.whishlist.WhishlistApplication;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.WishlistDTO;
import com.jofofe.whishlist.utils.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WhishlistApplication.class})
public class WishlistControllerTest extends AbstractBaseControllerTest {

    @Test
    public void addWishlistTest() throws Exception {
        mockMvc.perform(post("/wishlist")
                        .content(new Gson().toJson(createWishlist()).getBytes())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void addWishlistProductTest() throws Exception {
        mockMvc.perform(post("/wishlist/" + 1 + "/products")
                        .content(new Gson().toJson(createProduct()).getBytes())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }
    @Test
    public void findWishlistsTest() throws Exception {
        mockMvc.perform(get("/wishlist").accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void findWishlistTest() throws Exception {
        mockMvc.perform(get("/wishlist/" + 1).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void findWishlistProductsTest() throws Exception {
        mockMvc.perform(get("/wishlist/" + 1 + "/products").accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void findWishlistProductTest() throws Exception {
        mockMvc.perform(get("/wishlist/" + 1 + "/products/" + 1).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void deleteWishlistProductTest() throws Exception {
        mockMvc.perform(delete("/wishlist/" + 1 + "/products/" + 1).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    private WishlistDTO createWishlist() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setName("Teste");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("lktj34k5n3tjfke");
        wishlistDTO.setClient(clientDTO);
        return wishlistDTO;
    }

    private ProductDTO createProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Teste");
        productDTO.setDescription("Teste description");
        productDTO.setPrice(BigDecimal.TEN);
        return productDTO;
    }

}
