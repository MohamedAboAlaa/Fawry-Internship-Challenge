package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Test 6: Non-shippable items only
 * Customer checkout with digital/virtual products that don't require shipping
 */

public class test6 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Non-Shippable Items Only ===");
        
        Product mobileCard = new Product("Mobile Scratch Card", 50.0, 20);
        Product giftCard = new Product("Amazon Gift Card", 100.0, 15);
        Product gameCredit = new Product("Game Credit", 25.0, 50);
        Product musicSubscription = new Product("Music Subscription", 15.0, 100);
        
        Customer customer = new Customer("Abo Alaa", 300.0);
        
        System.out.println("Customer: " + customer);
        System.out.println("\nAvailable Non-Shippable Products:");
        System.out.println("- " + mobileCard + " | Requires Shipping: " + mobileCard.requiresShipping());
        System.out.println("- " + giftCard + " | Requires Shipping: " + giftCard.requiresShipping());
        System.out.println("- " + gameCredit + " | Requires Shipping: " + gameCredit.requiresShipping());
        System.out.println("- " + musicSubscription + " | Requires Shipping: " + musicSubscription.requiresShipping());
        
        Cart cart = new Cart();
        cart.add(mobileCard, 2);
        cart.add(giftCard, 1);
        cart.add(gameCredit, 3);
        cart.add(musicSubscription, 1);
        
        System.out.println("\nCart Contents:");
        System.out.println("- 2x Mobile Scratch Card = " + (2 * mobileCard.getPrice()));
        System.out.println("- 1x Amazon Gift Card = " + (1 * giftCard.getPrice()));
        System.out.println("- 3x Game Credit = " + (3 * gameCredit.getPrice()));
        System.out.println("- 1x Music Subscription = " + (1 * musicSubscription.getPrice()));
        System.out.println("Total items: " + cart.getTotalItemCount());
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        
        System.out.println("\nExpected behavior:");
        System.out.println("- No shipping notice should appear");
        System.out.println("- No shipping fees should be charged");
        System.out.println("- Only checkout receipt should be printed");
        
        System.out.println("\nPerforming checkout with non-shippable items only...\n");
        
        CheckoutService.checkout(customer, cart);
        
        System.out.println("\nFinal customer balance: " + customer.getBalance());
        System.out.println("Cart after checkout: " + (cart.isEmpty() ? "Empty (cleared)" : "Still has items"));
    }
}

