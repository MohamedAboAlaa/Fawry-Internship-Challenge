package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 1: Successful checkout with mixed products
 * This matches the exact example from the challenge document
 */

public class test1 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Checkout ===\n");
        
        ShippableProduct cheese = new ShippableProduct("Cheese", 100.0, 10, 0.4, LocalDate.now().plusDays(7));
        ShippableProduct tv = new ShippableProduct("TV", 266.67, 5, 5.0);
        Product scratchCard = new Product("Mobile Scratch Card", 50.0, 10);
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 150.0, 10, 0.7, LocalDate.now().plusDays(30));
        
        Customer customer = new Customer("Abo Alaa", 1500.0);
        
        Cart cart = new Cart();
        
        System.out.println("Adding items to cart...");
        cart.add(cheese, 2);
        System.out.println("Added 2x Cheese");
        
        cart.add(tv, 3);
        System.out.println("Added 3x TV");
        
        cart.add(scratchCard, 1);
        System.out.println("Added 1x Mobile Scratch Card");
        
        System.out.println("\nCustomer before checkout: " + customer);
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        
        // Perform checkout
        System.out.println("\nPerforming checkout...\n");
        CheckoutService.checkout(customer, cart);
        
        System.out.println("\nCustomer after checkout: " + customer);
    }
}

