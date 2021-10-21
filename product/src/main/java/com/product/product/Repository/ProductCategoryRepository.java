package com.product.product.Repository;

import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.product.product.Response.PriceResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    
   //@Query("SELECT p.price FROM Product p JOIN Producttypes c on c.productid = p.pTypes WHERE c.productid= ?1")
    //@Query("SELECT new com.product.product.Response.PriceResponse (p.price , c.category) FROM Product p JOIN Producttypes c on c.productid = p.pTypes WHERE c.productid= ?1")

    //@Query("SELECT p.category FROM Producttypes WHERE p.pTypes = ?1")
    //public PriceResponse getPrice(long id);  
}
