package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImp implements CheckoutService {

    private final CartRepository cartRepository;


    @Autowired
    public CheckoutServiceImp(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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

    cartItems.forEach(cartItem -> {
        cartItem.setCart(cart);
        cart.setCartItems(cartItems);


        Vacation vacation = cartItem.getVacation();
        Set<Excursion> excursions = cartItem.getExcursions();
        for (Excursion excursion : excursions){
            excursion.setVacation(vacation);
        }

    });

     cart.setStatus(StatusType.ordered);
     cartRepository.save(cart);



    return new  PurchaseResponse(orderTrackingNumber);
}

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}

