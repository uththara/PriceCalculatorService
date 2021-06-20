package com.example.pricecalservice;

import com.example.pricecalservice.model.Price;
import com.example.pricecalservice.model.Product;
import com.example.pricecalservice.model.ProductPrice;
import com.example.pricecalservice.model.ProductRequest;
import com.example.pricecalservice.service.PriceService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PricecalserviceApplicationTests {

    Logger log = Logger.getLogger(PricecalserviceApplicationTests.class);

    @Autowired
    private PriceService priceService;

    @Test
    void contextLoads() {
        log.info("Start Test");
        getProductListTest();
        getProductPriceListTest();
        getProductPriceTest();
        getTotalDiscountTest();
        log.info("End Test");
    }

    public void getProductListTest(){
        List<Product> productList = priceService.getProductList();
         for(Product product : productList){
             log.info(product.getProductName()+ "- "+product.getCartonPrice());
         }
        log.info("--------------------");
    }
    public void getProductPriceListTest(){
        List<ProductPrice> priceList = priceService.getPriceList();
        for(ProductPrice productPrice : priceList){
            log.info(productPrice.getProduct().getProductName()+ " - ");
            for(Price price : productPrice.getPriceList()){
                log.info(price.getQuantity()+" "+price.getAmount());
            }
            log.info("\n");
        }
        log.info("--------------------");
    }

    public void getProductPriceTest(){
        ProductRequest request = new ProductRequest();
        Product product = new Product("Penguine-Ear",175.0, 20);
        request.setProduct(product);
        request.setNoOfUnits(15);
        Double price = priceService.getProductPrice(request);
        log.info("Price:: "+price);
        log.info("--------------------");
    }

    public void getTotalDiscountTest(){
        List<ProductRequest> requestList = new ArrayList<>();
        ProductRequest req1 = new ProductRequest();
        Product product1 = new Product("Penguine-Ear",175.0, 20);
        req1.setProduct(product1);
        req1.setNoOfUnits(40);
        ProductRequest req2 = new ProductRequest();
        Product product2 = new Product("Horseshoe",825.0, 5);
        req2.setProduct(product2);
        req2.setNoOfUnits(15);
        requestList.add(req1);
        requestList.add(req2);

        Double discount = priceService.getTotalDiscount(requestList);
//        assertThat(found.getName())
//                .isEqualTo(alex.getName());
        log.info("discount :: "+discount);
        log.info("--------------------");
    }

}
