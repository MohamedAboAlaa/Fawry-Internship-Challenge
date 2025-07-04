package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 8: Exact Challenge Example
 * Reproduces the exact example from the challenge document
 * Expected output should match the provided console output
 */

public class test8 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Exact Challenge Example ===\n");
        System.out.println("Reproducing the exact example from the challenge document\n");
    
        
        ShippableProduct cheese = new ShippableProduct("Cheese", 100.0, 10, 0.4, LocalDate.now().plusDays(7));
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 150.0, 10, 0.7, LocalDate.now().plusDays(30));
        ShippableProduct tv = new ShippableProduct("TV", 500.0, 5, 5.0);
        Product scratchCard = new Product("Mobile Scratch Card", 50.0, 20);
        
        Customer customer = new Customer("Abo Alaa", 1000.0);
        
        System.out.println("Products available:");
        System.out.println("- " + cheese);
        System.out.println("- " + biscuits);
        System.out.println("- " + tv);
        System.out.println("- " + scratchCard);
        
        System.out.println("\nCustomer: " + customer);
        
        Cart cart = new Cart();
        
        System.out.println("\nExecuting challenge example:");
        System.out.println("cart.add(cheese, 2);");
        cart.add(cheese, 2);
        
        System.out.println("cart.add(biscuits, 1);");
        cart.add(biscuits, 1);
        
        System.out.println("cart.add(scratchCard, 1);");
        cart.add(scratchCard, 1);
        
        System.out.println("checkout(customer, cart);");
        
        System.out.println("\nCart summary before checkout:");
        System.out.println("- 2x Cheese @ 100.0 each = 200.0");
        System.out.println("- 1x Biscuits @ 150.0 each = 150.0");
        System.out.println("- 1x Mobile Scratch Card @ 50.0 each = 50.0");
        System.out.println("Subtotal: " + cart.getSubtotal());
        
        System.out.println("Shippable items:");
        System.out.println("- 2x Cheese (0.4kg each) = 0.8kg total");
        System.out.println("- 1x Biscuits (0.7kg) = 0.7kg total");
        System.out.println("Total shipping weight: 1.5kg");
        System.out.println("Expected shipping: 5.0 + (1.5 * 25.0) = 42.5");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EXECUTING CHECKOUT - EXPECTED OUTPUT:");
        System.out.println("=".repeat(50));
        
        CheckoutService.checkout(customer, cart);
        
        System.out.println("=".repeat(50));
        System.out.println("CHECKOUT COMPLETED");
        System.out.println("=".repeat(50));
        
        System.out.println("\nCustomer final balance: " + customer.getBalance());
        System.out.println("Cart after checkout: " + (cart.isEmpty() ? "Empty" : "Still has items"));
    }
}

