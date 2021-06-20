package com.example.pricecalservice.model;

public class ProductRequest {
    private Product product;
    private int noOfUnits;

    public ProductRequest() {
    }

    public ProductRequest(Product product, int noOfUnits) {
        this.product = product;
        this.noOfUnits = noOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }
}
