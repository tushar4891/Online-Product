package com.user.userservice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    
    @Id
    private int zipcode;
    private String city;

    //Person table will contain column address (as a foreign key)
    // @OneToOne(fetch = FetchType.LAZY)
    // private Account account;
    
}
