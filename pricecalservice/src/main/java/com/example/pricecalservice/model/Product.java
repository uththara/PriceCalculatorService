package com.example.pricecalservice.model;

public class Product {
    private String productCode;
    private String productName;
    private Double cartonPrice;
    private int unitsPerCarton;

    public Product() {
    }

    public Product(String productName, Double cartonPrice, int unitsPerCarton) {
        this.productName = productName;
        this.cartonPrice = cartonPrice;
        this.unitsPerCarton = unitsPerCarton;
    }

    public Product(String productCode, String productName, Double cartonPrice, int unitsPerCarton) {
        this.productCode = productCode;
        this.productName = productName;
        this.cartonPrice = cartonPrice;
        this.unitsPerCarton = unitsPerCarton;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(Double cartonPrice) {
        this.cartonPrice = cartonPrice;
    }

    public int getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(int unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }
}
