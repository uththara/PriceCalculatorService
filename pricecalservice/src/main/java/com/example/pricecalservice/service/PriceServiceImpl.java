package com.example.pricecalservice.service;

import com.example.pricecalservice.dao.PriceDao;
import com.example.pricecalservice.model.Price;
import com.example.pricecalservice.model.ProductRequest;
import com.example.pricecalservice.model.Product;
import com.example.pricecalservice.util.AppConstants;
import com.example.pricecalservice.model.ProductPrice;
import com.example.pricecalservice.util.Utility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
//import java.util.stream.IntStream;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private Utility utility;

    Logger log = Logger.getLogger(PriceServiceImpl.class);
    private int unitPerCarton;
    private Double cartonPrice;
    private int totalCartons;

    @Override
    public List<Product> getProductList() {
        try {
            return priceDao.getProducts();
        }catch (Exception e){
            log.error(e,e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductPrice> getPriceList() {
        List<ProductPrice>  productPriceList= new ArrayList<ProductPrice>();
        try {
            List<Product> productList = priceDao.getProducts();
            log.info(productList.size());
            for(Product product : productList){
                unitPerCarton = product.getUnitsPerCarton();
                cartonPrice = product.getCartonPrice();
                List<Price> priceList = new ArrayList<>() ;
                IntStream.range(1, 51)
                        .forEach(i -> priceList.add(getPrice(i)));
                ProductPrice productPrice = new ProductPrice(product,priceList);
                productPriceList.add(productPrice);
            }
        }catch (Exception e){
            log.error(e,e);
        }
        finally {
            return productPriceList;
        }
    }

    private Price getPrice(int i) {
        Price price = new Price();
        price.setAmount(calculatePrice(i));
        price.setQuantity(i);
        return price;
    }

    @Override
    public Double getProductPrice(ProductRequest request) {
        try {
            unitPerCarton = request.getProduct().getUnitsPerCarton();
            cartonPrice = request.getProduct().getCartonPrice();
            return calculatePrice(request.getNoOfUnits());
        }catch (Exception e){
            log.error(e,e);
            return 0.00;
        }
    }

    @Override
    public Double getTotalDiscount(List<ProductRequest> requestedProductList) {
        try {
            int totalNoOfCartorns = 0;
            double totalAmount = 0.00;
            double discount = 0.00;
            for(ProductRequest productRequest : requestedProductList){
                int noOfCarton =  productRequest.getNoOfUnits() / productRequest.getProduct().getUnitsPerCarton();
                discount = discount + calculateDiscount(noOfCarton,productRequest.getProduct().getCartonPrice());
                log.info(discount);
                totalNoOfCartorns = totalNoOfCartorns + noOfCarton;
            }
            if(totalNoOfCartorns >= 3){
                return Utility.round(discount,2);
            }
        }catch (Exception e){
            log.error(e,e);
        }
        return 0.00;
    }

    public Double calculatePrice(int noOfUnits){

        try {
            int noOfCartons = noOfUnits / unitPerCarton;
            int singleUnits = noOfUnits % unitPerCarton;

            double totalAmount = ( noOfCartons * cartonPrice ) + (( cartonPrice * AppConstants.LABOUR_CHARGE) / unitPerCarton) * singleUnits;
            totalCartons = + noOfCartons;
            return utility.round(totalAmount,2);
        }catch(Exception e){
            log.error(e,e);
            return 0.0;
        }
    }

    public Double calculateDiscount(int noOfCartons, double cartonPrice){
        try {
            double discount = noOfCartons * cartonPrice * AppConstants.DISCOUNT;
            return Utility.round(discount,2);
        }catch (Exception e){
            log.error(e,e);
            return 0.0;
        }
    }
}
