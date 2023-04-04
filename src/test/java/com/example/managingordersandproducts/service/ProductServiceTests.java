package com.example.managingordersandproducts.service;

import com.example.managingordersandproducts.model.Product;
import com.example.managingordersandproducts.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById() {
        // given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // when
        Optional<Product> result = productService.getProductById(id);

        // then
        assertThat(result).isPresent().contains(product);
    }

    @Test
    public void testGetAllProducts() {
        // given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // when
        List<Product> result = productService.getAllProducts();

        // then
        assertThat(result).isEqualTo(productList);
    }

    @Test
    public void testCreateProduct() {
        // given
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // when
        Product result = productService.createProduct(product);

        // then
        assertThat(result).isEqualTo(product);
    }
}
