package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 4: Out of stock
 * Customer tries to add more items than available in stock
 */

public class test4 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Out of Stock ===\n");
        
        ShippableProduct tv = new ShippableProduct("TV", 300.0, 2, 3.0);
        
        Customer customer = new Customer("Abo Alaa", 2000.0);
        
        System.out.println("Customer: " + customer);
        System.out.println("Product: " + tv);
        System.out.println("Available stock: " + tv.getQuantity());
        
        Cart cart = new Cart();
        System.out.println("\nAdding more items to cart than available stock");
        System.out.println("Attempting to add 5x TV (only 2 available)...");
        
        try{
            cart.add(tv, 5); // fail
            System.out.println("ERROR: Should have failed but didn't!");
        }catch(IllegalArgumentException e){
            System.out.println("Correctly caught error: " + e.getMessage());
        }
        
        System.out.println("\nStock becomes unavailable between adding to cart and checkout");
        cart.clear();
        cart.add(tv, 2);
        
        System.out.println("Added 2x TV to cart successfully");
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        
        tv.setQuantity(1); // reduce stock to 1
        System.out.println("Stock reduced to: " + tv.getQuantity());
        
        System.out.println("\nAttempting checkout when stock is insufficient...\n");
        
        // fail with out of stock error
        CheckoutService.checkout(customer, cart);
        
        System.out.println("\nCustomer balance unchanged: " + customer.getBalance());
        System.out.println("Product stock unchanged: " + tv.getQuantity());
    }
}

