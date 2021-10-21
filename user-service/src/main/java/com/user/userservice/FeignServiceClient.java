package com.user.userservice;
import java.util.*;

import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.product.product.Entity.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productData-ws", url = "http://localhost:9091")
public interface FeignServiceClient {
    
    @GetMapping("/get/allProduct")
    public List<ProductCategory> getAllProduct();

    @GetMapping("/get/product/category/{c}")
    public List<Product> getProductByCategory(@PathVariable("c") Category c);
    
}
