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

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartitems = purchase.getCartitems();

        if (purchase.getCartitems() == null) {
            throw new IllegalArgumentException("Cart items cannot be null");
        }

        for (CartItem item : purchase.getCartitems()) {
            item.setCart(purchase.getCart());
            cartItemRepository.save(item);
        }

        Customer customer = purchase.getCustomer();
        customerRepository.save(customer);

        cartRepository.save(cart);



        return new PurchaseResponse(orderTrackingNumber);
    }


    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
