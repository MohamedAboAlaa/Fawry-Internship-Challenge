package tests;

import models.*;
import cart.Cart;
import services.CheckoutService;
import java.time.LocalDate;
/**
 * Test 7: Mixed complex scenarios
 * Testing edge cases and complex combinations
 */

public class test7 {
    public static void main(String[] args) {
        System.out.println(" === E-Commerce System ===");
        System.out.println("===========================\n");
        
        System.out.println("=== Test Mixed Complex Scenarios ===");
        
        // Create various products
        ShippableProduct heavyItem = new ShippableProduct("Heavy Machine", 1000.0, 1, 50.0);
        ShippableProduct lightItem = new ShippableProduct("Light Gadget", 200.0, 10, 0.1);
        Product digitalItem = new Product("Software License", 150.0, 5);
        ShippableProduct bulkItem = new ShippableProduct("Bulk Rice", 30.0, 100, 2.0);
        
        Customer customer = new Customer("Abo Alaa", 2000.0);
        
        System.out.println("Customer: " + customer);
        System.out.println("\nProduct Catalog:");
        System.out.println("- " + heavyItem + " | Shipping: " + heavyItem.requiresShipping());
        System.out.println("- " + lightItem + " | Shipping: " + lightItem.requiresShipping());
        System.out.println("- " + digitalItem + " | Shipping: " + digitalItem.requiresShipping());
        System.out.println("- " + bulkItem + " | Shipping: " + bulkItem.requiresShipping());
        
        // large order with mixed items
        System.out.println("\n--- Test 7a: Large Mixed Order ---");
        Cart cart1 = new Cart();
        cart1.add(heavyItem, 1);      // 1 * 50kg = 50kg
        cart1.add(lightItem, 5);      // 5 * 0.1kg = 0.5kg
        cart1.add(digitalItem, 2);    // no shipping
        cart1.add(bulkItem, 3);       // 3 * 2kg = 6kg
        
        System.out.println("Cart contents: Heavy Machine + 5x Light Gadgets + 2x Software + 3x Bulk Rice");
        System.out.println("Total shippable weight: 50 + 0.5 + 6 = 56.5kg");
        System.out.println("Expected shipping: 5 + (56.5 * 25) = 1417.5");
        System.out.println("Cart subtotal: " + cart1.getSubtotal());
        
        System.out.println("\nProcessing large mixed order...\n");
        CheckoutService.checkout(customer, cart1);
        
        // boundary testing with exact balance
        System.out.println("\n--- Test 7b: Exact Balance Test ---");
        double remainingBalance = customer.getBalance();
        System.out.println("Customer remaining balance: " + remainingBalance);
        
        // create a product that costs exactly the remaining balance
        Product exactCostItem = new Product("Exact Cost Item", remainingBalance, 1);
        
        Cart cart2 = new Cart();
        cart2.add(exactCostItem, 1);
        
        System.out.println("Adding item that costs exactly remaining balance: " + exactCostItem.getPrice());
        System.out.println("This should succeed with 0.00 balance afterwards");
        
        System.out.println("\nProcessing exact balance order...\n");
        CheckoutService.checkout(customer, cart2);
        
        // buy anything with zero balance
        System.out.println("\n--- Test 7c: Zero Balance Test ---");
        System.out.println("Customer balance after previous purchase: " + customer.getBalance());
        
        Cart cart3 = new Cart();
        cart3.add(new Product("Cheap Item", 1.0, 10), 1);
        
        System.out.println("Attempting to buy 1.0 item with 0.0 balance...\n");
        CheckoutService.checkout(customer, cart3);
        
        // maximum quantity edge case
        System.out.println("\n--- Test 7d: Maximum Quantity Edge Case ---");
        customer.setBalance(5000.0); // reset balance
        
        Product maxQuantityItem = new Product("Max Quantity Item", 1.0, 1000);
        Cart cart4 = new Cart();
        cart4.add(maxQuantityItem, 1000); // add maximum available
        
        System.out.println("Adding maximum available quantity (1000) of item...");
        System.out.println("Cart subtotal: " + cart4.getSubtotal());
        
        System.out.println("\nProcessing maximum quantity order...\n");
        CheckoutService.checkout(customer, cart4);
        
        System.out.println("\nFinal customer balance: " + customer.getBalance());
        System.out.println("Max quantity item remaining stock: " + maxQuantityItem.getQuantity());
    }
}

