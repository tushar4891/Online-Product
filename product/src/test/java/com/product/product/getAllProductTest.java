package com.product.product;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.product.product.Controller.ProductController;
import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.product.product.Repository.ProductCategoryRepository;
import com.product.product.Repository.ProductRepository;
import com.product.product.Service.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class getAllProductTest {
    
    @MockBean
    ProductCategoryRepository proCateRepo;

    @Autowired
    private ProductService proService;

    @Test
    public void getAllProducts()
    {
        ProductCategory p1 = new ProductCategory(Category.MOBILE);
        ProductCategory p2 = new ProductCategory(Category.TV);
        ProductCategory p3 = new ProductCategory(Category.FRIDGE);
        List<ProductCategory> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
 
        when(proCateRepo.findAll()).thenReturn(list);
        assertEquals(3, proService.getAllTheProduct().size());
    }
}
