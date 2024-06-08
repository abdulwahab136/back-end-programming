package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImp implements CheckoutService{

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImp(CartRepository cartRepository, CartItemRepository cartItemRepository, CustomerRepository customerRepository){
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse checkout(Purchase purchase) {
        String orderTrackingNumber = null;
        try {

            if (purchase.getCustomer() == null) {
                throw new IllegalArgumentException("Customer Information is required");
            }

            if (purchase.getCartitems() == null || purchase.getCartitems().isEmpty()) {
                throw new IllegalArgumentException("Cart cannot be empty, atleast one item is required");
            }


            Customer customer = purchase.getCustomer();
            customerRepository.save(customer);

            orderTrackingNumber = generateOrderTrackingNumber();

            purchase.getCart().setOrderTrackingNumber(orderTrackingNumber);

            purchase.getCart().setCustomer(customer);

            cartRepository.save(purchase.getCart());
            for (CartItem item : purchase.getCartitems()) {
                item.setCart(purchase.getCart());
                cartItemRepository.save(item);
            }


        } catch (IllegalArgumentException e) {
            System.err.println("There was en Error During Checkout: " + e.getMessage());
        }

        return new PurchaseResponse(orderTrackingNumber);
    }


    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
