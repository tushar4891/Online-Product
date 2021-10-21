package com.product.product.Repository;

import com.product.product.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
    
}
