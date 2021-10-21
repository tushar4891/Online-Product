package com.user.userservice;

import com.user.userservice.Enum.AccountStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo {
    
    private int acc_id;
    private int pass;
    private AccountStatus status;
    private int zipcode;
    private String city;
    private String fname;
    private Boolean isPrimeUser;


}
