package com.user.userservice.Service;

import com.user.userservice.UserInfo;
import com.user.userservice.Repository.AccountRepository;
import com.user.userservice.Repository.AdminRepository;
import com.user.userservice.Repository.UserRepository;
import com.user.userservice.Repository.PaymentRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.user.userservice.Account;
import com.user.userservice.Address;
import com.user.userservice.Payment;
import com.user.userservice.ShoppingCart;
import com.user.userservice.User;
import com.user.userservice.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    
    private static int cartId = 0;
    private static int personId = 101;
    private static int userId = 11;

    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:9091";

    @Autowired
    AccountRepository accRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    PaymentRepository paymentRepo;

    /*
    public String addUser(UserInfo userInfo)
    {
        Account acc = new Account();
        acc.setId(userInfo.getAcc_id());
        acc.setPassword(userInfo.getPass());
        acc.setStatus(userInfo.getStatus());

        Address add = new Address();
        add.setZipcode(userInfo.getZipcode());
        add.setCity(userInfo.getCity());

        acc.setAddress(add);

        // set for ShoppingCart
        ShoppingCart sCart = new ShoppingCart();
        sCart.setCart_id(++cartId);

        Person person = new User(personId, userInfo.getFname(), userInfo.getIsPrimeUser(), sCart);
        personId += 100;
        
        userRepo.save(person);

        //setting person in account
        acc.setPerson(person);

        accRepo.save(acc);
        
        return "Successfully submitted ";
    }
    */

    public String addUser(UserInfo userInfo) throws InterruptedException, ExecutionException
    {
        CompletableFuture<Account> acc1 = CompletableFuture.supplyAsync(() -> {

            Account acc = new Account();
            acc.setId(userInfo.getAcc_id());
            acc.setPassword(userInfo.getPass());
            acc.setStatus(userInfo.getStatus());
            return acc;
        });

        CompletableFuture<Address> add1 = CompletableFuture.supplyAsync(() -> {

            Address add = new Address();
            add.setZipcode(userInfo.getZipcode());
            add.setCity(userInfo.getCity());
            return add;
        });

        CompletableFuture<Person> per = CompletableFuture.supplyAsync(() -> {

            ShoppingCart sCart = new ShoppingCart();
            sCart.setCart_id(++cartId);
            return sCart;
            })
            .thenApply((sCart) -> {
            Person person = new User(personId, userInfo.getFname(), userInfo.getIsPrimeUser(), sCart);
            personId += 100;
            return person;
        });

        Account a = acc1.get();
        Address add = add1.get();
        a.setAddress(add);

        new savePerson(per.get());
        new saveAccount(a, per.get());

        return "Successfully submitted ";
    }
    
    class saveAccount extends Thread
    {
        private Account acc;
        private Person person;

        public saveAccount(Account account, Person person)
        {
            this.acc = account;
            this.person = person;
            this.start();
        }

        @Override
        public void run() {
            
            acc.setPerson(person);
            accRepo.save(acc);
        }
    }
    class savePerson extends Thread
    {
        public Person person;

        public savePerson(Person person)
        {
            this.person = person;
            this.start();
        }

        @Override
        public void run() {
            userRepo.save(person);
        }
    }

    public Boolean makePayment(int paymentId,int totalCost, int productId)
    {        
        Payment payment = paymentRepo.getPayment(paymentId);
        int amount = payment.getAmount();
        if(amount >= totalCost)
        {
            amount -= totalCost;
            payment.setAmount(amount);
            paymentRepo.save(payment);
            // send request to product service to reduce quantity of product.

            return true;
        }
        else
            return false;
    }
}
