package com.example.pricecalservice.dao;

import com.example.pricecalservice.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceDaoImpl implements PriceDao{
    @Override
    public List<Product> getProducts() throws Exception {
        List<Product> productList= new ArrayList<Product>();
        Product product1 = new Product("Penguin-ears",175.0,20);
        Product product2 = new Product("Horseshoe",825.0,5);
        productList.add(product1);
        productList.add(product2);
        return productList;
    }
}
