package main;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Comprehensive E-Commerce System Demo
 * Covers all test cases with multiple customers and various scenarios
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    COMPREHENSIVE E-COMMERCE DEMO");
        System.out.println("========================================\n");
        
        // Create product catalog
        ShippableProduct cheese = new ShippableProduct("Cheese", 100.0, 10, 0.4, LocalDate.now().plusDays(7));
        ShippableProduct tv = new ShippableProduct("TV", 266.67, 5, 5.0);
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 150.0, 10, 0.7, LocalDate.now().plusDays(30));
        ShippableProduct expiredCheese = new ShippableProduct("Expired Cheese", 80.0, 5, 0.5, LocalDate.now().minusDays(3));
        ShippableProduct heavyMachine = new ShippableProduct("Heavy Machine", 1000.0, 2, 50.0);
        ShippableProduct lightGadget = new ShippableProduct("Light Gadget", 200.0, 15, 0.1);
        
        Product scratchCard = new Product("Mobile Scratch Card", 50.0, 20);
        Product giftCard = new Product("Amazon Gift Card", 100.0, 15);
        Product gameCredit = new Product("Game Credit", 25.0, 50);
        ExpirableProduct expiredBread = new ExpirableProduct("Expired Bread", 25.0, 5, LocalDate.now().minusDays(1));
        
        // Create multiple customers with different scenarios
        Customer[] customers = {
            new Customer("Mohamed Alaa", 1500.0),           // Normal customer
            new Customer("Mahmoud Ahmed", 5000.0),       // High balance customer
            new Customer("Sayed Khaled", 50.0),         // Low balance customer
            new Customer("Khaled Gamal", 442.34), // Exact balance for specific purchase
            new Customer("Abdullah Kamal", 300.0)         // Prefers digital products
        };
        
        // Test Case 1: Successful Mixed Products Checkout
        System.out.println("=== TEST 1: SUCCESSFUL MIXED PRODUCTS CHECKOUT ===");
        testSuccessfulCheckout(customers[0], cheese, tv, scratchCard);
        
        // Test Case 2: Empty Cart
        System.out.println("\n=== TEST 2: EMPTY CART CHECKOUT ===");
        testEmptyCart(customers[1]);
        
        // Test Case 3: Insufficient Balance
        System.out.println("\n=== TEST 3: INSUFFICIENT BALANCE ===");
        testInsufficientBalance(customers[2], heavyMachine);
        
        // Test Case 4: Out of Stock
        System.out.println("\n=== TEST 4: OUT OF STOCK SCENARIOS ===");
        testOutOfStock(customers[1], tv);
        
        // Test Case 5: Expired Products
        System.out.println("\n=== TEST 5: EXPIRED PRODUCTS ===");
        testExpiredProducts(customers[1], expiredCheese, expiredBread, cheese);
        
        // Test Case 6: Non-Shippable Items Only
        System.out.println("\n=== TEST 6: NON-SHIPPABLE ITEMS ONLY ===");
        testNonShippableOnly(customers[4], scratchCard, giftCard, gameCredit);
        
        // Test Case 7: Heavy vs Light Items
        System.out.println("\n=== TEST 7: SHIPPING WEIGHT COMPARISON ===");
        testShippingWeights(customers[1], heavyMachine, lightGadget);
        
        // Test Case 8: Exact Balance Purchase
        System.out.println("\n=== TEST 8: EXACT BALANCE PURCHASE ===");
        testExactBalance(customers[3], cheese, biscuits);
        
        // Test Case 9: Large Quantity Purchase
        System.out.println("\n=== TEST 9: LARGE QUANTITY PURCHASE ===");
        testLargeQuantity(customers[1], gameCredit);
        
        // Test Case 10: Multiple Customers Shopping
        System.out.println("\n=== TEST 10: MULTIPLE CUSTOMERS SHOPPING ===");
        testMultipleCustomers(customers, cheese, tv, scratchCard, giftCard);
        
        System.out.println("\n========================================");
        System.out.println("    DEMO COMPLETED - ALL TESTS DONE");
        System.out.println("========================================");
    }
    
    // Tests Functions
    
    private static void testSuccessfulCheckout(Customer customer, ShippableProduct cheese, 
                                              ShippableProduct tv, Product scratchCard) {
        System.out.println("Customer: " + customer);
        Cart cart = new Cart();
        
        cart.add(cheese, 2);
        cart.add(tv, 3);
        cart.add(scratchCard, 1);
        
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Performing checkout...\n");
        
        CheckoutService.checkout(customer, cart);
        System.out.println("Final customer balance: " + customer.getBalance());
    }
    
    private static void testEmptyCart(Customer customer) {
        System.out.println("Customer: " + customer);
        Cart emptyCart = new Cart();
        
        System.out.println("Cart status: " + (emptyCart.isEmpty() ? "Empty" : "Has items"));
        System.out.println("Attempting checkout with empty cart...\n");
        
        CheckoutService.checkout(customer, emptyCart);
    }
    
    private static void testInsufficientBalance(Customer customer, ShippableProduct expensiveItem) {
        System.out.println("Customer: " + customer);
        System.out.println("Product: " + expensiveItem);
        
        Cart cart = new Cart();
        cart.add(expensiveItem, 1);
        
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Attempting checkout with insufficient balance...\n");
        
        CheckoutService.checkout(customer, cart);
    }
    
    private static void testOutOfStock(Customer customer, ShippableProduct tv) {
        System.out.println("Customer: " + customer);
        System.out.println("Testing stock validation...");
        
        Cart cart = new Cart();
        
        // Test 1: Adding more than available
        try {
            cart.add(tv, 10); // Only 5 available
            System.out.println("ERROR: Should have failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly prevented adding excessive quantity: " + e.getMessage());
        }
        
        // Test 2: Stock reduces between add and checkout
        cart.add(tv, 2);
        System.out.println("Added 2x TV to cart");
        
        tv.setQuantity(1); // Reduce stock
        System.out.println("Stock reduced to: " + tv.getQuantity());
        System.out.println("Attempting checkout...\n");
        
        CheckoutService.checkout(customer, cart);
        
        // Restore stock for other tests
        tv.setQuantity(5);
    }
    
    private static void testExpiredProducts(Customer customer, ShippableProduct expiredCheese, 
                                           ExpirableProduct expiredBread, ShippableProduct freshCheese) {
        System.out.println("Customer: " + customer);
        System.out.println("Product status:");
        System.out.println("- Expired Cheese: Expired = " + expiredCheese.isExpired());
        System.out.println("- Expired Bread: Expired = " + expiredBread.isExpired());
        System.out.println("- Fresh Cheese: Expired = " + freshCheese.isExpired());
        
        // Test with expired items
        Cart cart1 = new Cart();
        cart1.add(expiredCheese, 1);
        System.out.println("\nTesting checkout with expired cheese...");
        CheckoutService.checkout(customer, cart1);
        
        // Test with fresh items
        Cart cart2 = new Cart();
        cart2.add(freshCheese, 1);
        System.out.println("\nTesting checkout with fresh cheese...");
        CheckoutService.checkout(customer, cart2);
    }
    
    private static void testNonShippableOnly(Customer customer, Product scratchCard, 
                                           Product giftCard, Product gameCredit) {
        System.out.println("Customer: " + customer);
        System.out.println("Testing digital/non-shippable items only...");
        
        Cart cart = new Cart();
        cart.add(scratchCard, 2);
        cart.add(giftCard, 1);
        cart.add(gameCredit, 3);
        
        System.out.println("Cart contains only non-shippable items");
        System.out.println("Expected: No shipping fees, no shipment notice");
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Performing checkout...\n");
        
        CheckoutService.checkout(customer, cart);
    }
    
    private static void testShippingWeights(Customer customer, ShippableProduct heavyItem, 
                                           ShippableProduct lightItem) {
        System.out.println("Customer: " + customer);
        System.out.println("Comparing shipping costs for different weights...");
        
        // Heavy item
        Cart cart1 = new Cart();
        cart1.add(heavyItem, 1);
        System.out.println("Heavy item (50kg): " + heavyItem.getName());
        System.out.println("Expected shipping: ~" + (10.0 + 50.0 * 20.0));
        CheckoutService.checkout(customer, cart1);
        
        System.out.println("\n" + "-".repeat(40));
        
        // Light items
        Cart cart2 = new Cart();
        cart2.add(lightItem, 10);
        System.out.println("Light items (10 × 0.1kg = 1kg): " + lightItem.getName());
        System.out.println("Expected shipping: ~" + (10.0 + 1.0 * 20.0));
        CheckoutService.checkout(customer, cart2);
    }
    
    private static void testExactBalance(Customer customer, ShippableProduct cheese, 
                                        ShippableProduct biscuits) {
        System.out.println("Customer: " + customer);
        System.out.println("Testing exact balance scenario...");
        
        Cart cart = new Cart();
        cart.add(cheese, 2);  // 200.0
        cart.add(biscuits, 1); // 150.0
        // Subtotal: 350.0
        // Shipping: 10.0 + (1.1kg * 20.0) = 32.0
        // Total: 382.0
        
        System.out.println("Cart designed to nearly match customer balance");
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Performing checkout...\n");
        
        CheckoutService.checkout(customer, cart);
        System.out.println("Final balance: " + customer.getBalance());
    }
    
    private static void testLargeQuantity(Customer customer, Product gameCredit) {
        System.out.println("Customer: " + customer);
        System.out.println("Testing large quantity purchase...");
        
        Cart cart = new Cart();
        cart.add(gameCredit, 20); // 20 × 25.0 = 500.0
        
        System.out.println("Purchasing 20x Game Credit");
        System.out.println("Cart subtotal: " + cart.getSubtotal());
        System.out.println("Performing checkout...\n");
        
        CheckoutService.checkout(customer, cart);
        System.out.println("Remaining stock: " + gameCredit.getQuantity());
    }
    
    private static void testMultipleCustomers(Customer[] customers, ShippableProduct cheese, 
                                            ShippableProduct tv, Product scratchCard, Product giftCard) {
        System.out.println("Demonstrating multiple customers shopping simultaneously...");
        
        for (int i = 0; i < Math.min(3, customers.length); i++) {
            Customer customer = customers[i];
            System.out.println("\n--- Customer " + (i + 1) + ": " + customer.getName() + " ---");
            
            Cart cart = new Cart();
            
            // Different shopping patterns for each customer
            switch (i) {
                case 0:
                    cart.add(cheese, 1);
                    cart.add(scratchCard, 2);
                    break;
                case 1:
                    cart.add(tv, 1);
                    cart.add(giftCard, 1);
                    break;
                case 2:
                    cart.add(scratchCard, 1);
                    break;
            }
            
            if (!cart.isEmpty()) {
                System.out.println("Cart subtotal: " + cart.getSubtotal());
                CheckoutService.checkout(customer, cart);
            }
        }
        
        System.out.println("\nRemaining stock after all customers:");
        System.out.println("- Cheese: " + cheese.getQuantity());
        System.out.println("- TV: " + tv.getQuantity());
        System.out.println("- Scratch Cards: " + scratchCard.getQuantity());
        System.out.println("- Gift Cards: " + giftCard.getQuantity());
    }
}