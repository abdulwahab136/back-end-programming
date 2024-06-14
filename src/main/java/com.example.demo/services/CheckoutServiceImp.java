package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImp implements CheckoutService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImp(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse checkout(Purchase purchase) {
    Cart cart = purchase.getCart();


    String orderTrackingNumber = generateOrderTrackingNumber();
    cart.setOrderTrackingNumber(orderTrackingNumber);


    Set<CartItem> cartItems = purchase.getCartItems();
    if (cartItems == null || cartItems.isEmpty()){

        return new PurchaseResponse("Cart is Empty");
    }
    cart.setCartItem(cartItems);

        return new    PurchaseResponse(orderTrackingNumber);
}

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}

