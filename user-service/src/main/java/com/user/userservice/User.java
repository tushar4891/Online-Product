package com.user.userservice;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class User extends Person {
    
    private boolean isPrimeUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ShoppingCart c;

    public User(int id, String fname, boolean isPrimeUser, ShoppingCart c) {
        super(id, fname);
        this.isPrimeUser = isPrimeUser;
        this.c = c;
    }

    public User(boolean isPrimeUser, ShoppingCart c) {
        this.isPrimeUser = isPrimeUser;
        this.c = c;
    }
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Admin extends Person
{
    private String code;
}

