package com.user.userservice;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
   // @JoinColumn(name = "cart_id")
    //@Column(name="cart_id", nullable = false, unique = true)
    //@Column(name="cart_id")
    private int cart_id;
    private int product_id;
    private String name;
    private int quantity;
    private int price;
    
    // @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    // private User user;

}
