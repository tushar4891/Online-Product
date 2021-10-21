package com.product.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.product.product.Controller.ProductController;
import com.product.product.Entity.Product;
import com.product.product.Repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateProductTest {
    
    @MockBean
    ProductRepository proRepo;

    @Autowired
    private ProductController proController;

    @Test
    public void updateProduct()
    {
        int id = 4;
        int quantity = 4;

        Optional<Product> product = Optional.of(new Product(1,"Nokia", 10, 11000,false));
        
        when(proRepo.findById(id)).thenReturn(product);
        assertEquals(6, proController.updateProduct(id, quantity));
    }
}
