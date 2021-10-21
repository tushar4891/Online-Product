package com.cart.shoppingcart.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.product.product.Entity.Product;
import com.user.userservice.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCart {
    
    @Id
    private int cartId;
    private String productId;
    private String name;
    private int quantity;
    private int price;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> product;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;
}
