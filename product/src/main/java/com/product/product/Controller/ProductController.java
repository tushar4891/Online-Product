package com.product.product.Controller;

import com.product.product.Enum.Category;
import com.product.product.Repository.ProductRepository;
import com.product.product.Repository.ReviewRepository;
import com.product.product.Repository.ProductCategoryRepository;
import com.product.product.Response.PriceResponse;
import com.product.product.Service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.product.product.Entity.DemoProduct;
import com.product.product.Entity.Product;
import com.product.product.Entity.ProductCategory;
import com.product.product.Entity.Review; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService pService;

    @Autowired
    private ProductCategoryRepository pcRepo;

    @Autowired
    private ProductRepository proRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    private static int reviewCounter = 0;

    @PostMapping("/add/category/{c}")
    public  ResponseEntity<String> addProductTypes(@PathVariable Category c)
    {
        String s = pService.addProductCategory(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @PostMapping("/add/product")
    public  ResponseEntity<String> addProduct(@RequestBody DemoProduct p)
    {
        String s = pService.addProduct(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
    
    @GetMapping("/get/allProduct")
    public List<ProductCategory> getAllProduct()
    {
        return pService.getAllTheProduct();
    }

    @GetMapping("/get/product/category/{c}")
    public List<Product> getProductByCategory(@PathVariable("c") Category c)
    {
        System.out.println("Category is : " + c);
        return proRepo.getProduct(c);
    }

    // @GetMapping("/isAvailable/{id}/{quantity}")
    // public ResponseEntity<Optional<Product>> isProductAvailable(@PathVariable("id") int id, 
    //                                                     @PathVariable("quantity") int quantity)
    // {
    //     System.out.println("id = " + id + "  " + "quantity "+ quantity);

    //     Optional<Product> p =  proRepo.getProductWithDesiredQuantity(id, quantity);    

    //     if(! p.isPresent())
    //     {
    //         System.out.println("Error ******");
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //         //return false;
    //     }
    //     else
    //     {
    //         System.out.println("No Error ******");
    //         return ResponseEntity.of(Optional.of(p));
    //         //return true;
    //     }
    // }

    @GetMapping("/isAvailable/{id}/{quantity}")
    public CompletableFuture<Void> isProductAvailable(@PathVariable("id") int id, 
                                                        @PathVariable("quantity") int quantity) throws InterruptedException, ExecutionException
    {
            CompletableFuture<Product> oProduct = CompletableFuture
            .supplyAsync(() -> {
                
                Product p = proRepo.getProductWithDesiredQuantity(id, quantity);
                if(p == null)
                    throw new RuntimeException("Error");
                return p;
            })   
              .handle((p,t) -> {

                if(t != null)
                {    
                    System.out.println("******** Handled the exception : ******" + t.getMessage());
                    return Product.builder()
                                .id(0)
                                .name("No product Available")
                                .quantity(0)
                                .price(0)
                                .build();
                }
                 return p;
              });

            oProduct.join();
            Product pro = oProduct.get();

            System.out.println("Id : " + pro.getId() + "  " + "Name : " + pro.getName());
            return null;
    }


    @PutMapping("/update/{id}/{quantity}")
    public int updateProduct(@PathVariable("id") int id, @PathVariable("quantity") int quantity)
    {
        Optional<Product> product = proRepo.findById(id);
        int finalQuantity = 0;

        if(product.isPresent())
        {
            Product p = product.get();
            int quant = p.getQuantity();

            finalQuantity = quant - quantity;

            p.setQuantity(quant-quantity);
            proRepo.save(p); 
        }
        return finalQuantity;
    }

    // @PostMapping("/review/{revie}/{id}")
    // public void uploadReview(@PathVariable("revie") int revie, @PathVariable("id") int id)
    // {
    //     Review r = new Review();
    //     r.setRating(revie);
    //     r.setId(++reviewCounter);

    //     Product product = proRepo.getById(id);
    //     product.setIsReview(true);
    //     proRepo.save(product);

    //     r.setProduct(product);
    //     reviewRepo.save(r);
    // }

    @PostMapping("/review/{rating}/{id}")
    public Review uploadReview(@PathVariable("rating") int rating, @PathVariable("id") int id)
    {
        CompletableFuture<Product> product = CompletableFuture.supplyAsync(() -> {

            Product pro = proRepo.getById(id);
            pro.setIsReview(true);
            proRepo.save(pro);
            return pro;
        });
        
        Review review = product.thenApply(pro -> {

            Review r = new Review();
            r.setRating(rating);
            r.setId(++reviewCounter);
            r.setProduct(pro);
            reviewRepo.save(r);
            return r;
        }).join();

        return review;
    }
}
