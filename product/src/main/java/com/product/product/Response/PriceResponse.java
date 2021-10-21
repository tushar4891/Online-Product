package com.product.product.Response;

import com.product.product.Enum.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceResponse {
    
    private int price;
    private Category category;
}
