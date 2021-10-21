package com.product.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.product.product.Repository.ProductCategoryRepository;
import com.product.product.Service.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@SpringBootTest
@RunWith(SpringRunner.class)
public class addproductTypeTest {
    
    @Autowired
    private ProductService pService;

    @MockBean
    ProductCategoryRepository proCateRepo;

    
    @Test
    public void addProductType()
    {
        Category c = Category.FRIDGE;
        ProductCategory pt = ProductCategory.builder()
                                            .category(c)
                                            .build();

        when(proCateRepo.save(pt)).thenReturn(pt);
        assertEquals("Successfully created !", pService.addProductCategory(c));
    }
}
