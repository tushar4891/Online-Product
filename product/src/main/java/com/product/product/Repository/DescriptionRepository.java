package com.product.product.Repository;

import com.product.product.Entity.Description;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository  extends JpaRepository<Description,Integer>{
    
}
