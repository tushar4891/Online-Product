package com.product.product.Entity;

import com.product.product.Enum.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemoProduct {
    
    private int id;
    private String name;
    private int quantity;
    private int price;
    private Boolean isReview;
    private Category category;
    private String description;
    public DemoProduct(int id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    
}
