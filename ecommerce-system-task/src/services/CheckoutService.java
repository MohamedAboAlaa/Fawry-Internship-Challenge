package services;

import models.Customer;
import models.Product;
import models.ShippableProduct;
import cart.Cart;
import interfaces.Shippable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CheckoutService handles the checkout process including validation,
 * payment processing, and shipping coordination
 */
public class CheckoutService {
    
    public static void checkout(Customer customer, Cart cart) {
        try{
            // validate checkout preconditions
            validateCheckout(customer, cart);
            
            // calculate totals
            double subtotal = cart.getSubtotal();
            
            // collect shippable items
            List<Shippable> shippableItems = new ArrayList<>();
            Map<String, Integer> itemQuantities = new HashMap<>();
            
            for(Map.Entry<Product, Integer> entry : cart.getItems().entrySet()){
                Product product = entry.getKey();
                int quantity = entry.getValue();
                if(product.requiresShipping() && product instanceof ShippableProduct){
                    shippableItems.add((ShippableProduct) product);
                    itemQuantities.put(product.getName(), quantity);
                }
            }
            
            // calculate shipping fee
            double shippingFee = ShippingService.calculateShippingFee(shippableItems);
            double totalAmount = subtotal + shippingFee;
            
            // final balance check
            if(!customer.canAfford(totalAmount)){
                throw new IllegalStateException("Customer's balance is insufficient. Required: " + totalAmount + ", Available: " + customer.getBalance());
            }
            
            // process payment
            customer.deductBalance(totalAmount);
            
            // update product quantities
            for(Map.Entry<Product, Integer> entry : cart.getItems().entrySet()){
                entry.getKey().reduceQuantity(entry.getValue());
            }
            
            // process shipment if needed
            if(!shippableItems.isEmpty()){
                ShippingService.processShipment(shippableItems, itemQuantities);
            }
            
            // print checkout receipt
            printCheckoutReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());
            
            // clear cart after successful checkout
            cart.clear();
            
        }catch(Exception e){
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
    
    private static void validateCheckout(Customer customer, Cart cart){
        // check if cart is empty
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        // check stock availability and expiration
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int requestedQuantity = entry.getValue();
            
            if(!product.isAvailable(requestedQuantity)){
                throw new IllegalStateException(product.getName() + " is out of stock. Available: " + product.getQuantity() + ", Requested: " + requestedQuantity);
            }
            if(product.isExpired()){
                throw new IllegalStateException(product.getName() + " is expired");
            }
        }
    }
    
    private static void printCheckoutReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount, double remainingBalance) {
        System.out.println("----------------------");
        System.out.println("** Checkout receipt **");
        
        // print items
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), 
                            product.getPrice() * quantity);
        }
        
        // print totals
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        if(shippingFee > 0){
            System.out.printf("Shipping %.0f%n", shippingFee);
        }
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: %.2f%n", remainingBalance);
        System.out.println("----------------------");
    }
}