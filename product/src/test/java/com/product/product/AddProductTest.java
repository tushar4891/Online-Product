package com.product.product;

import com.product.product.Repository.ProductRepository;
import com.product.product.Service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.product.product.Entity.DemoProduct;
import com.product.product.Entity.Product;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddProductTest {
    
    @Autowired
    private ProductService pService;

    @MockBean
    ProductRepository proRepo;

    @Test
    public void addProduct()
    {
        DemoProduct p = new DemoProduct(1,"Nokia",2,11000);
        Product product = new Product(1,"Nokia",2,11000,false);

        when(proRepo.save(product)).thenReturn(product);
        assertEquals("Successfully created !", pService.addProduct(p));
    }
}
