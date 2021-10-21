package com.product.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.product.product.Controller.ProductController;
import com.product.product.Entity.Product;
import com.product.product.Enum.Category;
import com.product.product.Repository.ProductRepository;
import com.product.product.Service.ProductService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class getProductByCategoryTest {
    
    @MockBean
    ProductRepository proRepo;

    @Autowired
    ProductService proService;

    @Autowired
    ProductController proController;

    @Test
    public void getAllProductByCategory()
    {
        Category c = Category.MOBILE;

        Product p = new Product(1,"Nokia",2,11000,false);
        List<Product> list = new ArrayList<>();
        list.add(p);

        when(proRepo.getProduct(c)).thenReturn(list);
        assertEquals(1, proController.getProductByCategory(c).size());

    }
}
