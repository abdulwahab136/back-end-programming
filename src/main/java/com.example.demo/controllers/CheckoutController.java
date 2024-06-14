package com.example.demo.controllers;

import com.example.demo.services.CheckoutService;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "http://localhost:4200")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {

        this.checkoutService = checkoutService;
    }


    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {



        PurchaseResponse purchaseResponse = checkoutService.checkout(purchase);
        return checkoutService.checkout(purchase);
    }

}