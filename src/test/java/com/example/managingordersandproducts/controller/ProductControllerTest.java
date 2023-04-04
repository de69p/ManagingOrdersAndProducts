package com.example.managingordersandproducts.controller;

import com.example.managingordersandproducts.model.Product;
import com.example.managingordersandproducts.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @Autowired
    private ProductController productController;

    @Test
    public void testGetAllProducts() {
        // given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Test Product 1", "Description 1", 10.00, 5));
        productList.add(new Product(2L, "Test Product 2", "Description 2", 20.00, 10));
        when(productService.getAllProducts()).thenReturn(productList);

        // when
        List<Product> response = productController.getAllProducts();

        // then
        assertEquals(2, response.size());
        assertEquals("Test Product 1", response.get(0).getName());
        assertEquals("Description 1", response.get(0).getDescription());
        assertEquals(10.00, response.get(0).getPrice());
        assertEquals(5, response.get(0).getQuantity());
        assertEquals("Test Product 2", response.get(1).getName());
        assertEquals("Description 2", response.get(1).getDescription());
        assertEquals(20.00, response.get(1).getPrice());
        assertEquals(10, response.get(1).getQuantity());
    }


    @Test
    public void testGetProductById() throws Exception {
        // given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productService.getProductById(id)).thenReturn(Optional.of(product));

        // when and then
        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testCreateProduct() throws Exception {
        // given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        String requestBody = "{\"name\": \"product 1\", \"price\": 10.0}";

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        // when and then
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated()) // modify the expected status to 201
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }
}

