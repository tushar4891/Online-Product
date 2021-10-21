package com.product.product.Repository;

import com.product.product.Entity.Product;
import com.product.product.Enum.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    
    // @Query("SELECT p.price FROM Product p WHERE p.id = ?1")
    // public int getMoney(String id);

    // @Query("SELECT d.desc FROM Description d JOIN Product p on p.description = d.descid WHERE p.quantity = ?1")
    // public String getDescription(int id);

    @Query("SELECT p FROM Product p where p.category = ?1")
    public List<Product> getProduct(Category category);

    
    @Query("SELECT p FROM Product p where p.quantity >= ?2 and p.id = ?1")
    public Product getProductWithDesiredQuantity(int id, int quantity);

}
