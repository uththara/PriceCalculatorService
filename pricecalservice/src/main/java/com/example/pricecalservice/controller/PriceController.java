package com.example.pricecalservice.controller;

import com.example.pricecalservice.model.ProductRequest;
import com.example.pricecalservice.model.Product;
import com.example.pricecalservice.model.ProductPrice;
import com.example.pricecalservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/helloWorld")
    public String sayHello() {
        return String.format("Hello World");
    }
    @PostMapping("/helloPost")
    public String sayHello(@RequestBody String name) {
        return String.format("Hello "+name);
    }


    @GetMapping("productList")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> productList = priceService.getProductList();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("getPriceList")
    public ResponseEntity<List<ProductPrice>> getPriceList() {
        List<ProductPrice> priceList = priceService.getPriceList();
        return new ResponseEntity<>(priceList, HttpStatus.OK);
    }

    @PostMapping("getProductPrice")
    public ResponseEntity<Double> getProductPrice(@RequestBody ProductRequest request) {
        Double price = priceService.getProductPrice(request);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping("getTotalDiscount")
    public ResponseEntity<Double> getTotalDiscount(@RequestBody List<ProductRequest> requestedProductList) {
        Double discount = priceService.getTotalDiscount(requestedProductList);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

//
//    @PostMapping("getCreatedWorkflowObjList")
//    public ResponseEntity<ResultListResponse> getCreatedWorkflowObjList(@RequestBody WorkflowRequest request) {
//        ResultListResponse workFlowObjList = workflowService.getCreatedWorkflowObjList(request);
//        return new ResponseEntity<>(workFlowObjList, HttpStatus.OK);
//    }

}
