package com.example.pricecalservice.dao;

import com.example.pricecalservice.model.Product;

import java.util.List;

public interface PriceDao {
    List<Product> getProducts() throws Exception;
}
