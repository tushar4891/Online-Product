package com.user.userservice.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.product.product.Entity.Product;
import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.user.userservice.FeignServiceClient;
import com.user.userservice.UserInfo;
import com.user.userservice.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
    
    static RestTemplate restTemplate = new RestTemplate();
    static String baseUrl = "http://localhost:9091";

    @Autowired
    private UserService uService;

    @Autowired
    private FeignServiceClient feignClient;
    
    @PostMapping("/add/account")
    public ResponseEntity<String> addAccount(@RequestBody UserInfo userInfo ) throws InterruptedException, ExecutionException
    {
        String s = uService.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping("/search/product")
    public void searchProduct()
    {
         //HttpHeaders headers = new HttpHeaders();
         //headers.setContentType(MediaType.APPLICATION_JSON);
         //HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        // Using Rest Template

        // ResponseEntity<ProductCategoryBody[]> responseEntity = restTemplate.exchange(baseUrl + "/get/allProduct",
        //                                              HttpMethod.GET,requestEntity,ProductCategoryBody[].class);

        // ProductCategoryBody[] productBody = responseEntity.getBody();
        // for (ProductCategoryBody product : productBody) {
        //     System.out.println(product.getId() + "  " + product.getCategory().name());
        // }

        // Using Feign Client
        List<ProductCategory> productBody = feignClient.getAllProduct();

        for(ProductCategory product : productBody){
            System.out.println(product.getId() + "  " + product.getCategory().name());
        }
    }

    @GetMapping("get/product/{c}")
   public void getProductByTheirCategory(@PathVariable("c") Category c)
    {
        System.out.println("category is : " + c );
        

        //using Rest Template (getFor Object)*****
       // ProductInfo[] allProduct = restTemplate.getForObject(baseUrl + "/get/product/category?category=WASHINGMACHINE", ProductInfo[].class);


       // Using Rest Template (getForEntity) ******

         //ResponseEntity<ProductInfo[]> allProduct = restTemplate.
           //                  getForEntity(baseUrl + "/get/product/category/{c}" , ProductInfo[].class, c);

        //ProductInfo[] product = allProduct.getBody();

        // for(ProductInfo pro : product)
        // {
        //     System.out.println(pro.getId() + "  "+ pro.getName()+ "  "+ pro.getCategory() + "  "+ pro.getDescription().getInfo() + 
        //         "   " + pro.getQuantity());
        // }

        //Using feign Client
        List<Product> product = feignClient.getProductByCategory(c);
        
        for(Product p : product)
        {
            System.out.println( p.getId() + " " + p.getName() + " "+ p.getCategory() + " "+ p.getDescription().getInfo()
                + " " + p.getQuantity());
        }  

       // Uisng completableFuture
      
    }
    // @GetMapping("get/product/{c}")
    // public void getProductByTheirCategory(@PathVariable("c") Category c)
    // {
    //     CompletableFuture<Void> future = getProByCategory(c)
    //                                         .thenCompose(s -> {
    //                                             return  printProduct(s);
    //                                         });
    // }
       
    

    CompletableFuture<List<Product>> getProByCategory(Category c)
    {
         return  CompletableFuture.supplyAsync( () -> {
            
            List<Product> product = null;
            product = feignClient.getProductByCategory(c);
    
            return product;
              
             });
            
    }
    
    CompletableFuture<Void> printProduct(List<Product> product)
    {
        return  CompletableFuture.runAsync(() -> {

            for(Product p : product)
            {
                System.out.println( p.getId() + " " + p.getName() + " " + " "+ p.getDescription().getInfo()
                    + " " + p.getQuantity());
            } 
        });
    }


    @GetMapping("/purchase/{productId}/{quantity}/{paymentId}")
    public  void purchaseProduct(@PathVariable("productId") int productId, 
                                 @PathVariable("quantity") int quantity,
                                 @PathVariable ("paymentId") int paymentId)
    {
        ResponseEntity<Product> response = restTemplate.getForEntity(baseUrl + "/isAvailable/{id}/{quantity}", 
                                                Product.class, productId,quantity);
         
        System.out.println(" Status code ****" + response.getStatusCode()); 

        // It means I have enough product quantity, Now I have to go for payment.
        if(response.getStatusCode().equals(HttpStatus.OK))
        {
            Product pro = response.getBody();
            int totalCost = pro.getPrice() * quantity;

            if(uService.makePayment(paymentId, totalCost, productId))
                System.out.println("Payment successful !");
            else
                System.out.println("Not enough money...");


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    
            restTemplate.exchange(baseUrl + "/update/{id}/{quantity}", 
                            HttpMethod.PUT,requestEntity, String.class, productId,quantity);
        }
        else
            System.out.println("Not enough quantity of product !");
    }  
    
    @PostMapping("/review/{review}/{id}")
    public void giveReview(@PathVariable("review") int review, @PathVariable("id") int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(baseUrl + "/review/{revie}/{id}" , 
                            HttpMethod.POST, requestEntity,String.class,review,id);
    }
}
