package com.jofofe.whishlist.controller;

import com.jofofe.whishlist.WhishlistApplication;
import com.jofofe.whishlist.utils.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WhishlistApplication.class})
public class ProductControllerTest extends AbstractBaseControllerTest {

    @Test
    public void findProductsTest() throws Exception {
        mockMvc.perform(get("/product").accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }

    @Test
    public void findProductTest() throws Exception {
        mockMvc.perform(get("/product/" + 1).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(Util.statusMatcher);
    }
    
}
