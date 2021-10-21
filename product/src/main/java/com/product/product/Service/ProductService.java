package com.product.product.Service;

import com.product.product.Entity.DemoProduct;
import com.product.product.Entity.Description;
import com.product.product.Entity.Product;
import com.product.product.Entity.ProductCategory;
import com.product.product.Enum.Category;
import com.product.product.Repository.ProductRepository;
import com.product.product.Repository.DescriptionRepository;
import com.product.product.Repository.ProductCategoryRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    static int count = 0;

    @Autowired
    private ProductCategoryRepository ptRepository;

    @Autowired
    private ProductRepository pRepository;
 
    @Autowired
    private DescriptionRepository dRepository;

    public List<ProductCategory> getAllTheProduct()
    {
        return ptRepository.findAll();
    }
    
    public String addProductCategory(Category c)
    {
        
        ProductCategory pt = ProductCategory.builder()
                            .category(c)
                            .build();

        ptRepository.save(pt);
        return "Successfully created !";

    }

    public String addProduct(DemoProduct p)
    {
        //System.out.println("Started**********");
        Product pr = new Product();
        pr.setId(p.getId());
        pr.setName(p.getName());
        pr.setQuantity(p.getQuantity());
        pr.setPrice(p.getPrice());
        pr.setIsReview(p.getIsReview());
        pr.setCategory(p.getCategory());

        Description d = new Description();
        d.setInfo(p.getDescription());
        d.setDescid(++count);
        //dRepository.save(d);

        //System.out.println("DONE********");
        pr.setDescription(d);
        pRepository.save(pr);
        
        return "Successfully created !";
    }
}
