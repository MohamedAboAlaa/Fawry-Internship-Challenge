package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 5: Expired product
 * Customer tries to checkout with expired products
 */

public class test5 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Expired Product ===");
        
        ShippableProduct expiredCheese = new ShippableProduct("Expired Cheese", 80.0, 10, 0.5, LocalDate.now().minusDays(3));
        ExpirableProduct expiredBread = new ExpirableProduct("Expired Bread", 25.0, 5, LocalDate.now().minusDays(1));
        ShippableProduct freshCheese = new ShippableProduct("Fresh Cheese", 100.0, 10, 0.4, LocalDate.now().plusDays(7));
        
        Customer customer = new Customer("Abo Alaa", 500.0);
        
        System.out.println("Customer: " + customer);
        System.out.println("\nProduct Status:");
        System.out.println("- Expired Cheese: " + expiredCheese + " | Expired: " + expiredCheese.isExpired());
        System.out.println("- Expired Bread: " + expiredBread + " | Expired: " + expiredBread.isExpired());
        System.out.println("- Fresh Cheese: " + freshCheese + " | Expired: " + freshCheese.isExpired());
        
        // checkout with expired shippable product
        System.out.println("\nCheckout with expired shippable product");
        Cart cart1 = new Cart();
        cart1.add(expiredCheese, 2);
        cart1.add(freshCheese, 1);
        
        System.out.println("Cart contents: 2x Expired Cheese + 1x Fresh Cheese");
        System.out.println("Cart subtotal: " + cart1.getSubtotal());
        
        System.out.println("\nAttempting checkout with expired cheese...\n");
        CheckoutService.checkout(customer, cart1);
        
        // checkout with expired non-shippable product
        System.out.println("\nCheckout with expired non-shippable product");
        Cart cart2 = new Cart();
        cart2.add(expiredBread, 1);
        
        System.out.println("Cart contents: 1x Expired Bread");
        System.out.println("Cart subtotal: " + cart2.getSubtotal());
        
        System.out.println("\nAttempting checkout with expired bread...\n");
        CheckoutService.checkout(customer, cart2);
        
        // checkout with fresh products only
        System.out.println("\nCheckout with fresh products");
        Cart cart3 = new Cart();
        cart3.add(freshCheese, 2);
        
        System.out.println("Cart contents: 2x Fresh Cheese");
        System.out.println("Cart subtotal: " + cart3.getSubtotal());
        
        System.out.println("\nAttempting checkout with fresh cheese...\n");
        CheckoutService.checkout(customer, cart3);
        
        System.out.println("\nFinal customer balance: " + customer.getBalance());
    }
}

