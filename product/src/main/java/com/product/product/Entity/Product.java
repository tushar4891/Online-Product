package com.product.product.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.product.product.Enum.Category;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Product {
     
    @Id
    private int id;
    private String name;
    private int quantity;
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "desc_id")
    private Description description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Review> review;

    @Column(name = "is_Review")
    private Boolean isReview;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(int id, String name, int quantity, int price, Description description, List<Review> review,
            Boolean isReview, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.review = review;
        this.isReview = isReview;
        this.category = category;
    }

    public Product(int id, String name, int quantity, int price, Boolean isReview) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isReview = isReview;
    }

    

}