package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 3: Insufficient balance
 * Customer tries to buy expensive items with insufficient funds
 */

public class test3 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Insufficient Balance ===\n");
        
        ShippableProduct expensiveTV = new ShippableProduct("Premium TV", 800.0, 5, 10.0);
        
        Customer customer = new Customer("Poor Customer", 50.0);
        
        Cart cart = new Cart();
        cart.add(expensiveTV, 1);
        
        System.out.println("Customer: " + customer);
        System.out.println("Cart contents:");
        System.out.println("- 1x Premium TV (Price: 800.0, Weight: 10.0kg)");
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Estimated shipping: ~255.0 (base 5.0 + 10kg * 25.0)");
        System.out.println("Total needed: ~1055.0");
        System.out.println("Customer balance: " + customer.getBalance());
        
        System.out.println("\nAttempting checkout with insufficient balance...\n");
        
        CheckoutService.checkout(customer, cart);
        
        System.out.println("\nCustomer balance unchanged: " + customer.getBalance());
        System.out.println("Cart should still have items: " + !cart.isEmpty());
    }
}

