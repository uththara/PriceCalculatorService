package com.example.pricecalservice.service;

import com.example.pricecalservice.model.ProductRequest;
import com.example.pricecalservice.model.Product;
import com.example.pricecalservice.model.ProductPrice;

import java.util.List;

public interface PriceService {
    List<Product> getProductList();

    List<ProductPrice> getPriceList();

    Double getProductPrice(ProductRequest request);

    Double getTotalDiscount(List<ProductRequest> requestdProductList);
}
