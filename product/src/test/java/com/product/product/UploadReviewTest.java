package com.product.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.product.product.Controller.ProductController;
import com.product.product.Entity.Product;
import com.product.product.Entity.Review;
import com.product.product.Repository.ProductRepository;
import com.product.product.Repository.ReviewRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadReviewTest {
    
    @Autowired
    ProductController proController;
    
    @MockBean
    ProductRepository proRepo;

    @MockBean
    private ReviewRepository reviewRepo;

    @Test
    public void uploadReview()
    {
        int rating = 4;
        int id = 3;

        Review review = proController.uploadReview(rating, id);

        Product pro = new Product(3,"Nokia",2,11000,false);
        Review r = new Review(1,4,pro);

        when(proRepo.getById(id)).thenReturn(pro);
        when(proRepo.save(pro)).thenReturn(pro);

        //when(pro.setIsReview(true)).thenReturn(true);
        when(reviewRepo.save(r)).thenReturn(r);

        assertNotNull(review);
        assertNotNull(review.getRating());
    }
}
