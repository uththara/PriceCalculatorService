package com.example.pricecalservice.model;

import java.util.List;

public class ProductPrice {
    private Product product;
    private List<Price> priceList;

    public ProductPrice(Product product, List<Price> priceList) {
        this.product = product;
        this.priceList = priceList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}
