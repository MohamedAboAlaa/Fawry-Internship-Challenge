package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 2: Empty cart checkout
 * Should show error message about empty cart
 */

public class test2 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Empty Cart Checkout ===\n");
        
        Customer customer = new Customer("Abo Alaa", 500.0);
        
        Cart emptyCart = new Cart();
        
        System.out.println("Customer: " + customer);
        System.out.println("Cart status: " + (emptyCart.isEmpty() ? "Empty" : "Has items"));
        System.out.println("Cart contents: " + emptyCart);
        
        System.out.println("\nAttempting checkout with empty cart...\n");
        
        CheckoutService.checkout(customer, emptyCart);
        
        System.out.println("\nCustomer balance unchanged: " + customer.getBalance());
    }
}

