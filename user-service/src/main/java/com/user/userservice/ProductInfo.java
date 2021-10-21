package com.user.userservice;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

import com.product.product.Entity.Description;
import com.product.product.Entity.Review;
import com.product.product.Enum.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    
    private int id;
    private String name;
    private int quantity;
    private int price;

    private Description description;

    private List<Review> review;

    private Boolean isReview;

    private Category category;

    @Override
    public String toString() {
        return "ProductInfo [category=" + category + ", description=" + description + ", id=" + id + ", isReview="
                + isReview + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", review=" + review
                + "]";
    }

    
}
