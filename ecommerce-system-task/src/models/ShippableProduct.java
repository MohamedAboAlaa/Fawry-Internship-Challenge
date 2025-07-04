package models;

import interfaces.Shippable;
import java.time.LocalDate;

/**
 * Product that requires shipping and has weight
 * Can also be expirable if needed
 */
public class ShippableProduct extends Product implements Shippable {
    private double weight; // in kg
    private LocalDate expirationDate; // optional
    
    // Constructor for non-expirable shippable products
    public ShippableProduct(String name, double price, int quantity, double weight){
        super(name,price,quantity);
        this.weight = weight;
        this.expirationDate = null;
    }
    
    // Constructor for expirable shippable products
    public ShippableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate){
        super(name,price,quantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }
    
    @Override
    public double getWeight(){
        return weight;
    }
    
    @Override
    public boolean requiresShipping(){
        return true;
    }
    
    @Override
    public boolean isExpired(){
        return expirationDate != null && LocalDate.now().isAfter(expirationDate);
    }
    
    public LocalDate getExpirationDate(){
        return expirationDate;
    }
    
    @Override
    public String toString(){
        String expiration = expirationDate != null ? ", Expires: " + expirationDate.toString() : "";
        return String.format("%s (Price: %.2f, Quantity: %d, Weight: %.2fkg%s)", name, price, quantity, weight, expiration);
    }
}